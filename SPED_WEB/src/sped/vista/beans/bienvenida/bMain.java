package sped.vista.beans.bienvenida;

import java.awt.AWTKeyStroke;

import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;

import oracle.adf.view.rich.event.DialogEvent;

import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

/** Clase de Respaldo de Frm_Main.jsf
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class bMain implements Serializable {

    @EJB
    private LN_C_SFPermisosLocal ln_C_SFPermisosLocal;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    private String usuario;
    private String nomUsuario;
    private final static String LOGIN = "/faces/Frm_login";
    private RichMenuBar menu;
    private bSessionMain sessionMain;

    public bMain(){
        super();
        try {
            if (beanUsuario != null) {
                usuario = beanUsuario.getUsuario() + " - " + beanUsuario.getNombres();
                nomUsuario = beanUsuario.getUsuario();
            } else {
                logoutTarget(LOGIN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logoutTarget(LOGIN);
        }
    }

    public void createMenus(PhaseEvent phaseEvent) {
       // if(sessionMain.getExec() == 0){
           // sessionMain.setExec(1);
            if(sessionMain.getLstPermisos() != null){
                sessionMain.getLstPermisos().removeAll(sessionMain.getLstPermisos());   
            }
            sessionMain.setLstPermisos(ln_C_SFPermisosLocal.getCrearArbolNuevo(beanUsuario.getRol().getNidRol()));
            for (int i = 0; i < sessionMain.getLstPermisos().size(); i++) {
                int hijoDeMBar = 0;
                crearHijos(sessionMain.getLstPermisos().get(i), new RichMenu(), hijoDeMBar);
            }
     //   }
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
            try {
                if (menuItem.getAccelerator() != null) {
                    if (!menuItem.getAccelerator().equals("")) {
                        rcni.setAccelerator(AWTKeyStroke.getAWTKeyStroke(menuItem.getAccelerator()));
                    }
                }
            } catch (Exception e) {
                // TODO: GRABAR EN EL LOG
                e.printStackTrace();
            }
            rcni.setAccessKey(menuItem.getAccessKey());
            rcni.setPartialSubmit(true);
            rcni.setAction(Utils.createActionMethodBinding("#{beanRegion.getMainCall}"));
            rcni.setActionListener(Utils.createActionListenerMethodBinding("#{bMain.getUrl}"));
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
    
    public String logoutTarget(String aTarget) {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        String url = ectx.getRequestContextPath() + aTarget;
        HttpSession session = (HttpSession)ectx.getSession(false);
        //close session
        session.invalidate();
        try {//@TODO log
            Utils.sysout("usuario cerro sesion");
            response.sendRedirect(url);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dialogLogoutListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
         //   ADFUtil.invokeEL("#{chatBean.logout}");
            logoutTarget(LOGIN);
        }
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

    public void setSessionMain(bSessionMain sessionMain) {
        this.sessionMain = sessionMain;
    }

    public bSessionMain getSessionMain() {
        return sessionMain;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }
}
