package sped.vista.region;

import java.io.Serializable;
import oracle.adf.controller.TaskFlowId;
import sped.vista.Utils.Utils;
import sped.vista.beans.migracion.bSessionMigrarExcel;

/** Clase que maneja al componente Region y redirecciona a las paginas seleccionadas
 * @author dfloresgonz
 * @since 27.12.2013
 */
public class beanRegion implements Serializable {
    private String taskFlowId = "/WEB-INF/main_page.xml#main_page";
    private final static String DEFAULT = "/WEB-INF/main_page.xml#main_page";

    public beanRegion() {
    }

    public String getMainCall(){
        removerSesiones();
        taskFlowId = (String)Utils.getSession("url");
        if(taskFlowId == null){
            taskFlowId = DEFAULT;
        }
        return null;
    }
    
    public void removerSesiones(){
        Utils.removeSession("sessionRegistrarFicha");
        Utils.removeSession("sessionPlanificarEva");
        Utils.removeSession("sessionDesempenoProfesor");
        Utils.removeSession("sessionNoti");
        Utils.removeSession("sessionParteOcurrencia");
        Utils.removeSession("sessionEvaluar");
        Utils.removeSession("sessionConsultarEvaluacion");
        Utils.removeSession("sessionDesempenoEvaluador");
        Utils.removeSession("sessionGestionarUsuarios");
        Utils.removeSession("sessionGestionarPermisos");
        Utils.removeSession("sessionConsultarPlanificacion");
        Utils.removeSession("sessionConfiguracionHorario");
        Utils.removeSession("sessionGestionarHorario");
        Utils.removeSession("sessionbGestionarHorarios");
        Utils.removeSession("sessionConfiguracion");
        Utils.removeSession("sessionMigrarExcel");
    }
    
    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }
}
