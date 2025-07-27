import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CourseGUI extends JFrame {
    private static final String SCHOOL_NAME = "FutureTech University";
    private static List<Student> students = new ArrayList<>();

    public CourseGUI() {
        setTitle(SCHOOL_NAME + " - Course Management System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addCourseBtn = new JButton("Add Course");
        JButton addStudentBtn = new JButton("Add Student");
        JButton enrollBtn = new JButton("Enroll Student");
        JButton assignGradeBtn = new JButton("Assign Grade");
        JButton calcGradeBtn = new JButton("Calculate Grade");

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.add(new JLabel("Welcome to " + SCHOOL_NAME, SwingConstants.CENTER));
        panel.add(addCourseBtn);
        panel.add(addStudentBtn);
        panel.add(enrollBtn);
        panel.add(assignGradeBtn);
        panel.add(calcGradeBtn);
        add(panel);

        addCourseBtn.addActionListener(e -> showAddCourseDialog());
        addStudentBtn.addActionListener(e -> showAddStudentDialog());
        enrollBtn.addActionListener(e -> showEnrollDialog());
        assignGradeBtn.addActionListener(e -> showAssignGradeDialog());
        calcGradeBtn.addActionListener(e -> showCalculateGradeDialog());

        setVisible(true);
    }

    // ---- Login screen ----
    public static void showLogin() {
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        Object[] fields = {
            "Username:", username,
            "Password:", password
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if ("admin".equals(username.getText()) && "admin".equals(new String(password.getPassword()))) {
                new CourseGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Exiting.");
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    // ---- GUI Functions ----
    private void showAddCourseDialog() {
        JTextField code = new JTextField();
        JTextField name = new JTextField();
        JTextField cap = new JTextField();

        Object[] inputs = {
            "Course Code:", code,
            "Course Name:", name,
            "Max Capacity:", cap
        };

        int result = JOptionPane.showConfirmDialog(this, inputs, "Add Course", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                CourseManagement.addCourse(code.getText(), name.getText(), Integer.parseInt(cap.getText()));
                JOptionPane.showMessageDialog(this, "Course added.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        }
    }

    private void showAddStudentDialog() {
        JTextField name = new JTextField();
        JTextField id = new JTextField();

        Object[] inputs = {
            "Student Name:", name,
            "Student ID:", id
        };

        int result = JOptionPane.showConfirmDialog(this, inputs, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            students.add(new Student(name.getText(), id.getText()));
            JOptionPane.showMessageDialog(this, "Student added.");
        }
    }

    private void showEnrollDialog() {
        Student student = selectStudent();
        Course course = selectCourse();

        if (student != null && course != null) {
            if (CourseManagement.enrollStudent(student, course)) {
                JOptionPane.showMessageDialog(this, "Enrolled successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Course is full.");
            }
        }
    }

    private void showAssignGradeDialog() {
        Student student = selectStudent();
        Course course = selectCourse();

        if (student != null && course != null) {
            String input = JOptionPane.showInputDialog(this, "Enter grade:");
            try {
                double grade = Double.parseDouble(input);
                CourseManagement.assignGrade(student, course, grade);
                JOptionPane.showMessageDialog(this, "Grade assigned.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid grade input.");
            }
        }
    }

    private void showCalculateGradeDialog() {
        Student student = selectStudent();
        if (student != null) {
            double grade = CourseManagement.calculateOverallGrade(student);
            JOptionPane.showMessageDialog(this, "Overall Grade: " + grade);
        }
    }

    private Student selectStudent() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students available.");
            return null;
        }

        String[] options = students.stream().map(Student::toString).toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(this, "Select Student:", "Student Selection",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selected != null) {
            String id = selected.split(":")[0];
            return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        }
        return null;
    }

    private Course selectCourse() {
        List<Course> courses = CourseManagement.getCourses();
        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No courses available.");
            return null;
        }

        String[] options = courses.stream().map(Course::toString).toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(this, "Select Course:", "Course Selection",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selected != null) {
            String code = selected.split(":")[0];
            return courses.stream().filter(c -> c.getCourseCode().equals(code)).findFirst().orElse(null);
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourseGUI::showLogin);
    }
}
