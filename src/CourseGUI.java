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

        // Create a panel for the header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add school logo
        ImageIcon logo = createImageIcon("uopeople_logo.png", "School Logo"); // Replace with actual logo path
        JLabel logoLabel = new JLabel(logo);
        headerPanel.add(logoLabel, BorderLayout.NORTH);

        // Add text under the logo
        JLabel appTitle = new JLabel("University of the People Course Management App");
        appTitle.setFont(new Font("Arial", Font.BOLD, 14));
        appTitle.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(appTitle, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // Create tabs for different sections
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Course Management Tab
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel courseTitle = new JLabel("Course Management");
        courseTitle.setFont(new Font("Arial", Font.BOLD, 18));
        coursePanel.add(courseTitle);

        JButton addCourseBtn = new JButton("Add Course");
        addCourseBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        addCourseBtn.setBackground(new Color(101, 31, 118)); // Purple color
        addCourseBtn.setForeground(Color.WHITE);
        addCourseBtn.setIcon(createImageIcon("plus.png", "Add Course")); // Add icon
        addCourseBtn.setToolTipText("Add a new course to the system");
        addCourseBtn.setIconTextGap(10);
        addCourseBtn.setMargin(new Insets(2, 2, 2, 2)); // Make the icon box smaller
        coursePanel.add(addCourseBtn);

        JButton enrollBtn = new JButton("Enroll Student");
        enrollBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        enrollBtn.setBackground(new Color(101, 31, 118)); // Purple color
        enrollBtn.setForeground(Color.WHITE);
        enrollBtn.setIcon(createImageIcon("user.png", "Enroll Student")); // Add icon
        enrollBtn.setToolTipText("Enroll a student in a course");
        enrollBtn.setIconTextGap(10);
        enrollBtn.setMargin(new Insets(2, 2, 2, 2)); // Make the icon box smaller
        coursePanel.add(enrollBtn);

        tabbedPane.addTab("Course Management", coursePanel);

        // Student Management Tab
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel studentTitle = new JLabel("Student Management");
        studentTitle.setFont(new Font("Arial", Font.BOLD, 18));
        studentPanel.add(studentTitle);

        JButton addStudentBtn = new JButton("Add Student");
        addStudentBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        addStudentBtn.setBackground(new Color(101, 31, 118)); // Purple color
        addStudentBtn.setForeground(Color.WHITE);
        addStudentBtn.setIcon(createImageIcon("user-plus.png", "Add Student")); // Add icon
        addStudentBtn.setToolTipText("Add a new student to the system");
        addStudentBtn.setIconTextGap(10);
        addStudentBtn.setMargin(new Insets(2, 2, 2, 2)); // Make the icon box smaller
        studentPanel.add(addStudentBtn);

        tabbedPane.addTab("Student Management", studentPanel);

        // Grade Management Tab
        JPanel gradePanel = new JPanel();
        gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
        gradePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel gradeTitle = new JLabel("Grade Management");
        gradeTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gradePanel.add(gradeTitle);

        JButton assignGradeBtn = new JButton("Assign Grade");
        assignGradeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        assignGradeBtn.setBackground(new Color(101, 31, 118)); // Purple color
        assignGradeBtn.setForeground(Color.WHITE);
        assignGradeBtn.setIcon(createImageIcon("graduation.png", "Assign Grade")); // Add icon
        assignGradeBtn.setToolTipText("Assign a grade to a student for a course");
        assignGradeBtn.setIconTextGap(10);
        assignGradeBtn.setMargin(new Insets(2, 2, 2, 2)); // Make the icon box smaller
        gradePanel.add(assignGradeBtn);

        JButton calcGradeBtn = new JButton("Calculate Grade");
        calcGradeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        calcGradeBtn.setBackground(new Color(101, 31, 118)); // Purple color
        calcGradeBtn.setForeground(Color.WHITE);
        calcGradeBtn.setIcon(createImageIcon("calculator.png", "Calculate Grade")); // Add icon
        calcGradeBtn.setToolTipText("Calculate a student's overall grade");
        calcGradeBtn.setIconTextGap(10);
        calcGradeBtn.setMargin(new Insets(2, 2, 2, 2)); // Make the icon box smaller
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

    // Method to create image icons with error handling
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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