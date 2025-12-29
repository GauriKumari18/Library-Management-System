# Library Management System (Java + MySQL)

A desktop-based **Library Management System** built using **Java** with **MySQL** database integration.
This project demonstrates JDBC connectivity, basic CRUD operations, and GUI-based interaction.

---

## 📌 Features
- Add new books
- Update book details
- Delete books
- View all books
- MySQL database connectivity using JDBC

---

## 🛠 Tech Stack
- Java
- MySQL
- JDBC
- WSL (Ubuntu)
- Git & GitHub

---

## 📂 Project Structure
LibraryGUI/
├── src/
│ └── LibraryGUI_DB.java
├── database/
│ └── schema.sql
├── .gitignore
└── README.md


---

## 🗄 Database Setup

1. Start MySQL:
<commmand>
sudo service mysql start
git clone https://github.com/GauriKumari18/Library-Management-System.git
cd Library-Management-System

2. compile
javac src/LibraryGUI_DB.java

3.Run
java -cp ".:/usr/share/java/mysql-connector-java.jar" src.LibraryGUI_DB

