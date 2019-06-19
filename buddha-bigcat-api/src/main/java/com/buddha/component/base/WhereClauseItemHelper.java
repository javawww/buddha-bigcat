package com.buddha.component.base;

import java.util.ArrayList;
import java.util.List;

import com.buddha.component.base.model.WhereClauseItem;
import com.buddha.component.base.pubdef.LikeOperater;
import com.buddha.component.base.pubdef.WhereClauseSearchType;
import com.buddha.component.base.pubdef.WhereNullToDo;

public class WhereClauseItemHelper {

    private WhereClauseItemHelper()
    {
    }
    public static WhereClauseItemHelper newInst()
    {
        return new WhereClauseItemHelper();
    }
    /// <summary>
    /// 参数临时保存对象
    /// </summary>
    private List<WhereClauseItem> _pars = new ArrayList<WhereClauseItem>();
    public List<WhereClauseItem> Params()
    {
    	 return _pars;
    }
    public <T> WhereClauseItemHelper set(String FieldName, T FieldValue,
        String Operator, LikeOperater Like,WhereNullToDo NullToDo)
    {
    	WhereClauseItem theItem = new WhereClauseItem();
    	if(FieldValue!=null)
    	{
    	   theItem.DataType = PublicFunction.convertJavaTypeToSqlType(FieldValue.getClass());
    	}
    	else
    	{
    		theItem.DataType = java.sql.Types.VARCHAR;
    	}
    	theItem.FieldName = FieldName;
    	theItem.setFieldValue(FieldValue);
    	theItem.Operator = Operator;
    	theItem.Like = Like;
    	theItem.WhereNullToDo =NullToDo; 
        this._pars.add(theItem);
        return this;
    }
	public <T> WhereClauseItemHelper set(String FieldName, T FieldValue,String Operator, LikeOperater Like)
	{
		return this.set(FieldName,FieldValue,Operator,Like,WhereNullToDo.None);
	}
	public <T> WhereClauseItemHelper set(String FieldName, T FieldValue,String Operator)
	{
		return this.set(FieldName,FieldValue,Operator,LikeOperater.None,WhereNullToDo.None);
	}
	public <T> WhereClauseItemHelper set(String FieldName, T FieldValue)
	{
		return this.set(FieldName,FieldValue,"=",LikeOperater.None,WhereNullToDo.None);
	}
	public WhereClauseItemHelper setKS(Object FieldValue, String FieldName1)
	{
		List<String> theFields = new ArrayList<String>();
		theFields.add(FieldName1);
		return this.setKS(FieldValue,theFields);
	}
	public WhereClauseItemHelper setKS(Object FieldValue,String FieldName1,String FieldName2)
	{
		List<String> theFields = new ArrayList<String>();
		theFields.add(FieldName1);
		theFields.add(FieldName2);
		return this.setKS(FieldValue,theFields);
	}
	public WhereClauseItemHelper setKS(Object FieldValue,String FieldName1,String FieldName2,String FieldName3)
	{
		List<String> theFields = new ArrayList<String>();
		theFields.add(FieldName1);
		theFields.add(FieldName2);
		theFields.add(FieldName3);
		return this.setKS(FieldValue,theFields);
	}
	public WhereClauseItemHelper setKS(Object FieldValue,String FieldName1,String FieldName2,String FieldName3,String FieldName4)
	{
		List<String> theFields = new ArrayList<String>();
		theFields.add(FieldName1);
		theFields.add(FieldName2);
		theFields.add(FieldName3);
		theFields.add(FieldName4);
		return this.setKS(FieldValue,theFields);
	}
	public WhereClauseItemHelper setKS(Object FieldValue,String FieldName1,String FieldName2,String FieldName3,String FieldName4,String FieldName5)
	{
		List<String> theFields = new ArrayList<String>();
		theFields.add(FieldName1);
		theFields.add(FieldName2);
		theFields.add(FieldName3);
		theFields.add(FieldName4);
		theFields.add(FieldName5);
		return this.setKS(FieldValue,theFields);
	}
	public WhereClauseItemHelper setKS(Object FieldValue,String FieldName1,String FieldName2,
									   String FieldName3,String FieldName4,String FieldName5,
									   String FieldName6)
	{
		List<String> theFields = new ArrayList<String>();
		theFields.add(FieldName1);
		theFields.add(FieldName2);
		theFields.add(FieldName3);
		theFields.add(FieldName4);
		theFields.add(FieldName5);
		theFields.add(FieldName6);
		return this.setKS(FieldValue,theFields);
	}
	public WhereClauseItemHelper setKS(Object FieldValue,String FieldName1,String FieldName2,
									   String FieldName3,String FieldName4,String FieldName5,
									   String FieldName6,String FieldName7)
	{
		List<String> theFields = new ArrayList<String>();
		theFields.add(FieldName1);
		theFields.add(FieldName2);
		theFields.add(FieldName3);
		theFields.add(FieldName4);
		theFields.add(FieldName5);
		theFields.add(FieldName6);
		theFields.add(FieldName7);
		return this.setKS(FieldValue,theFields);
	}
	public WhereClauseItemHelper setKS(Object FieldValue,String FieldName1,String FieldName2,
									   String FieldName3,String FieldName4,String FieldName5,
									   String FieldName6,String FieldName7,String FieldName8)
	{
		List<String> theFields = new ArrayList<String>();
		theFields.add(FieldName1);
		theFields.add(FieldName2);
		theFields.add(FieldName3);
		theFields.add(FieldName4);
		theFields.add(FieldName5);
		theFields.add(FieldName6);
		theFields.add(FieldName7);
		theFields.add(FieldName8);
		return this.setKS(FieldValue,theFields);
	}
    public WhereClauseItemHelper setKS(Object ObjValue,List<String> FieldNames)
    {
        //2016-06-13 修改，如果为空值，则不添加条件.
        if (ObjValue==null)
        {
            return this;
        }
        String FieldValue = ObjValue.toString();
		if(FieldValue.trim().isEmpty())
		{
			return  this;
		}
        if (FieldNames != null)
        {
            for (String FieldName : FieldNames)
            {
            	WhereClauseItem theItem = new WhereClauseItem();
            	theItem.DataType = java.sql.Types.VARCHAR;
            	theItem.FieldName = FieldName;
            	theItem.setFieldValue(FieldValue);
            	theItem.Operator = "like";
            	theItem.Like = LikeOperater.FullLike;
            	theItem.IsKeyWordSearch = true;
            	theItem.WhereNullToDo =WhereNullToDo.None; 
                this._pars.add(theItem);
            }
        }
        return this;
    }

    public WhereClauseItemHelper setSubSearch(String MasterField,String SubSearchSQL,Object Value,Boolean IsKeySearch)
    {
    	WhereClauseItem theItem = new WhereClauseItem();
    	theItem.SearchType= WhereClauseSearchType.SearchInDtl;
    	theItem.DataType = java.sql.Types.VARCHAR;
    	theItem.FieldName = MasterField;
    	theItem.setFieldValue(Value);
    	theItem.Operator = "like";
    	theItem.Like = LikeOperater.FullLike;
    	theItem.IsKeyWordSearch = IsKeySearch;
    	theItem.WhereNullToDo =WhereNullToDo.None; 
        this._pars.add(theItem);
        return this;
    }
    public WhereClauseItemHelper setOnlyParam(String ParamName,Object Value)
    {
    	WhereClauseItem theItem = new WhereClauseItem();
    	theItem.SearchType= WhereClauseSearchType.SearchInMaster;
    	theItem.SubSearchSQL="";
    	if(Value!=null)
    	{
    	   theItem.DataType = PublicFunction.convertJavaTypeToSqlType(Value.getClass());
    	}
    	else
    	{
    		theItem.DataType = java.sql.Types.VARCHAR;
    	}
    	theItem.FieldName = ParamName;
    	theItem.setFieldValue(Value);
    	theItem.Operator = "like";
    	theItem.Like = LikeOperater.FullLike;
    	theItem.IsKeyWordSearch = false;
    	theItem.WhereNullToDo =WhereNullToDo.None; 
    	theItem.IsOnlyParam=true;
        this._pars.add(theItem);
        return this;
    }

}
