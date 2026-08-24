package com.jobportal.gui;

import javax.swing.*;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;
import com.jobportal.model.User;

public class PostJobFrame extends JFrame {

    private User user;

    public PostJobFrame(User user) {

        this.user = user;

        setTitle("Post New Job");
        setSize(600, 650);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("POST NEW JOB");
        title.setBounds(230, 20, 150, 30);

        JLabel jobTitleLabel = new JLabel("Job Title:");
        jobTitleLabel.setBounds(50, 70, 120, 30);

        JTextField jobTitleField = new JTextField();
        jobTitleField.setBounds(180, 70, 350, 30);

        JLabel companyLabel = new JLabel("Company Name:");
        companyLabel.setBounds(50, 120, 120, 30);

        JTextField companyField = new JTextField();
        companyField.setBounds(180, 120, 350, 30);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(50, 170, 120, 30);

        JTextField locationField = new JTextField();
        locationField.setBounds(180, 170, 350, 30);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(50, 220, 120, 30);

        JTextField salaryField = new JTextField();
        salaryField.setBounds(180, 220, 350, 30);

        JLabel typeLabel = new JLabel("Job Type:");
        typeLabel.setBounds(50, 270, 120, 30);

        JComboBox<String> typeBox =
                new JComboBox<>(
                        new String[]{"Full Time", "Part Time", "Internship"}
                );

        typeBox.setBounds(180, 270, 350, 30);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 320, 120, 30);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);

        JScrollPane descriptionScroll =
                new JScrollPane(descriptionArea);

        descriptionScroll.setBounds(180, 320, 350, 80);

        JLabel skillsLabel = new JLabel("Required Skills:");
        skillsLabel.setBounds(50, 420, 120, 30);

        JTextField skillsField = new JTextField();
        skillsField.setBounds(180, 420, 350, 30);

        JButton postButton = new JButton("Post Job");
        postButton.setBounds(180, 490, 150, 40);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(350, 490, 150, 40);

        add(title);
        add(jobTitleLabel);
        add(jobTitleField);
        add(companyLabel);
        add(companyField);
        add(locationLabel);
        add(locationField);
        add(salaryLabel);
        add(salaryField);
        add(typeLabel);
        add(typeBox);
        add(descriptionLabel);
        add(descriptionScroll);
        add(skillsLabel);
        add(skillsField);
        add(postButton);
        add(cancelButton);

        postButton.addActionListener(e -> {

            String jobTitle = jobTitleField.getText();
            String company = companyField.getText();
            String location = locationField.getText();
            String salary = salaryField.getText();
            String jobType = typeBox.getSelectedItem().toString();
            String description = descriptionArea.getText();
            String skills = skillsField.getText();

            if (jobTitle.isEmpty() ||
                company.isEmpty() ||
                location.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all required fields!"
                );

                return;
            }

            Job job = new Job(
                    user.getUserId(),
                    jobTitle,
                    company,
                    location,
                    salary,
                    jobType,
                    description,
                    skills
            );

            JobDAO jobDAO = new JobDAO();

            boolean success = jobDAO.addJob(job);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Job posted successfully!"
                );

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to post job!"
                );
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}