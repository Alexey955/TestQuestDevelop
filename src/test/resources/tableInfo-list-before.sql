delete from employee;
delete from chief;
delete from admin;
delete from department;

insert into department (id, department_name) values
(10, 'DepartmentOne'),
(30, 'DepartmentTwo');

insert into employee (id, first_name, last_name, department_id) values
(10, 'Alexey', 'Vasiliev', 10),
(40, 'Dmitry', 'Jilinsky', 30);

insert into chief (id, first_name, last_name, department_id) values
(20, 'Valery', 'Primachenko', 10);

insert into admin (id, first_name, last_name) values
(30, 'Alexandr', 'Terechov');