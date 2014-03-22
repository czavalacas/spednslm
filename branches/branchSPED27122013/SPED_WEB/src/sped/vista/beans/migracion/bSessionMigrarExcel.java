package sped.vista.beans.migracion;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class bSessionMigrarExcel implements Serializable {
    private String nidSede;
    private UploadedFile file;
    private InputStream inputStreamFile;
    private String nombreArchivo;
    private boolean estadoBtnSubArchivo=true;
    private int tipoMigracion;
    private boolean estadouploadFile=true;
    
    public bSessionMigrarExcel() {
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
}
