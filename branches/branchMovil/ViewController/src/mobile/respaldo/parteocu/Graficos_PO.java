package mobile.respaldo.parteocu;

import java.util.ArrayList;
import java.util.List;
import javax.el.ValueExpression;
import mobile.AdfmUtils;
import mobile.beans.BeanBar;
import mobile.beans.BeanPie;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class Graficos_PO {
    
    private List lstPie = new ArrayList();
    private List lstBarra = new ArrayList();

    public Graficos_PO(){
        
    }
    
    public void initGraficos(){
        try {
            lstPie.removeAll(lstPie);
            lstBarra.removeAll(lstBarra);
            ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.listaBarras}", BeanBar[].class);
            ValueExpression ve2 = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.listaPies}", BeanPie[].class);

            BeanBar[] barras = (BeanBar[])ve1.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(barras != null){
                for (int j = 0; j < barras.length; j++) {
                    lstBarra.add(barras[j]);
                }   
            }

            BeanPie[] pies = (BeanPie[])ve2.getValue(AdfmfJavaUtilities.getAdfELContext());
            if(pies != null){
                for (int j = 0; j < pies.length; j++) {
                    lstPie.add(pies[j]);
                }
            }
        } catch (Exception e) {
           /*  AdfmUtils.log(">>>ERROR "+e.getMessage());
            for(int i = 0; i < e.getStackTrace().length; i++){
                AdfmUtils.log(e.getStackTrace()[i]);
            } */
        }
    }
    
    public BeanPie[] getPieProfesor(){
        //initPieProfe();
        BeanPie c[] = null;
        if(lstPie != null){
            c = (BeanPie[])lstPie.toArray(new BeanPie[lstPie.size()]);
        }
        return c; 
    }
    
    public BeanBar[] getBarProfesor(){
        initGraficos();
        BeanBar c[] = null;
        if(lstBarra != null){
            c = (BeanBar[])lstBarra.toArray(new BeanBar[lstBarra.size()]);
        }
        return c; 
    }
    
    public void reset(ActionEvent actionEvent) {
        lstPie.removeAll(lstPie);
        lstBarra.removeAll(lstBarra);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.listaBarras}",null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.listaPies}",null);
    }
    
    public void setLstPie(List lstPie) {
        this.lstPie = lstPie;
    }

    public List getLstPie() {
        return lstPie;
    }

    public void setLstBarra(List lstBarra) {
        this.lstBarra = lstBarra;
    }

    public List getLstBarra() {
        return lstBarra;
    }
}