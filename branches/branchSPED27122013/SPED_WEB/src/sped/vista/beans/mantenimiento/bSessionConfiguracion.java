package sped.vista.beans.mantenimiento;

import java.io.Serializable;

public class bSessionConfiguracion implements Serializable {
    @SuppressWarnings("compatibility:-1281450986931422966")
    private static final long serialVersionUID = 1L;
    private String imgSource;
    private String rutaImg;
    private String puerto;
    private String host;
    private String correo;
    private int exec;

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }
}
