# Course Management System

A simple Java Swing application for managing students, courses, enrollments, and grades.

---

## 📜 Description

This project is a desktop application built with Java Swing that provides a graphical user interface (GUI) for managing academic records. It allows users to perform basic administrative tasks such as adding courses and students, enrolling students in courses, and assigning grades. The application persists data by saving it to a local file.

---

## ✨ Features

- **User Authentication:** A simple login screen to secure access.
- **Course Management:** Add new courses with details like course code, name, and maximum capacity.
- **Student Management:** Add new students with their name and ID.
- **Enrollment:** Enroll students into available courses, with checks for course capacity.
- **Grade Management:** Assign grades to students for specific courses and calculate their overall grade.
- **Data Persistence:** Save and load application data (students and courses) to a file (`course_data.ser`).

---

## 🚀 How to Run

### Prerequisites

- Java Development Kit (JDK) installed on your system.

### Compile the code

Open your terminal or command prompt, navigate to the directory containing the `.java` files, and run:

```sh
javac *.java
```

### Run the application

After successful compilation, run the main class to start the application:

```sh
java Main
```

### Login

Use the following credentials to log in:

- **Username:** admin
- **Password:** admin

---

## 📁 File Structure

Here's a brief overview of the key files in this project:

- **Main.java:** The main entry point of the application. It launches the login window.
- **CourseGUI.java:** The core of the user interface. It creates the main window with tabs for managing courses, students, and grades. It also handles all user interactions and dialog boxes.
- **Course.java:** A model class representing a university course. It holds data for the course code, name, capacity, and current enrollment.
- **Student.java:** A model class representing a student. It stores the student's name, ID, and a map of enrolled courses and their corresponding grades.
- **CourseManagement.java:** Acts as a service layer that separates the business logic (managing courses, students, enrollment) from the GUI (CourseGUI.java), improving code organization and maintainability.

---

## 👨‍💻 Author

Alli Oluwaseun (Lin[kedIn](https://www.linkedin.com/in/oluwaseun-alli-aa4a812ba/))