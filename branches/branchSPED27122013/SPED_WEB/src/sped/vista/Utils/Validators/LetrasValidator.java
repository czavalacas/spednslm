package sped.vista.Utils.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetrasValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String entrada = object.toString();
        Pattern patron = Pattern.compile("[^A-Za-z ]");
        Matcher encaja = patron.matcher(entrada);
        if(encaja.find()){
            FacesMessage fm = new FacesMessage("Solo letras");
            throw new ValidatorException(fm);
        }
    }
}
