create table IF NOT exists roles(
    id INT not null auto_increment,
    name varchar(255),
primary key (id)) engine=InnoDB;

create table IF NOT exists users (
    id INT not null auto_increment,
    username varchar(100),
    password VARCHAR(100) NOT NULL,
    enabled boolean,
primary key (id)) engine=InnoDB;

CREATE TABLE IF NOT exists users_roles (
    users_id INT,
    roles_id INT,
    FOREIGN KEY (users_id) REFERENCES users(id),
    FOREIGN KEY (roles_id) REFERENCES roles(id),
    PRIMARY KEY (users_id, roles_id)
);