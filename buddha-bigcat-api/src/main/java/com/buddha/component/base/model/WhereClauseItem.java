package com.buddha.component.base.model;

import com.buddha.component.base.pubdef.LikeOperater;
import com.buddha.component.base.pubdef.WhereClauseSearchType;
import com.buddha.component.base.pubdef.WhereNullToDo;

public class WhereClauseItem {


    public String FieldName;
    private Object _FieldValue;
    public Object getFieldValue()
    {
    	if (_FieldValue != null && _FieldValue.getClass() == String.class)
        {
            return _FieldValue.toString().trim();
        }
        return _FieldValue;
    }
    public void setFieldValue(Object Value)
    {
    	this._FieldValue = Value;
    }

    public String Operator;
    public LikeOperater Like;
    public int DataType;
    public Boolean IsKeyWordSearch;
    public WhereNullToDo WhereNullToDo;
    public WhereClauseSearchType SearchType;
    public Boolean IsOnlyParam;
    public String SubSearchSQL;
    public WhereClauseItem()
    {
        IsOnlyParam = false;
        Operator="=";
        Like = LikeOperater.None;
        WhereNullToDo = WhereNullToDo.None;
        SearchType = WhereClauseSearchType.SearchInMaster;
        DataType = java.sql.Types.VARCHAR;
        this.IsKeyWordSearch = false;
        this.SubSearchSQL ="";
        this.FieldName="";
    }
}
