package com.buddha.component.base.pubdef;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RouteInfor implements Serializable {
    public String getBusiId() {
        return busiId;
    }
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    private  String busiId;
    public List<String> getRouteIds() {
        return RouteIds;
    }
    private List<String> RouteIds;

    public boolean isRouteAll() {
        return routeAll;
    }

    public boolean isRouteMasterNode() {
        return routeMasterNode;
    }

    private boolean routeAll;
    private boolean routeMasterNode;
    public RouteInfor()
    {
        RouteIds = new ArrayList<String>();
    }
    public static RouteInfor buildRouteInfoInt(int routeId,int... routeIds)
    {
        RouteInfor theRouteInfor = new RouteInfor();
        theRouteInfor.getRouteIds().add(String.valueOf(routeId));
        if(routeIds!=null)
        {
            for (int theRouteId : routeIds)
            {
                theRouteInfor.getRouteIds().add(String.valueOf(theRouteId));
            }
        }
        return theRouteInfor;
    }
    public static RouteInfor buildRouteInfoStr(String routeId,String... routeIds)
    {
        RouteInfor theRouteInfor = new RouteInfor();
        theRouteInfor.getRouteIds().add(routeId);
        if(routeIds!=null)
        {
            for (String theRouteId : routeIds)
            {
                theRouteInfor.getRouteIds().add(theRouteId);
            }
        }
        return theRouteInfor;
    }

    public static RouteInfor buildRouteInfoForMstrNode()
    {
        RouteInfor theRouteInfor = new RouteInfor();
        theRouteInfor.routeMasterNode =true;
        return theRouteInfor;
    }
    public static RouteInfor buildRouteInfoAll()
    {
        RouteInfor theRouteInfor = new RouteInfor();
        theRouteInfor.routeAll =true;
        return theRouteInfor;
    }

}
