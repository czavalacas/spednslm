package sped.vista.beans.evaluacion.consulta;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import java.math.BigInteger;

import java.text.DateFormat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.List;

import java.util.Locale;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import jxl.write.WriteException;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;

import oracle.adf.view.rich.component.rich.layout.RichShowDetail;

import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.event.DialogEvent;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import sped.negocio.LNSF.IL.LN_C_SFValorLocal;
import sped.negocio.LNSF.IR.LN_C_SFEvaluacionRemote;
import sped.negocio.LNSF.IR.LN_C_SFFichaCriterioRemote;
import sped.negocio.LNSF.IR.LN_C_SFProfesorRemote;
import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFEvaluacionRemote;
import sped.negocio.entidades.beans.BeanConstraint;
import sped.negocio.entidades.beans.BeanCriterioIndicador;
import sped.negocio.entidades.beans.BeanEvaluacion;
import sped.negocio.entidades.beans.BeanFichaCriterio;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bConsultarEvaluacion {
    
    private RichSelectOneChoice choiceFSede;
    private UISelectItems si1;
    private RichSelectOneChoice choiceFNivel;
    private RichSelectOneChoice choiceFArea;
    private UISelectItems si2;
    private UISelectItems si3;
    private RichSelectOneChoice choiceFCurso;
    private RichSelectOneChoice choiceFGrado;    
    private RichPanelGridLayout pgl1;
    private RichInputDate idFechaInicio;
    private RichInputDate idfechaIniciofin;
    private RichInputDate idfechaEvaluacion;
    private RichInputDate idfechaEvaluacionf;
    private RichSubform s1;
    private RichSelectOneChoice choiceFEstado;
    private UISelectItems si4;
    private RichPanelFormLayout pfl7;
    private RichShowDetail sd1;
    private RichShowDetail sd2;
    private RichTable tbEval;
    private RichPopup popCom;
    private RichButton bexcel;
    private RichPopup popComEva;
    private RichPopup popProb;
    @EJB
    private LN_C_SFEvaluacionRemote ln_C_SFEvaluacionRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_C_SFFichaCriterioRemote ln_C_SFFichaCriterioRemote;
    @EJB
    private LN_C_SFValorLocal ln_C_SFValorLocal;
    @EJB
    private LN_T_SFEvaluacionRemote ln_T_SFEvaluacionRemote;
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    @EJB
    private LN_C_SFProfesorRemote ln_C_SFProfesorRemote;
    private bSessionConsultarEvaluacion sessionConsultarEvaluacion;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    FacesContext ctx = FacesContext.getCurrentInstance();
    private String comentarioProf;
    private String comentarioEvaluador;

    public bConsultarEvaluacion() {
        
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionConsultarEvaluacion.getExec() == 0){
            sessionConsultarEvaluacion.setExec(1);
            renderColumns(beanUsuario.getRol().getNidRol());
            sessionConsultarEvaluacion.setLstSede(Utils.llenarCombo(ln_C_SFUtilsRemote.getSedes_LN()));
            sessionConsultarEvaluacion.setLstNivel(Utils.llenarCombo(ln_C_SFUtilsRemote.getNiveles_LN()));
            sessionConsultarEvaluacion.setLstArea(Utils.llenarCombo(ln_C_SFUtilsRemote.getAreas_LN_WS()));
            sessionConsultarEvaluacion.setLstCurso(Utils.llenarCombo(ln_C_SFUtilsRemote.getCursos_LN()));
            sessionConsultarEvaluacion.setLstGrado(Utils.llenarCombo(ln_C_SFUtilsRemote.getGrados_LN()));
            sessionConsultarEvaluacion.setLstEstadoEvaluacion(Utils.llenarComboString(ln_C_SFUtilsRemote.getEstadoEvaluacionFromConstraint()));            
            sessionConsultarEvaluacion.setItemProfesor(Utils.llenarListItem(ln_C_SFProfesorRemote.getNombreProfesor_LN()));
            llenarTabla();
        }
    }
    
    public void renderColumns(int nidRol){
        if(beanUsuario.getRol().getNidRol() == 2 && "1".compareTo(beanUsuario.getIsSupervisor()) == 0){
            int nidArea = beanUsuario.getAreaAcademica().getNidAreaAcademica();
            sessionConsultarEvaluacion.setItemEvaluador(Utils.llenarListItem(ln_C_SFUsuarioRemote.getNombresUsuarios_LN(nidArea, nidRol)));
        }else{
            sessionConsultarEvaluacion.setItemEvaluador(Utils.llenarListItem(ln_C_SFUsuarioRemote.getEvaluadores_LN()));
        }
        if(nidRol == 2 || nidRol == 4){
            if(nidRol == 4 || (nidRol == 2 && "0".compareTo(beanUsuario.getIsNuevo()) == 0)){
                sessionConsultarEvaluacion.setColumnEvaluador(false);
            }
            if(nidRol == 2){
                sessionConsultarEvaluacion.setColumnArea(false);
            }
            if(nidRol == 4){
                sessionConsultarEvaluacion.setColumnSede(false);
            }
        }
        if(nidRol == 3){
            sessionConsultarEvaluacion.setColumnProfesor(false);
        }
    }
    
    public void buscarByFiltro(ActionEvent actionEvent) {
        llenarTabla();
    }

    public void resetFiltro(ActionEvent actionEvent) {
        idFechaInicio.resetValue();
        idfechaIniciofin.resetValue();
        idfechaEvaluacion.resetValue();
        idfechaEvaluacionf.resetValue();
        sessionConsultarEvaluacion.setFechaP(null);
        sessionConsultarEvaluacion.setFechaPf(null);
        sessionConsultarEvaluacion.setFechaF(null);
        sessionConsultarEvaluacion.setFechaFf(null);
        sessionConsultarEvaluacion.setNombreProfesor(null);
        sessionConsultarEvaluacion.setNombreEvaluador(null);
        sessionConsultarEvaluacion.setDescripcionEstadoEvaluacion(null);
        sessionConsultarEvaluacion.setNidSede(null);
        sessionConsultarEvaluacion.setNidNivel(null);
        sessionConsultarEvaluacion.setNidArea(null);
        sessionConsultarEvaluacion.setNidCurso(null);
        sessionConsultarEvaluacion.setNidGrado(null);
        sessionConsultarEvaluacion.setEstadoEvaluacion(0);
        sd1.setDisclosed(false);
        sd2.setDisclosed(false);
        Utils.addTarget(pfl7);
        llenarTabla();
    }
    
    public void llenarTabla(){
        sessionConsultarEvaluacion.setLstBeanEvaluacion(ln_C_SFEvaluacionRemote.getEvaluacionesByUsuarioLN(beanUsuario, 
                                                        transforString(sessionConsultarEvaluacion.getNidSede()), 
                                                        transforString(sessionConsultarEvaluacion.getNidNivel()), 
                                                        transforString(sessionConsultarEvaluacion.getNidArea()), 
                                                        transforString(sessionConsultarEvaluacion.getNidCurso()),
                                                        transforString(sessionConsultarEvaluacion.getNidGrado()),
                                                        sessionConsultarEvaluacion.getNombreProfesor(),
                                                        sessionConsultarEvaluacion.getNombreEvaluador(),
                                                        sessionConsultarEvaluacion.getFechaP(),
                                                        sessionConsultarEvaluacion.getFechaPf(),
                                                        sessionConsultarEvaluacion.getFechaF(),
                                                        sessionConsultarEvaluacion.getFechaFf()));
        if(sessionConsultarEvaluacion.getLstBeanEvaluacion().size() == 0){
            sessionConsultarEvaluacion.setRenderExcel(false);
        }else{
            sessionConsultarEvaluacion.setRenderExcel(true);
        }
        if(tbEval != null){
            Utils.addTargetMany(tbEval, bexcel);
        }        
    }
    
    public int transforString(String valor){
        return valor == null ? 0 : Integer.parseInt(valor);
    }
    
    public void changeListenerEstadoEvaluacion(ValueChangeEvent vce) {
        try {
            if(vce.getNewValue() != null){
                sessionConsultarEvaluacion.setDescripcionEstadoEvaluacion(Utils.getChoiceLabel(vce));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exportData(FacesContext facesContext, OutputStream outputStream) throws IOException,WriteException{
        Data(outputStream, sessionConsultarEvaluacion.getEvaluacion());
    }     
    
    public void Data(OutputStream outputStream, BeanEvaluacion eva){
        if(eva != null){
            List<BeanFichaCriterio> LstBeanFC = ln_C_SFFichaCriterioRemote.
                                                getLstFichaCriterioByEvaluacion(eva.getNidEvaluacion());            
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraphOne = document.createParagraph();
            paragraphStyle(paragraphOne, 1);
            XWPFRun paragraphOneRunOne = paragraphOne.createRun();
            XWPFRunStyle(paragraphOneRunOne, true, 20, "GU\u00cdA DE OBSERVACI\u00d3N DOCENTE NSLM");
                    
            XWPFParagraph paragraphTwo = document.createParagraph();
            XWPFRun paragraphTwoRunOne = paragraphTwo.createRun();
            XWPFRunStyle(paragraphTwoRunOne, true, 12, "I.DATOS GENERALES");
            
            XWPFParagraph paragraphthree = document.createParagraph();
            XWPFRun paragraphthreeRunOne = paragraphthree.createRun();
            XWPFRunStyle(paragraphthreeRunOne, false, 0, " \t1.1.  Docente\t"
                                                           +eva.getMain().getProfesor().getApellidos());
            paragraphthreeRunOne.addBreak();
            XWPFRun paragraphthreeRunTwo = paragraphthree.createRun();
            XWPFRunStyle(paragraphthreeRunTwo, false, 0, " \t1.2.  \u00c1rea\t"
                                                           +eva.getMain().getCurso().getAreaAcademica().getDescripcionAreaAcademica());
            paragraphthreeRunTwo.addBreak();
            XWPFRun paragraphthreeRunThree = paragraphthree.createRun();
            XWPFRunStyle(paragraphthreeRunThree, false, 0, " \t1.3.  Curso\t"
                                                             +eva.getMain().getCurso().getDescripcionCurso());            
            paragraphthreeRunThree.addBreak();
            XWPFRun paragraphthreeRunThree2 = paragraphthree.createRun();
            XWPFRunStyle(paragraphthreeRunThree2, false, 0, " \t1.4.  Sede\t"
                                                             +eva.getMain().getAula().getSede().getDescripcionSede());            
            paragraphthreeRunThree2.addBreak();
            XWPFRun paragraphthreeRunFour = paragraphthree.createRun();
            XWPFRunStyle(paragraphthreeRunFour, false, 0, " \t1.5.  Nivel\t"
                                                            +eva.getMain().getAula().getGradoNivel().getNivel().getDescripcionNivel()+
                                                            ".  Grado y Aula  "+
                                                            eva.getMain().getAula().getGradoNivel().getGrado().getDescripcionGrado()+
                                                            " - "+eva.getMain().getAula().getDescripcionAula());
            paragraphthreeRunFour.addBreak();
            XWPFRun paragraphthreeRunFive = paragraphthree.createRun();
            XWPFRunStyle(paragraphthreeRunFive, false, 0, " \t1.6.  Fecha\t"
                                                            +rangoFecha(eva));
            paragraphthreeRunFive.addBreak();
            XWPFRun paragraphthreeRunSix = paragraphthree.createRun();
            XWPFRunStyle(paragraphthreeRunSix, false, 0, " \t1.7.  Valores\t"
                                                            +ln_C_SFValorLocal.getRangoValorByFicha(
                                                                    LstBeanFC.get(0).getFicha().getNidFicha()));
            paragraphthreeRunSix.addBreak();
            int cols[] = {300,5000,1700,3000};
            double totalCriterios = 0;
            int sizeCri = LstBeanFC.size();
            XWPFTable table = document.createTable();
            table.setInsideVBorder(XWPFTable.XWPFBorderType.DOUBLE, 4, 0, "000000");
            table.setInsideHBorder(XWPFTable.XWPFBorderType.DOUBLE, 4, 0, "000000");
            XWPFTableRow rowOne = table.getRow(0);
            rowOne.createCell();
            rowOne.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols[0]));
            createParagraphCell(rowOne.getCell(0), "N", 1, true, "000000", "ffffff",11);
            rowOne.createCell();
            rowOne.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols[1]));
            createParagraphCell(rowOne.getCell(1), "RESULTADO GLOBAL", 0, true, "000000", "ffffff",11);
            rowOne.getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols[2]));
            rowOne.createCell();
            rowOne.getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols[3]));
            createParagraphCell(rowOne.getCell(3), "Descripci\u00f3n", 1, true, "000000", "ffffff",11);
            for(int i = 0; i < sizeCri; i++){
                XWPFTableRow row = table.createRow();
                createParagraphCell(row.getCell(0), (i+1)+"", 1, true,"808080","ffffff",11);
                createParagraphCell(row.getCell(1), LstBeanFC.get(i).getCriterio().getDescripcionCriterio(), 0, true,"808080","ffffff",10);
                double notaC = LstBeanFC.get(i).getResultadoCriterio().getValor();
                createParagraphCell(row.getCell(2), Pnota(notaC), 1, true,colorNota(notaC),"808080",10);
                createParagraphCell(row.getCell(3), "", 1, true,"808080","",10);
                totalCriterios = totalCriterios + LstBeanFC.get(i).getResultadoCriterio().getValor();
                for(int j = 0; j < LstBeanFC.get(i).getLstcriterioIndicador().size(); j++){
                    XWPFTableRow subrow = table.createRow();
                    BeanCriterioIndicador crin = LstBeanFC.get(i).getLstcriterioIndicador().get(j);
                    createParagraphCell(subrow.getCell(0), (j+1)+"", 1, false,"","",9);  
                    createParagraphCell(subrow.getCell(1), crin.getIndicador().getDescripcionIndicador(), 0, false,"","",9);
                    createParagraphCell(subrow.getCell(2), crin.getResultadoEvaluacion().getValor()+"", 1, false,"","",9);
                    createParagraphCell(subrow.getCell(3),crin.getLeyenda().getDescripcionLeyenda(), 0, false,"","",9); 
                }
            }
            totalCriterios = totalCriterios/sizeCri;            
            createParagraphCell(rowOne.getCell(2), Pnota(totalCriterios), 1, true, colorNota(totalCriterios), "000000",10);
            try {
                document.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }
    
    public void XWPFRunStyle(XWPFRun paragraph, 
                             boolean Bold, 
                             int size, 
                             String texto){
        if(texto != null){
            paragraph.setText(texto);            
        }
        if(size != 0){
            paragraph.setFontSize(size);
        }
        if(Bold){
            paragraph.setBold(Bold);
        }                
    }
    
    public void paragraphStyle(XWPFParagraph paragraph, int alignment){
        if(alignment == 1){
            paragraph.setAlignment(ParagraphAlignment.CENTER);
        }
    }
    
    public void createParagraphCell(XWPFTableCell celda, 
                                    String texto, 
                                    int alignment, 
                                    boolean Bold, 
                                    String colorCelda, 
                                    String colorLetra, 
                                    int tamanoL){
        celda.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        if(colorCelda.length() == 6){
            celda.setColor(colorCelda);
        }
        XWPFParagraph para = celda.getParagraphs().get(0);
        paragraphStyle(para, alignment);
        XWPFRun run = para.createRun();
        if(colorLetra.length() == 6){
            run.setColor(colorLetra);
        }
        XWPFRunStyle(run, Bold, tamanoL, texto);
    }
    
    public String colorNota(double nota){
        String color = "";
        if(nota <= 5){
            color = "ff0000";
        }else if(nota <= 10){
            color = "ff4500";
        }else if(nota <= 15){
            color = "ffff00";
        }else{
            color = "00ff00";
        }
        return color;
    }
    
    public void comentarEvaluacion(ActionEvent actionEvent) {
        this.setComentarioProf(sessionConsultarEvaluacion.getEvaSelect().getComentario_profesor());
        Utils.showPopUpMIDDLE(popCom);
    }
    
    public void comentarEvaluacionByEvaluador(ActionEvent actionEvent) {
        this.setComentarioEvaluador(sessionConsultarEvaluacion.getEvaSelect().getComentario_evaluador());
        Utils.showPopUpMIDDLE(popComEva);
    }
    
    public void confirmarEnvioMensaje(DialogEvent dialogEvent) {
        if(beanUsuario.getRol().getNidRol() == 3){//SOLO EL PROFESOR PUEDE REGISTRAR COMENTARIO
            DialogEvent.Outcome outcome = dialogEvent.getOutcome();        
            String detalle = "Error";
            String error = "Operacion Incorrecta, vuelva intentarlo";
            int severidad = 2;
            if(outcome == DialogEvent.Outcome.ok){            
                if(sessionConsultarEvaluacion.getEvaSelect() != null){
                    error = ln_T_SFEvaluacionRemote.updateEvaluacionbyComentarioProfesor(sessionConsultarEvaluacion.getEvaSelect().getNidEvaluacion(),
                                                                                         comentarioProf);
                    if("000".compareTo(error) == 0){
                        detalle = "Se registro el comentario";
                        error = "Operacion Realizada Correctamente";
                        severidad = 3;
                        llenarTabla();
                        Utils.addTarget(pfl7);
                    }                
                }
                Utils.mostrarMensaje(ctx, 
                                     error, 
                                     detalle, 
                                     severidad);
                
            }   
        }
    }
    
    public void confirmarEnvioMensajeEvaluador(DialogEvent dialogEvent) {
        if(beanUsuario.getNidUsuario().compareTo(sessionConsultarEvaluacion.getEvaSelect().getNidEvaluador()) == 0 &&
           beanUsuario.getRol().getNidRol() != 3){//SOLO EL MISMO EVALUADOR PUEDE EDITAR SU COMENTARIO
            DialogEvent.Outcome outcome = dialogEvent.getOutcome();        
            String detalle = "Error";
            String error = "Operacion Incorrecta, vuelva intentarlo";
            int severidad = 2;
            if(outcome == DialogEvent.Outcome.ok){            
                if(sessionConsultarEvaluacion.getEvaSelect() != null){
                    error = ln_T_SFEvaluacionRemote.updateEvaluacionbyComentarioEvaluador(sessionConsultarEvaluacion.getEvaSelect().getNidEvaluacion(),
                                                                                          comentarioEvaluador);
                    if("000".compareTo(error) == 0){
                        detalle = "Se registro el comentario";
                        error = "Operacion Realizada Correctamente";
                        severidad = 3;
                        llenarTabla();
                        Utils.addTarget(pfl7);
                    }                
                }
                Utils.mostrarMensaje(ctx, 
                                     error, 
                                     detalle, 
                                     severidad);
                
            }   
        }
    }
    
    public String Pnota(double nota){
        DecimalFormat df = new DecimalFormat("#.##");
        double porcentaje = (nota*100) / 20;
        return df.format(nota)+" - "+df.format(porcentaje)+" %";
    }
    
    public String rangoFecha(BeanEvaluacion eva){
        DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.US);
        DateFormat Hora = new SimpleDateFormat("hh:mm a", Locale.US);
        return fechaHora.format(eva.getStartDate())+" - "+Hora.format(eva.getEndDate());
    }

    public List<SelectItem> suggestEvaluador(String string) {        
        return Utils.getSuggestions(sessionConsultarEvaluacion.getItemEvaluador(), string);
    }
    
    public List<SelectItem> suggestProfesor(String string) {
        return Utils.getSuggestions(sessionConsultarEvaluacion.getItemProfesor(), string);
    }
    
    public void setSessionConsultarEvaluacion(bSessionConsultarEvaluacion sessionConsultarEvaluacion) {
        this.sessionConsultarEvaluacion = sessionConsultarEvaluacion;
    }

    public bSessionConsultarEvaluacion getSessionConsultarEvaluacion() {
        return sessionConsultarEvaluacion;
    }

    public void setChoiceFSede(RichSelectOneChoice choiceFSede) {
        this.choiceFSede = choiceFSede;
    }

    public RichSelectOneChoice getChoiceFSede() {
        return choiceFSede;
    }

    public void setComentarioEvaluador(String comentarioEvaluador) {
        this.comentarioEvaluador = comentarioEvaluador;
    }

    public String getComentarioEvaluador() {
        return comentarioEvaluador;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setChoiceFNivel(RichSelectOneChoice choiceFNivel) {
        this.choiceFNivel = choiceFNivel;
    }

    public RichSelectOneChoice getChoiceFNivel() {
        return choiceFNivel;
    }

    public void setChoiceFArea(RichSelectOneChoice choiceFArea) {
        this.choiceFArea = choiceFArea;
    }

    public RichSelectOneChoice getChoiceFArea() {
        return choiceFArea;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setChoiceFCurso(RichSelectOneChoice choiceFCurso) {
        this.choiceFCurso = choiceFCurso;
    }

    public RichSelectOneChoice getChoiceFCurso() {
        return choiceFCurso;
    }

    public void setChoiceFGrado(RichSelectOneChoice choiceFGrado) {
        this.choiceFGrado = choiceFGrado;
    }

    public RichSelectOneChoice getChoiceFGrado() {
        return choiceFGrado;
    }

    public void setPgl1(RichPanelGridLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGridLayout getPgl1() {
        return pgl1;
    }

    public void setIdFechaInicio(RichInputDate idFechaInicio) {
        this.idFechaInicio = idFechaInicio;
    }

    public RichInputDate getIdFechaInicio() {
        return idFechaInicio;
    }
    
    public void setIdfechaIniciofin(RichInputDate idfechaIniciofin) {
        this.idfechaIniciofin = idfechaIniciofin;
    }

    public RichInputDate getIdfechaIniciofin() {
        return idfechaIniciofin;
    }

    public void setIdfechaEvaluacion(RichInputDate idfechaEvaluacion) {
        this.idfechaEvaluacion = idfechaEvaluacion;
    }

    public RichInputDate getIdfechaEvaluacion() {
        return idfechaEvaluacion;
    }

    public void setIdfechaEvaluacionf(RichInputDate idfechaEvaluacionf) {
        this.idfechaEvaluacionf = idfechaEvaluacionf;
    }

    public RichInputDate getIdfechaEvaluacionf() {
        return idfechaEvaluacionf;
    }

    public void setS1(RichSubform s1) {
        this.s1 = s1;
    }

    public RichSubform getS1() {
        return s1;
    }

    public void setChoiceFEstado(RichSelectOneChoice choiceFEstado) {
        this.choiceFEstado = choiceFEstado;
    }

    public RichSelectOneChoice getChoiceFEstado() {
        return choiceFEstado;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setPfl7(RichPanelFormLayout pfl7) {
        this.pfl7 = pfl7;
    }

    public RichPanelFormLayout getPfl7() {
        return pfl7;
    }

    public void setSd1(RichShowDetail sd1) {
        this.sd1 = sd1;
    }

    public RichShowDetail getSd1() {
        return sd1;
    }

    public void setSd2(RichShowDetail sd2) {
        this.sd2 = sd2;
    }

    public RichShowDetail getSd2() {
        return sd2;
    }

    public void setTbEval(RichTable tbEval) {
        this.tbEval = tbEval;
    }

    public RichTable getTbEval() {
        return tbEval;
    }

    public void setPopCom(RichPopup popCom) {
        this.popCom = popCom;
    }

    public RichPopup getPopCom() {
        return popCom;
    }

    public void setComentarioProf(String comentarioProf) {
        this.comentarioProf = comentarioProf;
    }

    public String getComentarioProf() {
        return comentarioProf;
    }

    public void setBexcel(RichButton bexcel) {
        this.bexcel = bexcel;
    }

    public RichButton getBexcel() {
        return bexcel;
    }

    public void setPopComEva(RichPopup popComEva) {
        this.popComEva = popComEva;
    }

    public RichPopup getPopComEva() {
        return popComEva;
    }

    public void setPopProb(RichPopup popProb) {
        this.popProb = popProb;
    }

    public RichPopup getPopProb() {
        return popProb;
    }
}
