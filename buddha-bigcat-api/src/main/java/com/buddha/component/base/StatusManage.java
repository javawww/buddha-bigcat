package com.buddha.component.base;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddha.component.base.model.JRStatusData;
import com.buddha.component.base.model.JRStatusOptR;
import com.buddha.component.base.model.Ss29StatusOptRelation;
import com.buddha.component.base.pubdef.RouteInfor;
import com.buddha.component.base.service.BaseStsOptRelationService;

/**
 * Created by tsq on 2019-02-19.
 */
@Service
public class StatusManage {
    @Autowired
    private BaseStsOptRelationService baseStsOptRelationService;
    private static final HashMap<String,HashMap<String,JRStatusData>> stsOptRelations = new HashMap<String,HashMap<String,JRStatusData>>();
    public static HashMap<String,HashMap<String,JRStatusData>> getStsOptRelations()
    {
        return stsOptRelations;
    }

    private  void _loadStsOptRelations(RouteInfor RouteData, String StsObjCode)
    {
        try
        {
            HashMap<String,JRStatusData> theStsOptRModels = new HashMap<String,JRStatusData>();
            String theSQL ="SELECT * FROM SS29_STATUS_OPT_RELATION WHERE SS29_OBJ_ID='"+StsObjCode+"'";
            List<Ss29StatusOptRelation> theRelations = baseStsOptRelationService.queryModelsBySQL(RouteData,theSQL,null);
            if(theRelations!=null)
            {
                for(Ss29StatusOptRelation theMode:theRelations)
                {
                    String theStsCode =theMode.getSs29StatusId().toUpperCase();
                    JRStatusOptR theStsOptRModel = new JRStatusOptR(theMode.getSs29OptId(),
                            theMode.getSs29NextStatus1(),theMode.getSs29NextStatus2(),theStsCode);
                    JRStatusData theStatusData = theStsOptRModels.get(theStsCode);
                    if(theStatusData==null)
                    {
                        theStatusData = new JRStatusData();
                        theStatusData.setStsCode(theMode.getSs29StatusId().toUpperCase());
                        theStsOptRModels.put(theStsCode,theStatusData);
                    }
                    theStatusData.getOptPrvs().put(theStsOptRModel.getOptCode(),theStsOptRModel);
                }
            }
            stsOptRelations.put(StsObjCode.toUpperCase(),theStsOptRModels);
        }
        catch(Exception e)
        {
            JRRuntimeHelper.logger.debug(e.getMessage());
        }
    }

    private class LoadRefValsLock
    {
        public String _Lock="LoadRefValsLock";
    }
    private static boolean _LoadingValues =false;


    private void loadStsOptRelations(RouteInfor RouteData,String stsObjCode)
    {
        if(_LoadingValues==false)
        {
            synchronized (LoadRefValsLock.class){
                if(_LoadingValues == false){
                    _LoadingValues = true;
                    _loadStsOptRelations(RouteData,stsObjCode);
                    _LoadingValues = false;
                }
            }
        }
    }

    public HashMap<String,JRStatusData> getStatusOptRelations(RouteInfor RouteData,String stsObjCode)
    {
        HashMap<String,JRStatusData> theStsOptRMap = stsOptRelations.get(stsObjCode.toUpperCase());
        if(theStsOptRMap!=null && theStsOptRMap.size()>0)
        {
            return  theStsOptRMap;
        }
        loadStsOptRelations(RouteData,stsObjCode);
        theStsOptRMap = stsOptRelations.get(stsObjCode.toUpperCase());
        return  theStsOptRMap;
    }

    public  JRStatusData getStatusOptRelation(RouteInfor RouteData,String stsObjCode,String stsCode)
    {
        JRStatusData theStsData = getStatusOptRelation2(RouteData,stsObjCode,stsCode);
        if(theStsData!=null)
        {
            return theStsData.clone();
        }
        return  theStsData;
    }
    /*
    * 这个用于后端加载，不采用克隆模式.
    * */
    public  JRStatusData getStatusOptRelation2(RouteInfor RouteData,String stsObjCode,String stsCode)
    {
        if(stsObjCode==null || stsObjCode.isEmpty() || stsCode==null || stsCode.isEmpty())
        {
            return null;
        }
        HashMap<String,JRStatusData> theRelationMap = getStatusOptRelations(RouteData,stsObjCode);
        JRStatusData theStsData = null;
        if(theRelationMap!=null)
        {
            return theRelationMap.get(stsCode.toUpperCase());
        }
        return null;
    }
    public JRStatusOptR CheckStatusCfgPrv(RouteInfor RouteData,String stsObjCode,String stsCode,String optCode)
    {
        JRStatusData thePrvData = getStatusOptRelation2(RouteData,stsObjCode,stsCode);
        if(thePrvData!=null)
        {
            return thePrvData.getOptPrvs().get(optCode.toUpperCase());
        }
        return null;
    }
    private static volatile StatusManage _Instantce = null;
    public static StatusManage GetInstantce()
    {
        if(_Instantce==null)
        {
            synchronized (StatusManage.class){
                if(_Instantce == null){
                    _Instantce = JRRuntimeHelper.getCurrentAppContext().getBean(StatusManage.class);;
                }
            }
        }
        return _Instantce;
    }
}
