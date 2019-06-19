package com.buddha.component.base.model;

import java.io.Serializable;
import java.util.Map;

public class JRTableInfo implements Serializable{

	public JRFieldInfo KeyField;
	public String TableName;
	public Class<?> Type;
	public String EntityName;
	public Map<String,JRFieldInfo> Fields;
	public Map<String,JRFieldInfo> Properties;
	public String getColumnByProperty(String PropertyName)
	{
		return Properties.get(PropertyName).ColumnName;
	}
	public String getPropertyByColumn(String ColumnName)
	{
		return Properties.get(ColumnName.toUpperCase()).PropertyName;
	}
}
