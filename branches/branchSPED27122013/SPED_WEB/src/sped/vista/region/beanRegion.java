package sped.vista.region;

import java.io.Serializable;
import oracle.adf.controller.TaskFlowId;
import sped.vista.Utils.Utils;

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
    }
    
    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }
}
