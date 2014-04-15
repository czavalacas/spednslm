package sped.negocio.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Calendar;
import java.util.Date;

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
    
    
}