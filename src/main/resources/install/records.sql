CREATE TABLE IF NOT EXISTS attendance_record (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    entrance_time DATETIME NOT NULL,
    exit_time DATETIME NOT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

INSERT INTO attendance_record(entrance_time, exit_time, employee_id)
VALUES ('2021-07-29 09:58:00','2021-07-29 14:20:00',18), ('2021-07-29 09:59:20','2021-07-29 18:20:00',20);