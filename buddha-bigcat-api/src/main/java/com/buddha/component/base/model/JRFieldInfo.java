package com.buddha.component.base.model;

import java.io.Serializable;
import java.lang.reflect.Method;

public class JRFieldInfo implements Serializable {
	
   public String PropertyName;
   public String ColumnName;
   public int DataType=java.sql.Types.VARCHAR;
   public Class<?> Type=String.class;
   public Boolean IsIdField=false;
   public Method GetMethod;
   public Method SetMethod;
   
   public boolean Unique=false;
   public boolean Nullable=true;
   public boolean Insertable=true;
   public boolean Updatable=true;
   public int Length=255;
   public int Precision=255;
   public int Scale=255;
  
}
