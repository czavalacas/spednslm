package sped.vista.Utils.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import sped.vista.Utils.Utils;

public class restricionHorasDuracion implements Validator {
    
    public restricionHorasDuracion() {
        super();
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        Object nroDuracion = object;
        Object nroCantidad = Utils.getSession("nroCantidad");
        if(nroCantidad != null && nroDuracion != null){
            int horasLibres = Utils.transforString(Utils.getSession("horasLibres").toString());
            int nroDuracion_aux = Utils.transforString(nroDuracion.toString());
            int nroCantidad_aux = Utils.transforString(nroCantidad.toString());
            if( (horasLibres - (nroCantidad_aux * nroDuracion_aux)) < 0 ){
                int resto = horasLibres / nroDuracion_aux;
                String msj = (horasLibres - (nroCantidad_aux * nroDuracion_aux)) !=  0 ? " .Se sugiere: Duraci\u00F3n = " + nroDuracion_aux +" y Nro Horas : "+ resto : "";
                FacesMessage fm = new FacesMessage("Horas Disponibles : "+horasLibres+ " . El valor ingresado sobrepasa."+msj);
                throw new ValidatorException(fm);
            }
        }
    }
}
