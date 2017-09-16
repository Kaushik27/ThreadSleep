package com.knight.thread.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HitController {

	@Autowired
	Job job;

	@Autowired
	JobLauncher jobLauncher;

	@GetMapping("hit")
	public String hitMe(@RequestParam("id") String id) throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		System.out.println("Hit from ID :" + id);
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("myJob", String.valueOf(System.currentTimeMillis())).toJobParameters();
		jobLauncher.run(job, jobParameters);
		return "Thanks for hitting";
	}

}
