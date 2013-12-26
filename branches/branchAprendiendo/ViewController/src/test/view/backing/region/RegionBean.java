package test.view.backing.region;

import java.io.Serializable;

import oracle.adf.controller.TaskFlowId;

import test.view.backing.Utils;

public class RegionBean implements Serializable {
    private String taskFlowId = "/WEB-INF/TestFrag.xml#TestFrag";

    public RegionBean() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }
    
    public String getMainCall(){
        taskFlowId = (String)Utils.getSession("url");
        if(taskFlowId == null){
            taskFlowId = "/WEB-INF/TestFrag.xml#TestFrag";
        }
        return null;
    }
}
