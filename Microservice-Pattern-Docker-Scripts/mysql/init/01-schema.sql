CREATE database employee_movement;
CREATE database industrial_relation;
CREATE database organization_development;
CREATE database payroll_compensation;
CREATE database performance_management;
CREATE database ms_chassis_two;
CREATE database ms_chassis_three;

CREATE USER 'user'@'%' IDENTIFIED WITH mysql_native_password BY 'userpassword';

GRANT ALL PRIVILEGES ON employee_movement.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON industrial_relation.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON organization_development.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON payroll_compensation.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON performance_management.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON ms_chassis_two.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON ms_chassis_three.* TO 'user'@'%';

GRANT CREATE USER,FILE,PROCESS,RELOAD,REPLICATION CLIENT,REPLICATION SLAVE,SHOW DATABASES,SHUTDOWN,SUPER ON *.* TO 'user'@'%';