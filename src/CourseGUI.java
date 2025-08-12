import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

// Main GUI class for the Course Management System
public class CourseGUI extends JFrame {
    // School name constant
    private static final String SCHOOL_NAME = "University of the People";
    // List to store all students
    private static List<Student> students = new ArrayList<>();
    // List to store all courses
    private static List<Course> courses = new ArrayList<>();

    // Constructor to set up the GUI
    public CourseGUI() {
        setTitle(SCHOOL_NAME + " - Course Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout());

        // Header panel with logo and title
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon logo = createImageIcon("uopeople_logo.png", "School Logo");
        JLabel logoLabel = new JLabel(logo);
        headerPanel.add(logoLabel, BorderLayout.NORTH);

        JLabel appTitle = new JLabel("University of the People Course Management App");
        appTitle.setFont(new Font("Arial", Font.BOLD, 14));
        appTitle.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(appTitle, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // Tabbed pane for different management panels
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Course management panel
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel courseTitle = new JLabel("Course Management");
        courseTitle.setFont(new Font("Arial", Font.BOLD, 18));
        coursePanel.add(courseTitle);

        // Button to add a new course
        JButton addCourseBtn = new JButton("Add Course");
        addCourseBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        addCourseBtn.setBackground(new Color(101, 31, 118));
        addCourseBtn.setForeground(Color.WHITE);
        addCourseBtn.setIcon(createImageIcon("plus.png", "Add Course", 16, 16));
        addCourseBtn.setToolTipText("Add a new course to the system");
        addCourseBtn.setIconTextGap(10);
        addCourseBtn.setMargin(new Insets(2, 2, 2, 2));
        coursePanel.add(addCourseBtn);

        // Button to enroll a student in a course
        JButton enrollBtn = new JButton("Enroll Student");
        enrollBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        enrollBtn.setBackground(new Color(101, 31, 118));
        enrollBtn.setForeground(Color.WHITE);
        enrollBtn.setIcon(createImageIcon("user.png", "Enroll Student", 16, 16));
        enrollBtn.setToolTipText("Enroll a student in a course");
        enrollBtn.setIconTextGap(10);
        enrollBtn.setMargin(new Insets(2, 2, 2, 2));
        coursePanel.add(enrollBtn);

        tabbedPane.addTab("Course Management", coursePanel);

        // Student management panel
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel studentTitle = new JLabel("Student Management");
        studentTitle.setFont(new Font("Arial", Font.BOLD, 18));
        studentPanel.add(studentTitle);

        // Button to add a new student
        JButton addStudentBtn = new JButton("Add Student");
        addStudentBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        addStudentBtn.setBackground(new Color(101, 31, 118));
        addStudentBtn.setForeground(Color.WHITE);
        addStudentBtn.setIcon(createImageIcon("user-plus.png", "Add Student", 16, 16));
        addStudentBtn.setToolTipText("Add a new student to the system");
        addStudentBtn.setIconTextGap(10);
        addStudentBtn.setMargin(new Insets(2, 2, 2, 2));
        studentPanel.add(addStudentBtn);

        tabbedPane.addTab("Student Management", studentPanel);

        // Grade management panel
        JPanel gradePanel = new JPanel();
        gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
        gradePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel gradeTitle = new JLabel("Grade Management");
        gradeTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gradePanel.add(gradeTitle);

        // Button to assign a grade to a student
        JButton assignGradeBtn = new JButton("Assign Grade");
        assignGradeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        assignGradeBtn.setBackground(new Color(101, 31, 118));
        assignGradeBtn.setForeground(Color.WHITE);
        assignGradeBtn.setIcon(createImageIcon("graduation.png", "Assign Grade", 16, 16));
        assignGradeBtn.setToolTipText("Assign a grade to a student for a course");
        assignGradeBtn.setIconTextGap(10);
        assignGradeBtn.setMargin(new Insets(2, 2, 2, 2));
        gradePanel.add(assignGradeBtn);

        // Button to calculate a student's overall grade
        JButton calcGradeBtn = new JButton("Calculate Grade");
        calcGradeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        calcGradeBtn.setBackground(new Color(101, 31, 118));
        calcGradeBtn.setForeground(Color.WHITE);
        calcGradeBtn.setIcon(createImageIcon("calculator.png", "Calculate Grade", 16, 16));
        calcGradeBtn.setToolTipText("Calculate a student's overall grade");
        calcGradeBtn.setIconTextGap(10);
        calcGradeBtn.setMargin(new Insets(2, 2, 2, 2));
        gradePanel.add(calcGradeBtn);

        tabbedPane.addTab("Grade Management", gradePanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Add footer
        addFooter();

        // Add action listeners for buttons
        addCourseBtn.addActionListener(e -> showAddCourseDialog());
        addStudentBtn.addActionListener(e -> showAddStudentDialog());
        enrollBtn.addActionListener(e -> showEnrollDialog());
        assignGradeBtn.addActionListener(e -> showAssignGradeDialog());
        calcGradeBtn.addActionListener(e -> showCalculateGradeDialog());
    }

    // Helper method to create an ImageIcon with optional scaling
    protected ImageIcon createImageIcon(String path, String description, int width, int height) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            if (width == 0 || height == 0) {
                return new ImageIcon(imgURL, description);
            } else {
                Image image = new ImageIcon(imgURL).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(image, description);
            }
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    // Overloaded helper for ImageIcon without scaling
    protected ImageIcon createImageIcon(String path, String description) {
        return createImageIcon(path, description, 0, 0);
    }

    // Static method to show the login dialog before launching the main GUI
    public static void showLogin() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ImageIcon logo = new CourseGUI().createImageIcon("uopeople_logo.png", "School Logo");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel titleLabel = new JLabel("Course Management Software");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 0, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

        // Username and password fields with placeholders
        JTextField username = new JTextFieldWithPlaceholder("Username");
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBackground(Color.WHITE);
        username.setForeground(Color.BLACK);
        username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        username.setPreferredSize(new Dimension(300, 40));

        JPasswordField password = new JPasswordFieldWithPlaceholder("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        password.setBackground(Color.WHITE);
        password.setForeground(Color.BLACK);
        password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        password.setPreferredSize(new Dimension(300, 40));

        // Login button
        JButton loginButton = new JButton("LOG IN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(51, 0, 102));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Layout constraints for login panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        loginPanel.add(logoLabel, gbc);

        gbc.gridy = 1;
        loginPanel.add(titleLabel, gbc);

        gbc.gridy = 2;
        loginPanel.add(username, gbc);

        gbc.gridy = 3;
        loginPanel.add(password, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(30, 10, 10, 10);
        loginPanel.add(loginButton, gbc);

        // Modal dialog for login
        JDialog loginDialog = new JDialog((Frame)null, "Login", true);
        loginDialog.getContentPane().add(loginPanel);
        loginDialog.pack();
        loginDialog.setSize(450, 600);
        loginDialog.setLocationRelativeTo(null);

        // Login button action: checks credentials and launches main GUI
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("admin".equals(username.getText()) && "admin".equals(new String(password.getPassword()))) {
                    loginDialog.dispose();
                    CourseGUI courseGUI = new CourseGUI();
                    courseGUI.loadDataFromFile();
                    courseGUI.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                        loginDialog,
                        "Invalid credentials. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        loginDialog.setVisible(true);
    }

    // Dialog to add a new course
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
                courses.add(new Course(code.getText(), name.getText(), Integer.parseInt(cap.getText())));
                JOptionPane.showMessageDialog(this, "Course added.");
                saveDataToFile();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        }
    }

    // Dialog to add a new student
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
            saveDataToFile();
        }
    }

    // Dialog to enroll a student in a course
    private void showEnrollDialog() {
        Student student = selectStudent();
        Course course = selectCourse();

        if (student != null && course != null) {
            if (course.getCurrentEnrollment() < course.getMaxCapacity()) {
                if (student.enrollInCourse(course)) {
                    JOptionPane.showMessageDialog(this, "Enrolled successfully.");
                    saveDataToFile();
                } else {
                    JOptionPane.showMessageDialog(this, "Enrollment failed.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Course is full.");
            }
        }
    }

    // Dialog to assign a grade to a student for a course
    private void showAssignGradeDialog() {
        Student student = selectStudent();
        Course course = selectCourse();

        if (student != null && course != null) {
            String input = JOptionPane.showInputDialog(this, "Enter grade (0-100):");
            try {
                double grade = Double.parseDouble(input);
                if (grade < 0 || grade > 100) {
                    JOptionPane.showMessageDialog(this, "Grade must be between 0 and 100.");
                } else {
                    if (student.enrollInCourse(course)) { // Ensure student is enrolled
                        if (student.assignGrade(course, grade)) {
                            JOptionPane.showMessageDialog(this, "Grade assigned.");
                            saveDataToFile();
                        } else {
                            JOptionPane.showMessageDialog(this, "Grade assignment failed. Please ensure the student is enrolled in the course.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Student is not enrolled in the course. Please enroll the student first.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid grade input. Please enter a numeric value.");
            }
        }
    }

    // Dialog to calculate and show a student's overall grade
    private void showCalculateGradeDialog() {
        // Prompt the user to select a student from the list
        Student student = selectStudent();
        // Check if a student was selected
        if (student != null) {
            // Calculate the overall grade for the selected student
            double grade = student.calculateOverallGrade();
            // If the grade is 0.0, it means there are no grades available for this student
            if (grade == 0.0) {
                JOptionPane.showMessageDialog(this, "No grades available for this student.");
            } else {
                // Display the calculated overall grade to the user
                JOptionPane.showMessageDialog(this, "Overall Grade: " + grade);
            }
        }
    }

    // Helper to select a student from the list
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

    // Helper to select a course from the list
    private Course selectCourse() {
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

    // Save courses and students to a file for persistence
    private void saveDataToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("course_data.ser"))) {
            out.writeObject(courses);
            out.writeObject(students);
            JOptionPane.showMessageDialog(this, "Data saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load courses and students from a file
    private void loadDataFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("course_data.ser"))) {
            courses = (List<Course>) in.readObject();
            students = (List<Student>) in.readObject();
            JOptionPane.showMessageDialog(null, "Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No saved data found. Starting with empty records.");
        }
    }

    // Method to create footer
    private void addFooter() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); // zero horizontal gap
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));     // vertical padding only
        footerPanel.setBackground(Color.LIGHT_GRAY);

        JLabel createdByText = new JLabel("Created with love by ");
        createdByText.setFont(new Font("Arial", Font.PLAIN, 12));

        // Create a link for "Alli Oluwaseun"
        JButton linkedinLink = new JButton("Alli Oluwaseun");
        linkedinLink.setFont(new Font("Arial", Font.PLAIN, 12));
        linkedinLink.setContentAreaFilled(false);
        linkedinLink.setBorderPainted(false);
        linkedinLink.setFocusPainted(false);
        linkedinLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkedinLink.setForeground(Color.BLUE);
        linkedinLink.setMargin(new Insets(0, 0, 0, 0));

        linkedinLink.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(URI.create("https://www.linkedin.com/in/oluwaseun-alli-aa4a812ba/"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error opening link: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        footerPanel.add(createdByText);
        footerPanel.add(linkedinLink);

        add(footerPanel, BorderLayout.SOUTH);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CourseGUI.showLogin();
        });
    }
}

// Custom JTextField with placeholder text
class JTextFieldWithPlaceholder extends JTextField {
    private String placeholder;

    public JTextFieldWithPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw placeholder if field is empty
        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getDisabledTextColor());
            g2.drawString(placeholder, getInsets().left, getHeight() / 2 + g.getFontMetrics().getAscent() / 2);
            g2.dispose();
        }
    }
}

// Custom JPasswordField with placeholder text
class JPasswordFieldWithPlaceholder extends JPasswordField {
    private String placeholder;

    public JPasswordFieldWithPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw placeholder if field is empty
        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getDisabledTextColor());
            g2.drawString(placeholder, getInsets().left, getHeight() / 2 + g.getFontMetrics().getAscent() / 2);
            g2.dispose();
        }
    }
}