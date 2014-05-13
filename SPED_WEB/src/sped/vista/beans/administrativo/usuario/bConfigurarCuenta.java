package sped.vista.beans.administrativo.usuario;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.OutputStream;

import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.imageio.ImageIO;

import javax.servlet.ServletContext;

import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.component.rich.output.RichImage;

import org.apache.myfaces.trinidad.model.UploadedFile;

import sped.negocio.LNSF.IL.LN_C_SFUsuarioLocal;
import sped.negocio.LNSF.IR.LN_C_SFUtilsRemote;
import sped.negocio.LNSF.IR.LN_T_SFUsuarioRemote;
import sped.negocio.entidades.beans.BeanUsuario;

import sped.vista.Utils.Utils;

public class bConfigurarCuenta {
    private bSessionConfigurarCuenta sessionConfigurarCuenta;
    private BeanUsuario beanUsuario = (BeanUsuario) Utils.getSession("USER");
    FacesContext ctx = FacesContext.getCurrentInstance();
    @EJB
    private LN_T_SFUsuarioRemote ln_T_SFUsuarioRemote;
    @EJB
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    @EJB
    private LN_C_SFUsuarioLocal ln_C_SFUsuarioLocal;
    private String correo;
    private String correoNew;
    private String claveActual;
    private String claveNew;
    private String claveConf;
    private RichPanelFormLayout pclave;
    private RichImage image;
    private RichInputFile fileImg;
    private RichPanelFormLayout pimag;
    private RichPanelFormLayout pcoreo;

    public bConfigurarCuenta() {
        correo = beanUsuario.getCorreo();
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionConfigurarCuenta.getExec() == 0){
            sessionConfigurarCuenta.setImgSource("/imageservlet?nomusuario="+beanUsuario.getNidUsuario());
            sessionConfigurarCuenta.setExec(1);            
        }
    }
    
    public String cambiarClave() {
        if(ln_C_SFUsuarioLocal.testClave_LN(beanUsuario.getNidUsuario(), claveActual)){//dfloresgonz 13.05.2014 
        //if(claveActual.compareTo(beanUsuario.getClave()) == 0){
            if(claveNew.compareTo(claveConf) == 0){
                if(claveActual.compareTo(claveNew) != 0){
                    ln_T_SFUsuarioRemote.configuracionCuentaUsuario(beanUsuario.getNidUsuario(), 
                                                                    claveNew, 
                                                                    null, 
                                                                    null);
                    beanUsuario.setClave(claveNew);
                    Utils.putSession("USER",beanUsuario);
                    claveActual = "";
                    claveNew = "";
                    claveConf = "";
                    Utils.addTarget(pclave);
                    Utils.mostrarMensaje(ctx,"Se modifico su clave correctamente",null,3);
                }else{
                    Utils.mostrarMensaje(ctx,"La clave actual y la clave nueva son iguales",null,2);
                }
            }else{
                Utils.mostrarMensaje(ctx,"Las claves nuevas no coinciden",null,2);
            }
        }else{
            Utils.mostrarMensaje(ctx,"La clave actual es incorrecta",null,2);
        }        
        return null;
    }
    
    public void uploadFileValueChangeEvent(ValueChangeEvent valueChangeEvent) {
        try{
            UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();            
            long fileSize = file.getLength() / (1024 * 1024);//megabytes
            if(file.getLength() > 1048576){
                Utils.mostrarMensaje(ctx, "El archivo no puede ser de mas de 1 MB.", "Error", 4);
                return;
            }
            String extension = file.getFilename().substring(file.getFilename().lastIndexOf(".") + 1, file.getFilename().length());
            if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png")){
                String ext = file.getFilename().substring(file.getFilename().lastIndexOf(".") + 1, file.getFilename().length());
                String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";
                String rutaLocal = "";
                if(File.separator.equals("/")){
                    rutaLocal = File.separator+"recursos" + File.separator + "img" + File.separator + "usuarios" + File.separator +  timePath + "." + ext;     
                }else{
                    rutaLocal = "recursos" + File.separator + "img" + File.separator + "usuarios" + File.separator + timePath + "." + ext;   
                }
                ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
                String imageDirPath = servletCtx.getRealPath("/");
                InputStream inputStream = file.getInputStream();
                String rutaImg = imageDirPath + rutaLocal;
                sessionConfigurarCuenta.setRutaImg(rutaImg);
                TransferFile(rutaImg, rutaLocal, inputStream);
            }else{
                Utils.mostrarMensaje(ctx, "El archivo no es de tipo imagen suba un jpg/png", "Error", 1);
            }            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void TransferFile(String ruta, String rutalocal, InputStream in) throws Exception {
        OutputStream out = new FileOutputStream(new File(ruta));
        resize(in, out, 175, 150);  
        sessionConfigurarCuenta.setImgSource(rutalocal);
        fileImg.resetValue();
        Utils.addTarget(pimag);           
    }
    
    public void resize(InputStream input, OutputStream output, int width, int height) throws Exception {
            BufferedImage src = ImageIO.read(input);
            BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = dest.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance((double) width / src.getWidth(), (double) height / src.getHeight());
            g.drawRenderedImage(src, at);
            ImageIO.write(dest, "JPG", output);
            output.close();
    }
    
    public String cambiarImagen() {        
        ln_T_SFUsuarioRemote.configuracionCuentaUsuario(beanUsuario.getNidUsuario(), 
                                                        null, 
                                                        null, 
                                                        sessionConfigurarCuenta.getRutaImg());
        sessionConfigurarCuenta.setRutaImg(null);
        Utils.mostrarMensaje(ctx,"Se modifico su imagen correctamente",null,3);
        sessionConfigurarCuenta.setImgSource("/imageservlet?nomusuario="+beanUsuario.getNidUsuario());
        Utils.addTarget(pimag);
        return null;
    }
    
    public String cambiarCorreo() {
        if(correo.compareTo(correoNew) != 0){
            if(ln_C_SFUtilsRemote.findCountByProperty(correo, true, true)==0){
                ln_T_SFUsuarioRemote.configuracionCuentaUsuario(beanUsuario.getNidUsuario(), 
                                                                null, 
                                                                correoNew, 
                                                                null);
                beanUsuario.setCorreo(correoNew);
                Utils.putSession("USER",beanUsuario);
                correo = correoNew;
                correoNew = "";
                Utils.mostrarMensaje(ctx,"Se modifco correctamente su correo",null,3);                
                Utils.addTarget(pcoreo);
            }else{
                Utils.mostrarMensaje(ctx,"El correo ingresado ya esta registrado",null,2);
            }
        }else{
            Utils.mostrarMensaje(ctx,"La correo actual y el correo nuevo son iguales",null,2);
        }
        return null;
    }

    public void setSessionConfigurarCuenta(bSessionConfigurarCuenta sessionConfigurarCuenta) {
        this.sessionConfigurarCuenta = sessionConfigurarCuenta;
    }

    public bSessionConfigurarCuenta getSessionConfigurarCuenta() {
        return sessionConfigurarCuenta;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setBeanUsuario(BeanUsuario beanUsuario) {
        this.beanUsuario = beanUsuario;
    }

    public BeanUsuario getBeanUsuario() {
        return beanUsuario;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveNew(String claveNew) {
        this.claveNew = claveNew;
    }

    public String getClaveNew() {
        return claveNew;
    }

    public void setClaveConf(String claveConf) {
        this.claveConf = claveConf;
    }

    public String getClaveConf() {
        return claveConf;
    }

    public void setPclave(RichPanelFormLayout pclave) {
        this.pclave = pclave;
    }

    public RichPanelFormLayout getPclave() {
        return pclave;
    }

    public void setImage(RichImage image) {
        this.image = image;
    }

    public RichImage getImage() {
        return image;
    }

    public void setFileImg(RichInputFile fileImg) {
        this.fileImg = fileImg;
    }

    public RichInputFile getFileImg() {
        return fileImg;
    }

    public void setPimag(RichPanelFormLayout pimag) {
        this.pimag = pimag;
    }

    public RichPanelFormLayout getPimag() {
        return pimag;
    }

    public void setCorreoNew(String correoNew) {
        this.correoNew = correoNew;
    }

    public String getCorreoNew() {
        return correoNew;
    }

    public void setPcoreo(RichPanelFormLayout pcoreo) {
        this.pcoreo = pcoreo;
    }

    public RichPanelFormLayout getPcoreo() {
        return pcoreo;
    }
}
