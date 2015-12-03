package hw.hw9;

public class EnrollmentController {

    private StudentCourseModel model;
    private EnrollmentView ev;
    private StudentController sc;
    private CourseController cc;

    public EnrollmentController(StudentCourseModel model, EnrollmentView ev,
                                StudentController sc, CourseController cc) {
        this.model = model;
        this.ev = ev;
        this.sc = sc;
        this.cc = cc;
        model.addEnrollmentController(this);
        ev.addControllers(this, sc, cc);
    }

    public void add() {
        selectStudent().add(selectCourse());
        populateTable();
    }

    public void drop() {
        selectStudent().drop(selectCourse());
        populateTable();
    }

    public void populateTable() {
        ev.populateTable(selectStudent().getEnrollments());
    }

    public Student selectStudent() {
        return sc.selectStudent();
    }

    public Course selectCourse() {
        return cc.selectCourse();
    }


}
