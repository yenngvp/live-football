
-- 1. country
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('France', 'Europe'); 
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Albania', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Austria', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Belgium', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Croatia', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Czech Republic', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('England', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Germany', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Iceland', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Italy', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Northern Ireland', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Poland', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Portugal', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Romania', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Russia', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Slovakia', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Spain', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Switzerland', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Turkey', 'Europe');
INSERT IGNORE INTO `footballun`.`country` (name, continent) VALUES ('Wales', 'Europe');

-- 2. competition
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('Euro 2016', '2016', '2016', '2016-06-10', '2016-07-10', 'CUP');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('English Premier League', '2015', '2016', '2015-08-08', '2016-06-10', 'LEAGUE');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('Bundesliga', '2015', '2016', '2015-08-08', '2016-06-10', 'LEAGUE');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('Laliga', '2015', '2016', '2015-08-08', '2016-06-10', 'LEAGUE');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('League 1', '2015', '2016', '2015-08-08', '2016-06-10',  'LEAGUE');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('WorldCup 2018', '2018', '2018', '2018-06-10', '2018-07-10',  'CUP');

-- 3. group
INSERT IGNORE INTO `footballun`.`group` (name) VALUES ('A');
INSERT IGNORE INTO `footballun`.`group` (name) VALUES ('B');
INSERT IGNORE INTO `footballun`.`group` (name) VALUES ('C');
INSERT IGNORE INTO `footballun`.`group` (name) VALUES ('D');
INSERT IGNORE INTO `footballun`.`group` (name) VALUES ('E');
INSERT IGNORE INTO `footballun`.`group` (name) VALUES ('F');

-- team
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('France', 'FRA', 21);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Albania', '', 22);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Austria', '', 23);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Belgium', '', 24);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Croatia', '', 25);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Czech Republic', '', 26);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('England', '', 27);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Germany', '', 28);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Iceland', '', 29);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Italy', '', 30);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Northern Ireland', '', 31);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Poland', '', 32);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Portugal', '', 33);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Romania', '', 34);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Russia', '', 35);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Slovakia', '', 36);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Spain', '', 37);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Switzerland', '', 38);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Turkey', '', 39);
INSERT IGNORE INTO `footballun`.`team` (name, code, country_id) VALUES ('Wales', '', 40);

-- position
INSERT IGNORE INTO `footballun`.`position` (name) VALUES ('Goalkeeper');
INSERT IGNORE INTO `footballun`.`position` (name) VALUES ('Defender');
INSERT IGNORE INTO `footballun`.`position` (name) VALUES ('Midfielder');
INSERT IGNORE INTO `footballun`.`position` (name) VALUES ('Forward');




