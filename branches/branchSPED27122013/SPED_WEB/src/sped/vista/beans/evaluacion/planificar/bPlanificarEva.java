package sped.vista.beans.evaluacion.planificar;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.event.CalendarActivityEvent;
import oracle.adf.view.rich.event.CalendarEvent;
import oracle.adf.view.rich.model.CalendarActivity;

import sped.negocio.BDL.IR.BDL_C_SFMainRemote;
import sped.negocio.LNSF.IR.LN_C_SFMainRemote;
import sped.negocio.LNSF.SFBean.LN_C_SFMainBean;
import sped.negocio.entidades.admin.Main;
import sped.negocio.entidades.beans.BeanMain;

public class bPlanificarEva {  
    @EJB    
    private LN_C_SFMainRemote ln_C_SFMainRemote;
    private sessionPlanificar sessionPlanificarEva;
    private RichCalendar calendar;
    private RichPopup popupEvento;
    private RichTable tbHorario;
    

    public bPlanificarEva() {
    }    
    
    public void setSessionPlanificarEva(sessionPlanificar sessionPlanificarEva) {
        this.sessionPlanificarEva = sessionPlanificarEva;
    }
    public sessionPlanificar getSessionPlanificarEva() {
        return sessionPlanificarEva;
    }
     
    public String llenarHorarios(BeanMain beanMain){
        List<BeanMain> lis=new ArrayList<BeanMain>();
        lis=ln_C_SFMainRemote.llenarHorario(beanMain);
        System.out.println("TAMAÑO EN EL BACKING "+lis.size());
        if(lis!=null){
            sessionPlanificarEva.setNidAula(2);
            System.out.println("VALOR DEL AULA EN SESSION"+sessionPlanificarEva.getNidAula());
            sessionPlanificarEva.setListaHorarios(lis);       
                    }
     return null;
    }
    
    public void abrirPopupEvaluar(CalendarActivityEvent calendarActivityEvent) {
        
     
//        System.out.println("antes de igualar"+ calendarActivityEvent.getKeyStroke().getKeyCode());
        System.out.println("antes de igualar"+ calendarActivityEvent.getClickCount());
  //      System.out.println("antes de igualar"+ calendarActivityEvent.getKeyStroke().getKeyEventType());
        
        CalendarActivity activity =
            calendarActivityEvent.getCalendarActivity();

      
        System.out.println("DIA CAPTURADO"+ activity.getId());
    //    System.out.println("DIA CAPTURADO"+ calendarActivityEvent.getKeyStroke());
//          System.out.println("DIA CAPTURADO"+ calendarActivityEvent.getKeyStroke().getKeyChar());
          System.out.println("DIA CAPTURADO"+ calendarActivityEvent.getTriggerType());
      //    System.out.println("DIA CAPTURADO"+ calendarActivityEvent.getKeyStroke().getKeyCode());
          
          
    }

    public void abrirNuevoEvento(CalendarEvent calendarEvent) {
    //        System.out.println("1 antes de igualar "+ calendarEvent.getKeyStroke().getKeyCode());
        System.out.println("2 antes de igualar "+ calendarEvent.getClickCount());
    //      System.out.println("3 antes de igualar "+ calendarEvent.getKeyStroke().getKeyEventType());
        System.out.println("4 antes de igualar "+ calendarEvent.getTriggerDate());
        System.out.println("5 antes de igualar "+ calendarEvent.getTimeType());
        BeanMain beanMain=new BeanMain();
        beanMain.setNidMain(1);
        llenarHorarios(beanMain);
        showPopUp(popupEvento);       
        
    }
    
    public String showPopUp(RichPopup p){
        try{
            RichPopup.PopupHints ph = new RichPopup.PopupHints();        
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN,RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
            //ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,cb5);
            p.show(ph);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setCalendar(RichCalendar calendar) {
        this.calendar = calendar;
    }

    public RichCalendar getCalendar() {
        return calendar;
    }


    public void setPopupEvento(RichPopup popupEvento) {
        this.popupEvento = popupEvento;
    }

    public RichPopup getPopupEvento() {
        return popupEvento;
    }

    public void setTbHorario(RichTable tbHorario) {
        this.tbHorario = tbHorario;
    }

    public RichTable getTbHorario() {
        return tbHorario;
    }

}
