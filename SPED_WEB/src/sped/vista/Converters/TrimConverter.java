package sped.vista.Converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class TrimConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
            if (string == null) { 
                return null;
            }
            return string.replaceAll(" +", " ").trim();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (!(object instanceof String)) { 
            return null;
        }
        return object.toString().replaceAll(" +", " ").trim();
    }
}
