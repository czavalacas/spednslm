package sped.vista.beans.evaluacion.grafico_reporte;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.data.RichCarousel;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneListbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import sped.negocio.LNSF.IL.LN_C_SFEvaluacionLocal;
import sped.negocio.LNSF.IL.LN_C_SFIndicadorLocal;
import sped.negocio.LNSF.IL.LN_C_SFUtilsLocal;
import sped.negocio.LNSF.IR.LN_C_SFIndicadorRemote;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.entidades.beans.BeanUsuario;
import sped.vista.Utils.Utils;

public class bDesempenoIndicador {
    
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    private bSessionDesempenoIndicador sessionDesempenoIndicador;
    @EJB
    private LN_C_SFIndicadorLocal ln_C_SFIndicadorLocal;
    @EJB
    private LN_C_SFUtilsLocal ln_C_SFUtilsLocal;
    @EJB
    private LN_C_SFEvaluacionLocal ln_C_SFEvaluacionLocal;
    private ArrayList<SelectItem> indisList = null;
    private RichInputText itInd;
    private RichSelectOneListbox indlist;
    private RichCarousel carPies;
    Calendar cal= new GregorianCalendar();

    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionDesempenoIndicador.getExec() == 0) {
            llenarChoices();
            sessionDesempenoIndicador.setItemNombreIndicadores(Utils.llenarListItemAux(ln_C_SFIndicadorLocal.getNombreIndicadoresNew_LN()));   
            sessionDesempenoIndicador.setFechaFin(cal.getTime());
            cal.add(Calendar.MONTH, -4);
            sessionDesempenoIndicador.setFechaInicio(cal.getTime());
            if(beanUsuario.getRol().getNidRol() == 4){
                sessionDesempenoIndicador.setNidSede(""+beanUsuario.getSede().getNidSede());
                sessionDesempenoIndicador.setEstadoChoiceSede(true);
            }
        }
    }

    public void llenarChoices(){
        sessionDesempenoIndicador.setListaSedesFiltro(Utils.llenarCombo(ln_C_SFUtilsLocal.getSedes_LN()));
        sessionDesempenoIndicador.setListaAreasFiltro(Utils.llenarCombo(ln_C_SFUtilsLocal.getAreas_LN_WS()));
        sessionDesempenoIndicador.setListaNivelesFiltro(Utils.llenarCombo(ln_C_SFUtilsLocal.getNiveles_LN()));
    }

    public void buscarInforPieIndicadores(ActionEvent ae) {
        if(sessionDesempenoIndicador.getCidIndicador() != null){
            sessionDesempenoIndicador.setLstGrafPies(ln_C_SFEvaluacionLocal.getConsultaDesempenoValores_LN(Integer.parseInt(sessionDesempenoIndicador.getCidIndicador()),
                                                                                                           sessionDesempenoIndicador.getNidNivele() == null ? null : Integer.parseInt(sessionDesempenoIndicador.getNidNivele()),
                                                                                                           sessionDesempenoIndicador.getNidArea() == null ? null : Integer.parseInt(sessionDesempenoIndicador.getNidArea()), 
                                                                                                           sessionDesempenoIndicador.getNidSede() == null ? null : Integer.parseInt(sessionDesempenoIndicador.getNidSede()), 
                                                                                                           sessionDesempenoIndicador.getFechaInicio(), 
                                                                                                           sessionDesempenoIndicador.getFechaFin()));
            carPies.setValue(sessionDesempenoIndicador.getLstGrafPies());
            Utils.addTarget(carPies);
        }
    }
    
    public void busquedaConTecla(ClientEvent ce){
        String message = (String) ce.getParameters().get("subValue");
        sessionDesempenoIndicador.setCidIndicador(message);
        Utils.sysout("indicador: "+message);
        for(SelectItem item : getIndisList()){
            if(item.getValue() == Integer.parseInt(message) ){
                itInd.setValue(item.getLabel());
                Utils.addTarget(itInd);
            }
        }
    }
    
    public List<SelectItem> suggestIndicadores(String string) {
        return Utils.getSuggestions(sessionDesempenoIndicador.getItemNombreIndicadores(), string);
    }

    public void setIndisList(ArrayList<SelectItem> indisList) {
        this.indisList = indisList;
    }

    public ArrayList<SelectItem> getIndisList() {
        if (indisList == null){
            indisList = new ArrayList<SelectItem>();
            List<Object[]> filteredList = null;
            filteredList = ln_C_SFIndicadorLocal.getNombreIndicadoresNew_LN();
            indisList = populateList(filteredList);
        }        
        return indisList;
    }

    private ArrayList<SelectItem> populateList(List<Object[]> filteredList) {
        ArrayList<SelectItem> _list = new ArrayList<SelectItem>();
        for(Object[] suggestString : filteredList){
            SelectItem si = new SelectItem();
            si.setValue(suggestString[0]);
            si.setLabel((String) suggestString[1]);
            si.setDescription((String) suggestString[1]);
            _list.add(si);
        }
      return _list;
    }
    
    public void doFilterList(ClientEvent clientEvent) {
        // set the content for the suggest list
        String srchString = (String)clientEvent.getParameters().get("filterString");
        int maxSuggestedItems = 5; //este dato debe ser = al del autoSuggest; sirve para validar lo necesario
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for(SelectItem item : getIndisList()){
            if(item.getLabel().toUpperCase().contains(srchString.toUpperCase())){
                items.add(item);
            }
            if(items.size() == maxSuggestedItems){
                break;
            } 
        }
        indisList = items;
        Utils.addTarget(indlist);
    }
    
    public void setSessionDesempenoIndicador(bSessionDesempenoIndicador sessionDesempenoIndicador) {
        this.sessionDesempenoIndicador = sessionDesempenoIndicador;
    }

    public bSessionDesempenoIndicador getSessionDesempenoIndicador() {
        return sessionDesempenoIndicador;
    }

    public void setItInd(RichInputText itInd) {
        this.itInd = itInd;
    }

    public RichInputText getItInd() {
        return itInd;
    }

    public void setIndlist(RichSelectOneListbox indlist) {
        this.indlist = indlist;
    }

    public RichSelectOneListbox getIndlist() {
        return indlist;
    }
    
    public void setCarPies(RichCarousel carPies) {
        this.carPies = carPies;
    }

    public RichCarousel getCarPies() {
        return carPies;
    }
}
