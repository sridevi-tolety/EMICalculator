DROP TABLE IF EXISTS user;
 
CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age number NOT NULL,
  gender VARCHAR(250) NOT NULL,
  pan VARCHAR(250) NOT NULL,
  aadhar VARCHAR(250) NOT NULL
);
 

DROP TABLE IF EXISTS loandetails;
 
CREATE TABLE loandetails (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  userid number(250) NOT NULL,
  loanamount number NOT NULL,
  durationyears number NOT NULL,
  interestrate number NOT NULL,
  loanstartdate datetime not null,
  loadenddate datetime not null,
  emi number not null,
  CONSTRAINT `loandetails_fk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)

);


