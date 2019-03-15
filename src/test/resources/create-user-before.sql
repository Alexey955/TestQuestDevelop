delete from user_role;
delete from user_table;

insert into user_table(id, username, password) values
(10, 'Alexey', '$2a$08$s4BotudZ3R36wY/hrjoHFunyV9bhZLERkSkgNDgeWHKltBnm4e50y'),
(20, 'Valery', '$2a$08$e8MNmnN1QxagVhk5eeYglOxZmOZ736UoXEi6i.iir0eHG6Yide9z2'),
(30, 'Alexandr', '$2a$08$7UU1MwezIj2SMG88Md2RzuIAMHMRox7BqYhSpt0L8yj3o2/fNcl3e'),
(40, 'Dmitry', '$2a$08$nknCJz1B9Qq9R6qb.ALbV.5RiU8D0oKZm/NdwtTLxlvdw7rM9YsWO');

insert into user_role(user_id, roles) values
(10, 'EMPLOYEE'),
(20, 'CHIEF'),
(30, 'ADMIN'),
(40, 'EMPLOYEE');



