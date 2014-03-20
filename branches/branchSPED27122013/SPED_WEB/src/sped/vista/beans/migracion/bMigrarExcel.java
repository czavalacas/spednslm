package sped.vista.beans.migracion;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;

import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_T_SFCursoRemoto;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanCurso;

import sped.vista.Utils.Utils;

public class bMigrarExcel {
    
    @EJB    
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    @EJB    
    private LN_T_SFCursoRemoto ln_T_SFCursoRemoto;
    
    
    
    
    private bSessionMigrarExcel sessionMigrarExcel;
    private RichSelectOneChoice choiceSede;
    private RichInputFile inputFileExcel;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichPopup popupConfirmarMigracion;
    private RichButton btnSubirArchivo;
 
    
    
    public bMigrarExcel() {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {  
        sessionMigrarExcel.setNidSede("2");  
    }  

    public void setChoiceSede(RichSelectOneChoice choiceSede) {
        this.choiceSede = choiceSede;
    }

    public RichSelectOneChoice getChoiceSede() {
        return choiceSede;
    }

    public void setSessionMigrarExcel(bSessionMigrarExcel sessionMigrarExcel) {
        this.sessionMigrarExcel = sessionMigrarExcel;
    }

    public bSessionMigrarExcel getSessionMigrarExcel() {
        return sessionMigrarExcel;
    }

    public void setInputFileExcel(RichInputFile inputFileExcel) {
        this.inputFileExcel = inputFileExcel;
    }

    public RichInputFile getInputFileExcel() {
        return inputFileExcel;
    }

   
    public void seleccionaExcel(ValueChangeEvent valueChangeEvent) {
        try{
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();         
          if(Utils.validarExtensionXls(file.getFilename())){
        sessionMigrarExcel.setFile(file);           
        sessionMigrarExcel.setInputStreamFile(file.getInputStream());       
        sessionMigrarExcel.setNombreArchivo(file.getFilename());
        sessionMigrarExcel.setEstadoBtnSubArchivo(false);
        Utils.addTarget(btnSubirArchivo);
        }else{
            sessionMigrarExcel.setEstadoBtnSubArchivo(true);
            Utils.addTarget(btnSubirArchivo);
            Utils.mostrarMensaje(ctx,"El archivo no es de tipo excel suba un .xls"," nulo ",4);
        }
        }catch(Exception e){
        Utils.mostrarMensaje(ctx,"Hubo un error a subir el Archivo ingrese nuevamente"," nulo ",4);
        }
   
    }    
    
    public void leerExcel(InputStream file) throws IOException {    
        List sheetData = new ArrayList();  
        try{  
        HSSFWorkbook workbook = new HSSFWorkbook(file);  
            
        HSSFSheet sheet = workbook.getSheetAt(0);  
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
        if (file != null) {  
        file.close();  
        }  
        }       
        setearAlBean(sheetData);
    }
    
    private void setearAlBean(List sheetData){
        List<BeanCurso> listcursosAInsertar=new ArrayList<BeanCurso>();        
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
                listcursosAInsertar.add(curso);     
              }               
            }        
        }
         List<BeanCurso> listaActual=ln_C_SFCursoRemoto.getlistaCursos();
         if(listaActual!=null){          
                for(int j=0; j < listaActual.size(); j++){
                     for(int i=0; i < listcursosAInsertar.size(); i++){
                        if(listaActual.get(j).getNidCurso()==listcursosAInsertar.get(i).getNidCurso()){                       
                        listcursosAInsertar.remove(i);
                    }                 
                 }
             }                    
             if(listcursosAInsertar!=null){
                 ln_T_SFCursoRemoto.grabarCursosNuevos(listcursosAInsertar);  
             }            
                          
         }else{
             ln_T_SFCursoRemoto.grabarCursosNuevos(listcursosAInsertar);    
         }
        
            System.out.println("TAMAÑO DE LA LISTA DE CURSOS : "+listcursosAInsertar.size());
        }

    public String migrarExcel() {
        Utils.showPopUpMIDDLE(popupConfirmarMigracion);      
        return null;
    }
    public void confirmarMigracion(ActionEvent actionEvent) throws IOException {    
            leerExcel(sessionMigrarExcel.getInputStreamFile());      
    }

    public void cancelarMigracion(ActionEvent actionEvent) {
        popupConfirmarMigracion.hide();
    }

    public void setPopupConfirmarMigracion(RichPopup popupConfirmarMigracion) {
        this.popupConfirmarMigracion = popupConfirmarMigracion;
    }

    public RichPopup getPopupConfirmarMigracion() {
        return popupConfirmarMigracion;
    }

    public void setBtnSubirArchivo(RichButton btnSubirArchivo) {
        this.btnSubirArchivo = btnSubirArchivo;
    }

    public RichButton getBtnSubirArchivo() {
        return btnSubirArchivo;
    }
}
