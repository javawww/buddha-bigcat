package com.buddha.component.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.buddha.component.base.JRRuntimeHelper;
import com.buddha.component.base.pubdef.RouteInfor;

public  class RouteInforMgr {
    private static List<Map<String,Object>> _dc10PartitionCfgs;
    private static List<Map<String,Object>> _dc05StorageCfgs;
    private static List<Map<String,Object>> _dc06StorageCfgDtls;
    private static List<Map<String,Object>> _dc04DbNodeCfgs;
    private RouteInforMgr()
    {
        _dc10PartitionCfgs = new ArrayList<Map<String,Object>>();
        _dc05StorageCfgs = new ArrayList<Map<String,Object>>();
        _dc06StorageCfgDtls = new ArrayList<Map<String,Object>>();
        _dc04DbNodeCfgs = new ArrayList<Map<String,Object>>();
        _loadRouteInfors();
    }
    public static   DataSource createMasterNodeDataSource()
    {
        return JRRuntimeHelper.getCurrentAppContext().getBean(DataSource.class);
    }
    private void _loadRouteInfors()
    {
        try {
            JRRuntimeHelper.logger.info("Loading route data.....");
            DataSource theDataSource= createMasterNodeDataSource();
            JdbcTemplate theJdbcTemplate = new JdbcTemplate(theDataSource);
            _dc10PartitionCfgs = theJdbcTemplate.queryForList("SELECT * FROM DC10_PARTITION_CFG WHERE DC10_CFG_STATE='ACT'");
            _dc05StorageCfgs = theJdbcTemplate.queryForList("SELECT * FROM DC05_STORAGE_CFG_MSTR WHERE DC05_CFG_STATE='ACT'");
            _dc06StorageCfgDtls = theJdbcTemplate.queryForList("SELECT * FROM DC06_STORAGE_CFG_DTL WHERE DC06_STATE='ACT'");
            _dc04DbNodeCfgs = theJdbcTemplate.queryForList("SELECT * FROM DC04_DB_NODE_CFG WHERE DC04_STATE='ACT'");
            JRRuntimeHelper.logger.info("Loading route data...OK!");
            theDataSource.close();
        }
        catch (Exception ex)
        {
            JRRuntimeHelper.logger.error("Loading route data...failed!");
            JRRuntimeHelper.logger.error(ex.getMessage());
        }

    }
    public  List<String> getDbNodeIds(RouteInfor RouteData)
    {
        Map<String,String> theDbNodeIds = new HashMap<String, String>();
        for (String theRouteId : RouteData.getRouteIds())
        {
            String theDbNodeId = getDbNodeIdByRouteIdAndBusiId(theRouteId,RouteData.getBusiId());
            if(theDbNodeId!=null && theDbNodeId.isEmpty()==false)
            {
                theDbNodeIds.put(theDbNodeId,theDbNodeId);
            }
        }
        List<String> theDbNodeIdList = new ArrayList<String>();
        for (String theKey : theDbNodeIds.keySet())
        {
            theDbNodeIdList.add(theKey);
        }
        return  theDbNodeIdList;
    }
    public  List<String> getAllDbNodeIds()
    {
        List<String> theDbNodeIdList = new ArrayList<String>();
        for(Map<String,Object> theRow : _dc04DbNodeCfgs)
        {
            theDbNodeIdList.add(theRow.get("DC04_DB_NODE_ID").toString());
        }
        return  theDbNodeIdList;
    }
    private String getDbNodeIdByRouteIdAndBusiId(String routeId,String BusiId)
    {
        String theStorageCfgId = getStorageCfgIdByRouteId(routeId);
        return getDbNodeIdByStorageIdAndBusiId(theStorageCfgId,BusiId);
    }
    private String getDbNodeIdByStorageIdAndBusiId(String StorageCfgId,String BusiId)
    {
        if(BusiId==null || BusiId.isEmpty())
        {
            BusiId="B0";
        }
        for(Map<String,Object> theRow : _dc06StorageCfgDtls)
        {
            if(theRow.get("DC06_CFG_ID").toString().equals(StorageCfgId)
                    && theRow.get("DC06_BUSI_DB_ID").toString().equals(BusiId))
            {
                return theRow.get("DC06_DB_NODE_ID").toString();
            }
        }
        return null;
    }
    private String getStorageCfgIdByRouteId(String routeId)
    {
        int theRouteId = Integer.parseInt(routeId);
        for(Map<String,Object> theRow : _dc10PartitionCfgs)
        {
            int theS = Integer.parseInt(theRow.get("DC10_PARTITION_ID1").toString());
            int theE = Integer.parseInt(theRow.get("DC10_PARTITION_ID2").toString());
            if((theRouteId>= theS) && (theRouteId<=theE))
            {
                return theRow.get("DC10_STORAGE_CFG_ID").toString();
            }
        }
        return null;
    }
    public Map<String,Object> getDbNodeCfgMap(String DbNodeId)
    {
        for (Map<String,Object> theRow : _dc04DbNodeCfgs)
        {
            if(theRow.get("DC04_DB_NODE_ID").toString().equals(DbNodeId))
            {
                return  theRow;
            }
        }
        return null;
    }
    private static volatile RouteInforMgr _Instantce = null;
    public static RouteInforMgr getInstantce()
    {
        if(_Instantce==null)
        {
            synchronized (RouteInforMgr.class){
                if(_Instantce == null){
                    _Instantce = new RouteInforMgr();
                }
            }
        }
        return _Instantce;
    }
}
