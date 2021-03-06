package com.example.demo.config;



import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {


	  private final JdbcTemplate jdbcTemplate;

	  @Autowired
	  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	  }

	  @Override
	  public void afterJob(JobExecution jobExecution) {
	    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	    

	      /*jdbcTemplate.query("SELECT * FROM user",
	        (rs, row) -> new User(
	          rs.getLong(1),
	          rs.getString(2))
	      ).forEach(person -> System.out.println("Found <" + person + "> in the database."));*/
	    }
	  }

}
