INSERT INTO users (username, password, enabled) VALUES
                                                    ('bouncer1', '$2a$10$yJrK77Tkvibxcx2SvL5HFOqUbRbGG14SMJcL8jpCHCqafmGavFghi', true),
                                                    ('scraper1', '$2a$10$yJrK77Tkvibxcx2SvL5HFOqUbRbGG14SMJcL8jpCHCqafmGavFghi',  true),
                                                    ('cruder1', '$2a$10$yJrK77Tkvibxcx2SvL5HFOqUbRbGG14SMJcL8jpCHCqafmGavFghi', true);


INSERT INTO users_roles (users_id, roles_id) VALUES
                                                 (1, 1),
                                                 (2, 2),
                                                 (3, 3)
;
