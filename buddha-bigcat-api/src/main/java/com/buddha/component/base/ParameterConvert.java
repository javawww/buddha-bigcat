package com.buddha.component.base;

import java.util.ArrayList;
import java.util.List;

import com.buddha.component.base.model.ESParameter;
import com.buddha.component.base.model.WhereClauseItem;
import com.buddha.component.base.pubdef.DbParamType;
import com.buddha.component.base.pubdef.ParameterDirection;

public class ParameterConvert {
	public static ESParameter toESParameter(WhereClauseItem Param)
    {
        ESParameter theParam = new ESParameter();

        theParam.DataType = Param.DataType;
        theParam.Direction = ParameterDirection.Input;
        theParam.ParamName = Param.FieldName;
        theParam.Value = Param.getFieldValue();
        theParam.Like = Param.Like;
        theParam.PassParamType = DbParamType.Where;
        theParam.Operator = Param.Operator;
        theParam.FieldName = Param.FieldName;
        theParam.IsKeyWordSearch = Param.IsKeyWordSearch;
        theParam.NullToDo = Param.WhereNullToDo;
        theParam.SearchType = Param.SearchType;
        theParam.SubSearchSQL = Param.SubSearchSQL;
        theParam.IsOnlyParam = Param.IsOnlyParam;
        return theParam;
    }

    public static List<ESParameter> toESParameter(List<WhereClauseItem> Params)
    {
        List<ESParameter> theParams = new ArrayList<ESParameter>();
        if (Params == null)
        {
            return theParams;
        }
        for(WhereClauseItem theParam : Params)
        {
            theParams.add(toESParameter(theParam));
        }
        return theParams;
    }
}
