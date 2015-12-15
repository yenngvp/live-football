/**
 * @author: yen.nt
 * @created on Nov 27, 2015
 */
package com.footballun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footballun.model.Competition;
import com.footballun.model.Country;
import com.footballun.model.Hero;
import com.footballun.model.HeroRole;
import com.footballun.model.HeroStatus;
import com.footballun.model.Matchup;
import com.footballun.model.MatchupDetail;
import com.footballun.model.Position;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Team;
import com.footballun.service.FootballunService;


/**
 * @author yen.nt
 *
 */
@Component
public class DataImporter {

	private static final String DATA_PATH = "D:\\workspace\\footballun\\assets\\files\\data\\";
	private static final String FILE_NAME = "Competition Schedules.xlsx";
	
	
	private final Logger logger = LoggerFactory.getLogger(DataImporter.class);
	
	/*
	 *  Constants
	 *  
	 *  SHEET names
	 */
	private static final Map<String, Competition> COMPETITIONS_LIST = new HashMap<>();
	private static final Map<String, Country> COUNTRIES_LIST = new HashMap<>();
	
	
	//private static final String[] STANDING_SHEETS_LIST = {"EPL_Standing", "Bundesliga_Standing", "LigaBBVA_Standing", "SerieA_Standing", "France_Ligue1_Standing"};
	
	private static final String TEAM_PERSONELS_KITS_SHEET = "Personels_Kits";
	private static final String[] LEAGUES_CALENDAR_SHEETS_LIST = {"EPL_Cal", "Bundesliga_Cal", "LigaBBVA_Cal", "SerieA_Cal", "Ligue1_Cal"};
	private static final String STADIA_SHEET = "Stadia";
	private static final String[] LEAGUES_PLAYERS_SHEETS_LIST = {"EPL_Players", "Bundesliga_Players", "LigaBBVA_Players", "SerieA_Players", "Ligue1_Players"};
	
	/*
	 * Columns mapping
	 */
	private static final Map<String, String> STANDING_COLS_MAP = new HashMap<String, String>();
	private static final Map<String, String> TEAM_PERSONELS_KITS_COLS_MAP = new HashMap<String, String>();
	private static final Map<String, String> LEAGUES_CALENDAR_COLS_MAP = new HashMap<String, String>();
	private static final Map<String, String> LEAGUES_PLAYER_COLS_MAP = new HashMap<String, String>();
	private static final Map<String, String> MATCHDAYS_COLS_MAP = new HashMap<String, String>();
	
	/*
	 *  The pairs (key, value) below must be always going together with the sheet data
	 */
	private static final String[] COLUMN_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};

	private static final String KEY_COL_NAME = "_KEY_COL_NAME_";
	
	// ie: #	Team	GP	W	D	L	GF	GA	GD	PTS
	private static final String COL_NAME_POS = "#";
	private static final String COL_NAME_TEAM = "TEAM";
	private static final String COL_NAME_GAMES_PLAYED = "GP";
	private static final String COL_NAME_WINS = "W";
	private static final String COL_NAME_DRAWS = "D";
	private static final String COL_NAME_LOSE = "L";
	private static final String COL_NAME_GOALS_SCORED = "GF";
	private static final String COL_NAME_GOALS_AGAINST = "GA";
	private static final String COL_NAME_GOALS_DIFF = "GD";
	private static final String COL_NAME_POINTS = "PTS";
	private static final String[] STANDING_COL_HEADERS = {
		COL_NAME_POS, COL_NAME_TEAM, COL_NAME_GAMES_PLAYED, COL_NAME_WINS, COL_NAME_DRAWS, COL_NAME_LOSE,
		COL_NAME_GOALS_SCORED, COL_NAME_GOALS_AGAINST, COL_NAME_GOALS_DIFF,COL_NAME_POINTS};
	
	// Team personnel and kits columns header
	// ie: Full name	Manager	Captain	Kit manufacturer	Shirt sponsor	Logo	President/Chairman
	private static final String COL_NAME_FULLNAME = "Full name";
	private static final String COL_NAME_SHORTNAME = "Name";
	private static final String COL_NAME_MANAGER = "Manager";
	private static final String COL_NAME_CAPTAIN = "Captain";
	private static final String COL_NAME_KITMANUFACTURER = "Kit manufacturer";
	private static final String COL_NAME_SHIRTSPONSOR = "Shirt sponsor";
	private static final String COL_NAME_LOGO = "Logo";
	private static final String COL_NAME_PRESIDENTCHAIRMAN = "President/Chairman";
	private static final String[] TEAM_PERSKIT_COL_HEADERS = {
		KEY_COL_NAME,
		COL_NAME_FULLNAME, COL_NAME_MANAGER, COL_NAME_CAPTAIN, COL_NAME_KITMANUFACTURER,
		COL_NAME_SHIRTSPONSOR, COL_NAME_LOGO, COL_NAME_PRESIDENTCHAIRMAN, COL_NAME_SHORTNAME
	};
	
	// CALENDARS: IE: Date	Match	Kick-off	Status
	private static final String COL_NAME_DATE = "Date";
	private static final String COL_NAME_FIXTURE = "Fixture";
	private static final String COL_NAME_KICKOFF = "Status/Kick-off";
	private static final String[] LEAGUES_CAL_COL_HEADERS = {
		COL_NAME_DATE, COL_NAME_FIXTURE, COL_NAME_KICKOFF
	};
	
	// PLAYERS
	// IE: Team	No.	Position	Player
	//private static final String COL_NAME_CLUB = "Club";
	private static final String COL_NAME_SHIRTNUMBER = "No.";
	private static final String COL_NAME_POSITION = "Position";
	private static final String COL_NAME_PLAYER = "Player";
	private static final String[] LEAGUES_PLAYER_COL_HEADERS = {
		KEY_COL_NAME, COL_NAME_SHIRTNUMBER, COL_NAME_POSITION, COL_NAME_PLAYER
	};

	// Matchdays beginning
	private static final String MATCHDAYS_SHEET = "Matchdays";
	private static final String COL_NAME_MATCHDAY = "Matchday";
	private static final String COL_NAME_MATCHDAY_STARTDATE = "Matchday Start Date";
	private static final String[] MATCHDAY_COL_HEADERS = {
		KEY_COL_NAME, COL_NAME_MATCHDAY, COL_NAME_MATCHDAY_STARTDATE
	};

	
	private static final String[] MON_2_FRI = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
	
	private static final String[] KEY_COMPETITION_NAMES = {"EPL", "Bundesliga", "LigaBBVA", "SerieA", "Ligue1"};
	private static final String[] COMPETITION_NAMES = {"English Premier League", "Bundesliga", "La Liga", "Serie A", "Ligue1"};
	private static final String[] COUNTRIES_NAMES = {"England", "Germany", "Spain", "Italy", "France"};
	
	
	private FootballunService footballunService;

	private Country currentCountry;
	private Competition currentCompetition;
	private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	
	/*
	 * Hero role
	 */
	private static final String HERO_ROLE_PLAYER = "Player";
	private static final String HERO_ROLE_MANAGER = "Manager";
	private static final String HERO_ROLE_PRESIDENT = "President";
	private static final String HERO_ROLE_CAPTAIN = "Captain";
	private static final String HERO_ROLE_VICECAPTAIN = "Vice Captain";
	private static final String HERO_ROLE_3RDCAPTAIN = "3rd Captain";
	private static final Map<String, HeroRole> HERO_ROLES = new HashMap<String, HeroRole>();
	
	/*
	 * Hero status
	 */
	private static final String HERO_STATUS_ACTIVE = "Active";
	private static final String HERO_STATUS_OUTONLOAN = "Out on loan";
	private static final Map<String, HeroStatus> HERO_STATUS = new HashMap<String, HeroStatus>();
	
	/*
	 * Position
	 */
	private static final String POSITION_DF = "DF";
	private static final String POSITION_MF = "MF";
	private static final String POSITION_GK = "GK";
	private static final String POSITION_FW = "FW";
	private static final Map<String, Position> POSITIONS = new HashMap<String, Position>();
	
	private static int playersCounter = 0;
	private static int createdPlayersCounter = 0;
	
	private FormulaEvaluator evaluator;
	
	@Autowired
	public DataImporter(FootballunService footballunService) {
		this.footballunService = footballunService;
		
		/*
		 *  Populates columns mapping
		 */
		// Standing columns mapping
		for (int i = 0; i < STANDING_COL_HEADERS.length; i++) {
			STANDING_COLS_MAP.put(COLUMN_LETTERS[i], STANDING_COL_HEADERS[i]);
		}
		System.out.println(STANDING_COLS_MAP);
		
		// Team personels and kits columns mapping
		for (int i = 0; i < TEAM_PERSKIT_COL_HEADERS.length; i++) {
			// Ignore column 'A' 
			TEAM_PERSONELS_KITS_COLS_MAP.put(COLUMN_LETTERS[i], TEAM_PERSKIT_COL_HEADERS[i]);
		}
		
		// Leagues calendar columns mapping
		for (int i = 0; i < LEAGUES_CAL_COL_HEADERS.length; i++) {
			LEAGUES_CALENDAR_COLS_MAP.put(COLUMN_LETTERS[i], LEAGUES_CAL_COL_HEADERS[i]);
		}
		
		// Leagues player map
		for (int i = 0; i < LEAGUES_PLAYER_COL_HEADERS.length; i++) {
			LEAGUES_PLAYER_COLS_MAP.put(COLUMN_LETTERS[i], LEAGUES_PLAYER_COL_HEADERS[i]);
		}
		
		// Matchdays map
		for (int i = 0; i < MATCHDAY_COL_HEADERS.length; i++) {
			MATCHDAYS_COLS_MAP.put(COLUMN_LETTERS[i], MATCHDAY_COL_HEADERS[i]);
		}
		
		// Hero roles initialization
		HERO_ROLES.put(HERO_ROLE_PRESIDENT, footballunService.findHeroRoleByName((HERO_ROLE_PRESIDENT)));
		HERO_ROLES.put(HERO_ROLE_MANAGER, footballunService.findHeroRoleByName(HERO_ROLE_MANAGER));
		HERO_ROLES.put(HERO_ROLE_PLAYER, footballunService.findHeroRoleByName(HERO_ROLE_PLAYER));
		HERO_ROLES.put(HERO_ROLE_CAPTAIN, footballunService.findHeroRoleByName(HERO_ROLE_CAPTAIN));
		HERO_ROLES.put(HERO_ROLE_VICECAPTAIN, footballunService.findHeroRoleByName(HERO_ROLE_VICECAPTAIN));
		HERO_ROLES.put(HERO_ROLE_3RDCAPTAIN, footballunService.findHeroRoleByName(HERO_ROLE_3RDCAPTAIN));
		
		// Hero status initialization
		HERO_STATUS.put(HERO_STATUS_ACTIVE, footballunService.findHeroStatusByName(HERO_STATUS_ACTIVE));
		HERO_STATUS.put(HERO_STATUS_OUTONLOAN, footballunService.findHeroStatusByName(HERO_STATUS_OUTONLOAN));
		
		// Position initialization
		POSITIONS.put(POSITION_GK, footballunService.findPositionByName(POSITION_GK));
		POSITIONS.put(POSITION_DF, footballunService.findPositionByName(POSITION_DF));
		POSITIONS.put(POSITION_MF, footballunService.findPositionByName(POSITION_MF));
		POSITIONS.put(POSITION_FW, footballunService.findPositionByName(POSITION_FW));
		

		// Creates or queries for existing competitions
		createCompetition();
	}
	
	
	public void importExcel() {
		
		FileInputStream  file = null;
		XSSFWorkbook  workbook = null;
		try {

			file = new FileInputStream(new File(DATA_PATH + FILE_NAME));
		     
		    //Get the workbook instance for XLS file 
			workbook = new XSSFWorkbook (file);
		 
			evaluator =  workbook.getCreationHelper().createFormulaEvaluator();
//	    
//			if (!importTeamsData(workbook)) {
//				logger.error("Importing teams data failed. Should stop further processing!");
//				return;
//			}
//			
//
//			if (!importLeaguesCalendar(workbook)) {
//				logger.error("Importing leagues calenddar failed. Should stop further processing!");
//				return;
//			}
			
//			if (!importMatchdays(workbook)) {
//				logger.error("Importing leagues calenddar failed. Should stop further processing!");
//				return;
//			}
			
			
			if (!importPlayers(workbook)) {
				logger.error("Importing leagues player failed. Should stop further processing!");
				return;
			}
//
//			calculateStandings();
//			
			logger.info(String.format("FINAL RESULT: Found %d players, Saved %d players", playersCounter, createdPlayersCounter));
			
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {

			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void createCompetition() {
		// Builds competitions list and countries list
		for (int i = 0; i < COUNTRIES_NAMES.length; i++) {
			COUNTRIES_LIST.put(KEY_COMPETITION_NAMES[i], findCountry(COUNTRIES_NAMES[i]));
		}
		
		for (int i = 0; i < COUNTRIES_NAMES.length; i++) {
			COMPETITIONS_LIST.put(KEY_COMPETITION_NAMES[i], findOrCreateCompetition(COMPETITION_NAMES[i]));
		}
	}
	
	private boolean importTeamsData(XSSFWorkbook  workbook) {

		String sheetName = TEAM_PERSONELS_KITS_SHEET;			
		logger.info("Reading sheet: " + sheetName);
		
		//Get sheet by name from the workbook
		XSSFSheet sheet = workbook.getSheet(sheetName);

		boolean success = true;
		if (sheet == null) {
			logger.error("ERROR: Could not found sheet: " + sheetName);
			success = false;
		} else {

			Map<String, Object> cellValues = new HashMap<String, Object>();
			Map<String, String> colsMap = TEAM_PERSONELS_KITS_COLS_MAP;
			
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Ignores the first row (header row)
				if (row.getRowNum() > 0) {
					readRowDataIntoMap(row, colsMap, cellValues, true, "A");

					if (!cellValues.isEmpty()) {
						// Checks found key cell first
						if (cellValues.containsKey(KEY_COL_NAME)) {
							currentCompetition = COMPETITIONS_LIST.get(String.valueOf(cellValues.get(KEY_COL_NAME)));
							if (currentCompetition == null) {
								logger.error("Cannot find a competition for cell value is ", String.valueOf(cellValues.get(KEY_COL_NAME)));
							}
						} else  if (cellValues.size() > 3) {
							// Extracts cell values
							String fullName = trimString(String.valueOf(cellValues.get(COL_NAME_FULLNAME)));
							String shortName = trimString(String.valueOf(cellValues.get(COL_NAME_SHORTNAME)));
							String manager = trimString(String.valueOf(cellValues.get(COL_NAME_MANAGER)));
							String captain = trimString(String.valueOf(cellValues.get(COL_NAME_CAPTAIN)));
							String kitManufacturer = trimString(String.valueOf(cellValues.get(COL_NAME_KITMANUFACTURER)));
							String shirtSponsor = trimString(String.valueOf(cellValues.get(COL_NAME_SHIRTSPONSOR)));
							String president = trimString(String.valueOf(cellValues.get(COL_NAME_PRESIDENTCHAIRMAN)));
							String logo = trimString(String.valueOf(cellValues.get(COL_NAME_LOGO)));
							logger.info(String.format("Reading cell [%s, %s, %s, %s,%s, %s, %s, %s]", 
									fullName, shortName, manager, captain, kitManufacturer, shirtSponsor, president, logo));

							// Creates objects
							Squad squad = createSquad(fullName, shortName, kitManufacturer, shirtSponsor, logo);
							
							// Creates the squad's manager and president
							if (manager != null && !"null".equals(manager) && manager.length() > 0) {
								// Creates new hero
								// Parses player fullname to get first and last name separately
								String[] namesAndRole = parsePlayerName(manager);
								String firstName = namesAndRole[0], lastName = namesAndRole[1];
								
								playersCounter++;
								
								Hero hero = createHero(firstName, lastName, manager);
								createSquadMember(squad, hero, "", null, HERO_ROLE_MANAGER, HERO_STATUS_ACTIVE);
							}
							
							if (president != null && !"null".equals(president) && president.length() > 0) {
								// Creates new hero
								// Parses player fullname to get first and last name separately
								String[] namesAndRole = parsePlayerName(president);
								String firstName = namesAndRole[0], lastName = namesAndRole[1];
								
								playersCounter++;
								
								Hero hero = createHero(firstName, lastName, president);
								createSquadMember(squad, hero, "", null, HERO_ROLE_PRESIDENT, HERO_STATUS_ACTIVE);
							}
	
						} else {
							logger.error("Reading cell error: Number of columns is not identical with expected. Ignore row!");
						}
					}
				}
			}
		}

		return success;
	}

	private boolean importLeaguesCalendar(XSSFWorkbook  workbook) {
		
		for (String sheetName : LEAGUES_CALENDAR_SHEETS_LIST) {

			logger.info("Reading sheet: " + sheetName);
			
			//Get sheet by name from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			if (!getCurrentCountryAndCompetitionBySheet(sheetName)) return false; // Unknown sheet name
			
			LocalDate matchCal = null;
			LocalTime kickoff = null;
			boolean fulltime = false;

			
			boolean success = true;
			if (sheet == null) {
				logger.error("ERROR: Could not found sheet: " + sheetName);
				success = false;
			} else {

				Map<String, Object> cellValues = new HashMap<String, Object>();
				Map<String, String> colsMap = LEAGUES_CALENDAR_COLS_MAP;

				//Iterate through each rows from first sheet
				Iterator<Row> rowIterator = sheet.iterator();
				while(rowIterator.hasNext()) {
					Row row = rowIterator.next();
					
					// Ignores the first row (header row)
					if (row.getRowNum() > 0) {
						readRowDataIntoMap(row, colsMap, cellValues, false, null);

						if (!cellValues.isEmpty()) {
							// Checks found row has a single cell
							if (cellValues.size() == 1) {
								// Founds a date cell
								String string = trimString(String.valueOf(cellValues.get(COL_NAME_DATE)));
								if (isDateString(string)) {
									// Date cell
									matchCal = parseDateString(string);
								} else {
									logger.error("Unknown cell value");
									return false;
								}
							} else if (cellValues.size() == 2) {
								// Extracts cell values
								String fixture = trimString(String.valueOf(cellValues.get(COL_NAME_FIXTURE)));
								String fulltimeOrKickoff = trimString(String.valueOf(cellValues.get(COL_NAME_KICKOFF)));
								if ("Full time".equals(fulltimeOrKickoff)) {
									fulltime = true;
								} else {
									fulltime = false;
									Date time = (Date) cellValues.get(COL_NAME_KICKOFF);
									kickoff = time.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
								}
								logger.info(String.format("Reading cell [%s, %s, %s]", fixture, fulltimeOrKickoff, kickoff.toString()));
								
								// Parses fixture
								parseAndSaveSchedule(1, matchCal, kickoff, fixture, fulltime);							

							} else {
								logger.error("Reading cell error: Number of columns is not identical with expected. Ignore row!");
							}
						}
					}
				}


			}

			if (!success) {
				return success;
			}
		}
		return true;
	}
	
	private void parseAndSaveSchedule(int week, LocalDate matchCal, LocalTime kickoff, String matchString, boolean fulltime) {

		int goals1 = 0, goals2 = 0;
		String team1 = "", team2 = "";

		if (fulltime) {

			// Full-time match
			// ie: Man United 2-0 Tottenham
			// So parses the string in form of "[Team name 1] [Goal1]-[Goal2] [Team name 2]"
			String[] parsed = matchString.split("[-]");

			String string = parsed[0].trim();
			int goalIndex = string.lastIndexOf(" ");
			// Gets last digit as goals
			String digit = string.substring(goalIndex).trim();
			String teamName = string.substring(0, goalIndex).trim(); 
			goals1 = Integer.valueOf(digit);
			team1 = teamName;

			string = parsed[1].trim();
			goalIndex = string.indexOf(" ");
			// Gets first digit as goals
			digit = string.substring(0, goalIndex).trim();
			teamName = string.substring(goalIndex).trim(); 
			goals2 = Integer.valueOf(digit);
			team2 = teamName;

			logger.info(String.format("Team1 %s Goal %d - Team2 %s Goal %d", team1, goals1, team2, goals2));
		} else {
			// Future match
			// ie: Man United V Swansea City
			final String separator = " V ";
			int index = matchString.indexOf(separator);
			if (index < 0) {
				logger.error("Cannot find a matchup as: " + matchString);
				return;
			}  else {
				String[] parsed = {matchString.substring(0, index), matchString.substring(index + separator.length())};
				team1 = parsed[0].trim();
				team2 = parsed[1].trim();

				logger.info(String.format("Team1 %s V Team2 %s", team1, team2));
			}
		}

		/*
		 * Finds squads
		 */
		Squad squad1 = footballunService.findSquadByName(team1, currentCompetition.getId());
		Squad squad2 = footballunService.findSquadByName(team2, currentCompetition.getId());

		if (squad1 != null && squad2 != null) {
			// Creates matchup
			Matchup matchup = new Matchup();
			matchup.setCompetition(currentCompetition);
			matchup.setMatchday(week);
			matchup.setName(String.format("%s V %s", team1, team2));
			
			matchup.setStartAt(matchCal);
			matchup.setEndAt(matchup.getStartAt());
			
			if (fulltime) {
				matchup.setStatus(footballunService.getMatchupStatusFullTime());	
			} else {

				matchup.setKickoff(kickoff);
				matchup.setStatus(footballunService.getMatchupStatusNotBegin());
			}

			// Creates matchup details
			MatchupDetail detail1 = new MatchupDetail();
			detail1.setSquad(squad1);
			detail1.setGoal(goals1);
			detail1.setMatchup(matchup);

			MatchupDetail detail2 = new MatchupDetail();
			detail2.setSquad(squad2);
			detail2.setGoal(goals2);
			detail2.setMatchup(matchup);

			LinkedHashSet<MatchupDetail> details = new LinkedHashSet<MatchupDetail>();
			details.add(detail1);
			details.add(detail2);
			matchup.setDetails(details);

			footballunService.saveMatchup(matchup);
			footballunService.saveMatchupDetail(detail1);
			footballunService.saveMatchupDetail(detail2);
		} else if (squad1 == null) {
			logger.error("Cannot find squad for the squad:  " + team1);
		} else {
			logger.error("Cannot find squad for the squad:  " + team2);
		}
	}

	private boolean importPlayers(XSSFWorkbook  workbook) {

		for (String sheetName : LEAGUES_PLAYERS_SHEETS_LIST) {

			logger.info("Reading sheet: " + sheetName);
			
			//Get sheet by name from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			if (!getCurrentCountryAndCompetitionBySheet(sheetName)) return false; // Unknown sheet name
			
			boolean success = true;
			if (sheet == null) {
				logger.error("ERROR: Could not found sheet: " + sheetName);
				success = false;
			} else {

				Map<String, Object> cellValues = new HashMap<String, Object>();
				Map<String, String> colsMap = LEAGUES_PLAYER_COLS_MAP;
				
				Squad currentSquad = null;
				boolean isOutOnLoan = false;
				
				//Iterate through each rows from first sheet
				Iterator<Row> rowIterator = sheet.iterator();
				while(rowIterator.hasNext()) {
					Row row = rowIterator.next();

					// Ignores the first row (header row)
					if (row.getRowNum() > 0) {
						readRowDataIntoMap(row, colsMap, cellValues, true, "A");

						if (!cellValues.isEmpty()) {
							// Checks found key cell first
							if (cellValues.containsKey(KEY_COL_NAME)) {
								String clubNameOrOutOnLoan = trimString(String.valueOf(cellValues.get(KEY_COL_NAME)));
								if ("Out on loan".equals(clubNameOrOutOnLoan)) {
									isOutOnLoan = true;
								} else {
									isOutOnLoan = false;

									currentSquad = footballunService.findSquadByFullName(clubNameOrOutOnLoan, currentCompetition.getId());
									if (currentSquad == null) {
										logger.error("Cannot find a squad for name is ", String.valueOf(cellValues.get(KEY_COL_NAME)));
									}
								}
							} else  {
								// Extracts cell values
								String shirtNo = trimString(String.valueOf(cellValues.get(COL_NAME_SHIRTNUMBER)));
								String position = trimString(String.valueOf(cellValues.get(COL_NAME_POSITION)));
								String player = String.valueOf(cellValues.get(COL_NAME_PLAYER)).trim();

								logger.info(String.format("Reading cell [%s, %s, %s]", 
										shirtNo, position, player));
								if (player != null && player.length() > 0) {
									playersCounter++;
								}
								
								// Creates objects
								createPlayer(currentSquad, isOutOnLoan, shirtNo, position, player);
							}
						}
					}
				}
			}
		}

		return true;
	}
	
	private void createPlayer(Squad squad, boolean isOutOnLoan, String shirtNo, String position, String player) {
		
		// Parses number 
		Integer number;
		if (shirtNo.contains(".")) {
			shirtNo = shirtNo.substring(0, shirtNo.indexOf("."));
		}
		shirtNo = shirtNo.replaceAll("\\D", " ").trim(); // Replaces all non-digit characters by space and then strim it out
		
		if (shirtNo == null || shirtNo.length() < 1) {
			number = null; // unknown shirt number
		} else {
			number = Integer.valueOf(shirtNo);
		}
		
		// Parses player fullname to get first and last name separately
		String[] namesAndRole = parsePlayerName(player);
		String firstName = namesAndRole[0], lastName = namesAndRole[1], role;
		if (namesAndRole[2].length() > 0) {
			role = namesAndRole[2];
		} else {
			role = HERO_ROLE_PLAYER;
		}
		
		// Creates new hero
		Hero hero = createHero(firstName, lastName, player);
		createSquadMember(squad, hero, position, number, role, isOutOnLoan ? HERO_STATUS_OUTONLOAN : HERO_STATUS_ACTIVE);
	}
	
	private String[] parsePlayerName(String player) {
		String firstName = "", lastName = "", role = "";
	
		if (player != null) {
			
			String name, caption;
			int indexCaption = player.indexOf("("); // Find index of and open parenthesis known as 'a caption'
			if (indexCaption >= 0) {
				name = player.substring(0, indexCaption);
				caption = player.substring(indexCaption);
			} else {
				name = player;
				caption = "";
			}
			name = name.trim();
			// Parses name
			int firstSpaceIndex = name.indexOf(" ");
			if (firstSpaceIndex > 0) {
				firstName = name.substring(0, firstSpaceIndex).trim();
				lastName = name.substring(firstSpaceIndex).trim();
			} else {
				// Player's name has single word 
				firstName = name;
				lastName = name;
			}
			
			if (firstName == null || lastName == null) {
				logger.error("Unexpected that name is NULL for input string: " + player);
			}
			
			// Parses caption to find player role, ie: captain, vice-captain, third-captain
			if (caption.length() > 0) {
				if (caption.contains("vice-captain") 
						|| caption.contains("vice captain")
						|| caption.contains("Vice-captain")) {
					role = HERO_ROLE_VICECAPTAIN;
				} else if (caption.contains("third-captain")
						|| caption.contains("third captain")
						|| caption.contains("Third-captain")
						|| caption.contains("3rd-captain")
						|| caption.contains("3rd captain")) {
					role = HERO_ROLE_3RDCAPTAIN;
				} else if (caption.contains("captain")
						|| caption.contains("captain")) {
					role = HERO_ROLE_CAPTAIN;
				} else {
					logger.warn("Ignore caption: " + caption);
				}
			}
		}
		
		return new String[]{firstName, lastName, role};
	}
	
	private Hero createHero(String firstName, String lastName, String fullName) {
		Hero hero = new Hero();
		hero.setFirstName(firstName);
		hero.setLastName(lastName);
		hero.setName(fullName);
		footballunService.saveHero(hero);
		createdPlayersCounter++;
		return hero;
	}
	
	private void createSquadMember(Squad squad, Hero hero, String position, Integer shirtNumber, String role, String status) {
		SquadMember member;
		member = new SquadMember();
		member.setSquad(squad);
		member.setHero(hero);
		member.setHeroRole(HERO_ROLES.get(role));
		member.setHeroStatus(HERO_STATUS.get(status));
		member.setPosition(POSITIONS.get(position));
		member.setShirtNumber(shirtNumber);

		footballunService.saveSquadMember(member);
	}
	
	/**
	 * Calculates matchday for matchups in competitions
	 */
	public boolean importMatchdays(XSSFWorkbook  workbook) {
		
		String sheetName = MATCHDAYS_SHEET;			
		logger.info("Reading sheet: " + sheetName);
		
		//Get sheet by name from the workbook
		XSSFSheet sheet = workbook.getSheet(sheetName);

		boolean success = true;
		if (sheet == null) {
			logger.error("ERROR: Could not found sheet: " + sheetName);
			success = false;
		} else {

			Map<String, Object> cellValues = new HashMap<String, Object>();
			Map<String, String> colsMap = MATCHDAYS_COLS_MAP;
			
			int matchday, prevMatchday = 0;
			Date startDate, prevDate = null;
			
			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Ignores the first row (header row)
				if (row.getRowNum() > 0) {
					readRowDataIntoMap(row, colsMap, cellValues, true, "A");

					if (!cellValues.isEmpty()) {
						// Checks found key cell first
						if (cellValues.containsKey(KEY_COL_NAME)) {
							currentCompetition = COMPETITIONS_LIST.get(String.valueOf(cellValues.get(KEY_COL_NAME)));
							if (currentCompetition == null) {
								logger.error("Cannot find a competition for cell value is ", String.valueOf(cellValues.get(KEY_COL_NAME)));
							}
						} else if (cellValues.size() == 2){
							// Extracts cell values
							matchday = (int) Math.floor((Double) cellValues.get(COL_NAME_MATCHDAY));
							startDate = (Date)  cellValues.get(COL_NAME_MATCHDAY_STARTDATE);
							
							logger.info(String.format("Reading cell pair [%d, %s]", matchday, startDate.toString()));
							
							if (prevMatchday > 0) {
								updateMatchday(prevMatchday, prevDate, startDate, false);
							} 
							
							if (currentCompetition.getTotalMatchdays() == matchday) {
								// The last round
								// Don't worry from/to date is the same date because it has to be like that on the last day :)
								updateMatchday(matchday, startDate, startDate, true);
							}
							
							prevMatchday = matchday;
							prevDate = startDate;
							
						} else {
							logger.error("Reading cell error: Number of columns is not identical with expected. Ignore row!");
						}
					}
				}
			}
		}
		return success;
	}
		
	private void updateMatchday(int matchday, Date prevDate, Date startDate, boolean isLastMatchday) {
		LocalDate from = Instant.ofEpochMilli(prevDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate to = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		if (!isLastMatchday) {
			to = to.minusDays(1);
		}
		
		// Finds matchups has start date between from and to date
		List<Matchup> matchups = footballunService.findMatchupByStartAtBetween(currentCompetition.getId(), from, to);
		for (Matchup matchup : matchups) {
			// Updates the matchups' matchday
			matchup.setMatchday(matchday);
			footballunService.saveMatchup(matchup);
		}
	}
	
	private void readRowDataIntoMap(Row row, Map<String, String> colsMaps,  Map<String, Object> values, boolean searchForKeyCol, String keyCol) {

		values.clear();

		//For each row, iterate through each columns
		Iterator<Cell> cellIterator = row.cellIterator();
		while(cellIterator.hasNext()) {
			Cell cell = cellIterator.next();

			Boolean colBooleanVal;
			Number colIntVal;
			String colStringVal;
			Date colDateVal;
			
			String colLetter = CellReference.convertNumToColString(cell.getColumnIndex());

			System.out.println(colLetter);
			String colName = colsMaps.get(colLetter);
			if (colName != null) {
				// Get cell value
				//CellValue cellValue = evaluator.evaluate(cell);
				switch(evaluator.evaluateInCell(cell).getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t\t");
					colBooleanVal = cell.getBooleanCellValue();
					if (searchForKeyCol && colLetter.equals(keyCol)) {
						// Found a key cell
						values.put(KEY_COL_NAME, colBooleanVal);
					} else {
						values.put(colName, colBooleanVal);
					}

					break;

				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t\t");

					if (HSSFDateUtil.isCellDateFormatted(row.getCell(cell.getColumnIndex()))) {
						colDateVal = row.getCell(cell.getColumnIndex()).getDateCellValue();
						if (searchForKeyCol && colLetter.equals(keyCol)) {
							// Found a key cell
							values.put(KEY_COL_NAME, colDateVal);
						} else {
							values.put(colName, colDateVal);
						}
					} else {
						colIntVal =  cell.getNumericCellValue();
						if (searchForKeyCol && colLetter.equals(keyCol)) {
							// Found a key cell
							values.put(KEY_COL_NAME, colIntVal);
						} else {
							values.put(colName, colIntVal);
						}
					}

					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t\t");
					colStringVal = cell.getStringCellValue();
					if (!colName.equals(colStringVal)) {
						// This is not a header cell
						if (searchForKeyCol && colLetter.equals(keyCol)) {
							// Found a key cell
							values.put(KEY_COL_NAME, colStringVal);
						} else {
							values.put(colName, colStringVal);
						}
					}

					break;
				default:
					System.out.print("Unkown column type \t\t");
					break;
				}
			}
		}
	}
	
	private boolean getCurrentCountryAndCompetitionBySheet(String sheetName) {
		// For sheet naming convention we understood that sheet data for a competition always begins with a competition key name.
		// Checks contructor method for the keys list
		// Gets key
		String competitionKey = sheetName.substring(0, sheetName.indexOf("_"));
		return getCurrentCountryAndCompetition(competitionKey);
	}
	
	private boolean getCurrentCountryAndCompetition(String competitionKey) {
		// Sets up references data for the sheet
		currentCountry = COUNTRIES_LIST.get(competitionKey);
		if (currentCountry == null) {
			logger.error("Cannot find a country for: " + competitionKey);
			return false;
		}
		logger.info("Current country: " + currentCountry.getName());
		
		currentCompetition = COMPETITIONS_LIST.get(competitionKey);
		if (currentCompetition == null ) {
			logger.error("Cannot find a competition for: " + competitionKey);
			return false;
		}
		logger.info("Current competition: " + currentCompetition.getName());
		
		return true;
	}
	
	/**
	 * Creates team and squad if it's not yet created.
	 * 
	 * Also initialize its current standing.
	 */
	private void calculateStandings() {

		for (Map.Entry<String, Competition> entry : COMPETITIONS_LIST.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
			Competition competition = entry.getValue();
			footballunService.recalculateStandingForTheCompetition(competition.getId());
		}
	}
	
	private Squad createSquad(String fullName, String shortName, String kitManufacturer, String shirtSponsor, String logo) {
		Competition competition = currentCompetition;
		Squad squad;
		Team team = footballunService.findTeamByName(shortName);
		if (team == null) {

			/*
			 *  Creates a new team
			 */
			team = new Team();
			team.setName(shortName);
			team.setFullName(fullName);
			team.setCountry(currentCountry);

			footballunService.save(team);
			logger.info("Created team: " + team.getName());

			/*
			 *  Creates squad for the competition
			 */
			squad = new Squad();
			squad.setCompetition(competition);
			squad.setGeneration("First Team");
			squad.setName(shortName);
			squad.setFullName(fullName);
			squad.setLogo(logo);
			squad.setTeam(team);
			squad.setKitManufacturer(kitManufacturer);
			squad.setShirtSponsor(shirtSponsor);
			footballunService.saveSquad(squad);

			footballunService.saveSquad(squad);
			logger.info("Created squad for team: " + team.getName());

			/*
			 * Creates standing for the squad
			 */
			footballunService.createStandingForSquad(squad);
			logger.info("Created standing for squad: " + team.getName());
		} else {
			squad = footballunService.findSquadByName(shortName, competition.getId());
			squad.setName(shortName);
			squad.setFullName(fullName);
			squad.setKitManufacturer(kitManufacturer);
			squad.setShirtSponsor(shirtSponsor);
			squad.setLogo(logo);
			logger.info("Query squad: " + team.getName());
		}

		return squad;
	}

	
	private boolean isDateString(String str) {
		String upper = str.toUpperCase();
		
		for (String day : MON_2_FRI) {
			if (upper.contains(day)) {
				return true;
			}
		}
		return false;
	}

	private LocalDate parseDateString(String dateString) {
		
		// ie: Sunday 13th December 2015
		String[] arr = dateString.split("[ ]");
		if (arr.length < 4) return null;
		
		// Find and remove postfix 'st', 'nd', 'rd', 'th' from day number 
		String day = arr[1];
		arr = day.split("\\D");
		if (arr.length < 1) return null;
		String dayN = arr[0];
		
		String parsed = dateString.replace(day, dayN);

		LocalDate date = null;
		try {
			DateTimeFormatter formatter =
					DateTimeFormatter.ofPattern("EEEE d MMMMM yyyy");
			date = LocalDate.parse(parsed, formatter);
			logger.info("Parsed date " + date.toString());
		}
		catch (DateTimeParseException exc) {
			System.out.printf("%s is not parsable!%n", parsed);
			throw exc;      // Rethrow the exception.
		}

		return date;
	}

	private LocalTime parseTimeString(String timeString) {

		// Example form of input timeString as: "12:45 PM"

		LocalTime time = null;
		try {
			DateTimeFormatter formatter =
					DateTimeFormatter.ofPattern("HH:mm a");
			time = LocalTime.parse(timeString, formatter);
			logger.info("Parsed date " + time.toString());
		}
		catch (DateTimeParseException exc) {
			System.out.printf("%s is not parsable!%n", timeString);
			throw exc;      // Rethrow the exception.
		}

		return time;
	}

	
	private Competition findOrCreateCompetition(String name) {
		Competition competition = null;
		try{
			if (currentCountry == null) {
				currentCountry = COUNTRIES_LIST.get(name);
			}
			competition = footballunService.findCompetitionByName(name);
			if (competition == null) {
				competition = new Competition();
				competition.setName(name);
				competition.setType("LEAGUE");
				competition.setYearFrom(currentYear);
				competition.setYearTo(currentYear + 1);
				
				competition.setHostCountry(currentCountry);
				footballunService.save(competition);
				logger.info("Created competition: " + competition.getName());
			}
			competition.setHostCountry(currentCountry);
			footballunService.save(competition);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return competition;
	}
	
	private Country findCountry(String name) {
		Country country = footballunService.findCountryByName(name);
		if (country == null) {
			logger.error("Cannot find country: " + name);
		}
		return country;
	}
	
	/**
	 * Removes some unwanted string from input string.
	 * 
	 * @param input String
	 * @return substring of input starts from begin of input till first meet  unwanted characters,
	 * or orginal string if not found any such characters
	 */
	private String trimString(String input) {
		
		int index;
		if (input.contains("(")) {
			index = input.indexOf("(");
			if (index > 0) {
				input = input.substring(0, index - 1);
			} else {
				input = "";
			}
		}
		if (input.contains("[")) {
			index = input.indexOf("[");
			if (index > 0) {
				input = input.substring(0, index - 1);
			} else {
				input = "";
			}
		}
		return input.trim();
	}
}


