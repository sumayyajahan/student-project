#INSERT INTO roles (name) VALUES ('Admin'), ('Owner'), ('Renter'), ('Guest');

INSERT INTO roles (name)
SELECT 'Admin' FROM DUAL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='Admin' LIMIT 1);

INSERT INTO roles (name)
SELECT 'Owner' FROM DUAL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='Owner' LIMIT 1);

INSERT INTO roles (name)
SELECT 'Renter' FROM DUAL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='Renter' LIMIT 1);

INSERT INTO roles (name)
SELECT 'Guest' FROM DUAL
WHERE NOT EXISTS (SELECT * FROM roles WHERE name='Guest' LIMIT 1);
