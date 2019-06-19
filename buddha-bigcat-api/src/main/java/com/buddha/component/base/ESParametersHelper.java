package com.buddha.component.base;

import java.util.ArrayList;
import java.util.List;

import com.buddha.component.base.model.ESParameter;
import com.buddha.component.base.pubdef.LikeOperater;
import com.buddha.component.base.pubdef.ParameterDirection;

public class ESParametersHelper {

    private ESParametersHelper()
    {
    }
    public static ESParametersHelper newInst()
    {
        return new ESParametersHelper();
    }
    public List<ESParameter> params()
    {
        return _pars;
        
    }

    private List<ESParameter> _pars = new ArrayList<ESParameter>();
  

    public ESParameter addParameter(String name, int dbType,
    		Object value, ParameterDirection Direction)
    {

        ESParameter p = new ESParameter();
        p.ParamName = name;
        p.FieldName = name;
        p.DataType = dbType;
        p.Value = value;
        p.Direction = Direction;
        _pars.add(p);
        return p;

    }
  
    public <T> ESParametersHelper set(String name, T val)
    {
        if (val != null)
        {
            addParameter(name, PublicFunction.convertJavaTypeToSqlType(val.getClass()),getDbValue(val.getClass(), val),ParameterDirection.Input);
        }
        else
        {
            addParameter(name, PublicFunction.convertJavaTypeToSqlType(val.getClass()), getDbValue(val.getClass(), val),ParameterDirection.Input);
        }
        return this;
    }


    public ESParametersHelper setLike(String name, String value, LikeOperater like)
    {
        //2016-06-13 修改，如果为空值，则不添加条件.
        if (value.trim().isEmpty())
        {
            return this;
        }
        String theNewValue = "%"+value+"%";
        if (like == LikeOperater.LeftLike)
        {
        	theNewValue = "%"+value+"";
        }
        else if (like == LikeOperater.RightLike)
        {
        	theNewValue = ""+value+"%";
        }
       
        addParameter(name,java.sql.Types.VARCHAR,
        		theNewValue,ParameterDirection.Input);
        return this;
    }


    public <T> ESParametersHelper set(String name, T val, ParameterDirection parameterDirection)
    {
    	if (val != null)
        {
            addParameter(name, PublicFunction.convertJavaTypeToSqlType(val.getClass()),getDbValue(val.getClass(), val),parameterDirection);
        }
        else
        {
            addParameter(name, PublicFunction.convertJavaTypeToSqlType(val.getClass()), getDbValue(val.getClass(), val),parameterDirection);
        }
        return this;
    }


    public Object getDbValue(Class<?> type,Object v)
    {
        if (type.isEnum())
        {
            return v.hashCode();
        }
        else
        {
            return v;
        }
    }
}
