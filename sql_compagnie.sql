CREATE DATABASE compagnie;

USE compagnie;

CREATE TABLE IF NOT EXISTS passagers (
    identifiant VARCHAR(10) PRIMARY KEY,
    nom VARCHAR(100),
    adresse VARCHAR(255),
    contact VARCHAR(20),
    passeport VARCHAR(20) UNIQUE
);

CREATE TABLE IF NOT EXISTS avions (
    immatriculation VARCHAR(10) PRIMARY KEY,
    modele VARCHAR(100),
    capacite INT
);

CREATE TABLE IF NOT EXISTS vols (
    numeroVol VARCHAR(10) PRIMARY KEY,
    origine VARCHAR(10),
    destination VARCHAR(10),
    dateDepart DATETIME,
    dateArrivee DATETIME,
    etat VARCHAR(20),
    avion VARCHAR(10),
    FOREIGN KEY (avion) REFERENCES avions(immatriculation)
);

CREATE TABLE IF NOT EXISTS reservations (
    numeroReservation VARCHAR(10) PRIMARY KEY,
    dateReservation DATETIME,
    statut VARCHAR(20),
    passeport VARCHAR(20),
    FOREIGN KEY (passeport) REFERENCES passagers(passeport)
);
