package mobile.respaldo.parteocu;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class bDetalle_PO {
    public bDetalle_PO() {
    }

    public void resetearAlSalir(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._comentario}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._descProblema}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._profesor}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._curso}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._fechaRegistro}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._areaAcademica}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._aula}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._sede}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope._nombresUsuario}", null);
    }
}
