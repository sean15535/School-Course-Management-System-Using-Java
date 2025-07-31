import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private String name;
    private String id;
    private Map<Course, Double> enrolledCourses;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashMap<>();
    }

    // Getters
    public String getName() { return name; }
    public String getId() { return id; }
    public Map<Course, Double> getEnrolledCourses() { return enrolledCourses; }

    // Enrollment method
    public boolean enrollInCourse(Course course) {
        if (course.getCurrentEnrollment() < course.getMaxCapacity()) {
            if (enrolledCourses.containsKey(course)) {
                return false;
            }
            if (course.enrollStudent()) {
                enrolledCourses.put(course, null);
                return true;
            }
        }
        return false;
    }

    // Grade assignment method
    public boolean assignGrade(Course course, double grade) {
        if (enrolledCourses.containsKey(course) && grade >= 0.0 && grade <= 100.0) {
            enrolledCourses.put(course, grade);
            return true;
        }
        return false;
    }

    // Grade calculation method
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

    @Override
    public String toString() {
        return id + ": " + name;
    }
}