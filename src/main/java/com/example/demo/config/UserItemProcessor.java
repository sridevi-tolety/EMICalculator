package com.example.demo.config;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.models.User;

public class UserItemProcessor implements ItemProcessor<User ,User>{	
	
		@Override
		  public User process(final User User) throws Exception {
		    final String name = User.getName();
		    final int age = User.getAge();
		    final String pan = User.getPan().toUpperCase();
		    final String aadhar = User.getAadhar();
		    final String gender = User.getGender();

		    final User processedUser = new User(1,name, age,gender ,pan, aadhar);
		    return processedUser;
		  }
}
