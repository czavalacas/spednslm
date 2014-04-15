package sped.vista.beans.mantenimiento;

import java.io.Serializable;

public class bSessionConfiguracion implements Serializable {
    @SuppressWarnings("compatibility:-5162283322511947211")
    private static final long serialVersionUID = 1L;
    private String imgSource;
    private String rutaImg;

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
}
