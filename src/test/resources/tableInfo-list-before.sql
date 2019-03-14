delete from employee;
delete from chief;
delete from admin;
delete from department;

insert into employee (id, first_name, last_name, department_id) value
(1, 'Alexey', 'Vasiliev', 1),
(4, 'Dmitry', 'Jilinsky' 2);

insert into chief (id, first_name, last_name, department_id) value
(2, 'Valery', 'Primachenko', 1);

insert into admin (id, first_name, last_name) value
(3, 'Alexandr', 'Terechov', 2);

insert into department (id, department_name) value
(1, 'DepartmentOne'),
(3, 'DepartmentTwo');