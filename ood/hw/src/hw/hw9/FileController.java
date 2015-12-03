package hw.hw9;

public class FileController {

    private StudentCourseModel model;
    private FileView fv;

    public FileController(StudentCourseModel model, FileView fv) {
        this.model = model;
        this.fv = fv;
        model.addFileController(this);
        fv.addFileController(this);
    }

    public void save() {
        model.save();
    }

}
