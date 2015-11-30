/**
 * @author: yen.nt
 * @created on Nov 27, 2015
 */
package com.footballun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
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
	 */
	private static final String[] standingSheetsToRead = {"EPL_Standing", "Bundesliga_Standing", "LigaBBVA_Standing", "SerieA_Standing", "France_League1_Standing"};
	private static final String[] competitionsList = {"English Premier League", "Bundesliga", "LigaBBVA", "SerieA", "France League1"};
	private static final String[] countriesList = {"England", "Germany", "Spain", "Italy", "France"};
	
	private static final Map<String, String> standingColsMap = new HashMap<String, String>();
	
	private static final List<Country> countries = new ArrayList<Country>();
	
	// The pairs (key, value) below must be always going together with the sheet data
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
	private static final String[] columnsKey = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	private static final String[] columnsVal = {COL_NAME_POS,
		COL_NAME_TEAM, 
		COL_NAME_GAMES_PLAYED,
		COL_NAME_WINS,
		COL_NAME_DRAWS,
		COL_NAME_LOSE,
		COL_NAME_GOALS_SCORED,
		COL_NAME_GOALS_AGAINST,
		COL_NAME_GOALS_DIFF,
		COL_NAME_POINTS};
	
	private static final String[] daysInWeek = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
	
	
	private FootballunService footballunService;

	private Country currentCountry;
	private Competition currentCompetition;
	private int currentYear = 2015;
	
	@Autowired
	public DataImporter(FootballunService footballunService) {
		this.footballunService = footballunService;
		
		// Populates columns mapping
		for (int i = 0; i < columnsKey.length; i++) {
			standingColsMap.put(columnsKey[i], columnsVal[i]);
		}
		System.out.println(standingColsMap);
		
		// Looks for countries
		for (String name : countriesList) {
			Country country = footballunService.findCountryByName(name);
			if (country != null) {
				countries.add(country);
				logger.info("Created country: " + country.getName());
			} else {
				logger.error("Cannot find country: " + name);
			}
		}
	}
	
	public void importExcel() {
		
		try {

			FileInputStream  file = new FileInputStream(new File(DATA_PATH + FILE_NAME));
		     
		    //Get the workbook instance for XLS file 
			XSSFWorkbook  workbook = new XSSFWorkbook (file);
		 
			// Cleanup database before doing import?
			
//			importTeamAndStanding(workbook);
		    
			importSchedules(workbook);
			
		    file.close();
		    
		    workbook.close();
		    
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void importTeamAndStanding(XSSFWorkbook  workbook) {
		
		int len = standingSheetsToRead.length;
		for (int i = 0; i < len; i++) {
			
			// Sets up references data for the sheet
			currentCountry = getCountry(countriesList[i]);
			logger.info("Current country: " + currentCountry == null ? "" : currentCountry.getName());
			currentCompetition = findOrCreateCompetition(competitionsList[i]);
			if (currentCompetition == null) {
				logger.error("Cannot find competition: " + competitionsList[i] + ". Exiting ...");
				return;
			}
			logger.info("Current competition: " + currentCompetition.getName());
						
			String sheetName = standingSheetsToRead[i];
			
			//Get sheet by name from the workbook
		    XSSFSheet sheet = workbook.getSheet(sheetName);
		     
		    if (sheet == null) {
		    	System.out.println("ERROR: Could not found sheet: " + sheetName);
		    } else {
		    	
		    	int standingPosition;
		    	String teamName;
				int gamePlayed, wins, draws, loses, goalScored, goalAgainst, points;
		    	
				boolean colBooleanVal;
				int colIntVal;
				String colStringVal;
				
		    	//Iterate through each rows from first sheet
		    	Iterator<Row> rowIterator = sheet.iterator();
		    	while(rowIterator.hasNext()) {
		    		Row row = rowIterator.next();
		    		
		    		// Ignores the first row (header row)
		    		if (row.getRowNum() > 0) {
		    			standingPosition = 0;
				    	teamName = "";
						gamePlayed = 0;
						wins = 0;
						draws = 0;
						loses = 0;
						goalScored = 0;
						goalAgainst = 0;
						points = 0;
						
		    			//For each row, iterate through each columns
		    			Iterator<Cell> cellIterator = row.cellIterator();
		    			while(cellIterator.hasNext()) {

		    				Cell cell = cellIterator.next();

		    				colBooleanVal = false;
							colIntVal = 0;
							colStringVal = "";
		    				
		    				String colLetter = CellReference.convertNumToColString(cell.getColumnIndex());
		    				System.out.println(colLetter);
		    				String propertyName = standingColsMap.get(colLetter);
		    				if (propertyName != null && !"".equals((propertyName = propertyName.trim()))) {
		    					
		    					// Get cell value
		    					switch(cell.getCellType()) {
			    				case Cell.CELL_TYPE_BOOLEAN:
			    					System.out.print(cell.getBooleanCellValue() + "\t\t");
			    					colBooleanVal = cell.getBooleanCellValue();
			    					break;
			    				case Cell.CELL_TYPE_NUMERIC:
			    					System.out.print(cell.getNumericCellValue() + "\t\t");
			    					colIntVal =  (int) cell.getNumericCellValue();
			    					break;
			    				case Cell.CELL_TYPE_STRING:
			    					System.out.print(cell.getStringCellValue() + "\t\t");
			    					colStringVal = cell.getStringCellValue();
			    					break;
			    				default:
			    					System.out.print("Unkown column type \t\t");
			    					break;
			    				}

		    					switch (propertyName.toUpperCase()) {
		    					case COL_NAME_POS:
		    						standingPosition = colIntVal;
		    						break;
		    					case COL_NAME_TEAM:
		    						teamName = colStringVal;
		    						break;
		    					case COL_NAME_GAMES_PLAYED:
		    						gamePlayed = colIntVal;
		    						break;
		    					case COL_NAME_WINS:
		    						wins  = colIntVal;
		    						break;
		    					case COL_NAME_DRAWS:
		    						draws = colIntVal;
		    						break;
		    					case COL_NAME_LOSE:
		    						loses = colIntVal;
		    						break;
		    					case COL_NAME_GOALS_SCORED:
		    						goalScored = colIntVal;
		    						break;
		    					case COL_NAME_GOALS_AGAINST:
		    						goalAgainst = colIntVal;
		    						break;
		    					case COL_NAME_GOALS_DIFF:
		    						// no needs
		    						break;
		    					case COL_NAME_POINTS:
		    						points = colIntVal;
		    						break;
		    					default:
		    						System.out.println("ERROR: Don't know the column name: " + propertyName);
		    						break;
		    					}
		    					
		    				}
			    				
		    			}
		    			
		    			// Persists date in row
		    			
		    			createTeams(i, standingPosition, teamName, gamePlayed, wins, draws, loses, goalScored, goalAgainst, points);
		    		}

		    		System.out.println("");
		    	}
		    }
		}
	    
	}
	
	/**
	 * Creates team and squad if it's not yet created.
	 * 
	 * Also initialize its current standing.
	 */
	private void createTeams(int index, int standingPosition, String teamName,
			int gamePlayed, int wins, int draws, int loses, int goalScored, int goalAgainst, int points) {
		System.out.println(String.format("%d,%s,%d,%d,%d,%d,%d,%d,%d", standingPosition, teamName, gamePlayed, wins, draws, loses, goalScored, goalAgainst, points));
		
		// Finds or creates the competition
		Competition competition = currentCompetition;
		Squad squad;
		Team team = footballunService.findTeamByName(teamName);
		if (team == null) {
			
			/*
			 *  Creates a new team
			 */
			team = new Team();
			team.setName(teamName);
			team.setFullName(teamName);
			team.setCountry(currentCountry);
			
			footballunService.save(team);
			logger.info("Created team: " + team.getName());
			
			/*
			 *  Creates squad for the competition
			 */
			squad = new Squad();
			squad.setCompetition(competition);
			squad.setGeneration("First Team");
			squad.setTeam(team);
			
			footballunService.saveSquad(squad);
			logger.info("Created squad for team: " + team.getName());
			
			/*
			 * Creates standing for the squad
			 */
			footballunService.createStandingForSquad(squad);
			logger.info("Created standing for squad: " + team.getName());
		} else {
			squad = footballunService.findSquadByName(teamName, competition.getId());
			logger.info("Query squad: " + team.getName());
		}
		
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
			logger.info("Created standing for squad: " + team.getName());
		}
	}
	
	private void importSchedules(XSSFWorkbook  workbook) {
		int len = competitionsList.length;
		for (int i = 0; i < len; i++) {
			
			// Sets up references data for the sheet
			currentCountry = getCountry(countriesList[i]);
			logger.info("Current country: " + currentCountry == null ? "" : currentCountry.getName());
			currentCompetition = findOrCreateCompetition(competitionsList[i]);
			if (currentCompetition == null) {
				logger.error("Cannot find competition: " + competitionsList[i] + ". Exiting ...");
				return;
			}
			logger.info("Current competition: " + currentCompetition.getName());
						
			String sheetName = competitionsList[i];
			
			//Get sheet by name from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			if (sheet == null) {
				System.out.println("ERROR: Could not found sheet: " + sheetName);
			} else {

				int week = 0;
				String dateString = null;
				String matchString = null;

				boolean colBooleanVal;
				int colIntVal;
				String colStringVal;

				//Iterate through each rows from first sheet
				Iterator<Row> rowIterator = sheet.iterator();
				while(rowIterator.hasNext()) {
					Row row = rowIterator.next();

					//For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
					while(cellIterator.hasNext()) {

						Cell cell = cellIterator.next();

						// Get cell value
						switch(cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							System.out.print(cell.getBooleanCellValue() + "\t\t");
							colBooleanVal = cell.getBooleanCellValue();
							logger.warn("Interesting! Found a boolean cell here!!!!");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							System.out.print("Week " + cell.getNumericCellValue() + "\t\t");
							week =  (int) cell.getNumericCellValue();
							break;
						case Cell.CELL_TYPE_STRING:
							System.out.print(cell.getStringCellValue() + "\t\t");
							colStringVal = cell.getStringCellValue();

							// Checks the string is a date or a fixture
							colStringVal = colStringVal.trim();
							if (colStringVal.length() > 0) {

								if (isDateString(colStringVal)) {
									dateString = colStringVal;
									logger.info("Reading cell: " + colStringVal + " is a date cell");
								} else {
									matchString = colStringVal;
									// Parses and saves the schedule
									if (week > 0 && dateString != null && matchString != null) {
										parseAndSaveSchedule(week, dateString, matchString);	
										dateString = null;
										matchString = null;
									} else {
										logger.error("Something wrong with reading data for week: " + week);
									}
									
									logger.info("Reading cell: " + colStringVal + " is a fixture");
								}
							}

							break;
						default:
							logger.warn("Hit an empty cell");
							break;
						}

					}
				}
			}
		}

	}
	
	
	private void parseAndSaveSchedule(int week, String dateString, String matchString) {
		try {
			// Parses date
			dateString = dateString + ", " + currentYear;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMMM d, yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(dateString));
			
			logger.info("date: " + calendar);
			
			boolean fulltime;
			int goals1 = 0, goals2 = 0;
			String team1 = "", team2 = "";
			String time = "";
			String ampm = "";
						
			if (matchString.contains(" FT ")) {
				fulltime = true;
				
				// Full-time match
				// ie: Man United 1 FT Tottenham 0
				// So parses the string in form of "[Team name 1] [Goal] FT [Team name 2] [Goal]"
				int index = matchString.indexOf(" FT ");
				String[] parsed = {matchString.substring(0, index), matchString.substring(index + " FT ".length())};
				
				for (int i = 0; i < parsed.length; i++) {
					String string = parsed[i].trim();
					int goalIndex = string.lastIndexOf(" ");
					// Gets last digit as goals
					String digit = string.substring(goalIndex).trim();
					String teamName = string.substring(0, goalIndex).trim(); 
					if (i == 0) {
						goals1 = Integer.valueOf(digit);
						team1 = teamName;
						logger.info(team1 + " Goals: " + goals1);
					} else {
						goals2 = Integer.valueOf(digit);
						team2 = teamName;
						logger.info(team2 + " Goals: " + goals2);
					}
				}
				
				logger.info(String.format("Team1 %s Goal %d - Team2 %s Goal %d", team1, goals1, team2, goals2));
			} else {
				fulltime = false;
				
				// Future match
				// ie: Man United 10:00 PM Swansea City
				if (matchString.contains(" PM ")) {
					// Afternoon match
					ampm = "PM";
				} else {
					// Morning match
					ampm = "AM";
				}
				int index = matchString.indexOf(ampm);
				String[] parsed = {matchString.substring(0, index - 1), matchString.substring(index + ampm.length())};
			
				String string = parsed[0].trim();
				String[] arr = string.split("[ ]");
				time = arr[arr.length - 1];
				team1 = string.substring(0, string.indexOf(time) - 1).trim();
				team2 = parsed[1].trim();
				logger.info(String.format("Team1 %s - Team2 %s - Time %s", team1, team2, time));
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
					matchup.setStatus(footballunService.getMatchupStatusNotBegin());
					if (time.length() > 0 && ampm.length() > 0) {
						String[] hrsStr = time.split("[:]");
						int hour = Integer.valueOf(hrsStr[0].trim());
						int min = Integer.valueOf(hrsStr[1].trim());
						calendar.set(Calendar.HOUR, hour);
						calendar.set(Calendar.MINUTE, min);
						calendar.set(Calendar.AM_PM, "AM".equals(ampm) ? Calendar.AM : Calendar.PM);
						matchup.setStartAt(calendar.getTime());
						calendar.set(Calendar.HOUR, hour + 2); // Supposed to have 2-hours game long
						matchup.setEndAt(calendar.getTime());
					} else {
						logger.error("Fatal error when creating matchup, time value is incorect");
						return;
					}
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
				logger.error("Cannot find squads for the team " + team1 + " or " + team2);
			}
			
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			logger.error("Fatal error when parseAndSaveSchedule");
		}
	}
	
	private boolean isDateString(String str) {
		String upper = str.toUpperCase();
		
		for (String day : daysInWeek) {
			if (upper.contains(day)) {
				return true;
			}
		}
		return false;
	}

	private Competition findOrCreateCompetition(String name) {
		Competition competition = null;
		try{
			competition = footballunService.findCompetitionByName(name);
			if (competition == null) {
				competition = new Competition();
				competition.setName(name);
				competition.setType("LEAGUE");
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
	
	private Country getCountry(String name) {
		for (Country country : countries) {
			if (country.getName().equals(name)) return country;
		}
		return null;
	}
}


