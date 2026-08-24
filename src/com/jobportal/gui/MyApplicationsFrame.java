package com.jobportal.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jobportal.dao.ApplicationDAO;
import com.jobportal.model.Application;
import com.jobportal.model.User;

public class MyApplicationsFrame extends JFrame {

    private User candidate;
    private ApplicationDAO applicationDAO;

    public MyApplicationsFrame(User candidate) {

        this.candidate = candidate;
        this.applicationDAO = new ApplicationDAO();

        setTitle("My Applications");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {
                "Application ID",
                "Job ID",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        List<Application> applications =
                applicationDAO.getApplicationsByCandidate(
                        candidate.getUserId()
                );

        for (Application application : applications) {

            model.addRow(new Object[] {
                    application.getApplicationId(),
                    application.getJobId(),
                    application.getStatus()
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }
}