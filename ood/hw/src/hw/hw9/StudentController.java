package hw.hw9;

public class StudentController {

    private StudentCourseModel model;
    private StudentView sv;

    public StudentController(StudentCourseModel model, StudentView sv) {
        this.model = model;
        this.sv = sv;
        sv.addStudentController(this);
        model.addStudentController(this);
    }

    public void updateFromView(String name, Integer gradYear) {
        model.newStudent(name, gradYear);
        populateTable();
    }

    public void populateTable(){
        sv.populateTable(model.getStudents());
    }

    public Student selectStudent() {
        // Hardcoded index, instead search through students for where getId() == id
        return model.getStudents().get(sv.selectStudent());
    }

}
