package hw.hw9;

import java.util.ArrayList;
import java.io.*;

public class StudentCourseModel {

    // Controllers
    private StudentController sc;
    private CourseController cc;
    private EnrollmentController ec;
    private FileController fc;

    // Collections
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    public StudentCourseModel() {
        File f = new File("output.dat");
        if (f.isFile() && f.canRead()) {
            try {
                FileInputStream in = new FileInputStream(f);
                try {
                    ObjectInputStream input = new ObjectInputStream(in);
                    this.courses = (ArrayList<Course>) input.readObject();
                    this.students = (ArrayList<Student>) input.readObject();
                } catch (ClassNotFoundException ex) {

                } finally {
                    in.close();
                }
            } catch (IOException ex) {
                // Appropriate error handling here.
            }
        } else {

            this.courses = new ArrayList<>();
            this.students = new ArrayList<>();
        }

    }

    public void addStudentController(StudentController sc) {
        this.sc = sc;
    }

    public void addCourseController(CourseController cc) {
        this.cc = cc;
    }

    public void addEnrollmentController(EnrollmentController ec) {
        this.ec = ec;
    }

    public void addFileController(FileController fc) {
        this.fc = fc;
    }

    public void newStudent(String name, Integer gradYear) {
        students.add(new Student(name, gradYear));
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void newCourse(String code, String title) {
        courses.add(new Course(code, title));
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void save() {
        try {
            OutputStream os = new FileOutputStream("output.dat");
            ObjectOutputStream output = new ObjectOutputStream(os);

            output.writeObject(getCourses());
            output.writeObject(getStudents());
            output.close();
        } catch (IOException ex) {
            // Appropriate error handling here.
        }
    }

}
