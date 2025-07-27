import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();

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
        return student.calculateOverallGrade();
    }
}
