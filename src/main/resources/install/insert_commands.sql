INSERT INTO role(role_name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO positions(position_name, position_short_name)
VALUES ('Chief Executive Officer', 'ceo'),
       ('Human Resource Manager', 'hr_manager'),
       ('Human Resource', 'hr'),
       ('Team Lead', 'teamlead'),
       ('Senior Developer', 'senior'),
       ('Mid Developer', 'mid'),
       ('Junior Developer', 'junior'),
       ('Intern Developer', 'intern');

INSERT INTO department(department_name)
VALUES ('Management'),
       ('HR'),
       ('IT');

INSERT INTO employee(fname, lname, birthday, email, position_id, department_id)
VALUES ('Hrant', 'Arakelyan', '2001-12-03', 'hrant@mail.am', 7, 3),
       ('Argisht', 'Tigranyan', '2001-02-09', 'argisht@mail.am', 6, 3);

INSERT INTO user(username, password, employee_id, role_id)
VALUES ('user1', '$2a$12$em1JvaMBjCGNcBRfKIVu1OIk4kaD11rIldkpSjgVhanwqpn3Hl/qa', 1, 1),
       ('user2', '$2a$12$YvQ.3gOp30W1mytxwdoMHuXHJKf3uU2J5/WkQ8LGD.F1Kf/phvGF2', 2, 2);

INSERT INTO attendance_record(entrance_time, exit_time, employee_id)
VALUES ('2021-07-29 09:58:00', '2021-07-29 14:20:00', 1),
       ('2021-07-29 09:59:20', '2021-07-29 18:20:00', 2);
