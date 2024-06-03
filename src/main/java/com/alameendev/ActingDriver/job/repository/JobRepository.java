package com.alameendev.ActingDriver.job.repository;

import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByClient(Client client);
}
