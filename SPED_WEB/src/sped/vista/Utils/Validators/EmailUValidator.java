package sped.vista.Utils.Validators;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.Context;
import javax.naming.InitialContext;

import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;

public class EmailUValidator implements Validator {
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private final static String LOOKUP_UTILS = "SPED_APP-SPED_NEGOCIO-LN_C_SFUtils#sped.negocio.LNSF.IR.LN_C_SFUtilsRemote";
    
    public EmailUValidator() {
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote) ctx.lookup(LOOKUP_UTILS);
        }catch(Exception e){
            e.printStackTrace();
        }        
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String dato = object.toString();
        if (dato.length()>0 && !dato.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            throw new ValidatorException(new FacesMessage("El e-mail tiene formato Incorrecto."));
        }else{
            int cont = ln_C_SFUtilsRemote.findCountByProperty(dato, true, false);
            if(cont > 0){
                throw new ValidatorException(new FacesMessage("El e-mail ya esta registrado"));                     
            }                   
        }
    }
}
