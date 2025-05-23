INSERT INTO avions VALUES ('AAPI1', 'BOEING 737-800', 180);
INSERT INTO avions VALUES ('AAPI2', 'BOEING 747-400', 180);
INSERT INTO avions VALUES ('AAPI3', 'AIRBUS A321NEO', 180);
INSERT INTO avions VALUES ('AAPI4', 'AIRBUS A321NEO', 180);
INSERT INTO avions VALUES ('AAPI5', 'AIRBUS A320', 180);
INSERT INTO avions VALUES ('AAPI6', 'AIRBUS A320', 180);
INSERT INTO avions VALUES ('AAPI7', 'AIRBUS A330-900NEO PASSENGER', 180);
INSERT INTO avions VALUES ('AAPI8', 'AIRBUS A321NEO', 180);
INSERT INTO avions VALUES ('AAPI9', 'AIRBUS A330-900NEO PASSENGER', 180);
INSERT INTO avions VALUES ('AAPI10', 'AIRBUS A321NEO', 180);

INSERT INTO passagers VALUES ('62358', 'Tristan Pau', '10 rue de Vanves', '0695149567', 'FR990877');
INSERT INTO passagers VALUES ('62453', 'Marc Touvet', '7 avenue de Paris', '0699845628', 'FR498566P001');
INSERT INTO passagers VALUES ('62438', 'Manon George', '12 rue Victor Hugo', '0674894632', 'FR197536');

INSERT INTO vols VALUES ('API1', 'JFK', 'MAD', '2025-06-01 18:00:00', '2025-06-02 11:50:00', 'Planifié', 'AAPI1');
INSERT INTO vols VALUES ('API2', 'JFK', 'MAD', '2025-06-01 20:00:00', '2025-06-02 13:00:00', 'Planifié', 'AAPI2');
INSERT INTO vols VALUES ('API3', 'JFK', 'MAD', '2025-06-01 20:55:00', '2025-06-02 19:20:00', 'Planifié', 'AAPI3');
INSERT INTO vols VALUES ('API4', 'JFK', 'MAD', '2025-06-01 20:55:00', '2025-06-02 17:05:00', 'Planifié', 'AAPI4');
INSERT INTO vols VALUES ('API5', 'JFK', 'MAD', '2025-06-01 10:30:00', '2025-06-02 10:15:00', 'Planifié', 'AAPI5');
INSERT INTO vols VALUES ('API6', 'JFK', 'MAD', '2025-06-01 10:30:00', '2025-06-02 12:40:00', 'Planifié', 'AAPI6');
INSERT INTO vols VALUES ('API7', 'EWR', 'MAD', '2025-06-01 22:50:00', '2025-06-02 15:25:00', 'Planifié', 'AAPI7');
INSERT INTO vols VALUES ('API8', 'EWR', 'MAD', '2025-06-01 17:35:00', '2025-06-02 10:15:00', 'Planifié', 'AAPI8');
INSERT INTO vols VALUES ('API9', 'EWR', 'MAD', '2025-06-01 22:50:00', '2025-06-02 17:05:00', 'Planifié', 'AAPI9');
INSERT INTO vols VALUES ('API10', 'EWR', 'MAD', '2025-06-01 17:35:00', '2025-06-02 12:40:00', 'Planifié', 'AAPI10');

INSERT INTO reservations VALUES ('API7', '2025-06-01 22:50:00', 'Réservée', 'FR498566P001');
INSERT INTO reservations VALUES ('API5', '2025-06-08 20:50:00', 'Réservée', 'FR498566P001');
INSERT INTO reservations VALUES ('API7BIS', '2025-06-01 22:50:00', 'Réservée', 'FR498566F001');
INSERT INTO reservations VALUES ('API3', '2025-06-01 20:55:00', 'Réservée', 'FR197536');
INSERT INTO reservations VALUES ('R003', '2025-05-04 09:30:00', 'Réservée', 'P11111');
INSERT INTO reservations VALUES ('R004', '2025-05-05 11:00:00', 'Réservée', 'P22222');
INSERT INTO reservations VALUES ('API7TER', '2025-06-01 22:50:00', 'Réservée', 'FR990877');
