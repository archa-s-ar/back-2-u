/*CREATE DATABASE back2u;*/

USE back2u;

/*CREATE TABLE accountdetails(user_id int auto_increment primary key ,name varchar(100), student_id varchar(20), email varchar(50),phone varchar(10), password varchar(50));*/

show tables;

SELECT * from accountdetails;

/*CREATE TABLE items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    report_type ENUM('LOST','FOUND'),
    item_name VARCHAR(100),
    category VARCHAR(50),
    description VARCHAR(500),
    date_occurred DATE,
    time_occurred VARCHAR(50),
    location VARCHAR(200),
    status VARCHAR(30) DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    unique_details VARCHAR(500)
);*/

SELECT * FROM items;
