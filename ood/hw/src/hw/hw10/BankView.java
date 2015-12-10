package hw.hw10;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.border.*;

public class BankView extends JFrame {

    private JPanel mainPanel;
    private SelectAccountPanel selectAccountPanel;
    private AccessAccountPanel accessAccountPanel;

    public void addBankController(BankController bc) {
        Border blackline = BorderFactory.createLineBorder(Color.black);

        TitledBorder selectAccountTitle = BorderFactory.createTitledBorder(blackline, "Select Account");
        selectAccountTitle.setTitleJustification(TitledBorder.LEFT);

        TitledBorder accessAccountTitle = BorderFactory.createTitledBorder(blackline, "Access Account");
        accessAccountTitle.setTitleJustification(TitledBorder.LEFT);

        this.selectAccountPanel = new SelectAccountPanel(bc);
        this.selectAccountPanel.setBorder(selectAccountTitle);
        this.accessAccountPanel = new AccessAccountPanel(bc);
        this.accessAccountPanel.setBorder(accessAccountTitle);

        this.mainPanel = new JPanel();
        this.mainPanel.add(selectAccountPanel);
        this.mainPanel.add(accessAccountPanel);

        setTitle("Bank");
        setSize(600, 300);
        setLocation(40, 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    public String getUserName() {
        return selectAccountPanel.getUserName();
    }
}

class SelectAccountPanel extends JPanel {

    private BankController bc;

    // Labels
    private JLabel userNameLabel;

    // Text Fields
    private JTextField userNameTextField;

    // Buttons
    private JButton newAccountButton;
    private JButton getAccountButton;

    public SelectAccountPanel(BankController bc) {
        this.bc = bc;

        // Text Fields
        this.userNameLabel = new JLabel("UserName");

        // Text Fields
        this.userNameTextField = new JTextField(8);

        // Buttons
        this.newAccountButton = new JButton("New Account");
        newAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = userNameTextField.getText();
                bc.newAccount(userName);
            }
        });

        this.getAccountButton = new JButton("Get Account");
        getAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = userNameTextField.getText();
                bc.setAccount(userName);
            }
        });

        // Add Labels
        add(userNameLabel);

        // Add Text Fields
        add(userNameTextField);

        // Add Buttons
        add(newAccountButton);
        add(getAccountButton);
    }

    public String getUserName() {
        return userNameTextField.getText();
    }

}

class AccessAccountPanel extends JPanel {

    private BankController bc;

    // Labels
    private JLabel amountLabel;

    // Text Fields
    private JTextField amountTextField;
    private JTextField balanceTextField;

    // Buttons
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton showBalanceButton;

    public AccessAccountPanel(BankController bc) {
        this.bc = bc;

        // Labels
        this.amountLabel = new JLabel("Amount");

        // Text Fields
        this.amountTextField = new JTextField(4);
        this.balanceTextField = new JTextField(4);

        // Buttons
        this.depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bc.deposit(Integer.valueOf(amountTextField.getText()));
                // Deposit updates the balance text field
                showBalance();
            }
        });

        this.withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bc.withdraw(Integer.valueOf(amountTextField.getText()));
                // Withdraw updates the balance text field
                showBalance();
            }
        });

        this.showBalanceButton = new JButton("Show Balance");
        showBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showBalance();
            }
        });

        // Add Elements
        add(amountLabel);
        add(amountTextField);
        add(depositButton);
        add(withdrawButton);
        add(showBalanceButton);
        add(balanceTextField);
    }

    public void showBalance() {
        balanceTextField.setText(bc.getBalance().toString());
    }

}
