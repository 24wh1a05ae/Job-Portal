package com.jobportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.database.DBConnection;
import com.jobportal.model.Job;

public class JobDAO {

    // ADD JOB
    public boolean addJob(Job job) {

        String sql = "INSERT INTO jobs "
                + "(employer_id, job_title, company_name, location, "
                + "salary, job_type, description, required_skills) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, job.getEmployerId());
            statement.setString(2, job.getJobTitle());
            statement.setString(3, job.getCompanyName());
            statement.setString(4, job.getLocation());
            statement.setString(5, job.getSalary());
            statement.setString(6, job.getJobType());
            statement.setString(7, job.getDescription());
            statement.setString(8, job.getRequiredSkills());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // GET ALL JOBS
    public List<Job> getAllJobs() {

        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT * FROM jobs";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Job job = new Job();

                job.setJobId(resultSet.getInt("job_id"));
                job.setEmployerId(resultSet.getInt("employer_id"));
                job.setJobTitle(resultSet.getString("job_title"));
                job.setCompanyName(resultSet.getString("company_name"));
                job.setLocation(resultSet.getString("location"));
                job.setSalary(resultSet.getString("salary"));
                job.setJobType(resultSet.getString("job_type"));
                job.setDescription(resultSet.getString("description"));
                job.setRequiredSkills(
                        resultSet.getString("required_skills")
                );

                jobs.add(job);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return jobs;
    }
    public List<Job> searchJobs(String keyword) {

        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT * FROM jobs "
                   + "WHERE job_title LIKE ? "
                   + "OR company_name LIKE ? "
                   + "OR location LIKE ? "
                   + "OR required_skills LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String search = "%" + keyword + "%";

            statement.setString(1, search);
            statement.setString(2, search);
            statement.setString(3, search);
            statement.setString(4, search);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Job job = new Job();

                job.setJobId(resultSet.getInt("job_id"));
                job.setEmployerId(resultSet.getInt("employer_id"));
                job.setJobTitle(resultSet.getString("job_title"));
                job.setCompanyName(resultSet.getString("company_name"));
                job.setLocation(resultSet.getString("location"));
                job.setSalary(resultSet.getString("salary"));
                job.setJobType(resultSet.getString("job_type"));
                job.setDescription(resultSet.getString("description"));
                job.setRequiredSkills(
                        resultSet.getString("required_skills")
                );

                jobs.add(job);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return jobs;
    }
}