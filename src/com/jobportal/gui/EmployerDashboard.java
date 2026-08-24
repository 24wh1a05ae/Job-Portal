package com.jobportal.gui;

import javax.swing.*;

import com.jobportal.model.User;

public class EmployerDashboard extends JFrame {

    private User user;

    public EmployerDashboard(User user) {

        this.user = user;

        setTitle("Job Portal - Employer Dashboard");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel =
                new JLabel("EMPLOYER DASHBOARD");

        titleLabel.setBounds(160, 30, 250, 30);

        JLabel welcomeLabel =
                new JLabel("Welcome, " + user.getUsername() + "!");

        welcomeLabel.setBounds(180, 65, 200, 30);

        JButton postJobButton =
                new JButton("Post Job");

        postJobButton.setBounds(150, 110, 200, 40);

        JButton viewJobsButton =
                new JButton("View My Jobs");

        viewJobsButton.setBounds(150, 160, 200, 40);

        JButton editJobButton =
                new JButton("Edit Job");

        editJobButton.setBounds(150, 210, 200, 40);

        JButton deleteJobButton =
                new JButton("Delete Job");

        deleteJobButton.setBounds(150, 260, 200, 40);

        JButton logoutButton =
                new JButton("Logout");

        logoutButton.setBounds(150, 310, 200, 40);
        postJobButton.addActionListener(e -> {

            new PostJobFrame(user);

        });

        add(titleLabel);
        add(welcomeLabel);
        add(postJobButton);
        add(viewJobsButton);
        add(editJobButton);
        add(deleteJobButton);
        add(logoutButton);

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame();

        });

        setVisible(true);
    }
}