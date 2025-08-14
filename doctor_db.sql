-- Doctors Appointment System Database Dump
-- Created for Qube Health Assignment

-- Drop database if exists and create new one
DROP DATABASE IF EXISTS doctors_dev_db;
CREATE DATABASE doctors_dev_db;
USE doctors_dev_db;

-- Create Doctor table
CREATE TABLE doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(20)
);

-- Create Patient table
CREATE TABLE patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(20)
);

-- Create Appointment table
CREATE TABLE appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    appointment_date_time DATETIME NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    UNIQUE KEY idx_appt_doctor_time (doctor_id, appointment_date_time)
);

-- Insert sample doctors
INSERT INTO doctor (name, specialization, email, phone) VALUES
('Dr. John Smith', 'Cardiology', 'john.smith@hospital.com', '+1-555-0123'),
('Dr. Sarah Johnson', 'Neurology', 'sarah.johnson@hospital.com', '+1-555-0124'),
('Dr. Michael Brown', 'Orthopedics', 'michael.brown@hospital.com', '+1-555-0125'),
('Dr. Emily Davis', 'Pediatrics', 'emily.davis@hospital.com', '+1-555-0126'),
('Dr. Robert Wilson', 'Dermatology', 'robert.wilson@hospital.com', '+1-555-0127'),
('Dr. Lisa Anderson', 'Psychiatry', 'lisa.anderson@hospital.com', '+1-555-0128'),
('Dr. David Martinez', 'Oncology', 'david.martinez@hospital.com', '+1-555-0129'),
('Dr. Jennifer Taylor', 'Gynecology', 'jennifer.taylor@hospital.com', '+1-555-0130');

-- Insert sample patients
INSERT INTO patient (name, email, phone) VALUES
('Jane Doe', 'jane.doe@email.com', '+1-555-0456'),
('John Wilson', 'john.wilson@email.com', '+1-555-0457'),
('Mary Johnson', 'mary.johnson@email.com', '+1-555-0458'),
('James Brown', 'james.brown@email.com', '+1-555-0459'),
('Patricia Davis', 'patricia.davis@email.com', '+1-555-0460'),
('Robert Miller', 'robert.miller@email.com', '+1-555-0461'),
('Linda Garcia', 'linda.garcia@email.com', '+1-555-0462'),
('William Rodriguez', 'william.rodriguez@email.com', '+1-555-0463'),
('Elizabeth Martinez', 'elizabeth.martinez@email.com', '+1-555-0464'),
('Thomas Anderson', 'thomas.anderson@email.com', '+1-555-0465');

-- Insert sample appointments
INSERT INTO appointment (doctor_id, patient_id, appointment_date_time) VALUES
(1, 1, '2024-01-15 10:00:00'),
(1, 2, '2024-01-15 14:00:00'),
(2, 3, '2024-01-16 09:00:00'),
(2, 4, '2024-01-16 11:00:00'),
(3, 5, '2024-01-17 10:00:00'),
(3, 6, '2024-01-17 15:00:00'),
(4, 7, '2024-01-18 08:00:00'),
(4, 8, '2024-01-18 13:00:00'),
(5, 9, '2024-01-19 10:00:00'),
(5, 10, '2024-01-19 16:00:00'),
(6, 1, '2024-01-20 09:00:00'),
(6, 2, '2024-01-20 14:00:00'),
(7, 3, '2024-01-21 11:00:00'),
(7, 4, '2024-01-21 15:00:00'),
(8, 5, '2024-01-22 10:00:00');

-- Create indexes for better performance
CREATE INDEX idx_doctor_specialization ON doctor(specialization);
CREATE INDEX idx_patient_name ON patient(name);
CREATE INDEX idx_appointment_patient ON appointment(patient_id);
CREATE INDEX idx_appointment_date ON appointment(appointment_date_time);

-- Show table structures
DESCRIBE doctor;
DESCRIBE patient;
DESCRIBE appointment;

-- Show sample data
SELECT 'Doctors:' as table_name;
SELECT * FROM doctor LIMIT 5;

SELECT 'Patients:' as table_name;
SELECT * FROM patient LIMIT 5;

SELECT 'Appointments:' as table_name;
SELECT 
    a.id,
    d.name as doctor_name,
    p.name as patient_name,
    a.appointment_date_time
FROM appointment a
JOIN doctor d ON a.doctor_id = d.id
JOIN patient p ON a.patient_id = p.id
LIMIT 5;
