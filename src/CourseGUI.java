import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CourseGUI extends JFrame {
    private static final String SCHOOL_NAME = "University of the People";
    private static List<Student> students = new ArrayList<>();

    public CourseGUI() {
        setTitle(SCHOOL_NAME + " - Course Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add school logo
        ImageIcon logo = new ImageIcon("uopeople_logo.png"); // Replace with actual logo path
        JLabel logoLabel = new JLabel(logo);
        add(logoLabel, BorderLayout.NORTH);

        // Create tabs for different sections
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Course Management Tab
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.add(new JLabel("Course Management", SwingConstants.CENTER));
        JButton addCourseBtn = new JButton("Add Course");
        addCourseBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        addCourseBtn.setBackground(new Color(101, 31, 118)); // Purple color
        addCourseBtn.setForeground(Color.WHITE);
        coursePanel.add(addCourseBtn);
        JButton enrollBtn = new JButton("Enroll Student");
        enrollBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        enrollBtn.setBackground(new Color(101, 31, 118)); // Purple color
        enrollBtn.setForeground(Color.WHITE);
        coursePanel.add(enrollBtn);
        tabbedPane.addTab("Course Management", coursePanel);

        // Student Management Tab
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.add(new JLabel("Student Management", SwingConstants.CENTER));
        JButton addStudentBtn = new JButton("Add Student");
        addStudentBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        addStudentBtn.setBackground(new Color(101, 31, 118)); // Purple color
        addStudentBtn.setForeground(Color.WHITE);
        studentPanel.add(addStudentBtn);
        tabbedPane.addTab("Student Management", studentPanel);

        // Grade Management Tab
        JPanel gradePanel = new JPanel();
        gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
        gradePanel.add(new JLabel("Grade Management", SwingConstants.CENTER));
        JButton assignGradeBtn = new JButton("Assign Grade");
        assignGradeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        assignGradeBtn.setBackground(new Color(101, 31, 118)); // Purple color
        assignGradeBtn.setForeground(Color.WHITE);
        gradePanel.add(assignGradeBtn);
        JButton calcGradeBtn = new JButton("Calculate Grade");
        calcGradeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        calcGradeBtn.setBackground(new Color(101, 31, 118)); // Purple color
        calcGradeBtn.setForeground(Color.WHITE);
        gradePanel.add(calcGradeBtn);
        tabbedPane.addTab("Grade Management", gradePanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Add action listeners
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
            "Password:", password,
            "Remember me", new JCheckBox(),
            "Forgot password?", new JButton()
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if ("admin".equals(username.getText()) && "admin".equals(new String(password.getPassword()))) {
                new CourseGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Exiting.", "Error", JOptionPane.ERROR_MESSAGE);
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