DROP DATABASE IF EXISTS designation_service;
DROP USER IF EXISTS 'designation'@'%';

CREATE DATABASE designation_service;

CREATE USER 'designation'@'%' IDENTIFIED BY 'Desig@2025#Strong';
GRANT ALL PRIVILEGES ON designation_service.* TO 'designation'@'%';
FLUSH PRIVILEGES;

USE designation_service;

CREATE TABLE designation (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(50) NOT NULL UNIQUE,
    code VARCHAR(10) NOT NULL UNIQUE,

    status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE',

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO designation (name, code, status)
VALUES 
('Software Engineer', 'SE01', 'ACTIVE'),
('Senior Software Engineer', 'SSE01', 'ACTIVE'),
('Team Lead', 'TL01', 'ACTIVE'),
('HR Manager', 'HRM01', 'ACTIVE'),
('Accountant', 'ACC01', 'ACTIVE'),
('Product Manager', 'PM01', 'ACTIVE'),
('DevOps Engineer', 'DE01', 'ACTIVE');
