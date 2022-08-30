

USE employees;
DROP TABLE IF EXISTS PhoneNumbers;
DROP TABLE IF EXISTS EmployeeTable;


CREATE TABLE EmployeeTable(
	id INT NOT NULL AUTO_INCREMENT ,
	first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    personal_id VARCHAR(30) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    nationality VARCHAR(30) NOT NULL,
    salary DECIMAL(15,2) ,
    salary_currency CHAR(3),
    PRIMARY KEY(id)
);


CREATE TABLE PhoneNumbers(
	id INT,
    phone_number varchar(15),
    FOREIGN KEY(id) REFERENCES EmployeeTable(id)
);


