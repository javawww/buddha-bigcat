package com.buddha.component.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.buddha.component.base.model.BatchInsertSqlAndParam;
import com.buddha.component.base.model.ESParameter;
import com.buddha.component.base.model.JRFieldInfo;
import com.buddha.component.base.model.JRTableInfo;

public class DbSQLBuilder {

    private static HashMap<String,String> cachedInsertSQL= new HashMap<String,String>();
    private static HashMap<String,String> cachedUpdateSQL= new HashMap<String,String>();

    public HashMap<String,String> getCachedInsertSQL()
    {
        return cachedInsertSQL;
    }
    public HashMap<String,String> getCachedUpdateSQL()
    {
        return cachedUpdateSQL;
    }
	
	private static boolean isUpdatedField(String FieldName,List<String> NotUpdatedFields,List<String> UpdateFields)
	{
		boolean theCanUpdated = true;
		if(NotUpdatedFields!=null)
		{
			if(NotUpdatedFields.indexOf(FieldName)>=0)
			{
				theCanUpdated = false;
			}
		}
		if(UpdateFields!=null)
		{
			if(UpdateFields.indexOf(FieldName)<0)
			{
				theCanUpdated = false;
			}
		}
		return theCanUpdated;
	}
	public static String buildUpdateSQL(JRTableInfo TabInfo,boolean IsKeyCondition, List<String> NotUpdatedFields,List<String> UpdateFields)
	{
		StringBuilder theSQL = new StringBuilder();
        
        theSQL.append("UPDATE " + TabInfo.TableName + " SET ");
        boolean isFirst = true;
        JRFieldInfo theIdField=null;
        for(String Key : TabInfo.Fields.keySet())
        {
        	JRFieldInfo item = TabInfo.Fields.get(Key);
        	//关键字不能更新
            if (item.IsIdField==false && item.Updatable == true && isUpdatedField(item.ColumnName,NotUpdatedFields,UpdateFields))
            {
                if (isFirst)
                {
                    theSQL.append(item.ColumnName + " = :" + item.ColumnName);
                    isFirst = false;
                }
                else
                {
                    theSQL.append("," + item.ColumnName + " = :" + item.ColumnName);
                }
            }
            if(item.IsIdField)
            {
            	theIdField = item;
            }
            
        }
        if(IsKeyCondition)
        {
        	theSQL.append(" WHERE " + theIdField.ColumnName + " = :" + theIdField.ColumnName);
        }
        else
        {
        	theSQL.append(" WHERE 1=1 ");
        }
        return theSQL.toString();
	}
	public static String buildSTDUpdateSQL(JRTableInfo TabInfo)
	{
	    String theSQL = cachedUpdateSQL.get(TabInfo.TableName.toUpperCase());
        if(theSQL==null || theSQL=="")
        {
            synchronized (TabInfo)
            {
                if(theSQL==null || theSQL=="") {
                    theSQL = buildUpdateSQL(TabInfo, true, null, null);
                    cachedUpdateSQL.put(TabInfo.TableName.toUpperCase(), theSQL);
                }
            }
        }
		return theSQL;
	}
    public static BatchInsertSqlAndParam buildBatchInsertSQL(JRTableInfo TabInfo)
    {
        BatchInsertSqlAndParam theSqlAndParam = new BatchInsertSqlAndParam();
        StringBuilder theSQL1 = new StringBuilder();
        StringBuilder theSQL2 = new StringBuilder();
        boolean isFirst = true;
        List<JRFieldInfo> theFields = new ArrayList<JRFieldInfo>();
        for(String Key : TabInfo.Fields.keySet())
        {
            JRFieldInfo item = TabInfo.Fields.get(Key);
            if(item.Insertable)
            {
                if (isFirst)
                {
                    theSQL1.append(item.ColumnName);
                    theSQL2.append("?");
                    isFirst = false;
                }
                else
                {
                    theSQL1.append("," + item.ColumnName);
                    theSQL2.append(",?");
                }
                theFields.add(item);
            }

        }

        String theSqlStr = "INSERT INTO " + TabInfo.TableName + " (" + theSQL1.toString() + ") VALUES(" + theSQL2.toString() + ")";
        theSqlAndParam.setSql(theSqlStr);
        theSqlAndParam.setFields(theFields);


        return theSqlAndParam;
    }
	public static String buildInsertSQL(JRTableInfo TabInfo)
    {
        String theSQL = cachedInsertSQL.get(TabInfo.TableName.toUpperCase());
        if(theSQL==null || theSQL=="")
        {
            synchronized (TabInfo)
            {
                if(theSQL==null || theSQL=="") {
                    theSQL = createInsertSQL(TabInfo);
                    cachedInsertSQL.put(TabInfo.TableName.toUpperCase(), theSQL);
                }
            }
        }
        return theSQL;
    }
	private static String createInsertSQL(JRTableInfo TabInfo)
    {
        
            StringBuilder theSQL1 = new StringBuilder();
            StringBuilder theSQL2 = new StringBuilder();
           
            boolean isFirst = true;
            for(String Key : TabInfo.Fields.keySet())
            {
            	JRFieldInfo item = TabInfo.Fields.get(Key);
            	if(item.Insertable)
            	{
	                if (isFirst)
	                {
	                    theSQL1.append(item.ColumnName);
	                    theSQL2.append(":" + item.ColumnName);
	                    isFirst = false;
	                }
	                else
	                {
	                    theSQL1.append("," + item.ColumnName);
	                    theSQL2.append(",:" + item.ColumnName);
	                }
            	}

            }

            String theSqlStr = "INSERT INTO " + TabInfo.TableName + " (" + theSQL1.toString() + ") VALUES(" + theSQL2.toString() + ")";
            return theSqlStr;
    }
    public static String buildDeleteByKeySQL(JRTableInfo TabInfo)
    {
        StringBuilder theSQL = new StringBuilder();
        theSQL.append("DELETE FROM " + TabInfo.TableName);
        boolean isFirst = true;
        JRFieldInfo theIdField= TabInfo.KeyField;
        theSQL.append(" WHERE " + theIdField.ColumnName + " = :" + theIdField.ColumnName);
        return theSQL.toString();
    }
    public static String buildDeleteSQL(JRTableInfo TabInfo)
    {
        StringBuilder theSQL = new StringBuilder();
        theSQL.append("DELETE FROM " + TabInfo.TableName+" WHERE 1=1 ");
        return theSQL.toString();
    }

    public static List<ESParameter> buildSqlParameters(Class<?> ModelClass,Object modelObj) throws Exception
    {
        JRTableInfo theTabInfo = PublicFunction.getFieldInfoMap(ModelClass);
        List<ESParameter> theParams = new ArrayList<ESParameter>();
        for (String theKey : theTabInfo.Fields.keySet()) {
            JRFieldInfo theFldInfo = theTabInfo.Fields.get(theKey);
            if(theFldInfo.GetMethod!=null)
            {
                ESParameter theParam = new ESParameter();
                theParam.IsOnlyParam = true;
                theParam.ParamName = theFldInfo.ColumnName;
                theParam.DataType = theFldInfo.DataType;
                theParam.Value = theFldInfo.GetMethod.invoke(modelObj);
                theParams.add(theParam);
            }
        }
        return theParams;
    }
    public static List<ESParameter> buildKeyParameters(Class<?> ModelClass,Object modelObj) throws Exception
    {
        JRTableInfo theTabInfo = PublicFunction.getFieldInfoMap(ModelClass);
        List<ESParameter> theParams = new ArrayList<ESParameter>();
        ESParameter theParam = new ESParameter();
        theParam.IsOnlyParam = true;
        theParam.ParamName = theTabInfo.KeyField.ColumnName;
        theParam.DataType = theTabInfo.KeyField.DataType;
        theParam.Value = theTabInfo.KeyField.GetMethod.invoke(modelObj);
        theParams.add(theParam);
        return theParams;
    }
}
