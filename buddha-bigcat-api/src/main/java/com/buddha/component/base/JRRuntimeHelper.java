package com.buddha.component.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.buddha.component.base.pubdef.RouteInfor;

public class JRRuntimeHelper {

	private static ApplicationContext _applicationContext;
	public static void setApplicationContext(ApplicationContext appContext)
	{
		_applicationContext = appContext;
	}
	public static ApplicationContext getCurrentAppContext()	{
		if(_applicationContext==null)
		{
			_applicationContext = ApplicationContextProvider.getApplicationContext();
		}
		return _applicationContext;
	}
	public final static Logger logger = LoggerFactory.getLogger("Icbigroup");
	public static RouteInfor getBaseEntRouteInfor()
	{
		return  RouteInfor.buildRouteInfoInt(0);
	}

	public static Integer getBaseEntRouteId()
	{
		return  0;
	}
}
