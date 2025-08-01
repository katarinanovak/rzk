-- Tabela tournament_types
CREATE TABLE IF NOT EXISTS tournament_types (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT
    );

-- Ubacivanje podataka u tournament_types
INSERT INTO tournament_types (id, name, description) VALUES
                                                         (1, 'Singles', 'Individual tennis matches'),
                                                         (2, 'Doubles', 'Two players per team'),
                                                         (3, 'Mixed Doubles', 'Teams with one male and one female player');

-- Tabela tournaments
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

-- Ubacivanje podataka u tournaments
INSERT INTO tournaments (id, name, description, start_date, end_date, status, organizer_id, tournament_type_id) VALUES
                                                                                                                    (1, 'Summer Open 2025', 'Annual summer tennis tournament', '2025-08-01', '2025-08-15', 'PLANNED', 1, 1),
                                                                                                                    (2, 'City Doubles Cup', 'Doubles tennis championship', '2025-09-10', '2025-09-20', 'PLANNED', 2, 2),
                                                                                                                    (3, 'Autumn Championship 2025', 'Prestigious autumn tennis tournament', '2025-08-15', '2025-08-25', 'PLANNED', 3, 2),
                                                                                                                    (6, 'TEST', 'TESTt', '2025-10-05', '2025-10-15', 'PLANNED', 3, 2),
                                                                                                                    (9, 'PRVI 33', 'PRVI333', '2025-10-05', '2025-10-15', 'PLANNED', 3, 2);

-- Tabela participants
CREATE TABLE IF NOT EXISTS participants (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            user_id BIGINT,
                                            tournament_id BIGINT,
                                            joined_at DATE,
                                            score INT,
                                            CONSTRAINT fk_tournament FOREIGN KEY (tournament_id) REFERENCES tournaments(id)
    );

-- Ubacivanje podataka u participants
INSERT INTO participants (id, user_id, tournament_id, joined_at, score) VALUES
                                                                            (1, 2, 1, '2025-07-25', 58),
                                                                            (2, 16, 1, '2025-07-26', 72),
                                                                            (3, 17, 2, '2025-08-30', 89),
                                                                            (4, 2, 2, '2025-07-25', 60),
                                                                            (5, 16, 2, '2025-07-26', 75),
                                                                            (6, 17, 2, '2025-08-30', 92);
