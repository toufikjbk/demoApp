package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.reporters.jq.Main;

public class ExcelRead {
	
	public static String[][] ExcelReader(String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException{
		FileInputStream fis=new FileInputStream("C:\\Users\\toufi\\eclipse-workspace\\SelOjt_HandsOn\\src\\test\\resources\\Excels\\courses.xlsx");

		Sheet sheet=null;
		
		Workbook workbook=WorkbookFactory.create(fis); 
        if(sheetName.equals("course")) {
		sheet=workbook.getSheet("course");
        }
        else if(sheetName.equals("menu")) {
		sheet=workbook.getSheet("menu");
        }
        	
		int rows=sheet.getLastRowNum()+1;  //getLastRowNum() return index based last row no
		int colums=sheet.getRow(0).getLastCellNum();  //getLastCellNum() return physical based actual last cell no

		String logindata[][]=new String[rows][colums];

		Cell cell;
		String content;

		for(int i=0;i<rows;i++) {

			for(int j=0;j<colums;j++) {

				cell=sheet.getRow(i).getCell(j);

				content=cell.getStringCellValue();

				logindata[i][j]=content;
				
			}
		}
		return logindata;
	
	}

	
}
