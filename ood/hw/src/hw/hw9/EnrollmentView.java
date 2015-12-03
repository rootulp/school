package hw.hw9;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class EnrollmentView extends JFrame {

    private EnrollmentPanel panel;

    public void addControllers(EnrollmentController ev, StudentController sc, CourseController cc) {
        this.panel = new EnrollmentPanel(ev, sc, cc);
        setTitle("Enrollments");
        setSize(500, 200);
        setLocation(600,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setVisible(true);
    }

    public void populateTable(ArrayList<Course> courses) {
        panel.populateTable(courses);
    }
}

class EnrollmentPanel extends JPanel {

    private EnrollmentController ec;
    private StudentController sc;
    private CourseController cc;
    private DefaultTableModel dfm;
    private JTable table;
    private JButton selectStudentButton;
    private JButton addButton;
    private JButton dropButton;
    private String[] columnnames = new String[] {"Code", "Title"};
    private JScrollPane scrollPane;

    public EnrollmentPanel(EnrollmentController ec, StudentController sc, CourseController cc) {
        this.ec = ec;
        this.sc = sc;
        this.cc = cc;

        // Buttons
        this.selectStudentButton = new JButton("SELECT STUDENT");
        selectStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ec.populateTable();
            }
        });

        this.addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ec.add();
            }
        });

        this.dropButton = new JButton("DROP");
        dropButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ec.drop();
            }
        });

        // Table
        this.dfm = new DefaultTableModel();
        dfm.setColumnIdentifiers(columnnames);
        this.table = new JTable(dfm);
        table.setPreferredScrollableViewportSize(new Dimension(300,70));
        this.scrollPane = new JScrollPane(table);

        // Add elements
        add(scrollPane);
        add(selectStudentButton);
        add(addButton);
        add(dropButton);
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

}