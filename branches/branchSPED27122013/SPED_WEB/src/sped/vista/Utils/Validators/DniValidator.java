package sped.vista.Utils.Validators;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.Context;
import javax.naming.InitialContext;

import sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote;

import sped.negocio.LNSF.IR.LN_C_SFUsuarioRemote;

import sped.vista.Utils.Utils;

public class DniValidator implements Validator {
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private final static String LOOKUP_USUARIO = "mapLN_C_SFUsuario";

    public DniValidator() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote) ctx.lookup(LOOKUP_USUARIO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String dato = object.toString();
        String msj = "";
        if (Utils.isNumeric(dato) == false) {
            msj = "Ingrese solo numeros";
        } else {
            if (dato.length() != 8) {
                msj = "Dni debe tener 8 digitos";
            } else {
                if (ln_C_SFUsuarioRemote.countUsuarioByDniLN(dato)) {
                    msj = "El Dni se encuentra registrado";
                }
            }
        }
        if(msj != ""){
            FacesMessage fm = new FacesMessage(msj);
            throw new ValidatorException(fm);
        }        
    }
}
