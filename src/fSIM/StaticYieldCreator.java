package fSIM;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;	


public class StaticYieldCreator implements java.io.Serializable {
	

	private static final long serialVersionUID = -6042340360639682918L;
	
	ArrayList<GrowthYield> yYields = new ArrayList<GrowthYield>(); // yYields created based on IDs
	LinkedList<String> growthMetricNames = new LinkedList<String>(); // metric names for growth metric object creation
	

	public void staticYieldCreation(String path, int sheetNumber) {

	
		
		int yieldBeginIndex = 19; // add into method argument when this becomes the case
		int ageColumnIndex = 18;

		// public void staticYieldCreation(String path) {
		int count = 0;
		FileInputStream fis = null;
		
		
		try {

			fis = new FileInputStream(path);
			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);

			DataFormatter df = new DataFormatter(); //data formatter object to turn all cell values into string

			//0 is the index of the first excel worksheet
			Sheet sheet = workbook.getSheetAt(sheetNumber);

			Iterator<Row> rowIterator = sheet.iterator();

			String previousRowYieldID = "temp"; // temp variable for creating yield key, note, the yield table must be ordered input table.
			//iterating over each row
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				count = count + 1;

				if (row.getRowNum() == 0) { // get all growth metrics and perhaps create growth metrics?
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell.getColumnIndex() >= yieldBeginIndex) {
							growthMetricNames.add(cell.getStringCellValue());	
						} 
					}	
				}

				if (row.getRowNum() >= 1) { // iterating over each data row, if the yield ID has changed, create a new 
					String species = null;
					String productivity = null;
					String thinStatus = null;	

					//Create yield key of the current row
					String currentRowYieldID = null;
					int age = 0; //create age at that point of iteration for later object creation
					boolean createAge = true;

					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();

						if (cell.getColumnIndex() == 1) {
							productivity = df.formatCellValue(cell);	            			
						}
						if (cell.getColumnIndex() == 0) {	            			
							species = df.formatCellValue(cell);	            			
						}
						if (cell.getColumnIndex() == 8) {

							//for now assumes that a question mark is a thinning yield
							if (df.formatCellValue(cell).contains("?")) {
								thinStatus = "thin";
							} else { 
								thinStatus = df.formatCellValue(cell);
							}
						}

						//new yield ID based on iterating the criteria up to the age column of yield table
						if (cell.getColumnIndex() == ageColumnIndex) { // potentially have == instead of >= after this is working
							currentRowYieldID = species + productivity + thinStatus;
							age = (int) cell.getNumericCellValue();
						}

						//if previous and current ids don't match, create a new growth yield
						if ((cell.getColumnIndex() >= yieldBeginIndex)) {
							
							
							
							if (!previousRowYieldID.equals(currentRowYieldID)) { //ids are not equal, create a new growth yield

								yYields.add(new GrowthYield(species, productivity, thinStatus)); //creating a new growth yield object

								if (createAge == true) {
									yYields.get(yYields.size()-1).addGrowthAge(new GrowthAge(age));
									createAge = false;
								}

								double dNum = Double.parseDouble(df.formatCellValue(cell));
								String metricName = growthMetricNames.get(cell.getColumnIndex() - yieldBeginIndex);							
								yYields.get(yYields.size()-1).createAddMetricValue(dNum, metricName);

								previousRowYieldID = currentRowYieldID; // current yield ID

							} else if (previousRowYieldID.equals(currentRowYieldID)){ // id's are equal, don't create new growth yield

								if (createAge == true) {
									yYields.get(yYields.size()-1).addGrowthAge(new GrowthAge(age));
									createAge = false;
								}

								double dNum = Double.parseDouble(df.formatCellValue(cell));
								String metricName = growthMetricNames.get(cell.getColumnIndex() - yieldBeginIndex);
								yYields.get(yYields.size()-1).createAddMetricValue(dNum, metricName);

							}
						}
					}		
				} 		
			} // close row iteration

			fis.close();
			workbook.close();

		}catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		//showing the number of age and growth metrics created per growth yield
		for (GrowthYield s: yYields) {
			System.out.println("Linearly interpolating the ages now, this may take a few moments");
			System.out.println(s.getYieldIdentifier());
			s.yieldLinearInterpolation();
		}

		System.out.println("There are: " + yYields.size() + " yields and should be 80");

	}

	public ArrayList<GrowthYield> getFCYields() {
		return yYields;
	}	

	
	
	
	
	
	
	
	
	
	
	
	
} // close class
