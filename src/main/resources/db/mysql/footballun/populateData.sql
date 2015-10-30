
-- 2. competition
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('Euro 2016', '2016', '2016', '2016-06-10', '2016-07-10', 'CUP');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('English Premier League', '2015', '2016', '2015-08-08', '2016-06-10', 'LEAGUE');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('Bundesliga', '2015', '2016', '2015-08-08', '2016-06-10', 'LEAGUE');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('Laliga', '2015', '2016', '2015-08-08', '2016-06-10', 'LEAGUE');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('League 1', '2015', '2016', '2015-08-08', '2016-06-10',  'LEAGUE');
INSERT IGNORE INTO `footballun`.`competition` (name, year_from, year_to, start_at, end_at,  type) VALUES ('WorldCup 2018', '2018', '2018', '2018-06-10', '2018-07-10',  'CUP');

-- position
INSERT IGNORE INTO `footballun`.`position` (name) VALUES ('Goalkeeper');
INSERT IGNORE INTO `footballun`.`position` (name) VALUES ('Defender');
INSERT IGNORE INTO `footballun`.`position` (name) VALUES ('Midfielder');
INSERT IGNORE INTO `footballun`.`position` (name) VALUES ('Forward');

-- team
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Man City');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Arsenal');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('West Ham');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Man Utd');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Leicester');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Spurs');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Crystal Palace');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Southampton');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Liverpool');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('West Brom');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Everton');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Swansea');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Watford');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Stoke');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Chelsea');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Norwich');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Bournemouth');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Sunderland');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Newcastle');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Aston Villa');

INSERT IGNORE INTO `footballun`.`hero_role` (name) VALUES ('Player');
INSERT IGNORE INTO `footballun`.`hero_role` (name) VALUES ('Manager');
INSERT IGNORE INTO `footballun`.`hero_role` (name) VALUES ('President');
INSERT IGNORE INTO `footballun`.`hero_role` (name) VALUES ('Referee');


INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Man City');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Arsenal');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('West Ham');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Man Utd');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Leicester');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Spurs');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Crystal Palace');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Southampton');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Liverpool');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('West Brom');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Everton');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Swansea');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Watford');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Stoke');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Chelsea');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Norwich');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Bournemouth');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Sunderland');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Newcastle');
INSERT IGNORE INTO `footballun`.`team` (name) VALUES ('Aston Villa')

-- squad
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Man City'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Arsenal'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='West Ham'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Man Utd'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Leicester'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Spurs'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Crystal Palace'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Southampton'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Liverpool'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='West Brom'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Everton'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Swansea'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Watford'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Stoke'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Chelsea'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Norwich'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Bournemouth'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Sunderland'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Newcastle'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');
INSERT IGNORE INTO `footballun`.`squad` (team_id, competition_id, class) VALUES ((SELECT id FROM team WHERE name='Aston Villa'), (SELECT id FROM competition WHERE name='English Premier League'), 'First Team');

-- hero
-- Goalkeepers
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Willy', 'Caballero');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Joe', 'Hart');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Richard', 'Wright');
-- Defenders
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Nicolas', 'Otamendi');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Gael', 'Clichy');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Martin', 'Demichelis');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Jason', 'Denayer');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Aleksandar', 'Kolarov');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Vincent', 'Kompany');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Eliaquim', 'Mangala');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Bacary', 'Sagna');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Pablo', 'Zabaleta');
-- Midfielders
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Fabian', 'Delph');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Fernando', 'Francisco Reges');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Fernandinho', 'Luiz Roza');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Samir', 'Nasri');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Jesus', 'Navas');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('David', 'Silva');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Kevin', 'De Bruyne');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Raheem', 'Sterling');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Patrick', 'Roberts');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Yaya', 'Toure');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Bruno', 'Zuculini');
-- Strikers
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Sergio', 'Aguero');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Wilfried', 'Bony');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Edin', 'Dzeko');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Kelechi', 'Iheanacho');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Stevan', 'Jovetic');
-- Management
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Manuel', 'Pellegrini');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Ruben', 'Cousillas Fuse');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Xabier', 'Mancisidor');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Jose', 'Cabello');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Brian', 'Kidd');

-- Arsenal
-- Goal Keepers
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('David', 'Ospina');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Petr', 'Cech');
-- Defenders
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Mathieu', 'Debuchy');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Kieran', 'Gibbs');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Per', 'Mertesacker');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Gabriel', '');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Laurent', 'Koscielny');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Nacho', 'Monreal');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Calum', 'Chambers');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Hector', 'Bellerin');
-- Midfielders
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Tomas', 'Rosicky');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Mikel', 'Arteta');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Jack', 'Wilshere');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Mesut', 'Ozil');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Alex', 'Oxlade-Chamberlain');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Aaron', 'Ramsey');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Santi', 'Cazorla');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Mathieu', 'Flamini');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Francis', 'Coquelin');
-- Forwards
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Olivier', 'Giroud');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Theo', 'Walcott');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Alexis', 'Sanchez');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Danny', 'Welbeck');
INSERT IGNORE INTO `footballun`.`hero` (first_name, last_name) VALUES ('Joel', 'Campbell');

-- squad_detail
-- insert into squad_detail (hero_id, position_id, hero_role_id, squad_id)  select id, 8, 1, 21 from hero where id <= 33;
-- insert into squad_detail (hero_id, position_id, hero_role_id, squad_id)  select id, 8, 1, 22 from hero where id >= 34;

-- matchup
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-03 18:45:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-03 21:00:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-03 21:00:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-03 21:00:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-03 21:00:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-03 21:00:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-03 23:30:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-04 19:30:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-04 22:00:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-04 22:00:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-17 18:45', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-17 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-17 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-17 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-17 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-17 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-17 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-17 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-15 22:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-15 02:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);
INSERT IGNORE INTO `footballun`.`matchup` (start_at, status) VALUES ('2015-10-24 21:00', -1);

INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (18, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Leicester'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (19, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Sunderland'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (20, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Arsenal'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (21, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Norwich'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (22, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Stoke'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (23, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Swansea'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (24, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Crystal Palace'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (25, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='West Brom'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (26, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Watford'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (27, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Chelsea'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (28, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Everton'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (29, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Newcastle'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (30, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Spurs'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (31, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Man City'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (32, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Southampton'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (33, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Liverpool'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (34, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Man Utd'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (35, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Norwich'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (36, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Stoke'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (37, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Arsenal'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (38, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='West Ham'), 0);
INSERT IGNORE INTO `footballun`.`matchup_squad` (matchup_id, squad_id, is_home_team) VALUES (39, (SELECT s.id FROM squad s INNER JOIN team t on s.team_id=t.id WHERE t.name='Leicester'), 0);


