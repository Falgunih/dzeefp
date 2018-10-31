package commonActions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataFunctions {
	String contents;
	public  Properties config = null;
	static FileInputStream file;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	String setUpPropertiesFile = System.getProperty("user.dir")+"//src/test//resources//config.properties";
	
	/** This method reads the excel data using Apache POI library. 
	 * @param fileName The excel filename which has the test data
	 * @param sheetName The sheet name containing data for corresponding test
	 * @return
	 */
	public String [][] readAllExcel(String fileName, String sheetName){
		Log.info("Reading "+sheetName+"  excel sheet");
	try
	    {
	    List<List<String>> cellData = new ArrayList<List<String>>(); 
	   	FileInputStream fi = new FileInputStream(fileName);
	    XSSFWorkbook wb = new XSSFWorkbook(fi);
	     XSSFSheet sh = wb.getSheet(sheetName);
	     Iterator<Row> rowIterator = sh.iterator();
	     	while (rowIterator.hasNext())
	            {
	                Row row = rowIterator.next();
	                String[] v = new String[sh.getRow(row.getRowNum()).getLastCellNum()];
	                int i =0;
	                	if(row.getRowNum()!=0){
	                		Iterator<Cell> cellIterator = row.cellIterator();
	                			while (cellIterator.hasNext())
	                				{
	                				Cell cell = cellIterator.next();
	                				switch (cell.getCellType())
	                				{
	                				case Cell.CELL_TYPE_NUMERIC:
	                					double value = cell.getNumericCellValue();
	                					int digit = (int) value;
	                					String s = Integer.toString(digit);
	                					v[i] = s;
	                					i++;
	                					break;
	                        
	                				case Cell.CELL_TYPE_STRING:
	                					v[i]=cell.getStringCellValue();
	                					i++;
	                					break;
	                    }
	              }
	     cellData.add(Arrays.asList(v));
	     }
	  }
	  fi.close();
	  String [][] data = new String[cellData.size()][3];
      	for (int i = 0; i < cellData.size(); i++) {
      		List<String> list = cellData.get(i);
	        data[i]= new String[list.size()];
	        	for (int j = 0; j < list.size(); j++) {
                 data[i][j] = list.get(j);
	            }
	        }
 	          return data;
	 }
	          catch (Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	/** This method reads the config.properties file.
	 * @return properties 
	 * @throws IOException
	 */
	public  Properties getPropertiesFileData(){
		  config = new Properties();
		  FileInputStream Ist = null;
		try {
			Ist = new FileInputStream(setUpPropertiesFile);
		} catch (FileNotFoundException e) {
			Log.exception("Exception occured is ", e);
			e.printStackTrace();	
		}
		  try {
			  Log.info("Loading properties file data");
			config.load(Ist);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  return config;
	}
		
		public String [][] readRow(String fileName, String sheetName, String scenario){
			Log.info("Reading "+scenario+" row from "+sheetName+" sheet");
		  try
		        {
		   List<List<String>> cellData = new ArrayList<List<String>>(); 
		   		file = new FileInputStream(fileName);
		        workbook = new XSSFWorkbook(file);
		        sheet = workbook.getSheet(sheetName);
		            Iterator<Row> rowIterator = sheet.iterator();
		            while (rowIterator.hasNext())
		            {
		                Row row = rowIterator.next();
		                String[] v = new String[sheet.getRow(row.getRowNum()).getLastCellNum()];
		                int i =0;
		                if(row.getCell(0).toString().equalsIgnoreCase(scenario)){
		                	Iterator<Cell> cellIterator = row.cellIterator();
		                while (cellIterator.hasNext())
		                {
		                    Cell cell = cellIterator.next();
		                    switch (cell.getCellType())
		                    {
		                      case Cell.CELL_TYPE_NUMERIC:
		                         double value = cell.getNumericCellValue();
		                         int digit = (int) value;
		                         String s = Integer.toString(digit);
		                         v[i] = s;
		                         i++;
		                         break;
		                        
		                      case Cell.CELL_TYPE_STRING:
		                         v[i]=cell.getStringCellValue();
		                         i++;
		                            break;
		                    }
		                }
		                cellData.add(Arrays.asList(v));
		             }
		          }
		            file.close();
		            String [][] data = new String[cellData.size()][3];
	              for (int i = 0; i < cellData.size(); i++) {
		               List<String> list = cellData.get(i);
		             data[i]= new String[list.size()];
		                for (int j = 0; j < list.size(); j++) {
		                   data[i][j] = list.get(j);
		                }
		            }
	              return data;
		 }
		          catch (Exception e)
		        {
		            e.printStackTrace();
		            return null;
		        }
		    }
	}
		
