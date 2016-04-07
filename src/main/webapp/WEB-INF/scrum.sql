insert into role (id, name) values(1, 'System Admin');
insert into role (id, name) values(2, 'Product Owner');
insert into role (id, name) values(3, 'Scrum Master');
insert into role (id, name) values(4, 'Developer');

insert into user (first_name, last_name, email, password, role_id, version) values ('admin', 'admin', 'admin@tt.com', 'admin', 1, 0);