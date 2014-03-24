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

import sped.negocio.LNSF.IR.LN_C_SFAreaAcademicaRemote;
import sped.negocio.LNSF.IR.LN_C_SFAulaRemote;
import sped.negocio.LNSF.IR.LN_C_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_T_SFAreaAcademicaRemoto;
import sped.negocio.LNSF.IR.LN_T_SFAulaRemoto;
import sped.negocio.LNSF.IR.LN_T_SFCursoRemoto;
import sped.negocio.LNSF.IR.LN_T_SFProfesorRemoto;
import sped.negocio.entidades.beans.BeanAreaAcademica;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanCurso;

import sped.negocio.entidades.beans.BeanGrado;

import sped.negocio.entidades.beans.BeanGradoNivel;
import sped.negocio.entidades.beans.BeanNivel;

import sped.negocio.entidades.beans.BeanProfesor;
import sped.negocio.entidades.beans.BeanSede;

import sped.vista.Utils.Utils;

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
                Utils.mostrarMensaje(ctx, "El archivo no es de tipo excel suba un .xls", " nulo ", 4);
            }
        } catch (Exception e) {
            Utils.mostrarMensaje(ctx, "Hubo un error a subir el Archivo ingrese nuevamente", " nulo ", 4);
        }

    }

    public void leerExcel(InputStream file) throws IOException {
        List sheetData = new ArrayList();
        try {
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
    
    private void insertarProfesores(List sheetData) {
        List<BeanProfesor> listProfesoresAInsertar = new ArrayList<BeanProfesor>();
        for (int i = 0; i < sheetData.size(); i++) {         
            List list = (List) sheetData.get(i);
            if (!list.isEmpty()) {
                BeanProfesor profe=new BeanProfesor();               
                Cell cell = (Cell) list.get(0);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {                   
                 Utils.sysout((int) cell.getNumericCellValue()); Utils.sysout(list.get(1).toString()); Utils.sysout(list.get(2).toString());
                 profe.setDniProfesor(""+(int) cell.getNumericCellValue());
                 profe.setNombres(list.get(1).toString());
                 profe.setApellidos(list.get(2).toString());
                 listProfesoresAInsertar.add(profe);
                }
            }
        }
        List<BeanProfesor> listaActual = ln_C_SFProfesorRemote.getProfesoresLN();
        if (listaActual != null) {
            for (int j = 0; j < listaActual.size(); j++) {
                for (int i = 0; i < listProfesoresAInsertar.size(); i++) {
                    if (listaActual.get(j).getDniProfesor().equals(listProfesoresAInsertar.get(i).getDniProfesor())) {
                        listProfesoresAInsertar.remove(i);
                        System.out.println("QUITO:");
                    }
                }
            }
            if (listProfesoresAInsertar != null) {
                ln_T_SFProfesorRemoto.grabarProfesoresNuevos(listProfesoresAInsertar);
            }

        } else {
            ln_T_SFProfesorRemoto.grabarProfesoresNuevos(listProfesoresAInsertar);
        }

        System.out.println("TAMAÑO DE LA LISTA DE CURSOS : " + listProfesoresAInsertar.size());
    }
    
    public void seleccionarTipoMigracion(ValueChangeEvent valueChangeEvent) {
        if(choiceSede.getValue()==1||choiceSede.getValue()==2||choiceSede.getValue()==3||choiceSede.getValue()==4){
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

   
}
