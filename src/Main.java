import Boundary.MainMenu;
import Control.BankControl;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // JFrame style to match the OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainMenu mainMenu = new MainMenu(new BankControl());
    }
}
