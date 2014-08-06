package sped.negocio.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.faces.model.SelectItem;

import sped.negocio.entidades.beans.BeanCombo;
import sped.negocio.entidades.beans.BeanComboString;

public class Utiles {

    public static void sysout(Object o) {
        System.out.println(o);
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public static ArrayList llenarCombo(List<BeanComboString> lista) {
        ArrayList unItems = new ArrayList();
        for (BeanComboString c : lista) {
            unItems.add(new SelectItem(c.getId().toString(), c.getDescripcion().toString()));
        }
        return unItems;
    }
    
    public static byte[] extractBytes(String ImageName) throws IOException {
        File archivo = new File(ImageName);
        byte[] aBytes = null;
        long tamanoA = archivo.length(); 
        aBytes = new byte[(int) tamanoA];
        try{
            FileInputStream docu = new FileInputStream(archivo);
            int numBytes = docu.read(aBytes);
            docu.close();
            archivo.delete();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return aBytes;
    }
    
    public static byte[] Imagen(String rutaImg){
        try{
            if(rutaImg != null){
                if(!rutaImg.equals("")){
                    byte[] byt = extractBytes(rutaImg);
                    if(byt != null){
                        return byt;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    public static String getRandomClave(){
       StringBuilder sb = new StringBuilder(8);
       for( int i = 0; i < 8; i++ ){
          sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
       }
       return sb.toString();
    }
    
    public static String getHoyFormato(String _formato){
        SimpleDateFormat formato = new SimpleDateFormat(_formato);
        Calendar cal = new GregorianCalendar();
        return formato.format(cal.getTime());
    }
    
    public static String getStack(Exception e){
        try {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.toString();
        } catch (Exception e1) {
            return null;
        }
    }
}