package com.jobportal.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jobportal.dao.ApplicationDAO;
import com.jobportal.dao.JobDAO;
import com.jobportal.model.Application;
import com.jobportal.model.Job;
import com.jobportal.model.User;

public class ViewJobsFrame extends JFrame {

    private User candidate;
    private JobDAO jobDAO;
    private JPanel mainPanel;

    public ViewJobsFrame(User candidate) {

        this.candidate = candidate;
        this.jobDAO = new JobDAO();

        setTitle("Available Jobs");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // SEARCH PANEL
        JPanel searchPanel = new JPanel(new FlowLayout());

        JTextField searchField = new JTextField(25);

        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");

        searchPanel.add(new JLabel("Search Jobs:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        add(searchPanel, BorderLayout.NORTH);

        // JOB PANEL
        mainPanel = new JPanel();

        mainPanel.setLayout(
                new BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        );

        JScrollPane scrollPane = new JScrollPane(mainPanel);

        add(scrollPane, BorderLayout.CENTER);

        // LOAD ALL JOBS
        displayJobs(jobDAO.getAllJobs());

        // SEARCH BUTTON
        searchButton.addActionListener(e -> {

            String keyword = searchField.getText().trim();

            if (keyword.isEmpty()) {

                displayJobs(jobDAO.getAllJobs());

            } else {

                displayJobs(jobDAO.searchJobs(keyword));
            }
        });

        // SHOW ALL BUTTON
        showAllButton.addActionListener(e -> {

            searchField.setText("");

            displayJobs(jobDAO.getAllJobs());
        });

        setVisible(true);
    }

    // DISPLAY JOBS
    private void displayJobs(List<Job> jobs) {

        mainPanel.removeAll();

        if (jobs.isEmpty()) {

            mainPanel.add(
                    new JLabel("No jobs found.")
            );

        } else {

            for (Job job : jobs) {

                JPanel jobPanel = new JPanel();

                jobPanel.setLayout(
                        new BoxLayout(
                                jobPanel,
                                BoxLayout.Y_AXIS
                        )
                );

                jobPanel.setBorder(
                        BorderFactory.createTitledBorder(
                                job.getJobTitle()
                        )
                );

                jobPanel.add(
                        new JLabel(
                                "Company: "
                                + job.getCompanyName()
                        )
                );

                jobPanel.add(
                        new JLabel(
                                "Location: "
                                + job.getLocation()
                        )
                );

                jobPanel.add(
                        new JLabel(
                                "Salary: "
                                + job.getSalary()
                        )
                );

                jobPanel.add(
                        new JLabel(
                                "Job Type: "
                                + job.getJobType()
                        )
                );

                jobPanel.add(
                        new JLabel(
                                "Required Skills: "
                                + job.getRequiredSkills()
                        )
                );

                jobPanel.add(
                        new JLabel(
                                "Description: "
                                + job.getDescription()
                        )
                );

                // APPLY BUTTON
                JButton applyButton =
                        new JButton("Apply");

                jobPanel.add(applyButton);

                applyButton.addActionListener(e -> {

                    Application application =
                            new Application(
                                    job.getJobId(),
                                    candidate.getUserId()
                            );

                    ApplicationDAO applicationDAO =
                            new ApplicationDAO();

                    boolean applied =
                            applicationDAO.applyForJob(application);

                    if (applied) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Application submitted successfully!"
                        );

                        applyButton.setEnabled(false);

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Failed to apply for the job."
                        );
                    }
                });

                mainPanel.add(jobPanel);

                mainPanel.add(
                        Box.createVerticalStrut(10)
                );
            }
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}