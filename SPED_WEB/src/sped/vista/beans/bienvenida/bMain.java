package sped.vista.beans.bienvenida;

import java.awt.AWTKeyStroke;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;

import oracle.adf.view.rich.component.rich.output.RichMedia;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.PollEvent;

import sped.negocio.LNSF.IL.LN_C_SFNotificacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFPermisosLocal;
import sped.negocio.entidades.beans.BeanPermiso;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.ADFUtil;
import sped.vista.Utils.Utils;

/** Clase de Respaldo de Frm_Main.jsf
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class bMain implements Serializable {

    private RichMenuBar menu;
    private bSessionMain sessionMain;
    private RichOutputLabel cantNotif;
    private RichCommandLink clCantEvas;
    private RichCommandLink clCantPO;
    private RichCommandLink clCantAll;
    private RichMedia sonidoBuho;
    @EJB
    private LN_C_SFPermisosLocal ln_C_SFPermisosLocal;
    @EJB
    private LN_C_SFNotificacionLocal ln_C_SFNotificacionLocal;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    private String usuario;
    private String nomUsuario;
    private final static String LOGIN = "/faces/Frm_login";
    private FacesContext ctx = FacesContext.getCurrentInstance();

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
        try {
            if (sessionMain.getLstPermisos() != null) {
                sessionMain.getLstPermisos().removeAll(sessionMain.getLstPermisos());
                sessionMain.getLstPermisos().clear();
                sessionMain.setLstPermisos(new ArrayList<BeanPermiso>());
            }
            List<BeanPermiso> lstPerm = ln_C_SFPermisosLocal.getCrearArbolNuevo(beanUsuario.getRol().getNidRol(), beanUsuario.getNidUsuario());
            sessionMain.setLstPermisos(lstPerm);
            List<Integer> lstPermisos = lstPerm.get(0).getLstPermisos();
            beanUsuario.setLstPermisos(lstPermisos);
            sessionMain.setVerNotificaciones(Utils.hasPermiso(lstPermisos,new Integer("19")));
            sessionMain.setVerNotificacionesEvas(Utils.hasPermiso(lstPermisos,new Integer("16")));
            sessionMain.setVerNotificacionesPOs(Utils.hasPermiso(lstPermisos,new Integer("17")));
            if(menu != null){
                if(menu.getChildren() != null){
                    menu.getChildren().clear();
                }
            }
            for (int i = 0; i < sessionMain.getLstPermisos().size(); i++) {
                int hijoDeMBar = 0;
                crearHijos(sessionMain.getLstPermisos().get(i), new RichMenu(), hijoDeMBar);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            rcni.setImmediate(true);
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
            logoutTarget(LOGIN);
        }
    }
    
    public void verNotifEvas(ActionEvent actionEvent) {
        if(Utils.getSession("url") != null){
            if(Utils.getSession("url").equals("/WEB-INF/notificaciones.xml#notificaciones")){
                return;
            }
        }
        Utils._redireccionar(ctx,"/WEB-INF/notificaciones.xml#notificaciones");
    }
    
    public void verNotifPOs(ActionEvent actionEvent) {
        if(Utils.getSession("url") != null){
            if(Utils.getSession("url").equals("/WEB-INF/notificaciones.xml#notificaciones")){
                return;
            }
        }
        Utils._redireccionar(ctx,"/WEB-INF/notificaciones.xml#notificaciones");
    }
    
    public void verNotifTodos(ActionEvent actionEvent) {
        if(Utils.getSession("url") != null){
            if(Utils.getSession("url").equals("/WEB-INF/notificaciones.xml#notificaciones")){
                return;
            }
        }
        Utils._redireccionar(ctx,"/WEB-INF/notificaciones.xml#notificaciones");
    }


    public void getNumeroNotificacionesAll(PollEvent pe) {
        try {
            int vec[] = new int[3];
            vec = ln_C_SFNotificacionLocal.getCantidadAMostrarNotificaciones(beanUsuario.getNidUsuario(),sessionMain.isVerNotificacionesEvas(),sessionMain.isVerNotificacionesPOs());
            sessionMain.setCantNotifEvas(vec[0]);
            sessionMain.setCantNotifPO(vec[1]); 
            sessionMain.setCantNotif(vec[2]);
            cantNotif.setValue(sessionMain.getCantNotif());
            if(sessionMain.getCantNotif() > 0){
                cantNotif.setRendered(true);
                sessionMain.setVerNotificaciones(true);
                cantNotif.setVisible(true);
                if(sessionMain.getCantNotif() != sessionMain.getCantNotifAux()){
                    Utils.llamarJavascript("reproducirNotificacion");
                    Utils.sysout("play buhoo");
                    sessionMain.setCantNotifAux(sessionMain.getCantNotif());
                }
            }else{
                sessionMain.setCantNotif(0);
                cantNotif.setValue(0);
                cantNotif.setVisible(false);
            }
            Utils.addTarget(cantNotif);
            if(clCantPO != null){
                clCantPO.setText("Hay "+sessionMain.getCantNotifPO()+" Notificaciones de Partes de Ocurrencia");
                Utils.addTarget(clCantPO);
            }
            if(clCantEvas != null){
                clCantEvas.setText("Hay "+sessionMain.getCantNotifEvas()+" Notificaciones de Evaluaciones");
                Utils.addTarget(clCantEvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
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

    public void setCantNotif(RichOutputLabel cantNotif) {
        this.cantNotif = cantNotif;
    }

    public RichOutputLabel getCantNotif() {
        return cantNotif;
    }

    public void setClCantEvas(RichCommandLink clCantEvas) {
        this.clCantEvas = clCantEvas;
    }

    public RichCommandLink getClCantEvas() {
        return clCantEvas;
    }

    public void setClCantPO(RichCommandLink clCantPO) {
        this.clCantPO = clCantPO;
    }

    public RichCommandLink getClCantPO() {
        return clCantPO;
    }

    public void setClCantAll(RichCommandLink clCantAll) {
        this.clCantAll = clCantAll;
    }

    public RichCommandLink getClCantAll() {
        return clCantAll;
    }

    public void setSonidoBuho(RichMedia sonidoBuho) {
        this.sonidoBuho = sonidoBuho;
    }

    public RichMedia getSonidoBuho() {
        return sonidoBuho;
    }
}
