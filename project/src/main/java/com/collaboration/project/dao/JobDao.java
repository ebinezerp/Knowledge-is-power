package com.collaboration.project.dao;

import java.util.List;

import com.collaboration.project.model.Job;

public interface JobDao {
 boolean postJob(Job job);
 boolean updateJob(Job job);
 boolean deleteJob(Job job);
 Job getJob(Integer jobId);
 List<Job> getAllJobs();
}
