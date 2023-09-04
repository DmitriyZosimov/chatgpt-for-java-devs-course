INSERT INTO patient (id, first_name, last_name) VALUES
    (1, 'John', 'Doe'),
    (2, 'Jane', 'Smith');

INSERT INTO medication (id, name) VALUES
    (1, 'Medication A'),
    (2, 'Medication B'),
    (3, 'Medication C');

INSERT INTO appointment (id, date, patient_id) VALUES
    (1, '2023-08-25', 1),
    (2, '2023-08-26', 2);

INSERT INTO patient_medication (patient_id, medication_id) VALUES
    (1, 1),
    (1, 2),
    (2, 3);
