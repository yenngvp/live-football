
-- Creating 'country' data
INSERT IGNORE INTO country (name, continent) VALUES ('France', 'Europe');

-- Creating 'competition' data
INSERT IGNORE INTO competition (name, year_from, year_to, start_at, end_at, host_country_id) VALUES ('EURO 2016', '2016', '2016', '2016-06-10', '2016-07-10', 1);
