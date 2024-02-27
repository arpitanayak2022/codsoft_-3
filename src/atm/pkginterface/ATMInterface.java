package atm.pkginterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATMInterface extends JFrame implements ActionListener {
    private BankAccount account;

    JButton withdrawButton;
    JButton depositButton;
    JButton checkBalanceButton;

    JTextField amountField;

    public ATMInterface(BankAccount account) {
        this.account = account;
        setTitle("ATM Machine");
        setSize(800, 500);
        getContentPane().setBackground(Color.BLUE);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null); 

        // Heading label
        JLabel headingLabel = new JLabel("Welcome to ATM Machine !");
        headingLabel.setBounds(140, 40, 650, 80);
        headingLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 40));
        headingLabel.setForeground(Color.WHITE);
        add(headingLabel);
        
        // Sub-heading label
        JLabel subHeadingLabel = new JLabel("SELECT TRANSACTION");
        subHeadingLabel.setBounds(250, 120, 300, 60);
        subHeadingLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        subHeadingLabel.setForeground(Color.YELLOW);
        add(subHeadingLabel);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 200, 200, 50);
        withdrawButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        withdrawButton.setBackground(Color.YELLOW);
        withdrawButton.setForeground(Color.BLACK);
        withdrawButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(100, 270, 200, 50);
        depositButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        depositButton.setBackground(Color.YELLOW);
        depositButton.setForeground(Color.BLACK);
        depositButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        depositButton.addActionListener(this);
        add(depositButton);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(100, 340, 200, 50);
        checkBalanceButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        checkBalanceButton.setForeground(Color.BLACK);
        checkBalanceButton.setBackground(Color.YELLOW);
        checkBalanceButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        checkBalanceButton.addActionListener(this);
        add(checkBalanceButton);

        // text label
        JLabel textLabel = new JLabel("Enter Your Amount:");
        textLabel.setBounds(470, 200, 200, 100);
        textLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 24));
        textLabel.setForeground(Color.WHITE);
        add(textLabel);
        
        amountField = new JTextField();
        amountField.setBounds(470, 270, 200, 50);
        amountField.setFont(new Font("Mongolian Baiti", Font.PLAIN, 24));
        add(amountField);

        setVisible(true);
        // Disable maximize button
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid positive amount.");
                return;
            }
            if (e.getSource() == withdrawButton) {
                if (account.withdraw(amount)) {
                    JOptionPane.showMessageDialog(this, "Withdrawal successful.");
                    clearAmountField();
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds.");
                }
            } else if (e.getSource() == depositButton) {
                account.deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposit successful.");
                clearAmountField();
            } else if (e.getSource() == checkBalanceButton) {
                JOptionPane.showMessageDialog(this, "Your balance is: $" + String.format("%.2f", account.getBalance()));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
    }
    
    // Method to clear the amount field
    private void clearAmountField() {
        amountField.setText("");
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(100);
        new ATMInterface(account);
    }
}
