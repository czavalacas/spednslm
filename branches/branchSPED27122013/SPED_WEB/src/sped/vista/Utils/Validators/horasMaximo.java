package sped.vista.Utils.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import sped.vista.Utils.Utils;

public class horasMaximo implements Validator {
    public horasMaximo() {
        super();
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String dato = object.toString();
        String msj = "";
        if (Utils.isNumeric(dato) == false) {
            msj = "Ingrese solo numeros";
        }else{
            int horas = Integer.parseInt(dato);
            int maxHoras = Integer.parseInt(Utils.getSession("maxHoras").toString());//horas maximo a la semana
            int horasTotal = Integer.parseInt(Utils.getSession("Horas").toString());//horas maximo por salon
            if(horas == 0){
                msj = "Ingrese un valor mayor que 0";
            }
            if(horas > maxHoras){
                msj = "Maximo de horas permistidos a la semana :"+maxHoras;
            }
            if(horas > horasTotal){
                msj = "Quedan "+horasTotal+" horas sin asiganar";
            }
        }
        if(msj != ""){
            FacesMessage fm = new FacesMessage(msj);
            throw new ValidatorException(fm);
        } 
    }
}
