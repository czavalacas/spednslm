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


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sped.negocio.BDL.IR.BDL_T_SFMainRemoto;
import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFAulaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_T_SFAreaAcademicaRemoto;
import sped.negocio.LNSF.IR.LN_T_SFAulaRemoto;
import sped.negocio.LNSF.IR.LN_T_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_T_SFProfesorRemoto;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.admin.Aula;
import sped.negocio.entidades.admin.Curso;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.admin.Profesor;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCurso;

import sped.negocio.entidades.beans.BeanGrado;

import sped.negocio.entidades.beans.BeanGradoNivel;
import sped.negocio.entidades.beans.BeanNivel;

import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanSede;

import sped.vista.Utils.Utils;

import utils.system;

public class bMigrarExcel {

    @EJB
    private LN_C_SFCursoRemoto ln_C_SFCursoRemoto;
    @EJB
    private LN_T_SFCursoRemoto ln_T_SFCursoRemoto;
    @EJB
    private LN_C_SFAreaAcademicaRemote ln_C_SFAreaAcademicaRemote;
    @EJB
    private LN_T_SFAreaAcademicaRemoto ln_T_SFAreaAcademicaRemoto;
    @EJB
    private LN_C_SFAulaRemote ln_C_SFAulaRemoto;
    @EJB
    private LN_T_SFAulaRemoto ln_T_SFAulaRemoto;
    @EJB
    private LN_C_SFProfesorRemote ln_C_SFProfesorRemote;
    @EJB
    private LN_T_SFProfesorRemoto ln_T_SFProfesorRemoto;
    @EJB
    private LN_T_SFUsuarioRemote ln_T_SFUsuarioRemote;
        
    /** Temporal  */
    @EJB
    private BDL_T_SFMainRemoto bDL_T_SFMainRemoto;
        
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


    public String migrarExcel() {
        Utils.showPopUpMIDDLE(popupConfirmarMigracion);
        return null;
    }

    public void confirmarMigracion(ActionEvent actionEvent) throws IOException {
        leerExcel(sessionMigrarExcel.getInputStreamFile());
        inputFileExcel.setValue(null);
        Utils.addTarget(inputFileExcel);
    }

    public void cancelarMigracion(ActionEvent actionEvent) {
        popupConfirmarMigracion.hide();
    }

    public void seleccionaExcel(ValueChangeEvent valueChangeEvent) {
        try {
            UploadedFile file = (UploadedFile) valueChangeEvent.getNewValue();
            if (Utils.validarExtensionXls(file.getFilename())) {
                sessionMigrarExcel.setFile(file);
                sessionMigrarExcel.setInputStreamFile(file.getInputStream());
                sessionMigrarExcel.setNombreArchivo(file.getFilename());
                sessionMigrarExcel.setEstadoBtnSubArchivo(false);
                Utils.addTarget(btnSubirArchivo);
            } else {
                sessionMigrarExcel.setEstadoBtnSubArchivo(true);
                Utils.addTarget(btnSubirArchivo);
                Utils.mostrarMensaje(ctx, "El archivo no es de tipo excel suba un xls/xlsx", null, 4);
            }
        } catch (Exception e) {
            Utils.mostrarMensaje(ctx, "Hubo un error a subir el Archivo ingrese nuevamente", null, 4);
        }

    }
    
    public List leerExcelXLSX(InputStream file){
        List sheetData = new ArrayList();
        try{
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            Row row; Cell cell;
            while (rows.hasNext()) {
                row = rows.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                List data = new ArrayList();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    data.add(cell);
                }
                sheetData.add(data);
            }
            return sheetData;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public List leerExcelXLS(InputStream file){
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
            return sheetData;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void leerExcel(InputStream file) throws IOException {
        List sheetData = new ArrayList();
        try {
            String extension = sessionMigrarExcel.getNombreArchivo().substring(sessionMigrarExcel.getNombreArchivo().lastIndexOf(".") + 1, 
                                                                               sessionMigrarExcel.getNombreArchivo().length());
            if (extension.equalsIgnoreCase("xls")){
                sheetData = leerExcelXLS(file);
            }else{
                sheetData = leerExcelXLSX(file);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                file.close();
            }
        }        
        if(sessionMigrarExcel.getTipoMigracion()==1){
            insertarCursos(sheetData);  
        }
        if(sessionMigrarExcel.getTipoMigracion()==2){
            insertarAreaAcademica(sheetData);
        }
        if(sessionMigrarExcel.getTipoMigracion()==3){
            insertarAulas(sheetData);
        }
        if(sessionMigrarExcel.getTipoMigracion()==4){
            insertarProfesores(sheetData);  
        }
        /**Temporal */
        if(sessionMigrarExcel.getTipoMigracion()==5){
            insertarMain(sheetData);
        }
    }

    private void insertarCursos(List sheetData) {
        List<BeanCurso> listcursosAInsertar = new ArrayList<BeanCurso>();
        for (int i = 0; i < sheetData.size(); i++) {
            BeanCurso curso = new BeanCurso();
            BeanAreaAcademica area = new BeanAreaAcademica();
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                Cell cell = (Cell) list.get(0);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    curso.setNidCurso((int) cell.getNumericCellValue());
                    curso.setDescripcionCurso(list.get(1).toString());
                    Cell cell2 = (Cell) list.get(2);
                    area.setNidAreaAcademica((int) cell2.getNumericCellValue());
                    curso.setAreaAcademica(area);
                    curso.setTipoFichaCurso(list.get(3).toString());
                    Utils.sysout(curso.getNidCurso() + " - " + curso.getDescripcionCurso() + " - " +
                                 curso.getAreaAcademica().getNidAreaAcademica() + " - " + curso.getTipoFichaCurso());
                    listcursosAInsertar.add(curso);
                }
            }
        }
        List<BeanCurso> listaActual = ln_C_SFCursoRemoto.getlistaCursos();
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listcursosAInsertar.size(); i++) {
                    if (listaActual.get(j).getNidCurso().intValue() == listcursosAInsertar.get(i).getNidCurso().intValue()) {
                        listcursosAInsertar.remove(i);
                    }
                }
            }
            if (listcursosAInsertar != null) {
                ln_T_SFCursoRemoto.grabarCursosNuevos(listcursosAInsertar);
            }

        } else {
            ln_T_SFCursoRemoto.grabarCursosNuevos(listcursosAInsertar);
        }

        System.out.println("TAMAÑO DE LA LISTA DE CURSOS : " + listcursosAInsertar.size());
    }

    private void insertarAreaAcademica(List sheetData) {
        List<BeanAreaAcademica> listAreasAInsertar = new ArrayList<BeanAreaAcademica>();
        for (int i = 0; i < sheetData.size(); i++) {
            BeanAreaAcademica area = new BeanAreaAcademica();
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                Cell cell = (Cell) list.get(0);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    area.setNidAreaAcademica((int) cell.getNumericCellValue());
                    area.setDescripcionAreaAcademica(list.get(1).toString());

                    Utils.sysout(area.getNidAreaAcademica() + " - " + area.getDescripcionAreaAcademica());
                    listAreasAInsertar.add(area);
                }
            }
        }
        List<BeanAreaAcademica> listaActual = ln_C_SFAreaAcademicaRemote.getAreaAcademicaLN();
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listAreasAInsertar.size(); i++) {
                    if (listaActual.get(j).getNidAreaAcademica().intValue() == listAreasAInsertar.get(i).getNidAreaAcademica().intValue()) {
                        listAreasAInsertar.remove(i);
                        System.out.println("QUITO:");
                    }
                }
            }
            if (listAreasAInsertar != null) {
                ln_T_SFAreaAcademicaRemoto.grabarAreasNuevas(listAreasAInsertar);
            }

        } else {
            ln_T_SFAreaAcademicaRemoto.grabarAreasNuevas(listAreasAInsertar);
        }
        System.out.println("TAMAÑO DE LA LISTA DE CURSOS : " + listAreasAInsertar.size());
    }
    
    private void insertarAulas(List sheetData) {
        List<BeanAula> listaulasAInsertar = new ArrayList<BeanAula>();
        for (int i = 0; i < sheetData.size(); i++) {
            BeanAula aula = new BeanAula();
            BeanGrado grado=new BeanGrado();
            BeanNivel nivel=new BeanNivel();
            BeanGradoNivel grani=new BeanGradoNivel();
            BeanSede sede=new BeanSede();
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                Cell cell = (Cell) list.get(0);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    aula.setNidAula((int) cell.getNumericCellValue());
                    aula.setDescripcionAula(list.get(1).toString());
                    Cell cell2 = (Cell) list.get(2);
                    sede.setNidSede((int) cell2.getNumericCellValue());
                    aula.setSede(sede);
                    Cell cell3 = (Cell) list.get(3);
                    grado.setNidGrado((int) cell3.getNumericCellValue());
                    Cell cell4 = (Cell) list.get(4);
                    nivel.setNidNivel((int) cell4.getNumericCellValue());
                    grani.setGrado(grado);
                    grani.setNivel(nivel);
                    aula.setGradoNivel(grani);
                  /* Utils.sysout(curso.getNidCurso() + " - " + curso.getDescripcionCurso() + " - " +
                       curso.getAreaAcademica().getNidAreaAcademica() + " - " + curso.getTipoFichaCurso());*/
                    listaulasAInsertar.add(aula);
                }
            }
        }
        List<BeanAula> listaActual = ln_C_SFAulaRemoto.getAreaAulaLN();
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listaulasAInsertar.size(); i++) {
                    System.out.println(listaActual.get(j).getNidAula() +"=="+ listaulasAInsertar.get(i).getNidAula());
                    if (listaActual.get(j).getNidAula().intValue() == listaulasAInsertar.get(i).getNidAula().intValue()) {
                        listaulasAInsertar.remove(i);
                        System.out.println("QUITO:");
                    }
                }
            }
            if (listaulasAInsertar != null) {
                ln_T_SFAulaRemoto.grabarAulasNuevas(listaulasAInsertar);
            }

        } else {
            ln_T_SFAulaRemoto.grabarAulasNuevas(listaulasAInsertar);
        }

        System.out.println("TAMAÑO DE LA LISTA DE CURSOS : " + listaulasAInsertar.size());
    }
    
    /**
     * Metodo que verifica si la celda el DNI tiene 8 caracteres y es numerico
     * @author dfloresgonz
     * @since 12.04.2014
     * @param valorCelda - el valor de la celda
     * @return si es TRUE = es dni, FALSE = titulo 
     */
    public boolean isDNI(String valorCelda){//puede ser el titulo/dni
        if(Utils.isNumeric(valorCelda)){
            int len = valorCelda.length();
            if(len == 8){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    private void insertarProfesores(List sheetData) {
        List<BeanProfesor> listProfesoresAInsertar = new ArrayList<BeanProfesor>();        
        for (int i = 0; i < sheetData.size(); i++) {         
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                BeanProfesor profe=new BeanProfesor();               
                Cell cell = (Cell) list.get(0);
                //dfloresgonz 12.04.2014
                //EDUSYS tiene que mandar la celda como string sino no vendran ls dni con 8 caracteres
                if(this.isDNI(cell.getStringCellValue())){
                    profe.setDniProfesor(cell.getStringCellValue());
                    profe.setNombres(list.get(1).toString());
                    profe.setApellidos(list.get(2).toString());
                    profe.setCorreo(list.get(3).toString());Utils.sysout("dni: "+profe.getDniProfesor()+" corr: "+profe.getCorreo()+" nom: "+profe.getNombres()
                                                                         +" ape: "+profe.getApellidos());
                    listProfesoresAInsertar.add(profe);
                }
                //FIN dfloresgonz 12.04.2014
            }
        }
        ln_T_SFUsuarioRemote.cambiarEstadoUsuarioProfesores(listProfesoresAInsertar);
        List<BeanProfesor> listaActual = ln_C_SFProfesorRemote.getProfesoresLN2();    
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listProfesoresAInsertar.size(); i++) {
                    if (listaActual.get(j).getDniProfesor().equals(listProfesoresAInsertar.get(i).getDniProfesor())) {
                        listProfesoresAInsertar.remove(i);
                    }
                }
            }
            if (listProfesoresAInsertar != null && listProfesoresAInsertar.size() > 0) {
                ln_T_SFProfesorRemoto.grabarProfesoresNuevos(listProfesoresAInsertar);
            }

        } else {
            ln_T_SFProfesorRemoto.grabarProfesoresNuevos(listProfesoresAInsertar);
        }
    }
    
    public void seleccionarTipoMigracion(ValueChangeEvent valueChangeEvent) {
        if(choiceSede.getValue()==1||choiceSede.getValue()==2||choiceSede.getValue()==3||choiceSede.getValue()==4||choiceSede.getValue()==5){
            sessionMigrarExcel.setEstadouploadFile(false);
            Utils.addTarget(inputFileExcel);
        }else {
            sessionMigrarExcel.setEstadouploadFile(true);
            Utils.addTarget(inputFileExcel);
        }
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
/** BORRRAR LO DE ABAJO LUEGO DE MIGRAR */
    
     private void insertarMain(List sheetData) {
    try{
                 for (int i = 0; i < sheetData.size(); i++) {
                    
                     List list = (List) sheetData.get(i);
                     if (!list.isEmpty()) {
                       
                             
                         Main ma=new Main();
                         Profesor profesor=new Profesor();
                         profesor.setDniProfesor(list.get(0).toString());
                         Aula aula=new Aula();
                         Cell cell = (Cell) list.get(1);
                         aula.setNidAula((int) cell.getNumericCellValue());
                         Curso curso=new Curso();
                         Cell cell2 = (Cell) list.get(2);
                         curso.setNidCurso((int) cell2.getNumericCellValue());
                             
                         ma.setAula(aula);
                              ma.setCurso(curso);
                              ma.setProfesor(profesor);
                              ma.setEstado("1");
                        bDL_T_SFMainRemoto.persistMain(ma);
                     }
             }} catch (Exception e) {
                  e.printStackTrace();
         }}
   
}
