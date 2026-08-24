package com.jobportal.gui;

import javax.swing.*;

import com.jobportal.dao.UserDAO;
import com.jobportal.model.User;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private UserDAO userDAO;

    public LoginFrame() {

        userDAO = new UserDAO();

        setTitle("Job Portal - Login");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("JOB PORTAL LOGIN");
        titleLabel.setBounds(120, 30, 200, 30);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 80, 100, 30);

        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 180, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 180, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(80, 180, 100, 35);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 180, 100, 35);

        add(titleLabel);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(e -> login());

        registerButton.addActionListener(e -> {

            new RegisterFrame();

        });

        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText();

        String password =
                new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password!"
            );

            return;
        }

        User user =
                userDAO.loginUser(username, password);

        if (user != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!"
            );

            dispose();

            if (user.getRole().equalsIgnoreCase("CANDIDATE")) {

                new CandidateDashboard(user);

            } else if (user.getRole().equalsIgnoreCase("EMPLOYER")) {

                new EmployerDashboard(user);
            }

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password!"
            );
        }
    }
}