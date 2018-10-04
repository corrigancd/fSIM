package fSIM;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;	
import java.io.IOException;	
import java.util.Iterator;	
	


public class ExcelReader {

    private static final String FILE_PATH = "InputFiles/FCYields.xlsx";

    
    public static void main(String args[]) {
    	int count = 0;
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(FILE_PATH);

            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);

            int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet

            for (int i = 0; i < numberOfSheets; i++) {
            	
                Sheet sheet = workbook.getSheetAt(i);
                
                Iterator<Row> rowIterator = sheet.iterator();

                //iterating over each row
                while (rowIterator.hasNext()) {

                	 // will need to create growth yield and growth metric objects here instead of Student used in example
                     // Student student = new Student();

                    Row row = rowIterator.next();

                    Iterator<Cell> cellIterator = row.cellIterator();
                    count = count + 1;
                    
                    //Iterating over each cell (column wise)  in a particular row.
                    while (cellIterator.hasNext()) {

                         Cell cell = cellIterator.next();

                        //The Cell Containing String will is name.

                        if (CellType.STRING == cell.getCellType()) {

                        	System.out.println("getting cell type");
                            //student.setName(cell.getStringCellValue());

                            //The Cell Containing numeric value will contain marks

                        } else if (CellType.NUMERIC == cell.getCellType()) {

                            //Cell with index 1 contains marks in Maths

                            //if (cell.getColumnIndex() == columnIndex) {
                            if (cell.getColumnIndex() == 19) {

                            	//System.out.println("getting index 1");
                            	
                            	System.out.println(String.valueOf(cell.getNumericCellValue()));
                            }

                            //Cell with index 2 contains marks in Science

                            else if (cell.getColumnIndex() == 2) {

                            	System.out.println("getting index 2");
                                //student.setScience(String.valueOf(cell.getNumericCellValue()));
                            }

                            //Cell with index 3 contains marks in English

                            else if (cell.getColumnIndex() == 3) {
                            	
                            	System.out.println("getting index 3");
                            	
                               // student.setEnglish(String.valueOf(cell.getNumericCellValue()));	
                            }	
                        }	
                    }

                    //end iterating a row, add all the elements of a row in list
                    //studentList.add(student);
                    System.out.println("add info from row to structures here");
                }
            }

            fis.close();
            workbook.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Total rows: " + count);
        //return studentList;
    }
}

	

