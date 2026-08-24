package com.jobportal.model;

public class Application {

    private int applicationId;
    private int jobId;
    private int candidateId;
    private String status;

    public Application() {
    }

    public Application(int jobId, int candidateId) {

        this.jobId = jobId;
        this.candidateId = candidateId;
        this.status = "Applied";
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}