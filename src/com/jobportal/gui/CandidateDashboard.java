package com.jobportal.gui;

import javax.swing.*;

import com.jobportal.model.User;

public class CandidateDashboard extends JFrame {

    private User user;

    public CandidateDashboard(User candidate) {

        this.user = candidate;

        setTitle("Job Portal - Candidate Dashboard");

        setSize(500, 400);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // TITLE

        JLabel titleLabel =
                new JLabel("CANDIDATE DASHBOARD");

        titleLabel.setBounds(150, 30, 250, 30);

        // WELCOME

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, " +
                        user.getUsername() +
                        "!"
                );

        welcomeLabel.setBounds(
                180, 65, 200, 30
        );

        // VIEW JOBS

        JButton viewJobsButton =
                new JButton("View Jobs");

        viewJobsButton.setBounds(
                150, 110, 200, 40
        );

        viewJobsButton.addActionListener(e -> {

            new ViewJobsFrame(candidate);

        });

        // SEARCH JOBS

        JButton searchJobsButton =
                new JButton("Search Jobs");

        searchJobsButton.setBounds(
                150, 160, 200, 40
        );

        searchJobsButton.addActionListener(e -> {

            new ViewJobsFrame(candidate);

        });

        // MY APPLICATIONS

        JButton applicationsButton =
                new JButton("My Applications");

        applicationsButton.setBounds(
                150, 210, 200, 40
        );
        applicationsButton.addActionListener(e -> {
            new MyApplicationsFrame(candidate);
        });

        // LOGOUT

        JButton logoutButton =
                new JButton("Logout");

        logoutButton.setBounds(
                150, 270, 200, 40
        );

        // ADD COMPONENTS

        add(titleLabel);

        add(welcomeLabel);

        add(viewJobsButton);

        add(searchJobsButton);

        add(applicationsButton);

        add(logoutButton);

        // LOGOUT ACTION

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame();

        });

        setVisible(true);
    }
}