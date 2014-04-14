package sped.vista.Utils.Validators;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.Context;
import javax.naming.InitialContext;

import sped.negocio.LNSF.IR.LN_C_SFProblemaRemote;

import sped.vista.Utils.Utils;

public class ProblemaValidator implements Validator {
    @EJB
    private LN_C_SFProblemaRemote ln_C_SFProblemaRemote;
    private final static String LOOKUP_PROBLEMA = "mapLN_C_SFProblema";

    public ProblemaValidator() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFProblemaRemote = (LN_C_SFProblemaRemote) ctx.lookup(LOOKUP_PROBLEMA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String dato = Utils.quitaEspacios(object.toString());
        if (ln_C_SFProblemaRemote.existeProblema(dato)) {
            FacesMessage fm = new FacesMessage("El problema a registrar ya existe");
            throw new ValidatorException(fm);
        }
    }

}
