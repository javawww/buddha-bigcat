package com.buddha.component.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.buddha.component.base.model.ESParameter;
import com.buddha.component.base.model.PagedResult;
import com.buddha.component.base.model.WhereClauseItem;
import com.buddha.component.base.pubdef.JRTransaction;
import com.buddha.component.base.pubdef.RouteInfor;

@Service
public class DBContextService {

	public PagedResult queryBySQLPage(RouteInfor RouteData, String Sql, int PageSize, int PageIndex, List<WhereClauseItem> WhereClauseItems,
									  String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans) throws Exception {
		
		DBContextBase theDBContextBase = new DBContextBase();
		return theDBContextBase.queryBySQLPage(RouteData,Sql, PageSize, PageIndex, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
   }
    public  List<Map<String, Object>> queryBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
    		String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans)  throws Exception
    {
    	DBContextBase theDBContextBase = new DBContextBase();
    	return theDBContextBase.queryBySQL(RouteData, ASQL, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
    }
   
}
