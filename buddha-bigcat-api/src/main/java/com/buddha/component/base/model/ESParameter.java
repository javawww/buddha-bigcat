package com.buddha.component.base.model;

import com.buddha.component.base.pubdef.DbParamType;
import com.buddha.component.base.pubdef.LikeOperater;
import com.buddha.component.base.pubdef.ParameterDirection;
import com.buddha.component.base.pubdef.WhereClauseSearchType;
import com.buddha.component.base.pubdef.WhereNullToDo;

public class ESParameter {
	public WhereNullToDo NullToDo;
	public Object Value;
	public String ParamName;
    public int DataType;
    public ParameterDirection Direction;
    public int Length;
    public Boolean IsNullable;
   
    public Boolean IsKeyWordSearch;
    public Boolean IsOnlyParam;
    public Boolean NeedBuildParam;
    public String ValidationRegex;
    public WhereClauseSearchType SearchType;
    public String SubSearchSQL;
    
    /// <summary>
    /// 构造函数
    /// </summary>
    public ESParameter()
    {
        this.SearchType = WhereClauseSearchType.SearchInMaster;
        this.IsOnlyParam = false;
        this.DataType = java.sql.Types.VARCHAR;
        this.Direction = ParameterDirection.Input;
        this.ParamName = "";
        this.Value = null;
        this.ValidationRegex = "";
        this.NeedBuildParam = true;
        this.IsNullable = true;
        this.NullToDo = WhereNullToDo.None;
        this.Like = LikeOperater.None;
        this.IsKeyWordSearch =false;
        this.Length =0;
        this.SubSearchSQL="";
        this.PassParamType = DbParamType.Normal;
        this.Operator="=";
        
    }
    
    public String FieldName ;
    public String Operator ;
    public LikeOperater Like;
    public DbParamType PassParamType;

}
