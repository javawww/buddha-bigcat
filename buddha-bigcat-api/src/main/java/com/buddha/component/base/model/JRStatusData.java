package com.buddha.component.base.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tsq on 2019-02-19.
 */
public class JRStatusData  implements Serializable {
    private String stsCode;
    private Map<String,JRStatusOptR> optPrvs;
    public JRStatusData()
    {
        optPrvs = new HashMap<String,JRStatusOptR>();
    }
    public String getStsCode() {
        return stsCode;
    }
    public void setStsCode(String stsCode) {
        this.stsCode = stsCode;
    }
    public Map<String, JRStatusOptR> getOptPrvs() {
        return optPrvs;
    }

    public JRStatusData clone()
    {
        JRStatusData theData = new JRStatusData();
        theData.setStsCode(this.stsCode);
        for(String theKey : optPrvs.keySet()) {
            theData.getOptPrvs().put(theKey,optPrvs.get(theKey).clone());
        }
        return theData;
    }
}
