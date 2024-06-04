package com.alameendev.ActingDriver.job.service;

import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.exceptions.job.JobNotFoundException;
import com.alameendev.ActingDriver.job.dto.JobResponseDTO;
import com.alameendev.ActingDriver.job.dto.JobUpdateDTO;
import com.alameendev.ActingDriver.job.entity.Job;
import com.alameendev.ActingDriver.job.repository.JobPagingRepository;
import com.alameendev.ActingDriver.job.repository.JobRepository;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobPagingRepository jobPagingRepository;
    private final UserService userService;
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Override
    public List<JobResponseDTO> retriveAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map((job)->modelMapper.map(job,JobResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobResponseDTO> retrieveAllJobByPaging(Integer page, Integer size) {
        Pageable customPage = PageRequest.of(page,size);
        Page<Job> jobs = jobPagingRepository.findAll(customPage);
        return jobs.stream().map(job->modelMapper.map(job,JobResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public JobResponseDTO createJob(Job body) {
        User user = userService.getUser();
        Client client = clientService.retrieveClientByUser(user);
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String inputString = "2021-07-01";
//        LocalDate date = LocalDate.parse(inputString, inputFormatter);
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String outputString = date.format(outputFormatter);
        Job job = Job.builder()
                .client(client)
                .jobStatus(body.getJobStatus())
                .date(body.getDate())
                .time(body.getTime())
                .description(body.getDescription())
                .requirements(body.getRequirements())
                .location(body.getLocation())
                .title(body.getTitle())
                .build();
        jobRepository.save(job);
        return modelMapper.map(job,JobResponseDTO.class);
    }

    @Override
    public List<JobResponseDTO> retriveAllJobForClient(Client client) {
        List<Job> listJobs = jobRepository.findByClient(client);
        List<JobResponseDTO> jobs = listJobs.stream().map(job -> modelMapper.map(job,JobResponseDTO.class)).collect(Collectors.toList());
        return jobs;
    }

    @Override
    public JobResponseDTO retriveJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException(id));
        return modelMapper.map(job,JobResponseDTO.class);
    }

    @Override
    public JobResponseDTO updateJobById(Long id, JobUpdateDTO body) {
        Job job = jobRepository.findById(id).orElseThrow(()->new JobNotFoundException(id));
        job.setJobStatus(body.getJobStatus());
        job.setDate(LocalDate.parse(body.getDate()));
        job.setTime(LocalTime.parse(body.getTime()));
        job.setDescription(body.getDescription());
        job.setLocation(body.getLocation());
        job.setRequirements(body.getRequirements());
        job.setTitle(body.getTitle());
        jobRepository.save(job);
        return modelMapper.map(job,JobResponseDTO.class);
    }

    @Override
    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

}
