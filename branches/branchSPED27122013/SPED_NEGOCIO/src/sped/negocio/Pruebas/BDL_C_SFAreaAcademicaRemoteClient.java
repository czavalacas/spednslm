package sped.negocio.Pruebas;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import java.util.Random;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import sped.negocio.BDL.IR.BDL_C_SFAreaAcademicaRemote;
import sped.negocio.entidades.admin.AreaAcademica;
import sped.negocio.entidades.admin.Usuario;

public class BDL_C_SFAreaAcademicaRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFAreaAcademicaRemote bDL_C_SFAreaAcademicaRemote =
                (BDL_C_SFAreaAcademicaRemote) context.lookup("SPED_APP-SPED_NEGOCIO-BDL_C_SFAreaAcademica#sped.negocio.BDL.IR.BDL_C_SFAreaAcademicaRemote");
          /*
            String [] abecedario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
                                     "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"  };
            String [] numeros= {"0","1","2","3","4","5","6","7","8","9"};         
          
                
                String [] cadena={"","","","","","","","","","","","","","","","","","","",""};            
                
                for (int i=0; i<=7; i++){
                        cadena[i]=numeros[(int)(Math.random()*9+1)];                
                }            
                for (int i=8; i<=19; i++){
                    cadena[i]=abecedario[(int)(Math.random()*25+1)];   
                }              
                System.out.println(cadena[0]+cadena[1]+cadena[2]+cadena[3]+"-"+
                                   cadena[8]+cadena[9]+cadena[10]+cadena[11]+"-"+
                                   cadena[12]+cadena[13]+cadena[14]+cadena[15]+"-"+
                                   cadena[16]+cadena[17]+cadena[18]+cadena[19]+"-"+
                                   cadena[4]+cadena[5]+cadena[6]+cadena[7]);
            */
     /*       int [] num1 ={1,2,3,4};
            int [] num2={1,3,6,9,8};
            
            for(int i=0; i<num1.length; i++){
                for(int j=0; j<num2.length;j++){                    
                    System.out.println(num1[i]+" - "+num2[j]);
                    if(num1[i]==num2[j]){
                   System.out.println("IGUALDAD");
                    }
                }
               
            }*/
        /** NN-XXXX-XXXX-XXXX-NN*/
            
            Date hoy=new Date();
            String hora="10:20";
            String ho=hora.charAt(0)+""+hora.charAt(1);
            String mi=hora.charAt(3)+""+hora.charAt(4);
            hoy.setHours(Integer.parseInt(ho));
            hoy.setMinutes(Integer.parseInt(mi));
            System.out.println(hoy);
            
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

  
    
    private int getAleatorionNumeros( int num_01, int num_02 ){ 
         if( num_02 < num_01 ){
             return (int)( ( num_02 - num_01 + 1 ) * Math.random() + num_01 );
         }
         else{
             return (int)( ( num_01 - num_02 + 1 ) * Math.random() + num_02 );
         }
     }
    private String getAleatorionLetras(){             
               String [] abecedario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
                                        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"  };

               int    numLetraRandon = this.getAleatorionNumeros( 1, 26 );
               String letra          = abecedario[ numLetraRandon ];
              
               return letra;
        }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
