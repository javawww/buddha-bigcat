package com.buddha.component.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import com.buddha.component.base.model.EntityBase;
import com.buddha.component.base.model.JRFieldInfo;
import com.buddha.component.base.model.JRTableInfo;


public class PublicFunction {
	
	
	private static Map<Class<?>,JRTableInfo> _GNTableMaps = new HashMap<Class<?>,JRTableInfo> ();
	
	public static JRTableInfo getFieldInfoMap(Class<?> ClassObj)
    {
		JRTableInfo theResult = _GNTableMaps.get(ClassObj);
		if(theResult==null)
		{
			 synchronized (PublicFunction.class){
	              if(theResult == null){
	            	  JRTableInfo theRetTmp = loadFieldInfoMap(ClassObj);
	            	  _GNTableMaps.put(ClassObj, theRetTmp);
	              }
	          }
		}
		theResult = _GNTableMaps.get(ClassObj);
		return theResult;
    }
	
	public static int convertJavaTypeToSqlType(Class<?> ClassObj)
	{
		if(ClassObj==String.class)
		{
			return java.sql.Types.VARCHAR;
		}
		if(ClassObj==Integer.class || ClassObj==int.class)
		{
			return java.sql.Types.INTEGER;
		}
		if(ClassObj==long.class || ClassObj==Long.class)
		{
			return java.sql.Types.BIGINT;
		}
		if(ClassObj==Boolean.class || ClassObj==boolean.class)
		{
			return java.sql.Types.BOOLEAN;
		}
		if(ClassObj==Short.class || ClassObj==short.class)
		{
			return java.sql.Types.INTEGER;
		}
		
		if(ClassObj==Date.class)
		{
			return java.sql.Types.DATE;
		}
		if(ClassObj==Double.class || ClassObj==double.class)
		{
			return java.sql.Types.DOUBLE;
		}
	
		if(ClassObj==Float.class || ClassObj==float.class)
		{
			return java.sql.Types.FLOAT;
		}
	
		if(ClassObj==byte.class || ClassObj==Byte.class)
		{
			return java.sql.Types.BLOB;
		}
		if(ClassObj==byte[].class)
		{
			return java.sql.Types.BLOB;
		}
		return java.sql.Types.VARCHAR;
	}
    public static List<JRFieldInfo> getFieldInfos(Class<?> ClassObj)
    {
    	 List<JRFieldInfo> theFieldInfos = new ArrayList<JRFieldInfo>();
         Field[] theFields = ClassObj.getDeclaredFields();
         for(Field theField : theFields)
         {
        	 JRFieldInfo theGNField = new JRFieldInfo();
        	 theGNField.PropertyName = theField.getName();
        	 theGNField.Type = theField.getType();
        	 theGNField.DataType = convertJavaTypeToSqlType(theGNField.Type);
        	 Column theColumnAnn = theField.getDeclaredAnnotation(Column.class);
        	 if(theColumnAnn!=null)
        	 {
        		 theGNField.ColumnName = theColumnAnn.name();
        		 theFieldInfos.add(theGNField);
        	 }
        	 javax.persistence.Id theIdAnn = theField.getDeclaredAnnotation(javax.persistence.Id.class);
        	 if(theIdAnn!=null)
        	 {
        		 theGNField.IsIdField = true;
        	 }
         }
    	return theFieldInfos;
    }
    public static int convertToInt(Object ObjVal,int DefaultVal)
	{
		int theRet = DefaultVal;
		if(ObjVal!=null && ObjVal.toString().isEmpty()==false)
		{
			theRet = Integer.parseInt(ObjVal.toString().trim());
		}
		return  theRet;
	}
    private static Method getMethodFromByName(Method[] Methods,String Name)
    {
    	Method theMethod = null;
    	for(Method theM : Methods)
    	{
    		if(theM.getName().toLowerCase().equals(Name.toLowerCase()))
    		{
    			theMethod = theM;
    			break;
    		}
    	}
    	return theMethod;
    }
    private static JRTableInfo loadFieldInfoMap(Class<?> ClassObj)
    {
    	 JRTableInfo theTableInfo = new JRTableInfo();
    	 theTableInfo.Type = ClassObj;
    	 theTableInfo.EntityName = ClassObj.getName();
    	 Map<String,JRFieldInfo> theFieldInfos = new HashMap<String,JRFieldInfo>();
		Map<String,JRFieldInfo> theProperties = new HashMap<String,JRFieldInfo>();
    	 try
    	 {
    		 Table theTableAnno =  ClassObj.getDeclaredAnnotation(Table.class);
    		 if(theTableAnno!=null)
    		 {
    			 theTableInfo.TableName = theTableAnno.name();
    		 }
	    	 Method[] theMethods = ClassObj.getDeclaredMethods();
	         Field[] theFields = ClassObj.getDeclaredFields();
	         for(Field theField : theFields)
	         {
	        	 JRFieldInfo theGNField = new JRFieldInfo();
	        	 theGNField.PropertyName = theField.getName();
	        	 theGNField.Type = theField.getType();
	        	 theGNField.DataType = convertJavaTypeToSqlType(theGNField.Type);
	        	 Column theColumnAnn = theField.getDeclaredAnnotation(Column.class);
	        	 if(theColumnAnn!=null)
	        	 {
	        		 theGNField.ColumnName = theColumnAnn.name();
	        		 theGNField.Insertable = theColumnAnn.insertable();
	        		 theGNField.Nullable = theColumnAnn.nullable();
	        		 theGNField.Precision = theColumnAnn.precision();
	        		 theGNField.Scale = theColumnAnn.scale();
	        		 theGNField.Updatable = theColumnAnn.updatable();
	        		 theGNField.Unique = theColumnAnn.unique();
	        		 theGNField.Length = theColumnAnn.length();

	        		 theGNField.GetMethod = getMethodFromByName(theMethods,"get"+theGNField.PropertyName);
	        		 theGNField.SetMethod = getMethodFromByName(theMethods,"set"+theGNField.PropertyName);

					 theFieldInfos.put(theGNField.ColumnName, theGNField);
					 theProperties.put(theGNField.PropertyName, theGNField);
	        	 }
	        	 javax.persistence.Id theIdAnn = theField.getDeclaredAnnotation(javax.persistence.Id.class);
	        	 if(theIdAnn!=null)
	        	 {
	        		 theGNField.IsIdField = true;
					 theTableInfo.KeyField = theGNField;
	        	 }
	         }
    	 }
    	 catch(Exception e)
    	 {
    		 JRRuntimeHelper.logger.debug("PublicFunction.LoadFieldInfoMap:"+e.getMessage());
    	 }
    	 theTableInfo.Fields = theFieldInfos;
		 theTableInfo.Properties = theProperties;
    	 return theTableInfo;
    }
    public static <T> T columnMap2Entity(Map<String,Object> Row,Class<T> ClassType) throws Exception
    {
    	T theObj = (T)ClassType.newInstance(); 
    	JRTableInfo theTableInfo = getFieldInfoMap(ClassType);
    	Map<String,JRFieldInfo> theFieldMap = theTableInfo.Fields;
    	for(String theKey: Row.keySet())
    	{
    		Object theVal = Row.get(theKey);
    		JRFieldInfo theFieldInfo = theFieldMap.get(theKey);
    		if(theFieldInfo==null)
    		{
    			if((theObj instanceof EntityBase) && theVal!=null)
    			{
    				EntityBase theBase = (EntityBase)theObj;
    				theBase.writeOtherValue(theKey, theVal.toString());
    			}
    		}
    		else
    		{
    			if(theFieldInfo.SetMethod!=null && theVal !=null)
    			{
    				if(theVal.getClass()==BigDecimal.class)
    				{
    					theVal = convertBigDecimal2TargetType((BigDecimal)theVal,theFieldInfo.Type);
    					
    				}
    				else if(theVal.getClass()==String.class)
					{
						if(theFieldInfo.Type ==Date.class)
						{
							theVal = DateHelper.parseFromString(theVal.toString());
						}
						else
						{
							theVal = convertString2TargetType(theVal.toString(),theFieldInfo.Type);
						}

					}
    				theFieldInfo.SetMethod.invoke(theObj, theVal);
    			}
    		}
    	}
    	return theObj;
    }

	public static <T> T propertyMap2Entity(Map<String,Object> Row,Class<T> ClassType) throws Exception
	{
		T theObj = (T)ClassType.newInstance();
		JRTableInfo theTableInfo = getFieldInfoMap(ClassType);
		Map<String,JRFieldInfo> theFieldMap = theTableInfo.Properties;
		for(String theKey: Row.keySet())
		{
			Object theVal = Row.get(theKey);
			JRFieldInfo theFieldInfo = theFieldMap.get(theKey);
			if(theFieldInfo==null)
			{
				if((theObj instanceof EntityBase) && theVal!=null)
				{
					EntityBase theBase = (EntityBase)theObj;
					theBase.writeOtherValue(theKey, theVal.toString());
				}
			}
			else
			{
				if(theFieldInfo.SetMethod!=null && theVal !=null)
				{
					if(theVal.getClass()==BigDecimal.class)
					{
						theVal = convertBigDecimal2TargetType((BigDecimal)theVal,theFieldInfo.Type);

					}
					else if(theVal.getClass()==String.class)
					{
						if(theFieldInfo.Type ==Date.class)
						{
							theVal = DateHelper.parseFromString(theVal.toString());
						}
						else
						{
							theVal = convertString2TargetType(theVal.toString(),theFieldInfo.Type);
						}

					}
					theFieldInfo.SetMethod.invoke(theObj, theVal);
				}
			}
		}
		return theObj;
	}
    public static Object convertBigDecimal2TargetType(BigDecimal Value,Class<?> TargetClass)
    {
    	if(TargetClass==Integer.class || TargetClass==int.class)
		{
		   return Value.intValue();
		}
		if(TargetClass==Float.class || TargetClass==float.class)
		{
			return Value.floatValue();
		}
		if(TargetClass==Double.class || TargetClass==double.class)
		{
			return Value.doubleValue();
		}
		if(TargetClass==Byte.class || TargetClass==byte.class)
		{
			return Value.byteValue();
		}
		if(TargetClass==Long.class || TargetClass==long.class)
		{
			return Value.longValue();
		}
		if(TargetClass==Short.class || TargetClass==short.class)
		{
			return Value.shortValue();
		}
		if(TargetClass==BigInteger.class )
		{
			return Value.unscaledValue();
		}
		if(TargetClass==String.class )
		{
			return Value.toString();
		}
    	return Value;
    }
	public static Object convertString2TargetType(String Value,Class<?> TargetClass)
	{
		if(TargetClass==Integer.class || TargetClass==int.class)
		{
			return Integer.parseInt(Value);
		}
		if(TargetClass==Float.class || TargetClass==float.class)
		{
			return Float.parseFloat(Value);
		}
		if(TargetClass==Double.class || TargetClass==double.class)
		{
			return Double.parseDouble(Value);
		}
		if(TargetClass==Byte.class || TargetClass==byte.class)
		{
			return Byte.parseByte(Value);
		}
		if(TargetClass==Long.class || TargetClass==long.class)
		{
			return Long.parseLong(Value);
		}
		if(TargetClass==Short.class || TargetClass==short.class)
		{
			return Short.parseShort(Value);
		}

		return Value;
	}
    public static <T> List<T> columnMap2EntityList(List<Map<String,Object>> Rows,Class<T> ClassType) throws Exception
    {
    	List<T> theModelList = new ArrayList<T>();
    	if(Rows!=null)
    	{
	    	for(Map<String,Object> theRow: Rows)
	    	{
	    		theModelList.add(columnMap2Entity(theRow,ClassType));
	    	}
    	}
    	return theModelList;
    }
	public static <T> List<T> propertyMap2EntityList(List<Map<String,Object>> Rows,Class<T> ClassType) throws Exception
	{
		List<T> theModelList = new ArrayList<T>();
		if(Rows!=null)
		{
			for(Map<String,Object> theRow: Rows)
			{
				theModelList.add(propertyMap2Entity(theRow,ClassType));
			}
		}
		return theModelList;
	}
	public static String parseSortOrderSQL(String JSonSortString,Class<?> ClassObj)
	{
		String theOrderSQL="";
		List<Map<String,Object>> theSortMap = ConvertUtil.json2List(JSonSortString);
		for(Map<String,Object> theItem : theSortMap)
		{
			String thePName=theItem.get("property").toString();
			JRTableInfo theTableInfo = getFieldInfoMap(ClassObj);
			String theColumn = "";
			if(theTableInfo!=null)
			{
				theColumn = theTableInfo.getColumnByProperty(thePName);
				if(theOrderSQL.isEmpty()) {
					theOrderSQL = theColumn +" "+theItem.get("direction").toString();
				}
				else {
					theOrderSQL +=","+ theColumn +" "+theItem.get("direction").toString();
				}
			}
		}
		return theOrderSQL;
	}
}
