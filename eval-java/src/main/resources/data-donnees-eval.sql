-- Insérer des entreprises
INSERT INTO entreprise (nom) VALUES
('Entreprise Alpha'),
('Entreprise Bravo'),
('Entreprise Charlie'),
('Entreprise Delta');

-- Insérer des utilisateurs (liés ou non à une entreprise)
INSERT INTO utilisateur (email, password, entreprise_id) VALUES
 ('admin@admin.com', "$2a$10$31nhEmGLow2iIug.qqq6RuG3GXv1fo6wXfojXNswxqYqwR8kUJUEm", null), -- Administrateur (pas lié à une entreprise)
 ('contact@alpha.com', '$2y$10$FhTTefb1Z/i8pef7WPRzzO8A2lp.ASktPNaaQEvyT4FPLMNzR2Vwe', 1), -- Utilisateur lié à Entreprise Alpha 'alpha2024'
 ('contact@bravo.com', 'bravo2024', 2),  -- Utilisateur lié à Entreprise Beta
 ('contact@charlie.com', 'charlie2024', 3),  -- Utilisateur lié à Entreprise Charlie
('contact@delta.com', 'delta2024', 4);  -- Utilisateur lié à Entreprise Delta

-- Insérer des conventions (liées à des entreprises)
INSERT INTO convention (nom, subvention, salarie_maximum, entreprise_id) VALUES
    ( 'Convention A', 15000.50, 10, 1), -- Convention de l'Entreprise Alpha
    ( 'Convention B', 20000.75, 15, 1), -- Convention de l'Entreprise Alpha
    ( 'Convention C', 25000.00, 20, 2), -- Convention de l'Entreprise Beta
    ( 'Convention D', 2000.00, 5, 3), -- Convention de l'Entreprise Charlie
( 'Convention E', 35000.00, 2, 4); -- Convention de l'Entreprise Charlie

-- Insérer des salariés (liés à des conventions)
INSERT INTO salarie (matricule, code_barre, convention_id) VALUES
( 'SAL001', 'CODE001', 1), -- Salarié lié à la Convention A
( 'SAL002', 'CODE002', 1), -- Salarié lié à la Convention A
( 'SAL003', 'CODE003', 2), -- Salarié lié à la Convention B
( 'SAL004', 'CODE004', 3), -- Salarié lié à la Convention C
( 'SAL005', 'CODE005', 4); -- Salarié lié à la Convention D
