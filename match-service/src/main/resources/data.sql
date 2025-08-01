-- match_scores
INSERT INTO match_scores (id, match_id, player_id, set_number, games_won, points_won) VALUES
                                                                                          (1, 1, 1, 1, 4, 12),
                                                                                          (2, 1, 2, 1, 2, 6),
                                                                                          (3, 1, 1, 2, 4, 12),
                                                                                          (4, 1, 2, 2, 2, 6);

-- matches
INSERT INTO matches (id, tournament_id, date, status, description) VALUES
                                                                       (1, 1, '2025-08-01 15:00:00', 'SCHEDULED', 'Quarterfinal match'),
                                                                       (2, 1, '2025-08-02 15:00:00', 'SCHEDULED', 'Semifinal match'),
                                                                       (3, 1, '2025-08-01 15:00:00', 'SCHEDULED', 'Quarterfinal match'),
                                                                       (4, 1, '2025-08-02 15:00:00', 'SCHEDULED', 'Semifinal match');

-- participants (assuming this is match participants)
INSERT INTO participants (id, match_id, user_id, score, is_winner) VALUES
                                                                       (1, 2, 1, 15, true),
                                                                       (2, 16, 1, 10, true),
                                                                       (3, 2, 2, 8, true),
                                                                       (4, 17, 2, 12, false);
