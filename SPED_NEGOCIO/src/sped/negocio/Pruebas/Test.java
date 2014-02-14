package sped.negocio.Pruebas;

import sped.negocio.Utils.Utiles;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        int a = 160;
        int b = 25;
        double c = (double) a / b;
        Utiles.sysout("c:"+c);
       /* String cadenaPhoneData = "occam|Android|4.4.2|Android|Nexus 4|true|CarrierDataConnection|384|592|384|567|320|4.423300071425715";
        Utiles.sysout("cadenaPhoneData:"+cadenaPhoneData);
        String array1[] = cadenaPhoneData.split("\\|");
        for(String s : array1){
            Utiles.sysout("array1[]:"+s);
        }*/
    }
}
