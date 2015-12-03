package hw.hw9;

import javax.swing.JTable;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class StudentView extends JFrame {

    private StudentPanel panel;

    public void addStudentController(StudentController sc) {
        this.panel = new StudentPanel(sc);
        setTitle("Student Info");
        setSize(500, 200);
        setLocation(20, 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setVisible(true);
        sc.populateTable();
    }

    public void populateTable(ArrayList<Student> students) {
        panel.populateTable(students);
    }

    public int selectStudent() {
        return panel.selectStudent();
    }

}

class StudentPanel extends JPanel {

    private StudentController sc;
    private DefaultTableModel dfm;
    private JTable table;
    private JTextField nameTextField;
    private JTextField gradYearTextField;
    private JButton btn;
    private String[] columnnames = new String[] {"id", "Name", "GradYear"};
    private JScrollPane scrollPane;

    public StudentPanel(StudentController sc) {
        this.sc = sc;
        this.nameTextField = new JTextField(8);
        this.gradYearTextField = new JTextField(4);

        // Button
        this.btn = new JButton("NEW STUDENT");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                Integer gradYear = Integer.valueOf(gradYearTextField.getText());
                sc.updateFromView(name, gradYear);
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
        add(nameTextField);
        add(gradYearTextField);
        add(scrollPane);
    }

    // Table Methods
    public void populateTable(ArrayList<Student> students) {
        clearTable();
        for (Student s: students) {
            dfm.addRow(new Object[] { s.getStudentId(), s.getName(), s.getGradYear()});
        }
    }

    public void clearTable() {
        int rowCount = dfm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dfm.removeRow(i);
        }
    }

    public int selectStudent() {
        return table.getSelectedRow();
    }

}
