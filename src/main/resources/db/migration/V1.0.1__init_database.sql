
CREATE TABLE IF NOT EXISTS employee (
                                        id CHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dob DATE,
    gender VARCHAR(10),
    salary DECIMAL(10,2),
    phone VARCHAR(15),
    department_id INT
    );


CREATE TABLE IF NOT EXISTS department (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(100) NOT NULL
    );

INSERT INTO department (name) VALUES ('HR'), ('Engineering'), ('Sales');
