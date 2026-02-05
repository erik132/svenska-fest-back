DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE users(
  id SERIAL NOT NULL PRIMARY KEY,
  active int NOT NULL ,
  email VARCHAR(255) NOT NULL ,
  username VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  estonian_id_code VARCHAR(11) UNIQUE NOT NULL
);


CREATE TABLE roles(
  id INT NOT NULL PRIMARY KEY,
  role VARCHAR(255) NOT NULL
);


CREATE TABLE user_roles(
  user_id INT NOT NULL ,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles (id, role) VALUES (1,'ADMIN');
INSERT INTO roles (id, role) VALUES (2,'STANDARD');

INSERT INTO public.users
(active, email, username, "password", first_name, last_name, estonian_id_code)
VALUES(1, 'nils@nils.com', 'Nils123', '$2a$10$MIO2CTBIaB43ycHmX1ECQOhp6Av07NvfZetQOQpe3OGR6LPbOEXT2', 'Nils', 'Okso', '12345678911');

INSERT INTO public.users
(active, email, username, "password", first_name, last_name, estonian_id_code)
VALUES(1, 'johan@johan.com', 'Johan123', '$2a$10$D.OiAbJDhLGiUq5HCq0rruMn8L0/IfWqmtYJbTOmWk2cu31tawdGW', 'Johan', 'Okso', '12345678922');

INSERT INTO public.user_roles
(user_id, role_id)
VALUES(1, 1),
(2,2);