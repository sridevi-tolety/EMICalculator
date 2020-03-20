package com.example.demo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LoanDetails {
	@Id
	@GeneratedValue
	private long id;
	private long userid;
	private long loanamount;
	private int durationyears;
	private long interestrate;
	private Date loanstartdate;
	private Date loanenddate;
	private long emi;

}
