/*
 * File: InsufficientFunds.java
 * Author: Alex Bailey
 * Date: 8 April 18
 * Purpose: withdraw and transfer methods from Account class throw exceptions to this class
 */

import javax.swing.*;

public class InsufficientFunds extends Throwable {
    public InsufficientFunds() {
        JOptionPane frame = new JOptionPane();
        JOptionPane.showMessageDialog(frame, "Insufficient funds.");
    }
}
