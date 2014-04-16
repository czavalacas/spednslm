package sped.vista.beans.mantenimiento;

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
import javax.faces.event.ValueChangeEvent;

import javax.imageio.ImageIO;

import javax.servlet.ServletContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;

import sped.negocio.LNSF.IR.LN_C_SFCorreoRemote;
import sped.negocio.LNSF.IR.LN_T_SFCorreoRemote;
import sped.negocio.LNSF.IR.LN_T_SFImagenRemote;

import sped.negocio.entidades.beans.BeanMail;
import sped.negocio.entidades.beans.BeanMain;

import sped.vista.Utils.Utils;

public class bConfiguracion {
    @EJB
    private LN_T_SFImagenRemote ln_T_SFImagenRemote;
    @EJB
    private LN_C_SFCorreoRemote ln_C_SFCorreoRemote;
    @EJB
    private LN_T_SFCorreoRemote ln_T_SFCorreoRemote;
    private bSessionConfiguracion sessionConfiguracion;
    private RichInputFile fileImg;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichPanelFormLayout pimag;
    private RichPopup popClave;
    private String clave;

    public bConfiguracion() {
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if(sessionConfiguracion.getExec() == 0){
            sessionConfiguracion.setImgSource("/imageservlet?nomusuario=lol");//pongo un cadena para que el servelet coja la imagen de la tabla por default
            sessionConfiguracion.setExec(1);
            llenarCamposCorreo();
        }
    }
    
    public void llenarCamposCorreo(){
        BeanMail mail = ln_C_SFCorreoRemote.getMail();
        sessionConfiguracion.setCorreo(mail.getCorreo());
        sessionConfiguracion.setPuerto(mail.getPuerto());
        sessionConfiguracion.setHost(mail.getHost());
        
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
                sessionConfiguracion.setRutaImg(rutaImg);
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
        sessionConfiguracion.setImgSource(rutalocal);
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
        ln_T_SFImagenRemote.guardarImagen(sessionConfiguracion.getRutaImg());
        sessionConfiguracion.setImgSource("/imageservlet?nomusuario=lol");
        sessionConfiguracion.setRutaImg(null);
        Utils.mostrarMensaje(ctx,"Se modifico la imagen por default correctamente",null,3);
        Utils.addTarget(pimag);
        return null;
    }
    
    public String abrirPopUpConfirmacion() {
        Utils.showPopUpMIDDLE(popClave);
        return null;
    }
    
    public void dialogClaveListener(DialogEvent dialogEvent) {
        DialogEvent.Outcome outcome = dialogEvent.getOutcome();
        if(outcome == DialogEvent.Outcome.ok){  
            ln_T_SFCorreoRemote.guardarParametros(sessionConfiguracion.getCorreo(), 
                                                  clave, 
                                                  sessionConfiguracion.getHost(), 
                                                  sessionConfiguracion.getPuerto());
            if(ln_C_SFCorreoRemote.correoPrueba()){
                Utils.mostrarMensaje(ctx,"Se modifico los parametros del correo. Se le enviara un correo de Prueba",null,3);
            }else{
                Utils.mostrarMensaje(ctx,"Ocurrio un Error. Los datos ingresados son incorrectos. " +
                                         "Vuelva a configurar los parametros del correo",null,1);
            } 
        }
    }

    public void setSessionConfiguracion(bSessionConfiguracion sessionConfiguracion) {
        this.sessionConfiguracion = sessionConfiguracion;
    }

    public bSessionConfiguracion getSessionConfiguracion() {
        return sessionConfiguracion;
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

    public void setPopClave(RichPopup popClave) {
        this.popClave = popClave;
    }

    public RichPopup getPopClave() {
        return popClave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }    
}
