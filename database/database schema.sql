/*CREATE DATABASE back2u;*/

USE back2u;

/*CREATE TABLE accountdetails(user_id int auto_increment primary key ,name varchar(100), student_id varchar(20), email varchar(50),phone varchar(10), password varchar(50));*/

show tables;

SELECT * from accountdetails;

/*drop table items;*/


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
    unique_details VARCHAR(500),

    CONSTRAINT fk_items_user
        FOREIGN KEY (user_id)
        REFERENCES accountdetails(user_id)
);*/

SELECT * FROM items;
CREATE DATABASE IF NOT EXISTS back2u;
USE back2u;

/*CREATE TABLE IF NOT EXISTS handover (
    handover_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(100),
    category VARCHAR(50),
    description VARCHAR(255),
    found_date DATE,
    found_location VARCHAR(100),
    owner_name VARCHAR(100),
    reg_no VARCHAR(30),
    phone VARCHAR(15),
    meeting_location VARCHAR(100),
    meeting_date DATE,
    meeting_time VARCHAR(20),
    pin VARCHAR(6),
    status VARCHAR(20) DEFAULT 'PENDING',
    returned_at DATETIME
);

INSERT INTO handover (
    item_name, category, description, found_date,
    found_location, owner_name, reg_no, phone,
    meeting_location, meeting_date, meeting_time, pin
) VALUES (
    'Black Wireless Earbuds', 'Electronics',
    'Wireless earbuds with charging case',
    '2026-10-12', 'Main Block',
    'Archa S', '22IT3456', '9876543210',
    'Main Block (Entrance)', '2026-10-16',
    '3:30 PM', '478291'
);*/

/*CREATE TABLE admin_reports (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    issue_type VARCHAR(100) NOT NULL,
    subject VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(30) DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES accountdetails(user_id)
);*/

SELECT * from admin_reports;
