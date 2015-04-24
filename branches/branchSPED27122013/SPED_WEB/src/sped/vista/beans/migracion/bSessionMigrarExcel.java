package sped.vista.beans.migracion;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

import org.apache.myfaces.trinidad.model.UploadedFile;

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
    
    public bSessionMigrarExcel() {
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
}
