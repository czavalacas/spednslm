package test.view.backing.menu;

import java.util.List;

public class MenuBean {
    
    private int nidMenu;
    private String descMenu;
    private String urlMenu;
    private List<MenuBean> lstHijos;

    public void setNidMenu(int nidMenu) {
        this.nidMenu = nidMenu;
    }

    public int getNidMenu() {
        return nidMenu;
    }

    public void setDescMenu(String descMenu) {
        this.descMenu = descMenu;
    }

    public String getDescMenu() {
        return descMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setLstHijos(List<MenuBean> lstHijos) {
        this.lstHijos = lstHijos;
    }

    public List<MenuBean> getLstHijos() {
        return lstHijos;
    }
}
