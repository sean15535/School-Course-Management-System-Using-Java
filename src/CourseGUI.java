import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CourseGUI extends JFrame {
    private static final String SCHOOL_NAME = "University of the People";
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();

    public CourseGUI() {
        setTitle(SCHOOL_NAME + " - Course Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add school logo
        ImageIcon logo = createImageIcon("uopeople_logo.png", "School Logo");
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
        addCourseBtn.setBackground(new Color(101, 31, 118));
        addCourseBtn.setForeground(Color.WHITE);
        addCourseBtn.setIcon(createImageIcon("plus.png", "Add Course", 16, 16));
        addCourseBtn.setToolTipText("Add a new course to the system");
        addCourseBtn.setIconTextGap(10);
        addCourseBtn.setMargin(new Insets(2, 2, 2, 2));
        coursePanel.add(addCourseBtn);

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

        // Student Management Tab
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel studentTitle = new JLabel("Student Management");
        studentTitle.setFont(new Font("Arial", Font.BOLD, 18));
        studentPanel.add(studentTitle);

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

        // Grade Management Tab
        JPanel gradePanel = new JPanel();
        gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
        gradePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel gradeTitle = new JLabel("Grade Management");
        gradeTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gradePanel.add(gradeTitle);

        JButton assignGradeBtn = new JButton("Assign Grade");
        assignGradeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        assignGradeBtn.setBackground(new Color(101, 31, 118));
        assignGradeBtn.setForeground(Color.WHITE);
        assignGradeBtn.setIcon(createImageIcon("graduation.png", "Assign Grade", 16, 16));
        assignGradeBtn.setToolTipText("Assign a grade to a student for a course");
        assignGradeBtn.setIconTextGap(10);
        assignGradeBtn.setMargin(new Insets(2, 2, 2, 2));
        gradePanel.add(assignGradeBtn);

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

        // Add action listeners
        addCourseBtn.addActionListener(e -> showAddCourseDialog());
        addStudentBtn.addActionListener(e -> showAddStudentDialog());
        enrollBtn.addActionListener(e -> showEnrollDialog());
        assignGradeBtn.addActionListener(e -> showAssignGradeDialog());
        calcGradeBtn.addActionListener(e -> showCalculateGradeDialog());
    }

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

    protected ImageIcon createImageIcon(String path, String description) {
        return createImageIcon(path, description, 0, 0);
    }

    public static void showLogin() {
        // Login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add logo
        ImageIcon logo = new CourseGUI().createImageIcon("uopeople_logo.png", "School Logo");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Add title
        JLabel titleLabel = new JLabel("Course Management Software");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 0, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

        // Add login fields
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

        // Add login button
        JButton loginButton = new JButton("LOG IN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(51, 0, 102));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Set up grid bag constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        // Add components to login panel
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

        // Create login dialog
        JDialog loginDialog = new JDialog((Frame)null, "Login", true);
        loginDialog.getContentPane().add(loginPanel);
        loginDialog.pack();
        loginDialog.setSize(450, 600);
        loginDialog.setLocationRelativeTo(null);

        // Login action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("admin".equals(username.getText()) && "admin".equals(new String(password.getPassword()))) {
                    loginDialog.dispose();
                    
                    // Load data from file
                    CourseGUI courseGUI = new CourseGUI();
                    courseGUI.loadDataFromFile();
                    
                    // Create and display the main window
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

        // Show login dialog
        loginDialog.setVisible(true);
    }

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

    private void showAssignGradeDialog() {
        Student student = selectStudent();
        Course course = selectCourse();

        if (student != null && course != null) {
            String input = JOptionPane.showInputDialog(this, "Enter grade:");
            try {
                double grade = Double.parseDouble(input);
                if (student.assignGrade(course, grade)) {
                    JOptionPane.showMessageDialog(this, "Grade assigned.");
                    saveDataToFile();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid grade value.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid grade input.");
            }
        }
    }

    private void showCalculateGradeDialog() {
        Student student = selectStudent();
        if (student != null) {
            try {
                double grade = CourseManagement.calculateOverallGrade(student);
                JOptionPane.showMessageDialog(this, "Overall Grade: " + grade);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No grades available for this student.");
            }
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

    private void saveDataToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("course_data.ser"))) {
            out.writeObject(courses);
            out.writeObject(students);
            JOptionPane.showMessageDialog(this, "Data saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("course_data.ser"))) {
            courses = (List<Course>) in.readObject();
            students = (List<Student>) in.readObject();
            JOptionPane.showMessageDialog(null, "Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No saved data found. Starting with empty records.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourseGUI::showLogin);
    }
}

// Helper class for placeholder text in text fields
class JTextFieldWithPlaceholder extends JTextField {
    private String placeholder;

    public JTextFieldWithPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getDisabledTextColor());
            g2.drawString(placeholder, getInsets().left, getHeight() / 2 + g.getFontMetrics().getAscent() / 2);
            g2.dispose();
        }
    }
}

// Helper class for placeholder text in password fields
class JPasswordFieldWithPlaceholder extends JPasswordField {
    private String placeholder;

    public JPasswordFieldWithPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getDisabledTextColor());
            g2.drawString(placeholder, getInsets().left, getHeight() / 2 + g.getFontMetrics().getAscent() / 2);
            g2.dispose();
        }
    }
}