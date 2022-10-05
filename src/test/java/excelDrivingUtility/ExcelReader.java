package excelDrivingUtility;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import resources.UtillityFunctions;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReader extends UtillityFunctions {
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;

    {
        try{
        String ExcelPath=getGlobalPropertyValues("ExcelTestDataPath");
        File source= new File(ExcelPath);
        FileInputStream fis= new FileInputStream(source);
        workbook= new XSSFWorkbook(fis);
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
    }


    public static Object getData(int sheetNumber, int row, int column){
        sheet=workbook.getSheetAt(sheetNumber);
           Object data=null;
        if(sheet.getRow(row).getCell(column).getCellType()== CellType.STRING){
            data= sheet.getRow(row).getCell(column).getStringCellValue();
        }
        else{
            data= sheet.getRow(row).getCell(column).getNumericCellValue();
        }
        return data;
    }

    public static int getNumberofRowsinSheet(int sheetNumber){
        int rownumber=workbook.getSheetAt(sheetNumber).getLastRowNum();
        rownumber=rownumber+1;
        return rownumber;
    }

}
