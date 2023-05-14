package Boundary;

import Control.Bank;
import Entity.BankAccount;

import javax.swing.*;
import java.awt.*;

public class ClientMenu {
    Bank bank;
    BankAccount account;
    JFrame frame;

    public ClientMenu(Bank bank, int accountNumber) {
        this.bank = bank;
        this.account = bank.getAccount(accountNumber);

        // Create a frame
        frame = new JFrame("Client Menu");
        refresh();

    }

    public void refresh() {
        // clear the frame
        frame.getContentPane().removeAll();
        frame.repaint();

        frame.setTitle(account.getTypeByString());
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create a split pane to display header and record data
        JList<String> headerList = new JList<>(account.getHeader());
        JList<String> recordList = new JList<>(account.getRecord());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(headerList), new JScrollPane(recordList));
        splitPane.setDividerLocation(150);

        // Create a panel to hold the split pane and the buttons
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(splitPane, BorderLayout.CENTER);

        // Add the buttons to the bottom of the frame
        JButton button1 = new JButton("Deposit");
        JButton button2 = new JButton("Clear");
        JButton button3 = new JButton("Withdraw");
        JButton button4 = new JButton("Suspend");
        JButton button5 = new JButton("Close");
        JButton button6 = new JButton("Exit");
        JButton button7 = new JButton("Next day");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add listeners to the buttons
        button1.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter the amount to deposit:");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (account.deposit(amount) == 0) {
                        JOptionPane.showMessageDialog(frame, "Deposit successful.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Deposit failed.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input.");
                }
            }
            account.updateFile();
            refresh();
        });

        button2.addActionListener(e -> {
            if (account.clearFunds() == 0) {
                JOptionPane.showMessageDialog(frame, "Clear successful.");
            } else {
                JOptionPane.showMessageDialog(frame, "Clear failed.");
            }
            account.updateFile();
            refresh();
        });

        button3.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter the amount to withdraw:");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (account.withdraw(amount) == 0) {
                        JOptionPane.showMessageDialog(frame, "Withdraw successful.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Withdraw failed.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input.");
                }
            }
            account.updateFile();
            refresh();
        });

        button4.addActionListener(e -> {
            account.suspend();
            if (account.isSuspended()) {
                JOptionPane.showMessageDialog(frame, "Suspend successful.");
            } else {
                JOptionPane.showMessageDialog(frame, "Resume successful.");
            }
            account.updateFile();
            refresh();
        });

        button5.addActionListener(e -> {
            if (account.closeAccount() == 0) {
                JOptionPane.showMessageDialog(frame, "Close successful.");
            } else {
                JOptionPane.showMessageDialog(frame, "Close failed.");
            }
            account.updateFile();
            refresh();
        });

        button6.addActionListener(e -> {
            if (account.getBalance() >= 0) {
                frame.dispose();
                new Menu(new Bank());
            } else {
                JOptionPane.showMessageDialog(frame, "Account balance is negative.");
            }
        });

        button7.addActionListener(e -> {
            if (account.nextDay() == 0) {
                JOptionPane.showMessageDialog(frame, "A new day has dawned.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error occurred.");
            }
            account.updateFile();
            refresh();
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
}
