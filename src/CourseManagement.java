import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void addCourse(String code, String name, int capacity) {
        courses.add(new Course(code, name, capacity));
    }

    public static List<Course> getCourses() {
        return courses;
    }

    public static boolean enrollStudent(Student student, Course course) {
        return student.enrollInCourse(course);
    }

    public static boolean assignGrade(Student student, Course course, double grade) {
        return student.assignGrade(course, grade);
    }

    public static double calculateOverallGrade(Student student) {
        double total = 0.0;
        int count = 0;
        for (Course course : courses) {
            Double grade = student.getEnrolledCourses().get(course);
            if (grade != null) {
                total += grade;
                count++;
            }
        }
        return count > 0 ? total / count : 0.0;
    }

    public static void saveDataToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("course_data.ser"))) {
            out.writeObject(courses);
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void loadDataFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("course_data.ser"))) {
            courses = (List<Course>) in.readObject();
            students = (List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved data found.");
        }
    }
}