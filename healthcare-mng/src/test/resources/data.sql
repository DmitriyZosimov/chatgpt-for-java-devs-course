-- Insert sample patients
INSERT INTO patient (id, first_name, last_name) VALUES
    (1, 'John', 'Doe'),
    (2, 'Jane', 'Smith'),
    (3, 'Alice', 'Johnson');

-- Insert sample medications
INSERT INTO medication (id, name) VALUES
    (1, 'Medication A'),
    (2, 'Medication B'),
    (3, 'Medication C'),
    (4, 'Medication D'),
    (5, 'Medication E');

-- Insert sample appointments
INSERT INTO appointment (id, date, patient_id) VALUES
    (1, '2023-08-25', 1),
    (2, '2023-08-26', 2),
    (3, '2023-08-27', 1);

-- Insert prescribed medications for patients
INSERT INTO patient_medication (patient_id, medication_id) VALUES
    (1, 1),
    (1, 2),
    (2, 3);
