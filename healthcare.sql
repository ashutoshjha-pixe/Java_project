CREATE DATABASE IF NOT EXISTS healthcare;
USE healthcare;

-- WARNING: This reset script drops the project tables and their data.
DROP TABLE IF EXISTS medical_records;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role ENUM('ADMIN','DOCTOR','PATIENT') NOT NULL,
  active BOOLEAN DEFAULT TRUE
);

CREATE TABLE patients (
  patient_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT UNIQUE,
  name VARCHAR(100) NOT NULL,
  age INT NOT NULL,
  gender VARCHAR(20),
  phone VARCHAR(20),
  email VARCHAR(100),
  address VARCHAR(255),
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

CREATE TABLE doctors (
  doctor_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT UNIQUE,
  name VARCHAR(100) NOT NULL,
  specialization VARCHAR(100) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(100),
  available_time VARCHAR(100),
  consultation_fee DECIMAL(10,2) DEFAULT 500,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

CREATE TABLE appointments (
  appointment_id INT PRIMARY KEY AUTO_INCREMENT,
  patient_id INT NOT NULL,
  doctor_id INT NOT NULL,
  appointment_date DATE NOT NULL,
  appointment_time TIME NOT NULL,
  reason VARCHAR(255),
  status ENUM('Pending','Approved','Rejected','Completed') DEFAULT 'Pending',
  FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
  FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);

CREATE TABLE medical_records (
  record_id INT PRIMARY KEY AUTO_INCREMENT,
  patient_id INT NOT NULL,
  doctor_id INT NOT NULL,
  diagnosis VARCHAR(255) NOT NULL,
  prescription VARCHAR(500),
  notes VARCHAR(500),
  record_date DATE NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
  FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);

INSERT INTO users(username,password,role) VALUES
('admin','admin123','ADMIN'),
('doctor1','doctor123','DOCTOR'),
('patient1','patient123','PATIENT');

INSERT INTO doctors(user_id,name,specialization,phone,email,available_time,consultation_fee)
VALUES(2,'Dr. Sharma','Cardiologist','9876543210','sharma@healthcare.com','10:00 AM - 2:00 PM',800);

INSERT INTO patients(user_id,name,age,gender,phone,email,address)
VALUES(3,'Rahul Kumar',21,'Male','9876501234','rahul@gmail.com','Ghaziabad');
