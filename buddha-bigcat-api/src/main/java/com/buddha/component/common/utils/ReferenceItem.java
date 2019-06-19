package com.buddha.component.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buddha.component.base.ESParametersHelper;
import com.buddha.component.base.JRRuntimeHelper;
import com.buddha.component.base.model.RefItemModel;
import com.buddha.component.base.model.Ss19ReferenceDef;
import com.buddha.component.base.pubdef.RouteInfor;
import com.buddha.component.base.service.BaseRefDefService;
import com.buddha.component.base.service.RefItemModelService;

public class ReferenceItem {
    private Map<String,Ss19ReferenceDef> _RefDefList = null;
    private Map<String,Date> _LastLoadTimeList = null;
    private Map<String,Date> _LastLoadTimeDef = null;
    private Map<String,Map<String,RefItemModel>> _CachedValues = null;
    private RefItemModelService _RefItemModelService;
    private BaseRefDefService _BaseRefDefService;


    private int _routeId;
    private RouteInfor _routeData;

    public ReferenceItem(int routeId, RefItemModelService rms, BaseRefDefService brs)
    {
         _routeId = routeId;
         _routeData = RouteInfor.buildRouteInfoInt(routeId);
         _RefItemModelService = rms;
         _BaseRefDefService = brs;
         _RefDefList = new HashMap<String,Ss19ReferenceDef>();
         _LastLoadTimeDef = new HashMap<String,Date>();
         _LastLoadTimeList = new HashMap<String,Date>();
         _CachedValues = new HashMap<String,Map<String,RefItemModel>>();
    }

    private void _loadRefDefModel(String RefCode)
    {

        try
        {
            String theWhereSQL=" AND SS19_CODE='"+RefCode+"'";
            List<Ss19ReferenceDef> theDefModelList = this._BaseRefDefService.queryModelsBySQL(JRRuntimeHelper.getBaseEntRouteInfor(),null,theWhereSQL,"",null,false,false,null);
            if(theDefModelList!=null)
            {
                for(Ss19ReferenceDef theDef:theDefModelList)
                {
                    this._RefDefList.put(theDef.getSs19Code(), theDef);
                }
            }
            else
            {
                JRRuntimeHelper.logger.debug(RefCode+"无定义!");
            }
            _LastLoadTimeDef.put(RefCode,new Date());
        }
        catch(Exception e)
        {
            JRRuntimeHelper.logger.debug(e.getMessage());
        }

    }


    private void loadRefDefModel(String RefCode)
    {
        _loadRefDefModel(RefCode);
    }
    private RefItemModel getRefItemModel(String RefCode,String Key)
    {
        Map<String,RefItemModel> theRefValues = this.getRefValueMap(RefCode);
        if(theRefValues!=null) {
            return theRefValues.get(Key);
        }
        return null;
    }
    private Ss19ReferenceDef getRefItemDef(String RefCode)
    {
        String theSychSign=("LOADDEFxxxxxx"+RefCode).intern();
        Ss19ReferenceDef theRefItem = this._RefDefList.get(RefCode);
        if(theRefItem==null)
        {
            synchronized (theSychSign) {
                theRefItem = this._RefDefList.get(RefCode);
                if (theRefItem == null) {
                    this.loadRefDefModel(RefCode);
                    theRefItem = this._RefDefList.get(RefCode);
                }
            }
        }
        else
        {
            long theTimeout1 = this.getTimeDiffMinute(this._LastLoadTimeDef.get(RefCode), new Date());
            if(theTimeout1>30)
            {
                synchronized (theSychSign) {
                    theTimeout1 = this.getTimeDiffMinute(this._LastLoadTimeDef.get(RefCode), new Date());
                    if(theTimeout1>30) {
                        this.loadRefDefModel(RefCode);
                        theRefItem = this._RefDefList.get(RefCode);
                    }
                }
            }
        }
        return theRefItem;
    }
    private long getTimeDiffMinute(Date DtVal1,Date DtVal2)
    {
        return (DtVal2.getTime() - DtVal1.getTime())/60000;
    }

    private void loadRefValues(Ss19ReferenceDef RefItem)
    {
        _LoadRefValues(RefItem);
    }
    private void _LoadRefValues(Ss19ReferenceDef RefItem)
    {
        try
        {
            Map<String,RefItemModel> theRefValues = this._CachedValues.get(RefItem.getSs19Code());
            if(theRefValues==null)
            {
                theRefValues =new HashMap<String,RefItemModel>();
            }
            theRefValues.clear();
            ESParametersHelper theESHelper = ESParametersHelper.newInst().set("REF_ROUTE_ID",_routeId);
            List<RefItemModel> theValueList = _RefItemModelService.queryModelsBySQL(_routeData,RefItem.getSs19View(), null, "", "", theESHelper.params(),false,false,null);
            if(theValueList!=null)
            {
                for(RefItemModel theItem :theValueList)
                {
                    theRefValues.put(theItem.getKey(), theItem);
                }
                if(theValueList.size()<=0)
                {
                    JRRuntimeHelper.logger.debug(RefItem.getSs19Code()+"无数据!");
                }
            }
            else
            {
                JRRuntimeHelper.logger.debug(RefItem.getSs19Code()+"无数据!");
            }
            this._CachedValues.put(RefItem.getSs19Code(), theRefValues);
            this._LastLoadTimeList.put(RefItem.getSs19Code(), new Date());
        }
        catch(Exception e)
        {
            JRRuntimeHelper.logger.debug("ReferenceManage._LoadRefValues:"+e.getMessage());
        }
    }
    public void refresh()
    {
        this._RefDefList.clear();
        this._CachedValues.clear();
    }
    public void refresh(String RefCode)
    {
        this._RefDefList.remove(RefCode);
        this._CachedValues.remove(RefCode);
    }

    public String getRefValue(String RefCode,String Key,String DefaultValue)
    {
        RefItemModel theRefModel = this.getRefItemModel(RefCode, Key);
        if(theRefModel==null)
        {
            return DefaultValue;
        }
        return theRefModel.getValue();
    }
    public String getRefValue(String RefCode,String Key)
    {
        return this.getRefValue(RefCode, Key,Key);
    }
    public List<RefItemModel> getRefValueList(String RefCode,String LimitFld,String LimitVal)
    {
        List<RefItemModel> theRetList = new ArrayList<RefItemModel>();
        Map<String,RefItemModel> theRefValues = this.getRefValueMap(RefCode);
        if(theRefValues !=null) {
            for (RefItemModel theVal : theRefValues.values()) {
                if (LimitFld == null || LimitFld == "" || LimitFld.trim() == "") {
                    theRetList.add(theVal);
                } else {
                    if(theVal.getLimitFldVal(LimitFld).equals(LimitVal)==true)
                    {
                        theRetList.add(theVal);
                    }
                }
            }
        }
        return theRetList;
    }
    public Map<String,RefItemModel> getRefValueMap(String RefCode)
    {
        String theSychString =("LOADVALUESXXXX"+RefCode).intern();
        Map<String,RefItemModel> theRefValues = null;
        Ss19ReferenceDef theRefItem = this.getRefItemDef(RefCode);
        if(theRefItem!=null)
        {
            theRefValues = this._CachedValues.get(RefCode);
            if(theRefValues!=null)
            {
                long theTimeout1 = this.getTimeDiffMinute(this._LastLoadTimeList.get(RefCode), new Date());
                if(theTimeout1> theRefItem.getSs19TimeOut())
                {
                    synchronized (theSychString) {
                        theTimeout1 = this.getTimeDiffMinute(this._LastLoadTimeList.get(RefCode), new Date());
                        if(theTimeout1> theRefItem.getSs19TimeOut()) {
                            this.loadRefValues(theRefItem);
                            theRefValues = this._CachedValues.get(RefCode);
                        }
                    }
                }
            }
            else
            {
                synchronized (theSychString) {
                    theRefValues = this._CachedValues.get(RefCode);
                    if(theRefValues==null) {
                        this.loadRefValues(theRefItem);
                        theRefValues = this._CachedValues.get(RefCode);
                    }
                }
            }
        }
        return theRefValues;
    }

}
