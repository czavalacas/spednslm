package sped.vista.beans.administrativo.usuario;

import java.io.Serializable;

public class bSessionConfigurarCuenta implements Serializable {
    @SuppressWarnings("compatibility:5856487130466852733")
    private static final long serialVersionUID = 1L;
    private String rutaImg;
    private int exec;
    private String imgSource;
    
    public bSessionConfigurarCuenta() {
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

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public String getImgSource() {
        return imgSource;
    }
}
