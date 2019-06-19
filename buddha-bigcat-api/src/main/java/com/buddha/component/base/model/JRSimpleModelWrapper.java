package com.buddha.component.base.model;

import javax.persistence.Transient;
import java.util.Map;

/**
 * Created by tsq on 2019-02-23.
 */
public class JRSimpleModelWrapper {
    @Transient
    private Map<String,Object> modelMap;
    @Transient
    private Map<String,String> refValues;
    @Transient
    private JRStatusData statusData;

    public  Map<String,Object> getModelMap(){
        return this.modelMap;
    }
    public  void setModelMap(Map<String,Object>  modelMap)
    {
        this.modelMap = modelMap;
    }
    public  Map<String,String> getRefValues(){
        return this.refValues;
    }
    public  void setRefValues(Map<String,String>  refValues)
    {
        this.refValues = refValues;
    }

    public JRStatusData getStatusData() {
        return statusData;
    }
    public void setStatusData(JRStatusData statusData) {
        this.statusData = statusData;
    }
}
