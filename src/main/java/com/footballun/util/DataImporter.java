/**
 * @author: yen.nt
 * @created on Nov 27, 2015
 */
package com.footballun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footballun.service.FootballunService;


/**
 * @author yen.nt
 *
 */
@Component
public class DataImporter {

	private static final String DATA_PATH = "D:\\workspace\\footballun\\assets\\files\\data\\";
	private static final String FILE_NAME = "epl.xlsx";
	
	@Autowired
	private FootballunService footballunService;
	
	public void importExcel() {

		try {
		     
			FileInputStream  file = new FileInputStream(new File(DATA_PATH + FILE_NAME));
		     
		    //Get the workbook instance for XLS file 
			XSSFWorkbook  workbook = new XSSFWorkbook (file);
		 
		    //Get first sheet from the workbook
		    XSSFSheet sheet = workbook.getSheetAt(0);
		     
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();
		         
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		        while(cellIterator.hasNext()) {
		             
		            Cell cell = cellIterator.next();
		             
		            switch(cell.getCellType()) {
		                case Cell.CELL_TYPE_BOOLEAN:
		                    System.out.print(cell.getBooleanCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    System.out.print(cell.getNumericCellValue() + "\t\t");
		                    break;
		                case Cell.CELL_TYPE_STRING:
		                    System.out.print(cell.getStringCellValue() + "\t\t");
		                    break;
		            }
		        }
		        System.out.println("");
		    }
		    file.close();
		    
		    // Tests output
		    FileOutputStream out = 
		        new FileOutputStream(new File(DATA_PATH + "test.xlsx"));
		    workbook.write(out);
		    out.close();
		    
		    workbook.close();
		    
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
			
		}
	}
}
