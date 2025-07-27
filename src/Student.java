import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private String id;
    private Map<Course, Double> enrolledCourses;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean enrollInCourse(Course course) {
        if (course.enrollStudent()) {
            enrolledCourses.put(course, null);
            return true;
        }
        return false;
    }

    public boolean assignGrade(Course course, double grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
            return true;
        }
        return false;
    }

    public double calculateOverallGrade() {
        double total = 0.0;
        int count = 0;
        for (Double grade : enrolledCourses.values()) {
            if (grade != null) {
                total += grade;
                count++;
            }
        }
        return count > 0 ? total / count : 0.0;
    }

    public Map<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
