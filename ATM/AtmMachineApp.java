/*
 * File: AtmMachineApp.java
 * Author: Alex Bailey
 * Date: 8 April 18
 * Purpose: Classes that implement the ATM GUI (Frame, Panels, and Buttons)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

class AtmMachineApp extends JFrame{
    // instance variables
    private static final int WIDTH = 300, HEIGHT = 200;
    // constructors
    public AtmMachineApp() {
        super("ATM Machine");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new AtmMachineAppPanel());
    }
    public void display() {
        setVisible(true);
    }
    public static void main(String[] args) {
        AtmMachineApp atmApp = new AtmMachineApp();
        atmApp.display();
    }
}

class AtmMachineAppPanel extends JPanel {
    protected InputOutputPanel ioPanel = new InputOutputPanel(this);
    private ButtonsPanel btnPanel = new ButtonsPanel(this);
    public AtmMachineAppPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        add(ioPanel, BorderLayout.SOUTH);
        add(btnPanel, BorderLayout.CENTER);
    }
}

class InputOutputPanel extends JPanel {
    // instance variables
    private AtmMachineAppPanel atmPanel;
    private JTextField entryAmountTxt = new JTextField();
    // constructors
    public InputOutputPanel(AtmMachineAppPanel atmPanel) {
        this.atmPanel = atmPanel;
        setLayout(new GridLayout(1,1));
        setBorder(BorderFactory.createEmptyBorder(0,10,20,10));
        add(entryAmountTxt);
    }
    // methods
    public double getEntryAmount() {
        try {
            return Double.parseDouble(entryAmountTxt.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    public void clearEntry() {
        entryAmountTxt.setText("");
    }
}

// implement iopanel, buttons panels, and event listener functions to handle selected buttons
class ButtonsPanel extends JPanel {
    // instance variables
    private AtmMachineAppPanel atmPanel;
    private JButton withdrawBtn = new JButton("Withdraw");
    private JButton depositBtn = new JButton("Deposit");
    private JButton transferBtn = new JButton("Transfer to");
    private JButton balanceBtn = new JButton("Balance");
    private JRadioButton checkRadBtn = new JRadioButton("Checking", true);
    private JRadioButton savRadBtn = new JRadioButton("Savings");
    private ButtonGroup buttonsGroup = new ButtonGroup();
    private ButtonGroup radioButtonsGroup = new ButtonGroup();

    // constructors
    public ButtonsPanel(AtmMachineAppPanel atmPanel) {
        this.atmPanel = atmPanel;
        setLayout(new GridLayout(3,2,10,10));
        setBorder(BorderFactory.createEmptyBorder(15,15,0,15));
        withdrawBtn.setToolTipText("Withdraw in $20 increments");
        depositBtn.setToolTipText("Deposit to account");
        transferBtn.setToolTipText("Transfer to account");
        balanceBtn.setToolTipText("Check balance");
        add(withdrawBtn); add(depositBtn);
        add(transferBtn); add(balanceBtn);
        add(checkRadBtn); add(savRadBtn);
        // add buttons to ButtonGroup
        buttonsGroup.add(withdrawBtn);
        buttonsGroup.add(depositBtn);
        buttonsGroup.add(balanceBtn);
        buttonsGroup.add(transferBtn);
        // create ButtonGroup object
        // include radio-button objects in button-group object
        radioButtonsGroup.add(checkRadBtn);
        radioButtonsGroup.add(savRadBtn);
        // create checking and savings account
        Account checking = new Account();
        Account savings = new Account();
        DecimalFormat df = new DecimalFormat("$0.00");

        // implement listeners
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double entryAmount = atmPanel.ioPanel.getEntryAmount();
                    // check for negative numbers and increment of 20
                    if (checkRadBtn.isSelected()) {
                        if (entryAmount % 20 == 0) {
                            checking.withdraw(entryAmount);
                            JOptionPane.showMessageDialog(null, "Withdrew " + df.format(entryAmount) + " from Checking");
                            atmPanel.ioPanel.clearEntry();
                        } else if (entryAmount % 20 != 0) {
                            JOptionPane.showMessageDialog(null, "Amount must me a multiple of 20");
                            atmPanel.ioPanel.clearEntry();
                        } else {
                            JOptionPane.showMessageDialog(null, "Amount is not a number");
                            atmPanel.ioPanel.clearEntry();
                        }
                    } else {
                        if (entryAmount % 20 == 0) {
                            savings.withdraw(entryAmount);
                            JOptionPane.showMessageDialog(null, "Withdrew " + df.format(entryAmount) + " from Savings");
                            atmPanel.ioPanel.clearEntry();
                        } else if (entryAmount % 20 != 0) {
                            JOptionPane.showMessageDialog(null, "Amount must me a multiple of 20");
                            atmPanel.ioPanel.clearEntry();
                        } else {
                            JOptionPane.showMessageDialog(null, "Amount is not a number");
                            atmPanel.ioPanel.clearEntry();
                        }
                    }
                } catch (InsufficientFunds ex) {}
            }
        });
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double entryAmount = atmPanel.ioPanel.getEntryAmount();
                if(checkRadBtn.isSelected() && entryAmount > 0) {
                    checking.deposit(entryAmount);
                    JOptionPane.showMessageDialog(null, "Deposited " + df.format(entryAmount) + " to Checking");
                    atmPanel.ioPanel.clearEntry();
                } else {
                    if(entryAmount > 0) {
                        savings.deposit(entryAmount);
                        JOptionPane.showMessageDialog(null, "Deposited " + df.format(entryAmount) + " to Savings");
                        atmPanel.ioPanel.clearEntry();
                    }
                }
            }
        });
        balanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkRadBtn.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Checking balance is: " + df.format(checking.getBalance()));
                } else {
                    JOptionPane.showMessageDialog(null, "Savings balance is: " + df.format(savings.getBalance()));
                }
            }
        });
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double entryAmount = atmPanel.ioPanel.getEntryAmount();
                try {
                    if(checkRadBtn.isSelected()) {
                        savings.transfer(entryAmount, checking);
                        JOptionPane.showMessageDialog(null, "Transferred " + df.format(entryAmount) + " to Checking");
                        atmPanel.ioPanel.clearEntry();
                    } else {
                        checking.transfer(entryAmount, savings);
                        JOptionPane.showMessageDialog(null, "Transferred " + df.format(entryAmount) + " to Savings");
                        atmPanel.ioPanel.clearEntry();
                    }
                } catch (InsufficientFunds ex){}
            }
        });
    }
}
