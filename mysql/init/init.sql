CREATE DATABASE IF NOT EXISTS user_db;
CREATE DATABASE IF NOT EXISTS tournament_db;
CREATE DATABASE IF NOT EXISTS match_db;

-- User DB
USE user_db;

CREATE TABLE IF NOT EXISTS roles (
                                     id INT PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS users (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
    );

INSERT INTO roles (id, name) VALUES
                                 (1, 'PLAYER'),
                                 (2, 'ADMIN'),
                                 (3, 'ORGANIZER');

INSERT INTO users (id, username, password, email, role_id) VALUES
                                                               (2, 'player1', 'player123', 'player1@example.com', 1),
                                                               (1, 'admin', '1234', 'admin@example.com', 2),
                                                               (3, 'organizer1', 'organizer123', 'organizer1@example.com', 3),
                                                               (16, 'player2', 'player234', 'player2@example.com', 1),
                                                               (17, 'player36', 'player2345', 'player33@example.com', 1),
                                                               (18, 'player4', '{noop}player456', 'player4@example.com', 1);

-- Tournament DB
USE tournament_db;

CREATE TABLE IF NOT EXISTS tournament_types (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT
    );

CREATE TABLE IF NOT EXISTS tournaments (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           name VARCHAR(255),
    description TEXT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(50),
    organizer_id BIGINT,
    tournament_type_id BIGINT,
    CONSTRAINT fk_tournament_type FOREIGN KEY (tournament_type_id) REFERENCES tournament_types(id)
    );

CREATE TABLE IF NOT EXISTS participants (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            user_id BIGINT,
                                            tournament_id BIGINT,
                                            joined_at DATE,
                                            score INT,
                                            CONSTRAINT fk_tournament FOREIGN KEY (tournament_id) REFERENCES tournaments(id)
    );

INSERT INTO tournament_types (id, name, description) VALUES
                                                         (1, 'Singles', 'Individual tennis matches'),
                                                         (2, 'Doubles', 'Two players per team'),
                                                         (3, 'Mixed Doubles', 'Teams with one male and one female player');

INSERT INTO tournaments (id, name, description, start_date, end_date, status, organizer_id, tournament_type_id) VALUES
                                                                                                                    (1, 'Summer Open 2025', 'Annual summer tennis tournament', '2025-08-01', '2025-08-15', 'PLANNED', 1, 1),
                                                                                                                    (2, 'City Doubles Cup', 'Doubles tennis championship', '2025-09-10', '2025-09-20', 'PLANNED', 2, 2),
                                                                                                                    (3, 'Autumn Championship 2025', 'Prestigious autumn tennis tournament', '2025-08-15', '2025-08-25', 'PLANNED', 3, 2),
                                                                                                                    (6, 'TEST', 'TESTt', '2025-10-05', '2025-10-15', 'PLANNED', 3, 2),
                                                                                                                    (9, 'PRVI 33', 'PRVI333', '2025-10-05', '2025-10-15', 'PLANNED', 3, 2);

INSERT INTO participants (id, user_id, tournament_id, joined_at, score) VALUES
                                                                            (1, 2, 1, '2025-07-25', 58),
                                                                            (2, 16, 1, '2025-07-26', 72),
                                                                            (3, 17, 2, '2025-08-30', 89),
                                                                            (4, 2, 2, '2025-07-25', 60),
                                                                            (5, 16, 2, '2025-07-26', 75),
                                                                            (6, 17, 2, '2025-08-30', 92);

-- Match DB
USE match_db;

CREATE TABLE IF NOT EXISTS matches (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       tournament_id BIGINT,
                                       date DATETIME,
                                       status VARCHAR(50),
    description TEXT
    );

CREATE TABLE IF NOT EXISTS match_scores (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            match_id BIGINT,
                                            player_id BIGINT,
                                            set_number INT,
                                            games_won INT,
                                            points_won INT
);

CREATE TABLE IF NOT EXISTS participants (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            match_id BIGINT,
                                            user_id BIGINT,
                                            score INT,
                                            is_winner BOOLEAN
);

INSERT INTO matches (id, tournament_id, date, status, description) VALUES
                                                                       (1, 1, '2025-08-01 15:00:00', 'SCHEDULED', 'Quarterfinal match'),
                                                                       (2, 1, '2025-08-02 15:00:00', 'SCHEDULED', 'Semifinal match'),
                                                                       (3, 1, '2025-08-01 15:00:00', 'SCHEDULED', 'Quarterfinal match'),
                                                                       (4, 1, '2025-08-02 15:00:00', 'SCHEDULED', 'Semifinal match');

INSERT INTO match_scores (id, match_id, player_id, set_number, games_won, points_won) VALUES
                                                                                          (1, 1, 1, 1, 4, 12),
                                                                                          (2, 1, 2, 1, 2, 6),
                                                                                          (3, 1, 1, 2, 4, 12),
                                                                                          (4, 1, 2, 2, 2, 6);

INSERT INTO participants (id, match_id, user_id, score, is_winner) VALUES
                                                                       (1, 2, 1, 15, TRUE),
                                                                       (2, 16, 1, 10, TRUE),
                                                                       (3, 2, 2, 8, TRUE),
                                                                       (4, 17, 2, 12, FALSE);
