package sped.vista.beans.mantenimiento;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.OutputStream;

import java.util.GregorianCalendar;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.imageio.ImageIO;

import javax.servlet.ServletContext;

import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import org.apache.myfaces.trinidad.model.UploadedFile;

import sped.vista.Utils.Utils;

public class bConfiguracion {
    private bSessionConfiguracion sessionConfiguracion;
    private RichInputFile fileImg;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichPanelFormLayout pimag;

    public bConfiguracion() {
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

    public String cambiarImagen() {
        // Add event code here...
        return null;
    }
}
