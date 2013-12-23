package test.view.backing;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.util.DateCustomizer;

public class NeelmaniCalender extends DateCustomizer {

    public NeelmaniCalender(){
        super();
    }

    
    public String format(Date date, String key, Locale locale, TimeZone timeZone) {
        HashMap holidays = new HashMap();
        holidays.put(new Date("20-Dec-2013"),"Mi Graduacion");
        holidays.put(new Date("25-Dec-2013"),"Navidad");
        holidays.put(new Date("26-Dec-2013"),"Reunion de trabajo SPED en casa de Diego");
        if("af|calendar::month-grid-cell-header-misc".equals(key)){
            return holidays.get(date) != null ? holidays.get(date).toString() : null;
        }
        return null;
    }
}
