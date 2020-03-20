package com.example.demo.comtroller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EMIController {
	
	@Autowired
	JobLauncher  launcher;
	
	@Autowired
	Job job;

}
