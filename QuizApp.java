import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.security.MessageDigest;

public class QuizApp {

    // ===== DATABASE CONFIG — change if needed =====
    static final String DB_URL  = "jdbc:mysql://localhost:3306/quizapp";
    static final String DB_USER = "root";
    static final String DB_PASS = "majid@786";   // your MySQL password

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    // ===== ENCRYPTION =====
    static String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                String h = Integer.toHexString(0xff & b);
                if (h.length() == 1) hex.append('0');
                hex.append(h);
            }
            return hex.toString();
        } catch (Exception e) { return null; }
    }

    // ===== ABSTRACT CLASS =====
    static abstract class Person {
        protected String name;
        Person(String name) { this.name = name; }
        abstract void role();
    }

    // ===== INTERFACE =====
    interface Attemptable {
        int attempt(String topic);
    }

    // ===== QUESTION POJO =====
    static class Question {
        int    id;
        String q, a, b, c, d, correct;

        Question(int id, String q, String a, String b, String c, String d, String correct) {
            this.id = id;
            this.q = q; this.a = a; this.b = b;
            this.c = c; this.d = d; this.correct = correct;
        }
    }

    // ===== ADMIN =====
    // Table: admin (id, username, password)
    static class Admin extends Person {
        Admin(String name) { super(name); }
        void role() { System.out.println("I am Admin"); }

        boolean login(String username, String password) {
            String hashed = encrypt(password);
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM admin WHERE username = ? AND (password = ? OR password = ?)")) {
                ps.setString(1, username);
                ps.setString(2, password);  // plain-text match
                ps.setString(3, hashed);    // SHA-256 match
                return ps.executeQuery().next();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "DB error: " + e.getMessage());
                return false;
            }
        }

        // question table: id, topic, question, optA, optB, optC, optD, correct
        void addQuestion(String topic, String q,
                         String a, String b, String c, String d,
                         String correct) throws SQLException {
            // Insert topic if not exists
            try (Connection con = getConnection()) {
                PreparedStatement chk = con.prepareStatement(
                    "SELECT topic_id FROM topic WHERE topic_name = ?");
                chk.setString(1, topic);
                if (!chk.executeQuery().next()) {
                    PreparedStatement ins = con.prepareStatement(
                        "INSERT INTO topic (topic_name) VALUES (?)");
                    ins.setString(1, topic);
                    ins.executeUpdate();
                }

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO question (topic, question, optA, optB, optC, optD, correct)" +
                    " VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, topic);
                ps.setString(2, q);
                ps.setString(3, a);
                ps.setString(4, b);
                ps.setString(5, c);
                ps.setString(6, d);
                ps.setString(7, correct.toUpperCase());
                ps.executeUpdate();
            }
        }
    }

    // ===== USER =====
    // Table: user (id, username, password)
    static class User extends Person implements Attemptable {
        User(String name) { super(name); }
        void role() { System.out.println("I am User"); }

        public int attempt(String topic) {
            try {
                ArrayList<Question> list = new ArrayList<>();
                try (Connection con = getConnection();
                     PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM question WHERE topic = ?")) {
                    ps.setString(1, topic);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        list.add(new Question(
                            rs.getInt("id"),
                            rs.getString("question"),
                            rs.getString("optA"),
                            rs.getString("optB"),
                            rs.getString("optC"),
                            rs.getString("optD"),
                            rs.getString("correct")
                        ));
                    }
                }

                if (list.isEmpty()) return -1;

                int score = 0;
                for (Question q : list) {
                    String ans = ask(q);
                    if (ans!=null && ans.equalsIgnoreCase(q.correct)) score++;
                }
                return score;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "DB error: " + e.getMessage());
                return -1;
            }
        }

        String ask(Question q) {
            final String[] ans = {null};
            JDialog d = new JDialog();
            d.setSize(420, 300);
            d.setLayout(new BorderLayout());

            JTextArea area = new JTextArea(
                q.q + "\n\nA) " + q.a + "\nB) " + q.b + "\nC) " + q.c + "\nD) " + q.d);
            area.setEditable(false);
            area.setWrapStyleWord(true);
            area.setLineWrap(true);
            area.setMargin(new Insets(10, 10, 10, 10));

            JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
            for (String op : new String[]{"A", "B", "C", "D"}) {
                JButton btn = new JButton(op);
                btn.addActionListener(e -> { ans[0] = op; d.dispose(); });
                p.add(btn);
            }

            d.add(new JScrollPane(area), BorderLayout.CENTER);
            d.add(p, BorderLayout.SOUTH);
            d.setModal(true);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
            return ans[0];
        }
    }

    // ===== HISTORY DAO =====
    // Table: history (id, username, topic, score)
    static class History {

        static void save(String username, String topic, int score) {
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO history (username, topic, score) VALUES (?,?,?)")) {
                ps.setString(1, username);
                ps.setString(2, topic);
                ps.setInt(3, score);
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "DB error: " + e.getMessage());
            }
        }

        static String get() {
            return fetch("SELECT username, topic, score FROM history ORDER BY id DESC", null);
        }

        static String search(String key) {
            return fetch(
                "SELECT username, topic, score FROM history " +
                "WHERE username LIKE ? OR topic LIKE ? ORDER BY id DESC",
                "%" + key + "%");
        }

        private static String fetch(String sql, String param) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-15s %-15s %-8s\n", "Name", "Topic", "Score"));
            sb.append("----------------------------------------\n");
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                if (param != null) {
                    ps.setString(1, param);
                    ps.setString(2, param);
                }
                ResultSet rs = ps.executeQuery();
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    sb.append(String.format("%-15s %-15s %-8d\n",
                        rs.getString("username"),
                        rs.getString("topic"),
                        rs.getInt("score")));
                }
                if (!found) return param == null ? "No History Found!" : "No Match Found!";
            } catch (SQLException e) {
                return "DB error: " + e.getMessage();
            }
            return sb.toString();
        }

        static void clear() {
            try (Connection con = getConnection();
                 Statement st = con.createStatement()) {
                st.executeUpdate("DELETE FROM history");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "DB error: " + e.getMessage());
            }
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        // Quick connection test on startup
        try (Connection con = getConnection()) {
            System.out.println("DB connected successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Cannot connect to database!\n" + e.getMessage() +
                "\n\nCheck DB_URL, DB_USER, DB_PASS at top of QuizApp.java");
            return;
        }

        SwingUtilities.invokeLater(() -> {

            JFrame f = new JFrame("Quiz App");
            f.setSize(500, 420);
            f.setLocationRelativeTo(null);

            CardLayout cl = new CardLayout();
            JPanel main = new JPanel(cl);

            Admin admin = new Admin("Admin");

            // ---- HOME ----
            JPanel home = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 150));
            JButton adminBtn = new JButton("Admin");
            JButton userBtn  = new JButton("User");
            adminBtn.setPreferredSize(new Dimension(120, 40));
            userBtn .setPreferredSize(new Dimension(120, 40));
            home.add(adminBtn);
            home.add(userBtn);

            // ---- ADMIN PANEL ----
            JPanel adminP = new JPanel();
            adminP.setLayout(new BoxLayout(adminP, BoxLayout.Y_AXIS));
            adminP.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

            JTextField     adminId   = new JTextField();
            JPasswordField adminPass = new JPasswordField();
            JButton addQ  = new JButton("Add Question");
            JButton back1 = new JButton("Back");

            adminP.add(new JLabel("Username"));  adminP.add(adminId);
            adminP.add(javax.swing.Box.createVerticalStrut(10));
            adminP.add(new JLabel("Password"));  adminP.add(adminPass);
            adminP.add(javax.swing.Box.createVerticalStrut(15));
            adminP.add(addQ);
            adminP.add(javax.swing.Box.createVerticalStrut(5));
            adminP.add(back1);

            // ---- USER PANEL ----
            JPanel userP = new JPanel();
            userP.setLayout(new BoxLayout(userP, BoxLayout.Y_AXIS));
            userP.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

            JTextField userName   = new JTextField();
            JTextField topicField = new JTextField();
            JButton start      = new JButton("Start Quiz");
            JButton historyBtn = new JButton("History");
            JButton back2      = new JButton("Back");

            userP.add(new JLabel("Your Name")); userP.add(userName);
            userP.add(javax.swing.Box.createVerticalStrut(10));
            userP.add(new JLabel("Topic"));     userP.add(topicField);
            userP.add(javax.swing.Box.createVerticalStrut(15));
            userP.add(start);
            userP.add(javax.swing.Box.createVerticalStrut(5));
            userP.add(historyBtn);
            userP.add(javax.swing.Box.createVerticalStrut(5));
            userP.add(back2);

            // ---- HISTORY PANEL ----
            JPanel historyP = new JPanel(new BorderLayout());
            JTextArea histArea = new JTextArea();
            histArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            histArea.setEditable(false);

            JPanel bottom = new JPanel();
            JTextField searchField = new JTextField(10);
            JButton searchBtn = new JButton("Search");
            JButton clearBtn  = new JButton("Clear");
            JButton back3     = new JButton("Back");

            bottom.add(new JLabel("Search:"));
            bottom.add(searchField);
            bottom.add(searchBtn);
            bottom.add(clearBtn);
            bottom.add(back3);

            historyP.add(new JScrollPane(histArea), BorderLayout.CENTER);
            historyP.add(bottom, BorderLayout.SOUTH);

            main.add(home,     "home");
            main.add(adminP,   "admin");
            main.add(userP,    "user");
            main.add(historyP, "history");

            f.add(main);

            // ---- NAVIGATION ----
            adminBtn.addActionListener(e -> cl.show(main, "admin"));
            userBtn .addActionListener(e -> cl.show(main, "user"));
            back1   .addActionListener(e -> cl.show(main, "home"));
            back2   .addActionListener(e -> cl.show(main, "home"));
            back3   .addActionListener(e -> cl.show(main, "user"));

            // ---- ADD QUESTION ----
            addQ.addActionListener(e -> {
                String pass = new String(adminPass.getPassword());
                if (!admin.login(adminId.getText().trim(), pass)) {
                    JOptionPane.showMessageDialog(f, "Wrong username or password!");
                    return;
                }
                String t       = JOptionPane.showInputDialog(f, "Topic:");
                if (t == null || t.trim().isEmpty()) return;
                String q       = JOptionPane.showInputDialog(f, "Question:");
                String a       = JOptionPane.showInputDialog(f, "Option A:");
                String b       = JOptionPane.showInputDialog(f, "Option B:");
                String c       = JOptionPane.showInputDialog(f, "Option C:");
                String d       = JOptionPane.showInputDialog(f, "Option D:");
                String correct = JOptionPane.showInputDialog(f, "Correct Answer (A/B/C/D):");
                try {
                    admin.addQuestion(t.trim(), q, a, b, c, d, correct);
                    JOptionPane.showMessageDialog(f, "Question added successfully!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(f, "DB error: " + ex.getMessage());
                }
            });

            // ---- START QUIZ ----
            start.addActionListener(e -> {
                String uname = userName.getText().trim();
                String topic = topicField.getText().trim();
                if (uname.isEmpty() || topic.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Please enter your name and topic.");
                    return;
                }
                User u     = new User(uname);
                int  score = u.attempt(topic);
                if (score == -1) {
                    JOptionPane.showMessageDialog(f, "No questions found for topic: " + topic);
                    return;
                }
                JOptionPane.showMessageDialog(f, "Quiz Complete!\nScore: " + score);
                History.save(uname, topic, score);
            });

            // ---- VIEW HISTORY ----
            historyBtn.addActionListener(e -> {
                histArea.setText(History.get());
                cl.show(main, "history");
            });

            // ---- SEARCH ----
            searchBtn.addActionListener(e ->
                histArea.setText(History.search(searchField.getText().trim())));

            // ---- CLEAR HISTORY ----
            clearBtn.addActionListener(e -> {
                int choice = JOptionPane.showConfirmDialog(f, "Clear all history?");
                if (choice == JOptionPane.YES_OPTION) {
                    History.clear();
                    histArea.setText("");
                }
            });

            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        });
    }
}
