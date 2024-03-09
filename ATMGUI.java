import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

// Bank Account class to store user's account balance
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
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// ATM class
class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        return bankAccount.withdraw(amount);
    }

    public double checkBalance() {
        return bankAccount.getBalance();
    }
}

// ATM GUI class
public class ATMGUI extends JFrame implements ActionListener {
    private ATM atm;
    private JTextField amountField;
    private JTextArea outputArea;

    public ATMGUI(ATM atm) {
        this.atm = atm;
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");
        amountField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        balanceButton.addActionListener(this);

        panel.add(new JLabel("Enter amount: "));
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Withdraw":
                try {
                    double withdrawAmount = Double.parseDouble(amountField.getText());
                    if (atm.withdraw(withdrawAmount)) {
                        outputArea.setText("Withdrawn: $" + withdrawAmount + "\nRemaining Balance: $" + atm.checkBalance());
                    } else {
                        outputArea.setText("Insufficient balance!");
                    }
                } catch (NumberFormatException ex) {
                    outputArea.setText("Invalid input for amount!");
                }
                break;
            case "Deposit":
                try {
                    double depositAmount = Double.parseDouble(amountField.getText());
                    atm.deposit(depositAmount);
                    outputArea.setText("Deposited: $" + depositAmount + "\nNew Balance: $" + atm.checkBalance());
                } catch (NumberFormatException ex) {
                    outputArea.setText("Invalid input for amount!");
                }
                break;
            case "Check Balance":
                outputArea.setText("Current Balance: $" + atm.checkBalance());
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        BankAccount bankAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(bankAccount);
        new ATMGUI(atm);
    }
}

