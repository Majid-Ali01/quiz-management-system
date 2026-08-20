# 🎯 Quiz Management System

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-8.0%2B-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/JDBC-Database%20Connectivity-007396?style=for-the-badge">
  <img src="https://img.shields.io/badge/Java%20Swing-GUI-5382A1?style=for-the-badge">
  <img src="https://img.shields.io/badge/OOP-Implemented-success?style=for-the-badge">
  <img src="https://img.shields.io/badge/Project-2nd%20Semester-blueviolet?style=for-the-badge">
</p>

<h3 align="center">
  💻 A Desktop-Based Quiz Management System
</h3>

<p align="center">
  A Java Swing application integrated with MySQL for quiz management,
  question handling, score calculation, and attempt history.
</p>

---

## 📌 Project Overview

**Quiz Management System** is a Java-based desktop application developed as a **2nd Semester Final Project**.

The system provides an interactive quiz platform where administrators can manage questions and users can attempt quizzes, receive scores, and review previous attempts.

The project demonstrates practical implementation of **Object-Oriented Programming, Java Swing GUI development, JDBC connectivity, MySQL database management, SQL operations, authentication, and exception handling**.

### 🎯 Main Objectives

- Build a functional desktop application using Java
- Apply Object-Oriented Programming concepts
- Connect Java with a relational MySQL database
- Implement CRUD-based database operations
- Develop an interactive graphical user interface
- Store and retrieve quiz results
- Implement basic password hashing and authentication

---

## ✨ Features

### 👨‍💼 Admin Module

- 🔐 Admin authentication
- ➕ Add quiz questions
- 🏷️ Create quiz topics
- 📝 Add multiple-choice questions
- 🔤 Add four answer options
- ✅ Define the correct answer
- 🗄️ Store questions in MySQL

### 👨‍🎓 User Module

- 👤 Enter username
- 📚 Select quiz topic
- ▶️ Start quiz
- 🔘 Answer MCQs interactively
- 🧮 Automatic score calculation
- 🏆 Display final score

### 📊 Quiz History

- 📋 View previous quiz attempts
- 🔎 Search history by username or topic
- 💾 Store scores in MySQL
- 🗑️ Clear quiz history
- 📈 Retrieve previous results

### 🔐 Security

- Password verification
- SHA-256 password hashing support
- JDBC `PreparedStatement` for database queries
- Basic input validation
- Exception handling for database operations

---

## 🛠️ Technologies

| Technology | Purpose |
|---|---|
| ☕ Java | Core application development |
| 🖥️ Java Swing | Graphical User Interface |
| 🗄️ MySQL 8.0+ | Database management |
| 🔌 JDBC | Java–MySQL connectivity |
| 🧩 OOP | Application architecture |
| 🔐 SHA-256 | Password hashing |
| 📝 SQL | Database operations |
| 🔧 Git | Version control |
| 🌐 GitHub | Source-code hosting |

---

## 🧠 OOP Concepts Implemented

This project applies several important Object-Oriented Programming concepts:

### 🔹 Encapsulation

Classes organize related data and behavior into structured components.

### 🔹 Inheritance

`Admin` and `User` inherit common properties from the abstract `Person` class.

### 🔹 Abstraction

The `Person` class defines a common structure while leaving the `role()` method for subclasses.

### 🔹 Polymorphism

Different classes implement their own behavior for inherited methods.

### 🔹 Interface

The `Attemptable` interface defines the quiz-attempt functionality implemented by the `User` class.

### 🔹 Classes & Objects

The system uses classes such as:

- `QuizApp`
- `Person`
- `Admin`
- `User`
- `Question`
- `History`

---

## 📸 Screenshots

### 🏠 Home Screen

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20174832.png?raw=true" width="700">
</p>

### 🔐 Admin Login

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20174911.png?raw=true" width="700">
</p>

### 📝 Question Management

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20174958.png?raw=true" width="700">
</p>

### 👨‍🎓 User Interface

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175038.png?raw=true" width="700">
</p>

### 📚 Quiz Interface

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175115.png?raw=true" width="700">
</p>

### 🏆 Quiz Result

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175159.png?raw=true" width="700">
</p>

### 📊 Quiz History

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175227.png?raw=true" width="700">
</p>

### 🔎 History Search

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175250.png?raw=true" width="700">
</p>

### 🗂️ Database / Application View

<p align="center">
  <img src="https://github.com/Majid-Ali01/quiz-management-system/blob/main/Screenshot%202026-08-20%20175330.png?raw=true" width="700">
</p>

---
quiz-management-system/
│
├── QuizApp.java
│
├── Topic.sql
├── admin.sql
├── questions.sql
├── history.sql
│
├── Screenshot 2026-08-20 174832.png
├── Screenshot 2026-08-20 174911.png
├── Screenshot 2026-08-20 174958.png
├── Screenshot 2026-08-20 175038.png
├── Screenshot 2026-08-20 175115.png
├── Screenshot 2026-08-20 175159.png
├── Screenshot 2026-08-20 175227.png
├── Screenshot 2026-08-20 175250.png
├── Screenshot 2026-08-20 175330.png
│
├── .gitignore
└── README.md

---
🗃️ **Database Tables**
quizapp
│
├── admin
├── user
├── topic
├── question
└── history

---
              ┌──────────────────┐
              │    Quiz System   │
              └────────┬─────────┘
                       │
             ┌─────────┴─────────┐
             │                   │
             ▼                   ▼
       ┌───────────┐       ┌───────────┐
       │   Admin   │       │   User    │
       └─────┬─────┘       └─────┬─────┘
             │                   │
             ▼                   ▼
      Admin Login          Enter Name/Topic
             │                   │
             ▼                   ▼
      Add Questions          Start Quiz
             │                   │
             └─────────┬─────────┘
                       ▼
                ┌─────────────┐
                │   MySQL DB  │
                └──────┬──────┘
                       │
                       ▼
                 Score / History

---
🧠 What I Learned

Through this project, I gained practical experience in:

Java application development
Object-Oriented Programming
Abstract classes and interfaces
Inheritance and polymorphism
Java Swing GUI development
JDBC database connectivity
MySQL database design
SQL queries and prepared statements
CRUD operations
Password hashing concepts
Exception handling
Event-driven programming
Git and GitHub version control
Project organization and documentation
Connecting a desktop application with a relational database

---
🚀 Future Improvements

The project can be extended with the following features:

🎨 Modern responsive GUI design
⏱️ Countdown timer for quizzes
📊 Advanced performance analytics
📈 Graphical score reports
👥 Complete user registration and authentication
🔑 Secure password management
📝 Edit and delete questions
🏷️ Advanced topic/category management
🎯 Randomized questions
🔀 Randomized answer options
📄 Export results to PDF
☁️ Cloud database integration
🌐 Web-based version
🔔 Quiz notifications
🏆 Leaderboard system

---
## ⚙️ Installation

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Majid-Ali01/quiz-management-system.git

---
👨‍💻 Developer

Majid Ali

Software Engineering Student

<p align="center"> ⭐ If you find this project useful, consider giving the repository a star! </p> <p align="center"> <strong>Built with Java ☕ • MySQL 🗄️ • OOP 🧩 • JDBC 🔌 • GitHub 🚀</strong> </p>
