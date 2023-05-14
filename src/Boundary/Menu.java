package Boundary;


import Control.Bank;

import javax.swing.*;
import java.awt.*;

public class Menu {
    JFrame frame;
    Bank bank;

    public Menu(Bank bank) {
        this.bank = bank;
        frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 2 buttons, client or admin, in the center of the screen, fixed size
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50)); // Set the layout and gaps between buttons
        JButton clientButton = new JButton("CLIENT");
//        clientButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
//        clientButton.setSize(200, 100);
        // Set the font size and padding of the buttons
        Font buttonFont = new Font(clientButton.getFont().getName(), Font.PLAIN, 30);
        clientButton.setFont(buttonFont);
        clientButton.setMargin(new java.awt.Insets(20, 20, 20, 20));
        clientButton.setSize(200, 100);

        clientButton.addActionListener(e -> {
            frame.dispose();
            new ClientLogin(bank);
        });
        panel.add(clientButton);


        JButton adminButton = new JButton("ADMIN");
        adminButton.setFont(buttonFont);
        adminButton.setMargin(new java.awt.Insets(20, 20, 20, 20));
        adminButton.setSize(200, 100);

//        adminButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
//        adminButton.setSize(200, 100);
        adminButton.addActionListener(e -> {
            frame.dispose();
//            new AdminMenu();
        });
        panel.add(adminButton);



        frame.add(panel);
    }
}
