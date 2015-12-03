package hw.hw9;

public class StudentCourseMain {
    public static void main(String[] args) {
        StudentCourseModel model = new StudentCourseModel();

        FileView fv = new FileView();
        CourseView cv = new CourseView();
        EnrollmentView ev = new EnrollmentView();
        StudentView sv = new StudentView();

        FileController fc = new FileController(model, fv);
        CourseController cc = new CourseController(model, cv);
        StudentController sc = new StudentController(model, sv);
        EnrollmentController ec = new EnrollmentController(model, ev, sc, cc);
    }
}
