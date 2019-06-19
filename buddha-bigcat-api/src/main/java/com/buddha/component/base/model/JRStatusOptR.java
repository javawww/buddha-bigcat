package com.buddha.component.base.model;

import java.io.Serializable;

/**
 * Created by tsq on 2019-02-19.
 */
public class JRStatusOptR implements Serializable {
    private String optCode;
    private String successSts;
    private String failureSts;
    public JRStatusOptR()
    {

    }
    public JRStatusOptR(String optCode,String successSts,String failureSts,String defaultStsCode)
    {
        this.failureSts = failureSts;
        this.successSts = successSts;
        this.optCode = optCode;
        if(this.failureSts==null || this.failureSts.isEmpty())
        {
            this.failureSts = defaultStsCode;
        }
        if(this.successSts==null || this.successSts.isEmpty())
        {
            this.successSts = defaultStsCode;
        }
    }
    public JRStatusOptR clone()
    {
        return  new JRStatusOptR(this.optCode,this.successSts,this.failureSts,"");
    }
    public String getFailureSts() {
        return failureSts;
    }

    public void setFailureSts(String failureSts) {
        this.failureSts = failureSts;
    }

    public String getSuccessSts() {
        return successSts;
    }

    public void setSuccessSts(String successSts) {
        this.successSts = successSts;
    }



    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }
}
