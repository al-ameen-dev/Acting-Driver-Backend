package com.alameendev.ActingDriver.job.repository;

import com.alameendev.ActingDriver.job.entity.Job;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPagingRepository extends PagingAndSortingRepository<Job,Long> {
}
