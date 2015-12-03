package hw.hw9;

import java.util.ArrayList;

public class CourseController {

    private StudentCourseModel model;
    private CourseView cv;

    public CourseController(StudentCourseModel model, CourseView cv) {
        this.model = model;
        this.cv = cv;
        cv.addCourseController(this);
        model.addCourseController(this);
    }

    public void updateFromView(String code, String title) {
        model.newCourse(code, title);
        populateTable();
    }

    public void populateTable(){
        cv.populateTable(model.getCourses());
    }

    public Course selectCourse() {
        // Hardcoded index, instead search through course for where getCode() == code
        ArrayList<Course> courses = model.getCourses();
        return courses.get(cv.selectCourse());
    }
}
