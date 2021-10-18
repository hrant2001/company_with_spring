CREATE SCHEMA IF NOT EXISTS company;

CREATE TABLE IF NOT EXISTS positions (
    position_id TINYINT PRIMARY KEY AUTO_INCREMENT,
    position_name VARCHAR(50) NOT NULL,
    position_short_name VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS department (
     department_id TINYINT PRIMARY KEY AUTO_INCREMENT,
     department_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    position_id TINYINT NOT NULL,
    department_id TINYINT NOT NULL,
    enabled BOOLEAN NOT NULL,
    FOREIGN KEY (position_id) REFERENCES positions(position_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);

CREATE TABLE IF NOT EXISTS attendance_record (
     record_id INT PRIMARY KEY AUTO_INCREMENT,
     entrance_time DATETIME,
     exit_time DATETIME,
     employee_id INT NOT NULL,
     FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

CREATE TABLE IF NOT EXISTS user (
     username VARCHAR(45) PRIMARY KEY,
     password VARCHAR(200) NOT NULL,
     employee_id INT NOT NULL,
     role_id TINYINT NOT NULL,
     FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
     FOREIGN KEY (role_id) REFERENCES role(role_id)
);

CREATE TABLE IF NOT EXISTS role(
    role_id TINYINT primary key auto_increment,
    role_name varchar(11)
);

INSERT INTO role(role_name) VALUES('ROLE_ADMIN'),('ROLE_USER');

INSERT INTO positions(position_name, position_short_name)
VALUES ('Chief Executive Officer', 'ceo'), ('Human Resource Manager', 'hr_manager'), ('Human Resource', 'hr'), ('Team Lead', 'teamlead'),
       ('Senior Developer', 'senior'), ('Mid Developer', 'mid'), ('Junior Developer', 'junior'), ('Intern Developer', 'intern');

INSERT INTO department(department_name)
VALUES ('Management'), ('HR'), ('IT');

INSERT INTO user VALUES ('user1', '$2a$12$em1JvaMBjCGNcBRfKIVu1OIk4kaD11rIldkpSjgVhanwqpn3Hl/qa', 18, 1),
                        ('user2', '$2a$12$YvQ.3gOp30W1mytxwdoMHuXHJKf3uU2J5/WkQ8LGD.F1Kf/phvGF2', 20, 2);