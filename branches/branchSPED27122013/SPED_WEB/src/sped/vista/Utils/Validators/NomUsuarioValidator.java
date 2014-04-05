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

public class NomUsuarioValidator implements Validator {
    @EJB
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private final static String LOOKUP_USUARIO = "mapLN_C_SFUsuario";
    
    public NomUsuarioValidator(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote) ctx.lookup(LOOKUP_USUARIO); 
        }catch(Exception e){
            e.printStackTrace();        
        } 
    }
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
        String dato = object.toString();
        if(ln_C_SFUsuarioRemote.countUsuarioByNomUsuarioLN(dato)){
            FacesMessage fm = new FacesMessage("El Usuario se encuentra registrado");
            throw new ValidatorException(fm);
        }else if(!isOnlyletter(dato)){
            FacesMessage fm = new FacesMessage("Ingrese solo letras");
            throw new ValidatorException(fm);
        }
    }
    
    public static boolean isOnlyletter(String nombre) {
        for (int i = 0; i < nombre.length(); i++) {
            if (!Character.isLetter(nombre.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
