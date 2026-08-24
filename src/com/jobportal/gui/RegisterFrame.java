package com.jobportal.gui;

import javax.swing.*;

import com.jobportal.dao.UserDAO;
import com.jobportal.model.User;

public class RegisterFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    private UserDAO userDAO;

    public RegisterFrame() {

        userDAO = new UserDAO();

        setTitle("Job Portal - Register");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("CREATE ACCOUNT");
        titleLabel.setBounds(130, 30, 200, 30);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 80, 100, 30);

        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 180, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 180, 30);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(50, 160, 100, 30);

        String[] roles = {
                "CANDIDATE",
                "EMPLOYER"
        };

        roleBox = new JComboBox<>(roles);
        roleBox.setBounds(150, 160, 180, 30);

        JButton registerButton =
                new JButton("Register");

        registerButton.setBounds(130, 220, 120, 35);

        add(titleLabel);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(roleLabel);
        add(roleBox);
        add(registerButton);

        registerButton.addActionListener(e -> register());

        setVisible(true);
    }

    private void register() {

        String username =
                usernameField.getText();

        String password =
                new String(passwordField.getPassword());

        String role =
                roleBox.getSelectedItem().toString();

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!"
            );

            return;
        }

        User user =
                new User(username, password, role);

        boolean success =
                userDAO.registerUser(user);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration successful!"
            );

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration failed! Username may already exist."
            );
        }
    }
}