import Boundary.Menu;
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

//        BankAccount ba = new BankAccount(2, "Tim", "Singapore", 1000);
//        ba.appendFile();
        // 2 4389

        Menu menu = new Menu(new BankControl());
    }
}
