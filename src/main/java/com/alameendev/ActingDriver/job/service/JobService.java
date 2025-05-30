package com.alameendev.ActingDriver.job.service;

import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.job.dto.JobResponseDTO;
import com.alameendev.ActingDriver.job.dto.JobUpdateDTO;
import com.alameendev.ActingDriver.job.entity.Job;

import java.util.List;

public interface JobService {

    List<JobResponseDTO> retriveAllJobs();

    List<JobResponseDTO> retriveAllJobForClient(Client client);

    JobResponseDTO retriveJobById(Long id);

    JobResponseDTO updateJobById(Long id, JobUpdateDTO body);

    void deleteJobById(Long id);

    JobResponseDTO createJob(JobResponseDTO body);

    List<JobResponseDTO> retrieveAllJobByPaging(Integer page, Integer size);

    JobResponseDTO createJobByClientId(Long id, JobResponseDTO body);

    Job retrieveJobEntityById(Long id);
}
