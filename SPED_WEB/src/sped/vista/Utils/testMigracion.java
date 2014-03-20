package sped.vista.Utils;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCurso;

public class testMigracion {
    public static void main(String[] args) throws IOException {
        //  
        // An excel file name. You can create a file name with a full  
        // path information.  
        //  
        String filename = "/home/carlos/Escritorio/prueba.xls";  
          
        //  
        // Create an ArrayList to store the data read from excel sheet.  
        //  
        List sheetData = new ArrayList();  
          
        FileInputStream fis = null;  
        try {  
        //  
        // Create a FileInputStream that will be use to read the  
        // excel file.  
        //  
        fis = new FileInputStream(filename);  
          
        //  
        // Create an excel workbook from the file system.  
        //  
            
        HSSFWorkbook workbook = new HSSFWorkbook(fis);  
        //  
        // Get the first sheet on the workbook.  
        //  
        HSSFSheet sheet = workbook.getSheetAt(0);  
          
        //  
        // When we have a sheet object in hand we can iterator on  
        // each sheet's rows and on each row's cells. We store the  
        // data read on an ArrayList so that we can printed the  
        // content of the excel to the console.  
        //  
        Iterator rows = sheet.rowIterator();  
        while (rows.hasNext()) {  
        HSSFRow row = (HSSFRow) rows.next();  
        Iterator cells = row.cellIterator();  
          
        List data = new ArrayList();  
        while (cells.hasNext()) {  
        HSSFCell cell = (HSSFCell) cells.next();  
        data.add(cell);  
        }  
          
        sheetData.add(data);  
        }  
        } catch (IOException e) {  
        e.printStackTrace();  
        } finally {  
        if (fis != null) {  
        fis.close();  
        }  
        }  
          
        //showExelData(sheetData);  
        setearAlBean(sheetData);
    }  
    
    //Logica para Cursos
    private static void setearAlBean(List sheetData){
        List<BeanCurso> listcurso=new ArrayList<BeanCurso>();        
        for (int i=0; i<sheetData.size(); i++){
            BeanCurso curso=new BeanCurso();
            BeanAreaAcademica area=new BeanAreaAcademica();
            List list = (List) sheetData.get(i);         
            if(!list.isEmpty()){
            Cell cell = (Cell) list.get(0); 
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {                
                curso.setNidCurso((int)cell.getNumericCellValue());
                curso.setDescripcionCurso(list.get(1).toString());
                Cell cell2 = (Cell) list.get(2); 
                area.setNidAreaAcademica((int)cell2.getNumericCellValue());
                curso.setAreaAcademica(area);
                curso.setTipoFichaCurso(list.get(3).toString());                
                Utils.sysout(curso.getNidCurso()+ " - " +curso.getDescripcionCurso() +" - " + curso.getAreaAcademica().getNidAreaAcademica() + " - " + curso.getTipoFichaCurso());
                listcurso.add(curso);     
              }               
            }        
        }
            System.out.println("TAMAÑO DE LA LISTA DE CURSOS : "+listcurso.size());
        }        

        
        
        private static void showExelData(List sheetData) {  
        //  
        // Iterates the data and print it out to the console.  
        //         
        for (int i = 0; i < sheetData.size(); i++) {  
        List list = (List) sheetData.get(i);  
        for (int j = 0; j < list.size(); j++) {  
        Cell cell = (Cell) list.get(j);  
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {  
        System.out.print(cell.getNumericCellValue());  
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {  
        System.out.print(cell.getRichStringCellValue());  
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {  
        System.out.print(cell.getBooleanCellValue());  
        }  
        if (j < list.size() - 1) {  
        System.out.print(", ");  
        }  
        }  
        System.out.println("en blanco");  
        }  
        }}
