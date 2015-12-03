package hw.hw9;

import java.io.Serializable;

public class Course implements Serializable {

    private String code;
    private String title;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

}
