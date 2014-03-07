package sped.vista.beans.administrativo.usuario;

import java.io.Serializable;

import java.util.List;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import sped.negocio.entidades.beans.BeanPermiso;

public class bSessionGestionarPermisos implements Serializable {
    @SuppressWarnings("compatibility:1667722088073630492")
    private static final long serialVersionUID = 1L;
    
    private transient ChildPropertyTreeModel permisosTree;
    private transient ChildPropertyTreeModel permisosTree_aux;
    private BeanPermiso permisos;
    private int exec;
    private int nidPadre;
    private int validaPermiso;
    
    public void encuentraCheck_aux(String descripcion, boolean estado){
        encuentraCheck(descripcion, permisos, estado);
    }
    
    public void encuentraCheck(String descripcion, BeanPermiso permiso, boolean estado){
        if(validaPermiso != 1){
            if(permiso.getDescripcionPermiso().compareTo(descripcion) == 0){
                setValidaPermiso(1);//encuentra el check y cambia a 1
                checkHijos(permiso, estado);//en caso de ser padre seleciona o deseleciona a sus hijos
                setNidPadre(permiso.getNidPadre());//pasamos el nidpadre del que se busco
            }else{
                if(permiso.getListaHijos() != null){
                    for(int i=0; i<permiso.getListaHijos().size(); i++){
                        if(validaPermiso == 0){
                            encuentraCheck(descripcion, permiso.getListaHijos().get(i), estado);
                        }
                        if(validaPermiso == 1){//una vez que encuentra es 1
                            if(nidPadre == permiso.getListaHijos().get(i).getNidPermiso()){
                                if(estado){//si es verdadero marca a los padres
                                    permiso.getListaHijos().get(i).setEstado(estado);                                    
                                }else{//si es falso verifica que los padres tengan hijos con check
                                    checkPadres(permiso.getListaHijos().get(i));
                                    System.out.print(permiso.getListaHijos().get(i));
                                }                                
                                setNidPadre(permiso.getListaHijos().get(i).getNidPadre());
                            }
                        }                        
                    }
                }
            }            
        }        
    }
    
    public void checkHijos(BeanPermiso permiso, boolean estado){
        permiso.setEstado(estado);
        if(permiso.getListaHijos() != null){
            for(int i=0; i<permiso.getListaHijos().size(); i++){
                checkHijos(permiso.getListaHijos().get(i), estado);
            }            
        }
    }
    
    public void checkPadres(BeanPermiso permiso){
        if(permiso.getListaHijos() != null){
            permiso.setEstado(false);
            for(int i=0; i<permiso.getListaHijos().size(); i++){
                if(permiso.getListaHijos().get(i).isEstado()==true){
                    permiso.setEstado(true);
                    break;
                }
            }            
        }
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setPermisosTree_aux(ChildPropertyTreeModel permisosTree_aux) {
        this.permisosTree_aux = permisosTree_aux;
    }

    public ChildPropertyTreeModel getPermisosTree_aux() {
        return permisosTree_aux;
    }

    public void setPermisos(BeanPermiso permisos) {
        this.permisos = permisos;
    }

    public BeanPermiso getPermisos() {
        return permisos;
    }

    public void setValidaPermiso(int validaPermiso) {
        this.validaPermiso = validaPermiso;
    }

    public int getValidaPermiso() {
        return validaPermiso;
    }

    public void setNidPadre(int nidPadre) {
        this.nidPadre = nidPadre;
    }

    public int getNidPadre() {
        return nidPadre;
    }
}
