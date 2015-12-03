package hw.hw9;


import javax.swing.JTable;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class CourseView extends JFrame {
    private CoursePanel panel;

    public void addCourseController(CourseController cc) {
        this.panel = new CoursePanel(cc);
        setTitle("Course Info");
        setSize(500, 200);
        setLocation(600,40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setVisible(true);
        cc.populateTable();
    }

    public void populateTable(ArrayList<Course> courses) {
        panel.populateTable(courses);
    }

    public int selectCourse() {
        return panel.selectCourse();
    }
}

class CoursePanel extends JPanel {

    private CourseController cc;
    private DefaultTableModel dfm;
    private JTable table;
    private JTextField codeTextField;
    private JTextField titleTextField;
    private JButton btn;
    private String[] columnnames = new String[] {"Code", "Title"};
    private JScrollPane scrollPane;

    public CoursePanel(CourseController cc) {
        this.cc = cc;
        this.codeTextField = new JTextField(8);
        this.titleTextField = new JTextField(4);

        // Button
        this.btn = new JButton("NEW COURSE");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = codeTextField.getText();
                String title = titleTextField.getText();
                cc.updateFromView(code, title);
            }
        });

        // Table
        this.dfm = new DefaultTableModel();
        dfm.setColumnIdentifiers(columnnames);
        this.table = new JTable(dfm);
        table.setPreferredScrollableViewportSize(new Dimension(300,70));
        this.scrollPane = new JScrollPane(table);

        // Add elements
        add(btn);
        add(codeTextField);
        add(titleTextField);
        add(scrollPane);
    }

    // Table Methods
    public void populateTable(ArrayList<Course> courses) {
        clearTable();
        for (Course c: courses) {
            dfm.addRow(new Object[] { c.getCode(), c.getTitle() });
        }
    }

    public void clearTable() {
        int rowCount = dfm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dfm.removeRow(i);
        }
    }

    public int selectCourse() {
        return table.getSelectedRow();
    }

}
