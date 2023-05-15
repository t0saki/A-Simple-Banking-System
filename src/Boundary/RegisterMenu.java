package Boundary;

import Control.BankControl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class RegisterMenu extends JFrame {
    private JLabel accountNameLabel, locationLabel, balanceLabel, ageLabel, typeLabel;
    private JTextField accountNameField, locationField, balanceField, ageField;
    private JComboBox<String> typeBox;
    private JButton registerButton, exitButton;
    private BankControl bankControl;

    public RegisterMenu(BankControl bankControl) {
        super("Register");
        this.bankControl = bankControl;

        // openAccount(String type, String accountName, String location, double balance, int age)

        // Set up the labels
        accountNameLabel = new JLabel("Account Name:");
        locationLabel = new JLabel("Location:");
        balanceLabel = new JLabel("Balance:");
        ageLabel = new JLabel("Age:");
        typeLabel = new JLabel("Type:");

        // Set up the text fields
        accountNameField = new JTextField(20);
        locationField = new JTextField(20);
        balanceField = new JTextField(20);
        ageField = new JTextField(20);

        // Set up the type box
        String[] types = {"Current Account", "Junior Account", "Saver Account"};
        typeBox = new JComboBox<String>(types);

        registerButton = new JButton("Register");
        exitButton = new JButton("Exit");

        // 设置布局
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(accountNameLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(accountNameField, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(locationLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(locationField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(balanceLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(balanceField, c);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(ageLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(ageField, c);
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(typeLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(typeBox, c);
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(registerButton, c);
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(exitButton, c);


        // Add listeners
        registerButton.addActionListener(e -> {
            String accountName = accountNameField.getText();
            String location = locationField.getText();
            double balance = Double.parseDouble(balanceField.getText());
            int age = Integer.parseInt(ageField.getText());
            String type = (String) typeBox.getSelectedItem();
            type = switch (type) {
                case "Current Account" -> "Current";
                case "Junior Account" -> "Junior";
                case "Saver Account" -> "Saver";
                default -> (String) typeBox.getSelectedItem();
            };
            int[] result;
            if (type.equals("Junior")) {
                if (age >= 16) {
                    JOptionPane.showMessageDialog(null, "Junior account must be under 16 years old!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    result = bankControl.openAccount(type, accountName, location, balance, age);
                }
            } else {
                result = bankControl.openAccount(type, accountName, location, balance);
            }
            if (result[0] >= 0) {
                JOptionPane.showMessageDialog(null, "Register successfully!\n" +
                        "Account Number: " + result[0] + "\n" +
                        "PIN: " + result[1] + "\n" +
                        "Please remember your account number and PIN!\nIt will show only once!"
                        , "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new Menu(bankControl);
            } else {
                // Error dialog
                JOptionPane.showMessageDialog(this, "Error: Register failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        exitButton.addActionListener(e -> {
            dispose();
            new Menu(bankControl);
        });


        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
