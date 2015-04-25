package sped.vista.beans.migracion;

import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.model.UploadedFile;
import sped.negocio.entidades.beans.BeanAula;
import sped.negocio.entidades.beans.BeanMainWS;

public class bSessionMigrarExcel implements Serializable {
    private String nidSede;
    private UploadedFile file;
    private InputStream inputStreamFile;
    private String nombreArchivo;
    private boolean estadoBtnSubArchivo=true;
    private int tipoMigracion;
    private boolean estadouploadFile=true;
    /** Nueva Carga */
    private String accionSess;
    private String cidSedeSess;
    private String dniProfSess;
    private String cidCursoSess;
    private String cidAulaSess;
    private boolean estChoiceAula = true;
    private boolean estChoiceProf = true;
    private boolean estChoiceSede = true;
    private boolean estChoiceCurso = true;
    private List listaSedesChoice;
    private List listaAulasChoice;
    private List listaProfesChoice;
    private List listaCursosChoice;
    private List<BeanAula> listaAulas;
    private List listaNiveles;
    private String nidNivel;
    private List listGrados;
    private String nidGrado;
    private boolean estadoDescAula=false;
    private boolean disableChoiceSede=false;
    private boolean disableChoiceNivel=false;
    private boolean disableChoiceGrado=false;
    private boolean disableBtnEditar=true;
    private String tablaSeleccionable="none";
    private boolean disableBtnNuevo=true;
    private BeanAula beanAula;
    private int flgActivo;
    private boolean visibleChoiceEstado=false;
    private boolean visibleRegistrar=false;
    private boolean visibleNuevo=true;
    private int exec=0;
    private boolean requeridInput=false;
    private List<BeanMainWS> lstMain = new ArrayList<BeanMainWS>();
    private String descSede;
    private String nombresProf;
    private String descAula;
    private String descCurso;
    private Integer nidMainModif;
    
    public bSessionMigrarExcel() {
    }

    public void setNidMainModif(Integer nidMainModif) {
        this.nidMainModif = nidMainModif;
    }

    public Integer getNidMainModif() {
        return nidMainModif;
    }

    public void setDescSede(String descSede) {
        this.descSede = descSede;
    }

    public String getDescSede() {
        return descSede;
    }

    public void setNombresProf(String nombresProf) {
        this.nombresProf = nombresProf;
    }

    public String getNombresProf() {
        return nombresProf;
    }

    public void setDescAula(String descAula) {
        this.descAula = descAula;
    }

    public String getDescAula() {
        return descAula;
    }

    public void setDescCurso(String descCurso) {
        this.descCurso = descCurso;
    }

    public String getDescCurso() {
        return descCurso;
    }

    public void setLstMain(List<BeanMainWS> lstMain) {
        this.lstMain = lstMain;
    }

    public List<BeanMainWS> getLstMain() {
        return lstMain;
    }

    public void setListaAulasChoice(List listaAulasChoice) {
        this.listaAulasChoice = listaAulasChoice;
    }

    public List getListaAulasChoice() {
        return listaAulasChoice;
    }

    public void setListaProfesChoice(List listaProfesChoice) {
        this.listaProfesChoice = listaProfesChoice;
    }

    public List getListaProfesChoice() {
        return listaProfesChoice;
    }

    public void setListaCursosChoice(List listaCursosChoice) {
        this.listaCursosChoice = listaCursosChoice;
    }

    public List getListaCursosChoice() {
        return listaCursosChoice;
    }

    public void setListaSedesChoice(List listaSedesChoice) {
        this.listaSedesChoice = listaSedesChoice;
    }

    public List getListaSedesChoice() {
        return listaSedesChoice;
    }

    public void setNidSede(String nidSede) {
        this.nidSede = nidSede;
    }

    public String getNidSede() {
        return nidSede;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setEstadoBtnSubArchivo(boolean estadoBtnSubArchivo) {
        this.estadoBtnSubArchivo = estadoBtnSubArchivo;
    }

    public boolean isEstadoBtnSubArchivo() {
        return estadoBtnSubArchivo;
    }


    public void setInputStreamFile(InputStream inputStreamFile) {
        this.inputStreamFile = inputStreamFile;
    }

    public InputStream getInputStreamFile() {
        return inputStreamFile;
    }

    public void setTipoMigracion(int tipoMigracion) {
        this.tipoMigracion = tipoMigracion;
    }

    public int getTipoMigracion() {
        return tipoMigracion;
    }

    public void setEstadouploadFile(boolean estadouploadFile) {
        this.estadouploadFile = estadouploadFile;
    }

    public boolean isEstadouploadFile() {
        return estadouploadFile;
    }

    public void setCidSedeSess(String cidSedeSess) {
        this.cidSedeSess = cidSedeSess;
    }

    public String getCidSedeSess() {
        return cidSedeSess;
    }

    public void setDniProfSess(String dniProfSess) {
        this.dniProfSess = dniProfSess;
    }

    public String getDniProfSess() {
        return dniProfSess;
    }

    public void setCidCursoSess(String cidCursoSess) {
        this.cidCursoSess = cidCursoSess;
    }

    public String getCidCursoSess() {
        return cidCursoSess;
    }

    public void setCidAulaSess(String cidAulaSess) {
        this.cidAulaSess = cidAulaSess;
    }

    public String getCidAulaSess() {
        return cidAulaSess;
    }

    public void setEstChoiceAula(boolean estChoiceAula) {
        this.estChoiceAula = estChoiceAula;
    }

    public boolean isEstChoiceAula() {
        return estChoiceAula;
    }

    public void setAccionSess(String accionSess) {
        this.accionSess = accionSess;
    }

    public String getAccionSess() {
        return accionSess;
    }

    public void setEstChoiceProf(boolean estChoiceProf) {
        this.estChoiceProf = estChoiceProf;
    }

    public boolean isEstChoiceProf() {
        return estChoiceProf;
    }

    public void setEstChoiceSede(boolean estChoiceSede) {
        this.estChoiceSede = estChoiceSede;
    }

    public boolean isEstChoiceSede() {
        return estChoiceSede;
    }

    public void setEstChoiceCurso(boolean estChoiceCurso) {
        this.estChoiceCurso = estChoiceCurso;
    }

    public boolean isEstChoiceCurso() {
        return estChoiceCurso;
    }

    public void setListaAulas(List<BeanAula> listaAulas) {
        this.listaAulas = listaAulas;
    }

    public List<BeanAula> getListaAulas() {
        return listaAulas;
    }


    public void setListaNiveles(List listaNiveles) {
        this.listaNiveles = listaNiveles;
    }

    public List getListaNiveles() {
        return listaNiveles;
    }

    public void setNidNivel(String nidNivel) {
        this.nidNivel = nidNivel;
    }

    public String getNidNivel() {
        return nidNivel;
    }

    public void setListGrados(List listGrados) {
        this.listGrados = listGrados;
    }

    public List getListGrados() {
        return listGrados;
    }

    public void setNidGrado(String nidGrado) {
        this.nidGrado = nidGrado;
    }

    public String getNidGrado() {
        return nidGrado;
    }

    public void setEstadoDescAula(boolean estadoDescAula) {
        this.estadoDescAula = estadoDescAula;
    }

    public boolean isEstadoDescAula() {
        return estadoDescAula;
    }

    public void setDisableChoiceSede(boolean disableChoiceSede) {
        this.disableChoiceSede = disableChoiceSede;
    }

    public boolean isDisableChoiceSede() {
        return disableChoiceSede;
    }


    public void setDisableChoiceNivel(boolean disableChoiceNivel) {
        this.disableChoiceNivel = disableChoiceNivel;
    }

    public boolean isDisableChoiceNivel() {
        return disableChoiceNivel;
    }

    public void setDisableChoiceGrado(boolean disableChoiceGrado) {
        this.disableChoiceGrado = disableChoiceGrado;
    }

    public boolean isDisableChoiceGrado() {
        return disableChoiceGrado;
    }

    public void setDisableBtnEditar(boolean disableBtnEditar) {
        this.disableBtnEditar = disableBtnEditar;
    }

    public boolean isDisableBtnEditar() {
        return disableBtnEditar;
    }

    public void setTablaSeleccionable(String tablaSeleccionable) {
        this.tablaSeleccionable = tablaSeleccionable;
    }

    public String getTablaSeleccionable() {
        return tablaSeleccionable;
    }

   

    public void setDisableBtnNuevo(boolean disableBtnNuevo) {
        this.disableBtnNuevo = disableBtnNuevo;
    }

    public boolean isDisableBtnNuevo() {
        return disableBtnNuevo;
    }

    public void setBeanAula(BeanAula beanAula) {
        this.beanAula = beanAula;
    }

    public BeanAula getBeanAula() {
        return beanAula;
    }

    public void setFlgActivo(int flgActivo) {
        this.flgActivo = flgActivo;
    }

    public int getFlgActivo() {
        return flgActivo;
    }

    public void setVisibleChoiceEstado(boolean visibleChoiceEstado) {
        this.visibleChoiceEstado = visibleChoiceEstado;
    }

    public boolean isVisibleChoiceEstado() {
        return visibleChoiceEstado;
    }

    public void setVisibleRegistrar(boolean visibleRegistrar) {
        this.visibleRegistrar = visibleRegistrar;
    }

    public boolean isVisibleRegistrar() {
        return visibleRegistrar;
    }

    public void setVisibleNuevo(boolean visibleNuevo) {
        this.visibleNuevo = visibleNuevo;
    }

    public boolean isVisibleNuevo() {
        return visibleNuevo;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setRequeridInput(boolean requeridInput) {
        this.requeridInput = requeridInput;
    }

    public boolean isRequeridInput() {
        return requeridInput;
    }
}
