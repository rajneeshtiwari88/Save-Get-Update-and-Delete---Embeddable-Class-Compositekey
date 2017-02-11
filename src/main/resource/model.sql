
    drop table if exists employee;

    create table employee (
       emp_name varchar(25) not null,
        id varchar(255) not null,
        employee_age integer,
        employee_salary decimal(19,2),
        primary key (emp_name, id)
    );
