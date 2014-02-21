package sped.negocio.Pruebas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sped.negocio.Utils.Utiles;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
       // int a = 160;
        //int b = 25;
        //double c = (double) a / b;
       // double c = 10.50;
        //int r = (int) Math.round(c * 100);
        //double f = r / 100.0;
        List<Double> lst = new ArrayList<Double>();
        lst.add(1.0);
        double max = Collections.max(lst);
        Utiles.sysout("max:"+max);
       /* String cadenaPhoneData = "occam|Android|4.4.2|Android|Nexus 4|true|CarrierDataConnection|384|592|384|567|320|4.423300071425715";
        Utiles.sysout("cadenaPhoneData:"+cadenaPhoneData);
        String array1[] = cadenaPhoneData.split("\\|");
        for(String s : array1){
            Utiles.sysout("array1[]:"+s);
        }*/
    }
}
