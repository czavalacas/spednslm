package test.view.backing.menu;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.PhaseEvent;

import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.nav.RichGoMenuItem;

import oracle.adf.view.rich.event.DialogEvent;

import test.view.backing.Utils;

public class BBeanMenu {

    private List<MenuBean> lstMenus;
    private RichMenuBar menu;
    
    public BBeanMenu() {
        MenuBean menuPadre = new MenuBean();
        menuPadre.setDescMenu("Gestionar Fichas");
        menuPadre.setNidMenu(1);
        //menuPadre.setUrlMenu("registrar.com");

        MenuBean menuPadre2 = new MenuBean();
        menuPadre2.setDescMenu("Gestionar Usuarios");
        menuPadre2.setNidMenu(2);
        //menuPadre2.setUrlMenu("Usuario.com");

        MenuBean menuPadre3 = new MenuBean();
        menuPadre3.setDescMenu("Gestionar Profesores");
        menuPadre3.setNidMenu(3);
        //menuPadre3.setUrlMenu("Profesores.com");

        MenuBean menuPadre4 = new MenuBean();
        menuPadre4.setDescMenu("Gestionar Aulas");
        menuPadre4.setNidMenu(4);
        //menuPadre4.setUrlMenu("Aula.com");

        MenuBean menuPadre5 = new MenuBean();
        menuPadre5.setDescMenu("Registrar Fichas");
        menuPadre5.setNidMenu(5);


        MenuBean menuPadre6 = new MenuBean();
        menuPadre6.setDescMenu("Modificar Fichas");
        menuPadre6.setNidMenu(6);
        menuPadre6.setUrlMenu("/WEB-INF/registrar.xml#registrar");

        MenuBean menuPadre7 = new MenuBean();
        menuPadre7.setDescMenu("Borrar Fichas");
        menuPadre7.setNidMenu(7);
        menuPadre7.setUrlMenu("/WEB-INF/test2.xml#test2");

        MenuBean menuPadre8 = new MenuBean();
        menuPadre8.setDescMenu("Auditar Registrar Fichas");
        menuPadre8.setNidMenu(8);
        menuPadre8.setUrlMenu("/WEB-INF/TestFrag.xml#TestFrag");

        List<MenuBean> lstFichasHijos = new ArrayList<MenuBean>();
        lstFichasHijos.add(menuPadre5);
        lstFichasHijos.add(menuPadre6);
        lstFichasHijos.add(menuPadre7);
        menuPadre.setLstHijos(lstFichasHijos);

        List<MenuBean> lstFichasHijos2 = new ArrayList<MenuBean>();
        lstFichasHijos2.add(menuPadre8);
        menuPadre5.setLstHijos(lstFichasHijos2);

        lstMenus = new ArrayList<MenuBean>();
        lstMenus.add(menuPadre);
        lstMenus.add(menuPadre2);
        lstMenus.add(menuPadre3);
        lstMenus.add(menuPadre4);
        System.out.println("lleno las listas");
    }

    public void createMenus(PhaseEvent phaseEvent) {
        System.out.println("phaseEvent");
        for (int i = 0; i < lstMenus.size(); i++) {
            int hijoDeMBar = 0;
            crearHijos(lstMenus.get(i), new RichMenu(), hijoDeMBar);
        }
    }

    public void crearHijos(MenuBean menuItem, RichMenu _menu, int hijoDeMBar) {
        if (menuItem.getLstHijos() != null) {
            if (menuItem.getLstHijos().size() > 0) {
                RichMenu menu2 = new RichMenu();
                menu2.setId("hijosDe_" + menuItem.getNidMenu());
                menu2.setText(menuItem.getDescMenu());
                if (hijoDeMBar == 0) { //Es hijo directamente del menubar
                    menu.getChildren().add(menu2);
                } else if (hijoDeMBar > 0) {
                    _menu.getChildren().add(menu2);
                }
                for (int j = 0; j < menuItem.getLstHijos().size(); j++) {
                    hijoDeMBar++;
                    crearHijos(menuItem.getLstHijos().get(j), menu2, hijoDeMBar);
                }
            }
        } else {
            RichCommandMenuItem rcni = new RichCommandMenuItem();
            //RichGoMenuItem rcni = new RichGoMenuItem();
            rcni.setText(menuItem.getDescMenu());
            rcni.setId("menu" + menuItem.getNidMenu());
            rcni.setShortDesc(menuItem.getUrlMenu());
            rcni.setPartialSubmit(true);
            rcni.setAction(createActionMethodBinding("#{viewScope.RegionBean.getMainCall}"));
            rcni.setActionListener(createActionListenerMethodBinding("#{backingBeanScope.bbMenu.getUrl}"));
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

    private MethodBinding createActionListenerMethodBinding(String actionListenerString) {
        Class args[] = { ActionEvent.class };
        MethodBinding methodBinding = null;
        methodBinding =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding(actionListenerString, args);
        return methodBinding;
    }

    private MethodBinding createActionMethodBinding(String action) {
        Class args[] = { };
        MethodBinding methodBinding = null;
        methodBinding = FacesContext.getCurrentInstance().getApplication().createMethodBinding(action, args);
        return methodBinding;
    }
    
    public void cerrarSession(ActionEvent e){
        System.out.println("CERRO");
    }
    
    public void dialogLogoutListener(DialogEvent dialogEvent){
        System.out.println("CERRO !!!!!!!!!!!!!!!!!!!!");
    }

    public void setLstMenus(List<MenuBean> lstMenus) {
        this.lstMenus = lstMenus;
    }

    public List<MenuBean> getLstMenus() {
        return lstMenus;
    }

    public void setMenu(RichMenuBar menu) {
        this.menu = menu;
    }

    public RichMenuBar getMenu() {
        return menu;
    }
}