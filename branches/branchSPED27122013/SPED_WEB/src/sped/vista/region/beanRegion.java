package sped.vista.region;

import java.io.Serializable;

import oracle.adf.controller.TaskFlowId;

import sped.vista.Utils.Utils;

public class beanRegion implements Serializable {
    private String taskFlowId = "/WEB-INF/main_page.xml#main_page";
    private final static String DEFAULT = "/WEB-INF/main_page.xml#main_page";

    public beanRegion() {
    }

    public String getMainCall(){
        taskFlowId = (String)Utils.getSession("url");
        if(taskFlowId == null){
            taskFlowId = DEFAULT;
        }
        return null;
    }
    
    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }
}
