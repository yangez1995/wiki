USE wiki;

CREATE TABLE t_user (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(60) NOT NULL,
  locked CHAR(1) NOT NULL,
  log_time CHAR(20) NOT NULL,
  reg_date CHAR(10) NOT NULL
);

CREATE TABLE t_user_message (
  user_id INT PRIMARY KEY,
  nickname VARCHAR(20) NOT NULL,
  age INT NOT NULL,
  sex CHAR(1) NOT NULL
);
ALTER TABLE t_user_message ADD CONSTRAINT del_user FOREIGN KEY (user_id) REFERENCES t_user(user_id) ON DELETE CASCADE;

CREATE TABLE t_role (
  role_id INT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(30) NOT NULL,
  des VARCHAR(100)
);

CREATE TABLE t_authority (
  authority_id INT PRIMARY KEY AUTO_INCREMENT,
  authority_name VARCHAR(30) NOT NULL,
  authority_mark VARCHAR(30) NOT NULL,
  des VARCHAR(100)
);

CREATE TABLE t_resources (
  resources_id INT PRIMARY KEY AUTO_INCREMENT,
  resources_name VARCHAR(30) NOT NULL,
  resources_url VARCHAR(30) NOT NULL,
  resources_type CHAR(2) NOT NULL,
  des VARCHAR(100)
);

CREATE TABLE t_resource_type (
  id CHAR(2) PRIMARY KEY,
  name VARCHAR(30) NOT NULL
);

CREATE TABLE t_user_roles (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  role_id INT NOT NULL
);
ALTER TABLE t_user_roles ADD CONSTRAINT del_user_role FOREIGN KEY (user_id) REFERENCES t_user(user_id) ON DELETE CASCADE;
ALTER TABLE t_user_roles ADD CONSTRAINT del_role_user FOREIGN KEY (role_id) REFERENCES t_role(role_id) ON DELETE CASCADE;

CREATE TABLE t_role_authorities (
  id INT PRIMARY KEY AUTO_INCREMENT,
  role_id INT NOT NULL,
  authority_id INT NOT NULL
);
ALTER TABLE t_role_authorities ADD CONSTRAINT del_role_authority FOREIGN KEY (role_id) REFERENCES t_role(role_id) ON DELETE CASCADE;
ALTER TABLE t_role_authorities ADD CONSTRAINT del_authority_role FOREIGN KEY (authority_id) REFERENCES t_authority(authority_id) ON DELETE CASCADE;

CREATE TABLE t_resources_authorities (
  id INT PRIMARY KEY AUTO_INCREMENT,
  resources_id INT NOT NULL,
  authority_id INT NOT NULL
);
ALTER TABLE t_resources_authorities ADD CONSTRAINT del_resource_authority FOREIGN KEY (resources_id) REFERENCES t_resources(resources_id) ON DELETE CASCADE;
ALTER TABLE t_resources_authorities ADD CONSTRAINT del_authority_resource FOREIGN KEY (authority_id) REFERENCES t_authority(authority_id) ON DELETE CASCADE;

CREATE TABLE t_active_user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  number INT NOT NULL,
  date VARCHAR(10) NOT NULL
);