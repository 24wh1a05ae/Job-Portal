package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.database.DBConnection;
import com.jobportal.model.Application;

public class ApplicationDAO {

    // APPLY FOR A JOB
    public boolean applyForJob(Application application) {

        String sql =
                "INSERT INTO applications " +
                "(job_id, candidate_id, status) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, application.getJobId());
            statement.setInt(2, application.getCandidateId());
            statement.setString(3, application.getStatus());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // GET APPLICATIONS OF A CANDIDATE
    public List<Application> getApplicationsByCandidate(int candidateId) {

        List<Application> applications = new ArrayList<>();

        String sql =
                "SELECT * FROM applications " +
                "WHERE candidate_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, candidateId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Application application =
                        new Application();

                application.setApplicationId(
                        resultSet.getInt("application_id")
                );

                application.setJobId(
                        resultSet.getInt("job_id")
                );

                application.setCandidateId(
                        resultSet.getInt("candidate_id")
                );

                application.setStatus(
                        resultSet.getString("status")
                );

                applications.add(application);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return applications;
    }
}