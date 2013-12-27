package sped.vista.Converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/** Converter que transforma el input del usuario a minusculas
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class LowerConverter implements Converter {
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (!(modelValue instanceof String)) { 
            return null; // Or throw ConverterException.
        }
        return modelValue.toString().toLowerCase();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null) { 
            return null;
        }
        return submittedValue.toLowerCase();
    }
}