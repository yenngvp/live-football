/**
 * @author: yen.nt
 * @created on Nov 27, 2015
 */
package com.footballun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor.MatchCallback;
import org.springframework.stereotype.Component;

import com.footballun.model.Competition;
import com.footballun.model.Country;
import com.footballun.model.Matchup;
import com.footballun.model.MatchupDetail;
import com.footballun.model.MatchupStatus;
import com.footballun.model.MatchupStatus.MatchupStatusCode;
import com.footballun.model.Squad;
import com.footballun.model.Standing;
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
	
	
	private static final String[] STANDING_SHEETS_LIST = {"EPL_Standing", "Bundesliga_Standing", "LigaBBVA_Standing", "SerieA_Standing", "France_Ligue1_Standing"};
	
	private static final String CLUB_REFS_SHEET = "Club_Refs";
	private static final String TEAM_PERSONELS_KITS_SHEET = "Personels_Kits";
	private static final String[] LEAGUES_CALENDAR_SHEETS_LIST = {"EPL_Cal", "Bundesliga_Cal", "LigaBBVA_Cal", "SerieA_Cal", "Ligue1_Cal"};
	private static final String STADIA_SHEET = "Stadia";
//	private static final String[] LEAGUES_PLAYERS_SHEETS_LIST = {"EPL_Players", "Bundesliga_Players", "LigaBBVA_Players", "SerieA_Players", "Ligue1_Players"};
	private static final String[] LEAGUES_PLAYERS_SHEETS_LIST = {"EPL_Players"};
	
	/*
	 * Columns mapping
	 */
	private static final Map<String, String> STANDING_COLS_MAP = new HashMap<String, String>();
	private static final Map<String, String> TEAM_PERSONELS_KITS_COLS_MAP = new HashMap<String, String>();
	private static final Map<String, String> LEAGUES_CALENDAR_COLS_MAP = new HashMap<String, String>();
	private static final Map<String, String> LEAGUES_PLAYER_COLS_MAP = new HashMap<String, String>();
	
	/*
	 *  The pairs (key, value) below must be always going together with the sheet data
	 */
	private static final String[] COLUMN_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
	
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
	private static final String COL_NAME_CLUB = "Club";
	private static final String COL_NAME_SHIRTNUMBER = "No.";
	private static final String COL_NAME_POSITION = "Position";
	private static final String COL_NAME_PLAYER = "Player";
	private static final String[] LEAGUES_PLAYER_COL_HEADERS = {
		COL_NAME_CLUB, COL_NAME_SHIRTNUMBER, COL_NAME_POSITION, COL_NAME_PLAYER
	};

	
	private static final String[] MON_2_FRI = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
	
	private static final String[] KEY_COMPETITION_NAMES = {"EPL", "Bundesliga", "LigaBBVA", "SerieA", "Ligue1"};
	private static final String[] COMPETITION_NAMES = {"English Premier League", "Bundesliga", "La Liga", "Serie A", "France Ligue1"};
	private static final String[] COUNTRIES_NAMES = {"England", "Germany", "Spain", "Italy", "France"};
	
	private static final String KEY_COL_NAME = "_KEY_COL_NAME_";
	
	private FootballunService footballunService;

	private Country currentCountry;
	private Competition currentCompetition;
	private int currentYear = 2015;
	
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
			TEAM_PERSONELS_KITS_COLS_MAP.put(COLUMN_LETTERS[i + 1], TEAM_PERSKIT_COL_HEADERS[i]);
		}
		
		// Leagues calendar columns mapping
		for (int i = 0; i < LEAGUES_CAL_COL_HEADERS.length; i++) {
			LEAGUES_CALENDAR_COLS_MAP.put(COLUMN_LETTERS[i], LEAGUES_CAL_COL_HEADERS[i]);
		}

		
		// Leagues player map
		for (int i = 0; i < LEAGUES_PLAYER_COL_HEADERS.length; i++) {
			LEAGUES_PLAYER_COLS_MAP.put(COLUMN_LETTERS[i], LEAGUES_PLAYER_COL_HEADERS[i]);
		}
		
		// Builds competitions list and countries list
		for (int i = 0; i < COUNTRIES_NAMES.length; i++) {
			COMPETITIONS_LIST.put(KEY_COMPETITION_NAMES[i], findOrCreateCompetition(COMPETITION_NAMES[i]));
			COUNTRIES_LIST.put(KEY_COMPETITION_NAMES[i], findCountry(COUNTRIES_NAMES[i]));
		}
	}
	
	public void importExcel() {
		
		FileInputStream  file = null;
		XSSFWorkbook  workbook = null;
		try {

			file = new FileInputStream(new File(DATA_PATH + FILE_NAME));
		     
		    //Get the workbook instance for XLS file 
			workbook = new XSSFWorkbook (file);
		 
			// Cleanup database before doing import?
			
	    
			if (!importTeamsData(workbook)) {
				logger.error("Importing teams data failed. Should stop further processing!");
				return;
			}
			

			if (!importLeaguesCalendar(workbook)) {
				logger.error("Importing leagues calenddar failed. Should stop further processing!");
				return;
			}
			
			if (!importPlayers(workbook)) {
				logger.error("Importing leagues player failed. Should stop further processing!");
				return;
			}

		    
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
	
	private boolean importTeamsData(XSSFWorkbook  workbook) {

		String sheetName = TEAM_PERSONELS_KITS_SHEET;			

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
							Squad squad = createSquad(fullName, shortName, logo);
							squad.setKitManufacturer(kitManufacturer);
							squad.setShirtSponsor(shirtSponsor);
							footballunService.saveSquad(squad);

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

			//Get sheet by name from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			if (!getCurrentCountryAndCompetitionBySheet(sheetName)) return false; // Unknown sheet name
			
			Calendar matchCal = GregorianCalendar.getInstance();
			boolean fulltime = false;
			Calendar kickoff  = GregorianCalendar.getInstance();
			
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
									parseDateString(matchCal, string);
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
									kickoff.setTime((Date) cellValues.get(COL_NAME_KICKOFF));
								}
								logger.info(String.format("Reading cell [%s, %s, %s]", fixture, fulltimeOrKickoff, kickoff.getTime().toString()));

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
	
	private void parseAndSaveSchedule(int week, Calendar matchCal, Calendar kickoff, String matchString, boolean fulltime) {

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
			String[] parsed = matchString.split("[V]");
			team1 = parsed[0].trim();
			team2 = parsed[1].trim();

			logger.info(String.format("Team1 %s V Team2 %s", team1, team2));
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
			if (fulltime) {
				matchup.setStatus(footballunService.getMatchupStatusFullTime());	
			} else {

				Calendar	calendar = (Calendar) matchCal.clone();
				calendar.set(Calendar.HOUR_OF_DAY, kickoff.get(Calendar.HOUR_OF_DAY));
				calendar.set(Calendar.MINUTE, kickoff.get(Calendar.MINUTE));						
				matchup.setStartAt(calendar.getTime());
				calendar.add(Calendar.HOUR, 2); // Supposed to have 2-hours game long
				matchup.setEndAt(calendar.getTime());

				matchup.setKickoff(kickoff.getTime());
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
		} else {
			logger.error("Cannot find squad for the team " + team1 + " or " + team2);
		}
	}

	private boolean importPlayers(XSSFWorkbook  workbook) {

		for (String sheetName : LEAGUES_PLAYERS_SHEETS_LIST) {

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
								currentSquad = footballunService.findSquadByName(trimString(String.valueOf(cellValues.get(KEY_COL_NAME))), currentCompetition.getId());
								if (currentSquad == null) {
									logger.error("Cannot find a squad for name is ", String.valueOf(cellValues.get(KEY_COL_NAME)));
								}
							} else  if (cellValues.size() > 2) {
								// Extracts cell values
								String shirtNo = trimString(String.valueOf(cellValues.get(COL_NAME_SHIRTNUMBER)));
								String position = trimString(String.valueOf(cellValues.get(COL_NAME_POSITION)));
								String player = trimString(String.valueOf(cellValues.get(COL_NAME_PLAYER)));
								logger.info(String.format("Reading cell [%s, %s, %s]", 
										shirtNo, position, player));

								// Creates objects
								// createPlayer(currentSquad, shirtNo, position, player);
							} else {
								logger.error("Reading cell error: Number of columns is not identical with expected. Ignore row!");
							}
						}
					}
				}
			}

			return success;
		}

		return true;
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
				switch(cell.getCellType()) {
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
	private void createTeamStanding(int index, int standingPosition, String teamName,
			int gamePlayed, int wins, int draws, int loses, int goalScored, int goalAgainst, int points) {
		System.out.println(String.format("%d,%s,%d,%d,%d,%d,%d,%d,%d", standingPosition, teamName, gamePlayed, wins, draws, loses, goalScored, goalAgainst, points));
		
		Squad squad = createSquad(teamName, null, null);
		// Updates standing for the squad
		Standing standing = footballunService.findStandingBySquad(squad);
		if (standing != null) {
			standing.setPreviousPosition(standing.getCurrentPosition());
			standing.setCurrentPosition(standingPosition);
			standing.setPlayed(gamePlayed);
			standing.setWon(wins);
			standing.setDrawn(draws);
			standing.setLost(loses);
			standing.setGoalsScored(goalScored);
			standing.setGoalsAgainst(goalAgainst);
			standing.setPoint(points);
			
			footballunService.saveStanding(standing);
			logger.info("Created standing for squad: " + squad.getName());
		}
	}
	
	private Squad createSquad(String fullName, String shortName, String logo) {
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

	private Calendar parseDateString(Calendar calendar, String dateString) {
		
		// ie: Sunday 13th December 2015
		String[] arr = dateString.split("[ ]");
		if (arr.length < 4) return null;
		
		// Find and remove postfix 'st', 'nd', 'rd', 'th' from day number 
		String day = arr[1];
		arr = day.split("\\D");
		if (arr.length < 1) return null;
		String dayN = arr[0];
		
		String parsed = dateString.replace(day, dayN);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMMM yyyy");
		try {
			calendar.clear();
			calendar.setTime(dateFormat.parse(parsed));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("parseDateString error " + dateString);
		}
		
		logger.info("date: " + calendar);
	
		return calendar;
	}
	
	private Competition findOrCreateCompetition(String name) {
		Competition competition = null;
		try{
			competition = footballunService.findCompetitionByName(name);
			if (competition == null) {
				competition = new Competition();
				competition.setName(name);
				competition.setType("LEAGUE");
				competition.setYearFrom(2015);
				competition.setYearTo(2016);
				if (currentCountry == null) {
					currentCountry = COUNTRIES_LIST.get(name);
				}
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


