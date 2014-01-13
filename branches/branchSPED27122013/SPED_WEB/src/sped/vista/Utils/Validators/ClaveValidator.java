package sped.vista.Utils.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ClaveValidator implements Validator {
    public ClaveValidator() {
        super();
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String dato = object.toString();
        if(dato.length()<6){
            FacesMessage fm = new FacesMessage("La clave debe tener 6 digitos como minimo");
            throw new ValidatorException(fm);
        }
    }
}
