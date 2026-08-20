<div align="center">

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=700&size=32&duration=3000&pause=1000&color=4479A1&center=true&vCenter=true&width=700&lines=Quiz+Management+System;Java+%2B+MySQL+Desktop+App;Built+with+OOP+%26+JDBC;Admin+%7C+User+%7C+History" alt="Typing SVG" />

<br>

<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=6,11,20&height=120&section=header" width="100%"/>

<p>
<img src="https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
<img src="https://img.shields.io/badge/MySQL-8.0+-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
<img src="https://img.shields.io/badge/JDBC-Connectivity-007396?style=for-the-badge" />
<img src="https://img.shields.io/badge/Swing-GUI-5382A1?style=for-the-badge" />
</p>

<p>
<img src="https://img.shields.io/badge/status-active-success?style=flat-square" />
<img src="https://img.shields.io/github/last-commit/Majid-Ali01/quiz-management-system?style=flat-square&color=blue" />
<img src="https://img.shields.io/github/repo-size/Majid-Ali01/quiz-management-system?style=flat-square&color=orange" />
<img src="https://img.shields.io/github/stars/Majid-Ali01/quiz-management-system?style=flat-square&color=yellow" />
<img src="https://img.shields.io/github/license/Majid-Ali01/quiz-management-system?style=flat-square" />
</p>

<p align="center">A desktop quiz platform built with <b>Java Swing</b> and <b>MySQL</b> — question management, timed attempts, automatic scoring, and full history tracking.</p>

<sub>⭐ If this project is useful to you, consider starring the repository — it really helps!</sub>

</div>

<br>

<div align="center">

### 📚 Table of Contents

</div>

<div align="left">

• [Overview](#-overview) <br>
• [Features](#-features)   <br>
• [Tech Stack](#-tech-stack)    <br>
• [Architecture](#-architecture)    <br>
• [OOP Design](#-oop-design)   <br>
• [Screenshots](#-screenshots)   <br>
• [Project Structure](#-project-structure)   <br>
•[Installation](#-installation)  <br> 
• [Roadmap](#-roadmap)  <br>
• [Author](#-author)  <br>

</div>

<br>

## 🧭 Overview

**Quiz Management System** is a Java desktop application built as a 2nd-semester software engineering project. It gives administrators full control over a question bank while allowing users to take topic-based quizzes, get scored automatically, and review their attempt history — all backed by a normalized MySQL database.

The project was built to demonstrate applied **Object-Oriented Programming**, **Swing GUI development**, **JDBC connectivity**, and **secure SQL practices**, rather than to be a minimal classroom exercise.

<br>

## ✨ Features

<table>
<tr>
<td valign="top" width="50%">

### 🛠️ Admin
- 🔐 Authenticated admin login
- 📁 Create and manage quiz topics
- ➕ Add multiple-choice questions
- ✅ Define correct answers
- 🗄️ Question bank stored in MySQL

</td>
<td valign="top" width="50%">

### 🙋 User
- ✏️ Simple username entry
- 🎯 Topic selection
- 🧩 Interactive MCQ quiz flow
- 🧮 Automatic score calculation
- ⚡ Instant results display

</td>
</tr>
<tr>
<td valign="top">

### 📊 History
- 🕓 Per-user attempt history
- 🔎 Search by username or topic
- 💾 Persisted results in MySQL
- 🧹 Clear / reset history

</td>
<td valign="top">

### 🔒 Security
- 🧂 SHA-256 password hashing
- 🛡️ `PreparedStatement` for all queries
- ✔️ Input validation
- ⚠️ Centralized exception handling

</td>
</tr>
</table>

<br>

## 🧰 Tech Stack

<div align="left">

| Layer | Technology |
|:---:|:---:|
| Language | ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white) |
| GUI | ![Swing](https://img.shields.io/badge/Swing-5382A1?style=flat-square) |
| Database | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) |
| Connectivity | ![JDBC](https://img.shields.io/badge/JDBC-007396?style=flat-square) |
| Security | ![SHA--256](https://img.shields.io/badge/SHA--256-red?style=flat-square) |
| Architecture | ![OOP](https://img.shields.io/badge/OOP-blueviolet?style=flat-square) |
| Version Control | ![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white) |

</div>

<br>

## 🏗️ Architecture

```mermaid
flowchart TD
    A[🎓 Quiz System] --> B[🛠️ Admin Module]
    A --> C[🙋 User Module]

    B --> B1[🔐 Login]
    B1 --> B2[📁 Add / Manage Questions]

    C --> C1[✏️ Enter Name & Select Topic]
    C1 --> C2[🧩 Attempt Quiz]

    B2 --> D[(🗄️ MySQL Database)]
    C2 --> D

    D --> E[🧮 Score Calculation]
    E --> F[📊 History Stored]

    style A fill:#4479A1,color:#fff
    style D fill:#ED8B00,color:#fff
    style E fill:#5382A1,color:#fff
    style F fill:#007396,color:#fff
```

<br>

## 🧩 OOP Design

<div align="center">

| Concept | Implementation |
|:---|:---|
| 🧱 **Abstraction** | `Person` defines shared structure; `role()` is left to subclasses |
| 🌳 **Inheritance** | `Admin` and `User` extend `Person` |
| 🎭 **Polymorphism** | Each subclass overrides inherited behavior |
| 🔌 **Interface** | `Attemptable` defines the quiz-attempt contract implemented by `User` |
| 📦 **Encapsulation** | Each class exposes only what's needed through defined methods |

</div>

**Core classes:** `QuizApp` · `Person` · `Admin` · `User` · `Question` · `History`

<br>

## 🖼️ Screenshots

<table>
<tr>
<td align="center"><img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20174832.png?raw=true" width="380"><br><sub><b>🏠 Home Screen</b></sub></td>
<td align="center"><img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20174911.png?raw=true" width="380"><br><sub><b>🔐 Admin Login</b></sub></td>
</tr>
<tr>
<td align="center"><img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20174958.png?raw=true" width="380"><br><sub><b>📁 Question Management</b></sub></td>
<td align="center"><img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175038.png?raw=true" width="380"><br><sub><b>🙋 User Interface</b></sub></td>
</tr>
<tr>
<td align="center"><img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175115.png?raw=true" width="380"><br><sub><b>🧩 Quiz Interface</b></sub></td>
<td align="center"><img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175159.png?raw=true" width="380"><br><sub><b>🏆 Quiz Result</b></sub></td>
</tr>
<tr>
<td align="center"><img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175227.png?raw=true" width="380"><br><sub><b>📊 Quiz History</b></sub></td>
<td align="center"><img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175250.png?raw=true" width="380"><br><sub><b>🔎 History Search</b></sub></td>
</tr>
</table>

<br>

## 📂 Project Structure

```
quiz-management-system/
├── QuizApp.java
├── Topic.sql
├── admin.sql
├── questions.sql
├── history.sql
├── .gitignore
└── README.md
```

<div align="center">

**Database (`quizapp`)** — `admin` · `user` · `topic` · `question` · `history`

</div>

<br>

## ⚙️ Installation

<details open>
<summary><b>Click to expand setup steps</b></summary>

<br>

```bash
git clone https://github.com/Majid-Ali01/quiz-management-system.git
```

1. 🗄️ Import the `.sql` files into a MySQL 8.0+ instance to create the schema.
2. 🔧 Update the JDBC connection string, username, and password in `QuizApp.java`.
3. ▶️ Compile and run `QuizApp.java` in your preferred Java IDE or via CLI.

</details>

<br>

## 🗺️ Roadmap

- [ ] 🎨 Modernized, responsive GUI
- [ ] ⏱️ Countdown timer per quiz
- [ ] 🔀 Randomized questions & answer order
- [ ] 👤 Full user registration & authentication
- [ ] ✏️ Edit / delete existing questions
- [ ] 📈 Graphical performance analytics
- [ ] 📄 PDF export of results
- [ ] 🏆 Leaderboard system
- [ ] 🌐 Web-based version

<br>

## 👤 Author

<div align="center">

**Majid Ali**
*Software Engineering Student*

<img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=6,11,20&height=100&section=footer" width="100%"/>

<sub>Built with ❤️ using Java · MySQL · JDBC · OOP</sub>

</div>
