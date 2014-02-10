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
