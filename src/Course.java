//  a university course with enrollment functionality
import java.io.Serializable;

public class Course implements Serializable {
    // Course attributes with proper encapsulation
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;

    // Static variable to track total enrolled students across all courses
    private static int totalEnrolledStudents = 0;

    // Constructor initializes course with basic parameters
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }

    // Getters provide controlled access to private fields
    // (Teaching point: Encapsulation principles)
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getCurrentEnrollment() { return currentEnrollment; }
    public static int getTotalEnrolledStudents() { return totalEnrolledStudents; }

    // Method to handle student enrollment with capacity checking
    public boolean enrollStudent() {
        // Demonstrates conditional logic and state management
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
            totalEnrolledStudents++;
            return true; // Return boolean to indicate success/failure
        }
        return false;
    }

    // String representation for easy display (Teaching point: toString() method)
    @Override
    public String toString() {
        return courseCode + ": " + courseName;
    }
}