package Boundary;

import Control.Bank;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ClientLogin {
    JFrame frame;
    Bank bank;
    public ClientLogin(Bank bank) {
        this.bank = bank;
        System.out.println("ClientLogin");

        frame = new JFrame("Client Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // username and password fields
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5)); // Set the layout and gaps between buttons
        JLabel usernameLabel = new JLabel("Account Number:");
        JTextField usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("PID:");
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        // login button
        JButton loginButton = new JButton("Login");
        panel.add(loginButton);
        loginButton.addActionListener(e -> {
            int accountNumber = Integer.parseInt(usernameField.getText());
            StringBuilder sPID = new StringBuilder();
            char[] cPID = passwordField.getPassword();
            for (char c : cPID) {
                sPID.append(c);
            }
            int PID = Integer.parseInt(sPID.toString());
            switch (bank.checkLogin(accountNumber, PID)) {
                case 0 -> {
                    System.out.println("Login successful!");
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    frame.dispose();
                    new ClientMenu(bank, accountNumber);
                }
                case 1 -> JOptionPane.showMessageDialog(null, "Username does not exist!");
                case 2 -> JOptionPane.showMessageDialog(null, "Wrong password!");
                default -> JOptionPane.showMessageDialog(null, "Unknown error!");
            }
        });

        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new Menu(new Bank());
        });

        frame.add(panel);
    }
}
