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

public class DniValidator implements Validator {
    @EJB
    private BDL_C_SFUsuarioRemote bdL_C_SFUsuarioRemote;
    private final static String LOOKUP_USUARIO = "mapBDL_C_SFUsuario#sped.negocio.BDL.IR.BDL_C_SFUsuarioRemote";
    
    public DniValidator(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            bdL_C_SFUsuarioRemote = (BDL_C_SFUsuarioRemote) ctx.lookup(LOOKUP_USUARIO);
        }catch(Exception e){
            e.printStackTrace();        
        }        
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String dato = object.toString();
        int num = 0;
        if(num != bdL_C_SFUsuarioRemote.countUsuarioByDniBDL(dato)){
            FacesMessage fm = new FacesMessage("El dni se encuentra registrado");
            throw new ValidatorException(fm);
        }
    }
}
