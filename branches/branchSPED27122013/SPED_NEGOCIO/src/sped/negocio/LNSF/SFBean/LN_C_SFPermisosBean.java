package sped.negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import sped.negocio.BDL.IL.BDL_C_SFPermisoLocal;
import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.LNSF.IR.LN_C_SFPermisosRemote;
import sped.negocio.Utils.Utiles;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.sist.Permiso;

@Stateless(name = "LN_C_SFPermisos", mappedName = "mapLN_C_SFPermisos")
public class LN_C_SFPermisosBean implements LN_C_SFPermisosRemote,
                                               LN_C_SFPermisosLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SPED_NEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFPermisoLocal bdL_C_SFPermisoLocal;
    private MapperIF mapper = new DozerBeanMapper();

    public LN_C_SFPermisosBean() {
    }
    
    public List<BeanPermiso> getCrearArbolNuevo(int nidRol,
                                                int nidUsuario) {
        int nivel = 0;
        List<BeanPermiso> listaMenu = new ArrayList<BeanPermiso>();
        BeanPermiso raiz = new BeanPermiso();
        List<Integer> lstPermisos = new ArrayList<Integer>();
        if(nidRol != -1){
            List<Permiso> e_raiz = bdL_C_SFPermisoLocal.getByNidPermiso(0);
            for(int i = 0; i < e_raiz.size(); i++){
                raiz = setBean(e_raiz.get(i), "S",lstPermisos);
                raiz = crearArbolAux(nivel, null, null, raiz,nidRol,nidUsuario,lstPermisos);
                listaMenu.add(raiz);
            }
        }
        return listaMenu;
    }
    
    public BeanPermiso crearArbolAux(int nivel, 
                                      List<BeanPermiso> hijos, 
                                      BeanPermiso padre, 
                                      BeanPermiso raiz,
                                      int nidRol,
                                      int nidUsuario,
                                      List<Integer> lstPermisos){
        if(nivel == bdL_C_SFPermisoLocal.getNiveles()){
            return raiz;
        }
        if(hijos == null){
            raiz = setearHijos(raiz,hijos,null,0,1,nidRol,nidUsuario,lstPermisos);
            if(hayHijos(raiz.getListaHijos(),nidRol,nidUsuario)){
                nivel += 1;
                raiz = crearArbolAux(nivel, raiz.getListaHijos(), padre, raiz,nidRol,nidUsuario,lstPermisos);
            }else{
                return raiz;
            }
        }else{
            BeanPermiso hijo = new BeanPermiso();
            for(int i = 0; i <hijos.size(); i++){
                hijo = hijos.get(i);
                hijo = setearHijos(raiz,hijos,hijo,hijo.getNidPermiso(),2,nidRol,nidUsuario,lstPermisos);
                if (hayHijos(hijo.getListaHijos(),nidRol,nidUsuario)) {
                    nivel += 1;
                    crearArbolAux(nivel, hijo.getListaHijos(), padre, raiz,nidRol,nidUsuario,lstPermisos);
                } 
            }
        }
        return raiz;
    }
    
    public BeanPermiso setearHijos(BeanPermiso raiz, 
                                    List<BeanPermiso> hijos,
                                    BeanPermiso padre,
                                    int nidp, 
                                    int pv,
                                    int nidRol,
                                    int nidUsuario,
                                    List<Integer> lstPermisos){
        int bd = (pv == 2) ? nidp : raiz.getNidPermiso();
        BeanPermiso hijosObj = new BeanPermiso();
        hijos = new ArrayList<BeanPermiso>();
        List<Permiso> lstPerm = bdL_C_SFPermisoLocal.getHijosByPadre(bd,nidUsuario,nidRol);
        Iterator it = lstPerm.iterator();
        while(it.hasNext()){
            Permiso objP = (Permiso) it.next();
            if(!objP.getHabilidad().equals("S")){
                hijosObj = setBean(objP,"N",lstPermisos);
                hijos.add(hijosObj);
            }
            if(!lstPermisos.contains(objP.getNidPermiso())){
                lstPermisos.add(objP.getNidPermiso());
            }
        }
        if(pv == 1){
            raiz.setListaHijos(hijos);
            return raiz;
        }else{
            padre.setListaHijos(hijos);
            return padre;
        }
    }
    
    public boolean hayHijos(List<BeanPermiso> lista,
                            int nidRol,
                            int nidUsuario){
            Iterator it = lista.iterator();
            int cant = 0;
            while(it.hasNext()){
                BeanPermiso papa = (BeanPermiso) it.next();
                List<Permiso> papHijos = bdL_C_SFPermisoLocal.getHijosByPadre(papa.getNidPermiso(),nidUsuario,nidRol);
                cant += papHijos.size();
            }
            return (cant > 0) ? true : false;
    }
    
    public BeanPermiso setBean(Permiso permiso,String indMostrar,List<Integer> lstPermisos){
        BeanPermiso bean = (BeanPermiso) mapper.map(permiso, BeanPermiso.class);
        bean.setLstPermisos(lstPermisos);
        bean.setIndMostrar(indMostrar);
        return bean;
    }        
    
    public List<BeanPermiso> getPermisos_WS(int nidRol,
                                             int nidUsuario){
        List<BeanPermiso> lstPerms = new ArrayList<BeanPermiso>();
        for(Permiso perm : bdL_C_SFPermisoLocal.getHijosByPadre_WS(nidUsuario, nidRol)){
            BeanPermiso bean = (BeanPermiso) mapper.map(perm, BeanPermiso.class);
            if(perm.getUrl() != null){
                bean.setUrlIcono(perm.getUrl().substring(perm.getUrl().indexOf("#")+1,perm.getUrl().length()));
            }
            lstPerms.add(bean);
        }
        return lstPerms;
    }
    
    public BeanPermiso setBeanGP(Object[] datos){
        BeanPermiso bean = (BeanPermiso) mapper.map(datos[0], BeanPermiso.class);
        if(datos[1].toString().compareTo("1") == 0){
            bean.setEstado(true);
        }else{
            bean.setEstado(false);
        }
        bean.setNidPermisoUsuario(Integer.parseInt(datos[2].toString()));
        return bean;
    }
    
    public BeanPermiso getCrearArbolNuevoGP(int nidRol,
                                            int nidUsuario) {
        BeanPermiso aux = new BeanPermiso();
        aux.setDescripcionPermiso("SPED");
        List<BeanPermiso> permisos = new ArrayList();
        if(nidRol != -1){
            List lstraiz = bdL_C_SFPermisoLocal.getHijosByPadreGP(0, nidUsuario, nidRol);
            aux.setEstado(false);
            for(Object dato : lstraiz){
                Object[] datos = (Object[]) dato;
                BeanPermiso raiz = setBeanGP(datos);
                if(raiz.isEstado()){
                    aux.setEstado(true);
                }
                crearArbolAuxGP(raiz, nidRol, nidUsuario);
                permisos.add(raiz);
            }
        }
        aux.setListaHijos(permisos);
        return aux;
    }
    
    public BeanPermiso crearArbolAuxGP(BeanPermiso raiz,
                                       int nidRol,
                                       int nidUsuario){
        List raiz_aux = bdL_C_SFPermisoLocal.getHijosByPadreGP(raiz.getNidPermiso(), nidUsuario, nidRol);        
        if(raiz_aux != null){
            List<BeanPermiso> permisos = new ArrayList();
            for(Object dato : raiz_aux){
                Object[] datos = (Object[]) dato;
                BeanPermiso hijo = setBeanGP(datos);
                crearArbolAuxGP(hijo, nidRol, nidUsuario);
                permisos.add(hijo);
            }
            raiz.setListaHijos(permisos);
        }
        return raiz;
    }
    
    
}