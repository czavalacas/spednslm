package sped.vista.Utils.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String dato = object.toString();
        if (dato.length()>0 && !dato.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            throw new ValidatorException(new FacesMessage("El e-mail tiene formato Incorrecto."));
        }
    }
}