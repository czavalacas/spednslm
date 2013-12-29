package sped.vista.beans.evaluacion.planificar;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.event.CalendarActivityEvent;
import oracle.adf.view.rich.event.CalendarEvent;
import oracle.adf.view.rich.model.CalendarActivity;

public class bPlanificarEva {
    private sessionPlanificar sessionPlanificar;
    private RichCalendar calendar;
    private RichPopup popupEvento;

    public bPlanificarEva() {
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
    public void setSessionPlanificar(sessionPlanificar sessionPlanificar) {
        this.sessionPlanificar = sessionPlanificar;
    }

    public sessionPlanificar getSessionPlanificar() {
        return sessionPlanificar;
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
}
