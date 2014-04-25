package sped.negocio.entidades.beans;

import java.io.Serializable;

import java.util.List;

/** Clase BeanCurso.java
 * @author czavalacas
 * @since 29.12.2013
 */
public class BeanCurso implements Serializable {
    @SuppressWarnings("compatibility:2272983401047662390")
    private static final long serialVersionUID = 1L;
    private String descripcionCurso;
    private Integer nidCurso;
    private List<BeanMain> mainLista;
    private BeanAreaAcademica areaAcademica;
    private String tipoFichaCurso;
    private int nidAreaNativa;
    
    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
    }

    public String getDescripcionCurso() {
        return descripcionCurso;
    }


    public void setNidCurso(Integer nidCurso) {
        this.nidCurso = nidCurso;
    }

    public Integer getNidCurso() {
        return nidCurso;
    }

    public void setMainLista(List<BeanMain> mainLista) {
        this.mainLista = mainLista;
    }

    public List<BeanMain> getMainLista() {
        return mainLista;
    }

    public void setAreaAcademica(BeanAreaAcademica areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public BeanAreaAcademica getAreaAcademica() {
        return areaAcademica;
    }

    public void setTipoFichaCurso(String tipoFichaCurso) {
        this.tipoFichaCurso = tipoFichaCurso;
    }

    public String getTipoFichaCurso() {
        return tipoFichaCurso;
    }

    public void setNidAreaNativa(int nidAreaNativa) {
        this.nidAreaNativa = nidAreaNativa;
    }

    public int getNidAreaNativa() {
        return nidAreaNativa;
    }
}
