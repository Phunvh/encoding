package com.example.encoding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Main {
    private static JFrame frame;
    private static JPanel panel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        frame = new JFrame("User Registration and Login");
        panel = new JPanel();
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);

        panel.add(createRegisterPanel(), "Register");
        panel.add(createLoginPanel(), "Login");
        panel.add(createWelcomePanel(), "Welcome");

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        cardLayout.show(panel, "Register");
    }

    private static JPanel createRegisterPanel() {
        JPanel registerPanel = new JPanel(new GridLayout(4, 2));
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();
        JButton registerButton = new JButton("Register");
        JButton switchToLoginButton = new JButton("Switch to Login");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AuthService.register(userText.getText(), new String(passwordText.getPassword()));
                    JOptionPane.showMessageDialog(frame, "Registration Successful");
                    cardLayout.show(panel, "Login");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        switchToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "Login");
            }
        });

        registerPanel.add(userLabel);
        registerPanel.add(userText);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordText);
        registerPanel.add(registerButton);
        registerPanel.add(switchToLoginButton);

        return registerPanel;
    }

    private static JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(4, 2));
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton switchToRegisterButton = new JButton("Switch to Register");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (AuthService.login(userText.getText(), new String(passwordText.getPassword()))) {
                        JOptionPane.showMessageDialog(frame, "Login Successful");
                        cardLayout.show(panel, "Welcome");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        switchToRegisterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "Register");
            }
        });

        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);
        loginPanel.add(loginButton);
        loginPanel.add(switchToRegisterButton);

        return loginPanel;
    }

    private static JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomePanel.add(welcomeLabel);
        return welcomePanel;
    }
}
