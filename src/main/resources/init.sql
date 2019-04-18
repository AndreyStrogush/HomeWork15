CREATE SCHEMA `homework`;
USE homework;

CREATE TABLE IF NOT EXISTS developers(
                                       id INT NOT NULL AUTO_INCREMENT,
                                       name VARCHAR(255) NOT NULL,
                                       age INT NOT NULL,
                                       PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS skills(
                                   id INT NOT NULL AUTO_INCREMENT,
                                   branch VARCHAR(255) NOT NULL,
                                   lvl VARCHAR(255)NOT NULL,
                                   PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS projects(
                                     id INT NOT NULL AUTO_INCREMENT,
                                     project_name VARCHAR(255) NOT NULL,
                                     date VARCHAR(255),
                                     PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS companies(
                                      id INT NOT NULL AUTO_INCREMENT,
                                      company_name VARCHAR(255) NOT NULL,
                                      location VARCHAR(255),
                                      PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS customers(
                                      id INT NOT NULL AUTO_INCREMENT,
                                      customer_name VARCHAR(255) NOT NULL,
                                      country VARCHAR(255),
                                      PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS developers_projects(
                                                developer_id INT NOT NULL,
                                                project_id INT NOT NULL,
                                                FOREIGN KEY(developer_id) REFERENCES developers(id),
                                                FOREIGN KEY(project_id) REFERENCES projects(id)
);

CREATE TABLE IF NOT EXISTS developers_skills(
                                              developer_id INT NOT NULL,
                                              skill_id INT NOT NULL,
                                              FOREIGN KEY(developer_id) REFERENCES developers(id),
                                              FOREIGN KEY(skill_id) REFERENCES skills(id)
);

CREATE TABLE IF NOT EXISTS companies_projects(
                                               company_id INT NOT NULL ,
                                               project_id INT NOT NULL ,
                                               FOREIGN KEY (company_id) REFERENCES companies(id),
                                               FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE IF NOT EXISTS customers_projects(
                                               customer_id INT NOT NULL ,
                                               project_id INT NOT NULL ,
                                               FOREIGN KEY (customer_id) REFERENCES customers(id),
                                               FOREIGN KEY (project_id) REFERENCES projects(id)
);

INSERT INTO developers(name, age) VALUES
('First Dev', 20),
('Second Dev', 25),
('Another Dev', 21),
('And Another', 22),
('One More', 18),
('Last One', 30);

INSERT INTO skills(branch, lvl) VALUES
('Java', 'Junior'),
('Java', 'Middle'),
('Java', 'Senior'),
('JS', 'Junior'),
('JS', 'Middle'),
('JS', 'Senior'),
('C++', 'Junior'),
('C++', 'Middle'),
('C++', 'Senior');

INSERT INTO projects(project_name, date) VALUES
('NewApp', '01.01.2019'),
('MiddleApp', '02.02.2019'),
('OldApp', '03.03.2019');

INSERT INTO companies(company_name, location) VALUES
('Google', 'USA'),
('Privat', 'Ukraine'),
('BMW', 'Germany');

INSERT INTO customers(customer_name, country) VALUES
('Ivan', 'USA'),
('John', 'Ukraine'),
('Victoria', 'Germany');

INSERT INTO developers_projects(developer_id, project_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 2),
(4, 3),
(5, 1),
(5, 2),
(6, 1),
(6, 3);

INSERT INTO developers_skills(developer_id, skill_id) VALUES
(1, 4),
(1, 8),
(2, 12),
(2, 3),
(3, 6),
(3, 9),
(4, 5),
(4, 10),
(5, 12),
(5, 3),
(6, 8),
(6, 9);

INSERT INTO companies_projects(company_id, project_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 1),
(3, 3);

INSERT INTO customers_projects(customer_id, project_id) VALUES
(1, 2),
(1, 3),
(2, 1),
(2, 3),
(3, 2),
(3, 3);

ALTER TABLE developers ADD COLUMN salary INT;
UPDATE developers SET salary=1000 WHERE id = 1;
UPDATE developers SET salary=1100 WHERE id = 2;
UPDATE developers SET salary=1200 WHERE id = 3;
UPDATE developers SET salary=1300 WHERE id = 4;
UPDATE developers SET salary=1500 WHERE id = 5;
UPDATE developers SET salary=1700 WHERE id = 6;

SELECT SUM(developers.salary), projects.project_name
FROM developers_projects
         LEFT JOIN developers
                   ON developers.id = developers_projects.developer_id
         LEFT JOIN projects
                   ON projects.id = developers_projects.project_id
GROUP BY projects.id
ORDER BY SUM(developers.salary) DESC
LIMIT 1;

SELECT SUM(developers.salary)
FROM developers_skills
         LEFT JOIN developers
                   ON developers.id = developers_skills.developer_id
         LEFT JOIN skills
                   ON skills.id = developers_skills.developer_id
WHERE `skills`.branch = 'Java';

ALTER TABLE projects ADD COLUMN cost INT;

UPDATE projects SET Cost = (SELECT SUM(developers.salary)
                            FROM developers_projects
                                     LEFT JOIN developers
                                               ON developers.id = developers_projects.developer_id
                            GROUP BY id);

SELECT MIN(cost) FROM projects;

SELECT AVG(developers.salary), projects.project_name
FROM developers_projects
         LEFT JOIN developers
                   ON developers.id = developers_projects.developer_id
         LEFT JOIN projects
                   ON projects.id = developers_projects.project_id
GROUP BY projects.id
HAVING MIN(projects.cost)
ORDER BY SUM(developers.salary) DESC
LIMIT 1;