package hw.hw9;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    private static int STUDENT_ID = 1;

    private String name;
    private int gradYear;
    private int id;
    private ArrayList<Course> enrollments;

    public Student(String name, Integer gradYear) {
        this.name = name;
        this.gradYear = gradYear;
        this.id = STUDENT_ID;
        this.enrollments = new ArrayList<>();
        STUDENT_ID += 1;
    }

    public String getName() {
        return name;
    }

    public int getGradYear() {
        return gradYear;
    }

    public int getStudentId() {
        return id;
    }

    public ArrayList<Course> getEnrollments() {
        return enrollments;
    }

    public void add(Course c) {
        enrollments.add(c);
    }

    public void drop(Course c) {
        enrollments.remove(c);
    }
}
