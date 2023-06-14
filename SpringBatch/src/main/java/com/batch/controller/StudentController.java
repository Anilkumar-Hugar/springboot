package com.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;

	@PostMapping("/student")
	public void importStudent() {
		JobParameters parameters = new JobParametersBuilder().addLong("starts", System.currentTimeMillis())
				.toJobParameters();
		try {
			jobLauncher.run(job, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
