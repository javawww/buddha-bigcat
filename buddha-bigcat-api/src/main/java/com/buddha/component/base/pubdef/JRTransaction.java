package com.buddha.component.base.pubdef;

import java.util.HashMap;
import java.util.Map;

import com.buddha.component.common.utils.Identities;

public class JRTransaction implements IJRTransaction {

    private HashMap<String, JRTransConnGroup> _LocalConns;
    private String _TransactionId;
    public static JRTransaction BeginTransaction()
    {
        JRTransaction theTrans = new JRTransaction();
        theTrans._TransactionId = Identities.uuid();
        return theTrans;
    }

    public JRTransConnGroup getTransConnGroup(String DbNodeId)
    {
       return _LocalConns.get(DbNodeId);
    }
    private JRTransaction()
    {
        _LocalConns = new HashMap<String, JRTransConnGroup>();
     }
    @Override
    public void commit() throws Exception
    {
        synchronized (this)
        {
            for(String theTransKey : _LocalConns.keySet())
            {
                JRTransConnGroup theGroup = _LocalConns.get(theTransKey);
                for(JRTransConnection TransConn : theGroup.getTransConns())
                {
                    if (TransConn.getConnection() != null)
                    {
                        TransConn.getConnection().commit();
                        //JdbcUtils.closeConnection(TransConn.getConnection());
                    }
                }
                theGroup.getTransConns().clear();
            }
            _LocalConns.clear();
        }
    }
    @Override
    public void rollback() throws Exception
    {
        synchronized (this)
        {
            for(String theTransKey : _LocalConns.keySet())
            {
                JRTransConnGroup theGroup = _LocalConns.get(theTransKey);
                for(JRTransConnection TransConn : theGroup.getTransConns())
                {
                    if (TransConn.getConnection() != null)
                    {
                        TransConn.getConnection().rollback();
                    }
                }
                theGroup.getTransConns().clear();
            }
            _LocalConns.clear();
        }
    }

    public Map<String, JRTransConnGroup> getLocalConns()
    {
        return this._LocalConns;
    }
    public String getTransactionId()
    {
        return this._TransactionId;
    }

}
