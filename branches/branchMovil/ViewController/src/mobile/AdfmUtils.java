package mobile;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.exception.AdfException;

public class AdfmUtils {

    public static void alert(String feature, String metodoJS, Object args[]) {
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(feature, metodoJS, args);
    }

    public static void log(Object o) {
        System.out.println("Diego " + o);
    }
    
    public static void logStackTrace(Exception e) {
        AdfmUtils.log("ERROR "+e.getMessage());
        for(int i = 0; i < e.getStackTrace().length; i++){
            AdfmUtils.log(e.getStackTrace()[i]);
        }
    }

    public void accionError() {
        throw new AdfException("Error", AdfException.ERROR);
    }

    public void accionWarning() {
        throw new AdfException("Warning", AdfException.WARNING);
    }

    public void accionFatal() {
        throw new AdfException("Fatal", AdfException.FATAL);
    }

    public void accionIngo() {
        throw new AdfException("Info", AdfException.INFO);
    }
}
