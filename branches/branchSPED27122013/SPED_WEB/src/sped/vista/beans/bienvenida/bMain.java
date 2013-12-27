package sped.vista.beans.bienvenida;

import java.io.Serializable;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;

import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bMain implements Serializable {

    private List<BeanPermiso> lstPermisos;
    @EJB
    private LN_C_SFPermisosLocal ln_C_SFPermisosLocal;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    private String usuario;
    private RichMenuBar menu;

    public bMain(){
        super();
        
        if(beanUsuario != null){
            usuario = beanUsuario.getUsuario() + " - " + beanUsuario.getNombres();
        }
    }

    public void createMenus(PhaseEvent phaseEvent) {
        System.out.println("phaseEvent");
        if(this.getLstPermisos() != null){
            this.getLstPermisos().removeAll(this.getLstPermisos());   
        }else{
            System.out.println("NULO");
        }
        this.setLstPermisos(ln_C_SFPermisosLocal.getCrearArbolNuevo(beanUsuario.getRol().getNidRol()));
        for (int i = 0; i < lstPermisos.size(); i++) {
            int hijoDeMBar = 0;
            crearHijos(lstPermisos.get(i), new RichMenu(), hijoDeMBar);
        }
    }

    public void crearHijos(BeanPermiso menuItem, RichMenu _menu, int hijoDeMBar) {
        if (menuItem.getListaHijos() != null) {
            if (menuItem.getListaHijos().size() > 0) {
                RichMenu menu2 = new RichMenu();
                menu2.setId("hijosDe_" + menuItem.getNidPermiso());
                menu2.setText(menuItem.getDescripcionPermiso());
                if (hijoDeMBar == 0) { //Es hijo directamente del menubar
                    menu.getChildren().add(menu2);
                } else if (hijoDeMBar > 0) {
                    _menu.getChildren().add(menu2);
                }
                for (int j = 0; j < menuItem.getListaHijos().size(); j++) {
                    hijoDeMBar++;
                    crearHijos(menuItem.getListaHijos().get(j), menu2, hijoDeMBar);
                }
            }
        } else {
            RichCommandMenuItem rcni = new RichCommandMenuItem();
            rcni.setText(menuItem.getDescripcionPermiso());
            rcni.setId("menu" + menuItem.getNidPermiso());
            rcni.setShortDesc(menuItem.getUrl());
            rcni.setPartialSubmit(true);
            rcni.setAction(Utils.createActionMethodBinding("#{viewScope.RegionBean.getMainCall}"));
            rcni.setActionListener(Utils.createActionListenerMethodBinding("#{backingBeanScope.bbMenu.getUrl}"));
            if (hijoDeMBar == 0) { //Es hijo directamente del menubar
                menu.getChildren().add(rcni);
            } else if (hijoDeMBar > 0) {
                _menu.getChildren().add(rcni);
            }
        }
    }

    public void getUrl(ActionEvent e) {
        RichCommandMenuItem componente = (RichCommandMenuItem) e.getComponent();
        String url = componente.getShortDesc();
        Utils.putSession("url", url);
    }
    
    public void setLstPermisos(List<BeanPermiso> lstPermisos) {
        this.lstPermisos = lstPermisos;
    }

    public List<BeanPermiso> getLstPermisos() {
        return lstPermisos;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setMenu(RichMenuBar menu) {
        this.menu = menu;
    }

    public RichMenuBar getMenu() {
        return menu;
    }
}
