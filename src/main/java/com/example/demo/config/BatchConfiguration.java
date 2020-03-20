package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.models.User;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	  @Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	  
	  @Autowired
	  DataSource datasource;
	  
	  @Bean
	  public FlatFileItemReader<User> reader() {
	    return new FlatFileItemReaderBuilder<User>()
	      .name("UserItemReader")
	      .resource(new ClassPathResource("inputdata.csv"))
	      .delimited()
	      .names(new String[]{"name","age","gender","pan","aadhar"})
	      .fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
	        setTargetType(User.class);
	      }})
	      .build();
	  }

	  @Bean
	  public UserItemProcessor processor() {
	    return new UserItemProcessor();
	  }

	  @Bean
	  public JdbcBatchItemWriter<User> writer(DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<User>()
	      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	      .sql("INSERT INTO user (name, age, gender , pan ,aadhar) VALUES (:name, :age, :gender, :pan ,:aadhar)")
	      .dataSource(dataSource)
	      .build();
	  }
	  
	  @Bean
	  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
	    return jobBuilderFactory.get("importUserJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	  }

	  @Bean
	  public Step step1(JdbcBatchItemWriter<User> writer) {
	    return stepBuilderFactory.get("step1")
	      .<User, User> chunk(10)
	      .reader(reader())
	      .processor(processor())
	      .writer(writer)
	      .build();
	  }

	
}
