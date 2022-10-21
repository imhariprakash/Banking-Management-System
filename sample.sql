-- Active: 1665573499624@@127.0.0.1@3306@customers
/*

CREATE TABLE customer(
    account_number BIGINT NOT NULL AUTO_INCREMENT = 10000000000001;
    customer_id BIGINT NOT NULL AUTO_INCREMENT = 1000000001;
    customer_name VARCHAR(50) NOT NULL;
    customer_father_name VARCHAR(50) NOT NULL;
    ifsc_code VARCHAR(11) NOT NULL;
    customer_address VARCHAR(100) NOT NULL;
    customer_phone_number BIGINT NOT NULL CHECK (customer_phone_number > 1000000000 AND customer_phone_number < 9999999999);
    customer_email VARCHAR(50) NOT NULL;
    
    CONSTRAINT account_number PRIMARY KEY,
)

*/


/*

CREATE TABLE customers.customers(
    customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cust_name VARCHAR(255) NOT NULL,
    father_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone CHAR(10) NOT NULL,
    aadhar CHAR(12) NOT NULL,
    pan CHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL,
    pincode INTEGER CHECK(pincode > 100000 AND pincode < 999999) NOT NULL,
    dob DATE NOT NULL
)AUTO_INCREMENT=1000000001;

ALTER TABLE customers ADD UNIQUE(email, phone, aadhar, pan);

CREATE TABLE customers.accounts(
    customer_id INT NOT NULL PRIMARY KEY,
    account_number BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers.customers(customer_id),
    UNIQUE (account_number, customer_id)
);
ALTER TABLE accounts ADD COLUMN ifsc_code VARCHAR(11) NOT NULL;
ALTER TABLE accounts AUTO_INCREMENT = 100000000000001;
ALTER TABLE accounts ADD CHECK(account_number > 10000000000000 AND account_number < 99999999999999);

CREATE TABLE customers.cards(
    card_number INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT NOT NULL,
    card_type VARCHAR(255) NOT NULL,
    expiry_date DATE NOT NULL,
    cvv INTEGER NOT NULL CHECK(cvv > 100 AND cvv < 999),
    pin INTEGER NOT NULL CHECK(pin > 1000 AND pin < 9999),
    FOREIGN KEY (account_number) REFERENCES customers.accounts(account_number)
);


CREATE TABLE customers.loans(
    loan_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT NOT NULL,
    loan_type VARCHAR(255) NOT NULL,
    loan_amount INT NOT NULL,
    loan_date DATE NOT NULL,
    FOREIGN KEY (account_number) REFERENCES customers.accounts(account_number)
);

CREATE TABLE customers.chequebooks(
    chequebook_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT NOT NULL,
    chequebook_type VARCHAR(255) NOT NULL,
    chequebook_date DATE NOT NULL,
    FOREIGN KEY (account_number) REFERENCES customers.accounts(account_number)
);

CREATE TABLE customers.cheques(
    cheque_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    chequebook_id INT NOT NULL,
    cheque_number INT NOT NULL,
    cheque_date DATE NOT NULL,
    FOREIGN KEY (chequebook_id) REFERENCES customers.chequebooks(chequebook_id)
);

CREATE TABLE customers.balance(
    account_number BIGINT NOT NULL PRIMARY KEY,
    balance BIGINT NOT NULL,
    FOREIGN KEY (account_number) REFERENCES customers.accounts(account_number)
);

CREATE TABLE transactions.transactions(
    transaction_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT NOT NULL,
    transaction_type VARCHAR(255) NOT NULL,
    transaction_source VARCHAR(255) NOT NULL,
    transaction_amount INT NOT NULL,
    transaction_datetime DATETIME NOT NULL,
    FOREIGN KEY (account_number) REFERENCES customers.accounts(account_number)
)AUTO_INCREMENT=100000000000001;

CREATE TABLE customers.deposits(
    deposit_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT NOT NULL,
    deposit_type VARCHAR(255) NOT NULL,
    deposit_amount INT NOT NULL,
    deposit_date DATE NOT NULL,
    FOREIGN KEY (account_number) REFERENCES customers.accounts(account_number)
);

CREATE TABLE credit(
    credit_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT NOT NULL,
    credit_source VARCHAR(255) NOT NULL,
    credit_amount INT NOT NULL,
    credit_date DATE NOT NULL,
    FOREIGN KEY (account_number) REFERENCES customers.accounts(account_number)
)AUTO_INCREMENT=100000000000001;

ALTER TABLE credit
ADD COLUMN credit_source_id VARCHAR(255) NOT NULL;


CREATE TABLE debit(
    debit_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_number BIGINT NOT NULL,
    debit_source VARCHAR(255) NOT NULL,
    debit_amount INT NOT NULL,
    debit_date DATE NOT NULL,
    FOREIGN KEY (account_number) REFERENCES customers.accounts(account_number)
)AUTO_INCREMENT=100000000000001;

ALTER TABLE debit
ADD COLUMN debit_source_id VARCHAR(255) NOT NULL;

ALTER TABLE transactions
ADD COLUMN transaction_source_id VARCHAR(255) NOT NULL;

*/


/*

CREATE TABLE applications(
    cust_name VARCHAR(255) NOT NULL,
    father_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone CHAR(10) NOT NULL,
    aadhar CHAR(12) NOT NULL,
    pan CHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL,
    pincode CHAR(6) NOT NULL,
    dob DATE NOT NULL,
    notes VARCHAR(255) NOT NULL,
    PRIMARY KEY(email)
);


CREATE TABLE applications(
    cust_name VARCHAR(255) NOT NULL,
    father_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone CHAR(10) NOT NULL,
    aadhar CHAR(12) NOT NULL,
    pan CHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL,
    pincode CHAR(6) NOT NULL,
    dob DATE NOT NULL,
    notes VARCHAR(255),
    verified_by VARCHAR(255),
    verified_on DATETIME,
    review VARCHAR(1000),
    PRIMARY KEY(email)
);

*/


-- Active: 1665573212160@@127.0.0.1@3306@otp

/*
CREATE TABLE email_otp(
    email VARCHAR(255) NOT NULL,
    otp INT CHECK (otp >= 100000 AND otp <= 999999),
    time_created DATETIME DEFAULT NOW(),
    PRIMARY KEY (email, otp)
);

INSERT INTO email_otp (email,otp)
VALUES ('mail1@gmail.com', 123456),
       ('mail2@gmail.com', 234567);


CREATE TABLE email_otp_log(
    email VARCHAR(255) NOT NULL,
    otp INT CHECK (otp >= 100000 AND otp <= 999999),
    time_created DATETIME DEFAULT NOW(),
    time_verified DATETIME,
    PRIMARY KEY (email, otp)
);

CREATE TABLE mobile_otp (
    mobile CHAR(10) NOT NULL,
    otp INT CHECK (otp >= 100000 AND otp <= 999999),
    time_created DATETIME DEFAULT NOW(),
    PRIMARY KEY (mobile, otp)
);

ALTER TABLE email_otp_log DROP PRIMARY KEY;
ALTER TABLE email_otp_log ADD PRIMARY KEY (email,time_created);

ALTER TABLE email_otp DROP PRIMARY KEY;
ALTER TABLE email_otp ADD PRIMARY KEY (email,time_created);

ALTER TABLE mobile_otp DROP PRIMARY KEY;
ALTER TABLE mobile_otp ADD PRIMARY KEY (mobile,time_created);

ALTER TABLE mobile_otp_log DROP PRIMARY KEY;
ALTER TABLE mobile_otp_log ADD PRIMARY KEY (mobile,time_created);

CREATE TABLE mobile_otp_log(
    mobile CHAR(10) NOT NULL,
    otp INT CHECK (otp >= 100000 AND otp <= 999999),
    time_created DATETIME DEFAULT now(),
    time_verified DATETIME,
    PRIMARY KEY (mobile, otp)
);

CREATE TABLE mail_credentials(
    email VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

INSERT INTO mail_credentials (email, password)
VALUES ('abcb@gmail.com', 'ab');


CREATE TABLE twilio_credentials(
    account_sid VARCHAR(255) NOT NULL PRIMARY KEY,
    auth_token VARCHAR(255) NOT NULL
);

INSERT INTO twilio_credentials (account_sid, auth_token)
VALUES ('ACd55fb54126aaf05423', 'd20f261b2c6d63');


*/

-- Active: 1665644127355@@127.0.0.1@3306@account_opening

/*
CREATE TABLE account_opening_requests(
    cust_name VARCHAR(255) NOT NULL,
    cust_father_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone CHAR(10) NOT NULL,
    aadhar CHAR(12) NOT NULL,
    pan CHAR(10) NOT NULL,
    res_address VARCHAR(255) NOT NULL,
    pincode CHAR(6) NOT NULL,
    dob DATE NOT NULL,
    pdf MEDIUMBLOB NOT NULL,
    notes VARCHAR(255)
);


CREATE TABLE netbanking (
    customer_id INTEGER NOT NULL PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);



CREATE TABLE employee (
    employee_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    email VARCHAR(50),
    phone VARCHAR(50),
    hire_date DATE NOT NULL,
    role VARCHAR(50),
    ifsc_code CHAR(11), 
    blood_group VARCHAR(10),
    address VARCHAR(100)
)AUTO_INCREMENT=10000001;


CREATE TABLE salary_details(
    emp_id INT NOT NULL,
    account_no INT NOT NULL,
    ifsc_code VARCHAR(11) NOT NULL,
    PRIMARY KEY(emp_id),
    FOREIGN KEY(emp_id) REFERENCES employee(employee_id)
);


CREATE TABLE admin (
    employee_id INT NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

CREATE TABLE atm_deposit_rollbacks(
    account_number BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    t_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (account_number, t_time)
);

CREATE TABLE atm_withdraw_rollbacks(
    account_number BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    t_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (account_number, t_time)
);

CREATE TABLE netbanking_withdraw_rollbacks(
    account_number BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    t_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (account_number, t_time)
);

CREATE TABLE netbanking_deposit_rollbacks(
    account_number BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    t_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (account_number, t_time)
);


CREATE TABLE applications_log(
    cust_name VARCHAR(255) NOT NULL,
    father_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone CHAR(10) NOT NULL,
    aadhar CHAR(12) NOT NULL,
    pan CHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL,
    pincode CHAR(6) NOT NULL,
    dob DATE NOT NULL,
    notes VARCHAR(255),
    verified_by VARCHAR(255),
    verified_on DATETIME,
    review VARCHAR(1000),
    applied_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` VARCHAR(12)
);



CREATE TABLE otp(
    otp INTEGER CHECK (otp >= 100000 AND otp <= 999999),
    email VARCHAR(255) NOT NULL
);



CREATE TABLE otp_log(
    otp INTEGER CHECK (otp >= 100000 AND otp <= 999999),
    email VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

*/



uuidrandomijgene







