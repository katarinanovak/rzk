-- PODACI ZA DOCKER

INSERT INTO roles (id, name) VALUES
                                 (1, 'player'),
                                 (2, 'admin'),
                                 (3, 'organizer');

INSERT INTO users (id, username, password, email, role_id) VALUES
                                                               (1, 'admin', '1234', 'admin@example.com', 2),
                                                               (2, 'player1', 'player123', 'player1@example.com', 1),
                                                               (3, 'organizer1', 'organizer123', 'organizer1@example.com', 3),
                                                               (16, 'player2', 'player234', 'player2@example.com', 1),
                                                               (17, 'player36', 'player2345', 'player33@example.com', 1),
                                                               (18, 'player4', '{noop}player456', 'player4@example.com', 1);
