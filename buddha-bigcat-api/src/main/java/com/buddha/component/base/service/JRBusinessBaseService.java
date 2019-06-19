package com.buddha.component.base.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddha.component.base.DbSQLBuilder;
import com.buddha.component.base.ESParametersHelper;
import com.buddha.component.base.PublicFunction;
import com.buddha.component.base.model.BatchInsertSqlAndParam;
import com.buddha.component.base.model.ESParameter;
import com.buddha.component.base.model.EntityBase;
import com.buddha.component.base.model.JRSimpleModelWrapper;
import com.buddha.component.base.model.JRTableInfo;
import com.buddha.component.base.model.PagedResult;
import com.buddha.component.base.model.PagedResultM;
import com.buddha.component.base.model.WhereClauseItem;
import com.buddha.component.base.pubdef.JRTransaction;
import com.buddha.component.base.pubdef.RouteInfor;

@Service
public abstract class JRBusinessBaseService<T> {

	public abstract String getDefaultSelectSQL();
	private Class<T> classOfT;
	
	public Class<T> getClassOfT() {
		return classOfT;
	}

	@Autowired
	private DBContextBase dbContextBase;
	
	
	protected DBContextBase getDbContextBase() {
		return dbContextBase;
	}
	public JRBusinessBaseService()
	{
		ParameterizedType theType = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.classOfT = (Class<T>) theType.getActualTypeArguments()[0];
	}
	public abstract void setModelReference(T Model);
	public abstract void setModelStatusPrvData(T Model);

	public PagedResult queryMapBySQLPage(RouteInfor RouteData, String Sql, int PageSize, int PageIndex, List<WhereClauseItem> WhereClauseItems,
										 String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		return dbContextBase.queryBySQLPage(RouteData,Sql, PageSize, PageIndex, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
    }
    public List<Map<String, Object>> queryMapBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
    		String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans)  throws Exception
    {
    	return dbContextBase.queryBySQL(RouteData,ASQL, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
    }
    
    public  List<T> queryModelsBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
    		String WhereSQL, String OrderSQL, List<ESParameter> Parameters,Boolean BindRefVal,Boolean BindStsPrvData ,JRTransaction Trans)  throws Exception
    {
    	List<Map<String, Object>> theMapRet = dbContextBase.queryBySQL(RouteData,ASQL, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
    	List<T> theModels = PublicFunction.columnMap2EntityList(theMapRet, this.classOfT);
		if(BindRefVal) {
			if (theModels != null) {
				setReferenceModelList(theModels);
			}
		}
		if(BindStsPrvData)
		{
			if (theModels != null) {
				this.setModelListStatusPrvData(theModels);
			}
		}
		return theModels;
    }
	public  List<T> queryModelsBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
									 String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans)  throws Exception {
		return queryModelsBySQL(RouteData, ASQL,WhereClauseItems,WhereSQL,OrderSQL,Parameters, true,true,Trans);

	}
	public  List<T> queryModelsBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
									 String WhereSQL, String OrderSQL,JRTransaction Trans)  throws Exception
	{
		return queryModelsBySQL(RouteData, ASQL,WhereClauseItems,WhereSQL,OrderSQL,null,Trans);
	}
	public  List<T> queryModelsBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
									 String WhereSQL,JRTransaction Trans)  throws Exception
	{
		return queryModelsBySQL(RouteData,ASQL,WhereClauseItems,WhereSQL,"",Trans);
	}
	public  List<T> queryModelsBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,JRTransaction Trans)  throws Exception
	{
		return queryModelsBySQL(RouteData,ASQL,WhereClauseItems,"","",Trans);
	}
	public  List<T> queryModelsBySQL(RouteInfor RouteData,String ASQL,JRTransaction Trans)  throws Exception
	{
		return queryModelsBySQL(RouteData,ASQL,null,"","",Trans);
	}

    public  void setModelListStatusPrvData(List<T> Models)
	{
		if(Models!=null)
		{
			for (T theM : Models)
			{
				this.setModelStatusPrvData(theM);
			}
		}
	}
    public void setReferenceModelList(List<T> Models)
	{
		if(Models!=null)
		{
			for (T theM : Models)
			{
				this.setModelReference(theM);
			}
		}
	}

    public PagedResultM<T> queryModelsBySQLPage(RouteInfor RouteData,String Sql,int PageSize,int PageIndex,List<WhereClauseItem> WhereClauseItems,
    		String WhereSQL, String OrderSQL, List<ESParameter> Parameters,Boolean BindRefVal,Boolean BindStsPrvData,JRTransaction Trans) throws Exception
	{
		PagedResult theMapPageResult = dbContextBase.queryBySQLPage(RouteData, Sql, PageSize, PageIndex, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
		PagedResultM<T> theModelsPageResult = new PagedResultM<T>();
		List<T> theData = PublicFunction.columnMap2EntityList(theMapPageResult.getData(), this.classOfT);
		if(BindRefVal && theData!=null)
		{
			setReferenceModelList(theData);
		}
		if(BindStsPrvData && theData!=null)
		{
			setModelListStatusPrvData(theData);
		}
		theModelsPageResult.setData(theData);
		theModelsPageResult.setPageIndex(theMapPageResult.getPageIndex());
		theModelsPageResult.setPageSize(theMapPageResult.getPageSize());
		theModelsPageResult.setTotalCount(theMapPageResult.getTotalCount());
		theModelsPageResult.setPageCount(theMapPageResult.getPageCount());
		return theModelsPageResult;
    }
	public PagedResultM<T> queryModelsBySQLPage(RouteInfor RouteData,String Sql,int PageSize,int PageIndex,List<WhereClauseItem> WhereClauseItems,
												String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans) throws Exception {
           return this.queryModelsBySQLPage(RouteData,Sql,PageSize,PageIndex,WhereClauseItems,WhereSQL,OrderSQL,Parameters,true,true,Trans);
	}
    public  List<T> queryModelsBySQL(RouteInfor RouteData,List<WhereClauseItem> WhereClauseItems,
    		String WhereSQL, String OrderSQL, List<ESParameter> Parameters, Boolean BindRefVal, Boolean BindStsPrvData,JRTransaction Trans)  throws Exception
    {
		return queryModelsBySQL(RouteData,this.getDefaultSelectSQL(),WhereClauseItems,WhereSQL,OrderSQL,Parameters, BindRefVal,BindStsPrvData,Trans);
    }
	public  List<T> queryModelsBySQL(RouteInfor RouteData,List<WhereClauseItem> WhereClauseItems,
									 String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans)  throws Exception
	{
		return this.queryModelsBySQL(RouteData,this.getDefaultSelectSQL(), WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
	}
    public PagedResultM<T> queryModelsBySQLPage(RouteInfor RouteData,int PageSize,int PageIndex,List<WhereClauseItem> WhereClauseItems,
    		String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		return this.queryModelsBySQLPage(RouteData,this.getDefaultSelectSQL(),
				PageSize, PageIndex, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
    }
    private  T getModelByKey(RouteInfor RouteData,String id,JRTransaction Trans) throws Exception
	{
		String theKeyField = PublicFunction.getFieldInfoMap(classOfT).KeyField.ColumnName;
		String theWhereSQL=" AND "+theKeyField+" = :"+theKeyField;
		String theSQL = this.getDefaultSelectSQL()+theWhereSQL;
		ESParametersHelper theESHelper =  ESParametersHelper.newInst().set(theKeyField,id);
		List<T> theModels = queryModelsBySQL(RouteData,theSQL,null,"","",theESHelper.params(),Trans);
		if(theModels!=null && theModels.size()>0)
		{
			return  theModels.get(0);
		}
		return  null;
	}
	public T getOneModelByKey(RouteInfor RouteData,String id,JRTransaction Trans) throws Exception
	{
	    return  this.getOneModelByKey(RouteData,id,true,true,Trans);
	}
	public T getOneModelByKey(RouteInfor RouteData,String id,Boolean BindRefVal,Boolean BindStsPrvData,JRTransaction Trans) throws Exception
	{
		T theModel = this.getModelByKey(RouteData,id,Trans);
		if(BindRefVal) {
			this.setModelReference(theModel);
		}
		if(BindStsPrvData)
		{
			this.setModelStatusPrvData(theModel);
		}
		return theModel;
	}
	/**
	 * 删除操作
	 * @param id
	 * @throws Exception
	 */
	@Transactional
	public void doDelete(RouteInfor RouteData,String id,JRTransaction Trans) throws Exception {
		JRTableInfo theTabInfo = PublicFunction.getFieldInfoMap(classOfT);
		String theSQL = DbSQLBuilder.buildDeleteByKeySQL(theTabInfo);
		ESParametersHelper theHelper = ESParametersHelper.newInst().set(theTabInfo.KeyField.ColumnName,id);
		this.execUpdateSql(RouteData, theSQL,theHelper.params(),Trans);
	}
	/**
	 * 删除操作
	 * @param id
	 * @throws Exception
	 */
	@Transactional
	public void doDeleteByKey(RouteInfor RouteData,String id,JRTransaction Trans) throws Exception {
		doDelete(RouteData, id,Trans);
	}
	/**
	 * 保存/创建一个实体
	 * @throws Exception
	 */
	@Transactional
	public void doSave(RouteInfor RouteData,T Model,JRTransaction Trans) throws Exception {

		String theKeyValue = ((EntityBase)Model).getKeyValue();
		T theOldModel = this.getOneModelByKey(RouteData, theKeyValue,Trans);
		if(theOldModel==null)
		{
			this.doCreate(RouteData,Model,Trans);
		}
		else {
			this.doUpdate(RouteData,Model,Trans);
		}
	}

	/**
	 * 更新实体
	 */
	@Transactional
	public void doCreate(RouteInfor RouteData,T theNewModel,JRTransaction Trans) throws Exception {
		this.doInsertModel(RouteData, theNewModel,Trans);
	}


	/***
	 * 更新一条数据
	 * @throws Exception
	 */
	@Transactional
	public void doUpdate(RouteInfor RouteData,T Model,JRTransaction Trans) throws Exception {
		// 修改用户信息
		this.doUpdateModelWithKey(RouteData,Model,Trans);
	}
	public abstract void setWrappedMapReference(JRSimpleModelWrapper Model);
	public abstract void setWrappedMapStatusPrvData(JRSimpleModelWrapper Model);
	public  void setWrappedMapReferenceList(List<JRSimpleModelWrapper> Models)
	{
		if(Models!=null)
		{
			for (JRSimpleModelWrapper theM : Models)
			{
				this.setWrappedMapReference(theM);
			}
		}
	}
	public void setWrappedMapStatusPrvDataList(List<JRSimpleModelWrapper> Models)
	{
		if(Models!=null)
		{
			for (JRSimpleModelWrapper theM : Models)
			{
				this.setWrappedMapStatusPrvData(theM);
			}
		}
	}
	public PagedResultM<JRSimpleModelWrapper> queryWrappedMapListBySQLPage(RouteInfor RouteData,int PageSize,int PageIndex,
																		   List<WhereClauseItem> WhereClauseItems,
																		   String WhereSQL, String OrderSQL,
																		   List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		return  queryWrappedMapListBySQLPage(RouteData, this.getDefaultSelectSQL(),PageSize,PageIndex,WhereClauseItems,WhereSQL,OrderSQL, Parameters,true,true,Trans);
	}
	public PagedResultM<JRSimpleModelWrapper> queryWrappedMapListBySQLPage(RouteInfor RouteData,String Sql,int PageSize,int PageIndex,
																		   List<WhereClauseItem> WhereClauseItems,
																		   String WhereSQL, String OrderSQL,
																		   List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		return  queryWrappedMapListBySQLPage(RouteData,Sql,PageSize,PageIndex,WhereClauseItems,WhereSQL,OrderSQL, Parameters,true,true,Trans);
	}
	public PagedResultM<JRSimpleModelWrapper> queryWrappedMapListBySQLPage(RouteInfor RouteData,String Sql,int PageSize,int PageIndex,
										List<WhereClauseItem> WhereClauseItems,String WhereSQL, String OrderSQL,
										List<ESParameter> Parameters,Boolean BindRefVal,Boolean BindStsPrvData,JRTransaction Trans) throws Exception
	{
		PagedResult theRetMapList= dbContextBase.queryBySQLPage(RouteData,Sql, PageSize, PageIndex, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
		PagedResultM<JRSimpleModelWrapper> theRetModelWrapper = new PagedResultM<JRSimpleModelWrapper>();
		theRetModelWrapper.setPageCount(theRetMapList.getPageCount());
		theRetModelWrapper.setPageIndex(theRetMapList.getPageIndex());
		theRetModelWrapper.setPageSize(theRetMapList.getPageSize());
		theRetModelWrapper.setTotalCount(theRetMapList.getTotalCount());
		List<JRSimpleModelWrapper> theDataWrapped = new ArrayList<JRSimpleModelWrapper>();
		theRetModelWrapper.setData(theDataWrapped);
		if(theRetMapList!=null)
		{
			for(Map<String,Object> theMap : theRetMapList.getData())
			{
				JRSimpleModelWrapper theModelWrapper = new JRSimpleModelWrapper();
				theDataWrapped.add(theModelWrapper);
				theModelWrapper.setModelMap(theMap);
				if(BindRefVal)
				{
					theModelWrapper.setRefValues(new HashMap<String,String>());
					this.setWrappedMapReference(theModelWrapper);
				}
				if(BindStsPrvData)
				{
					this.setWrappedMapStatusPrvData(theModelWrapper);
				}
			}
		}
		return  theRetModelWrapper;
	}
	public List<JRSimpleModelWrapper> queryWrappedMapLisBySQL(RouteInfor RouteData,List<WhereClauseItem> WhereClauseItems,
														   String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans)  throws Exception
	{
		return queryWrappedMapListBySQL(RouteData,this.getDefaultSelectSQL(),WhereClauseItems,WhereSQL,OrderSQL,Parameters,true,true,Trans);
	}
	public List<JRSimpleModelWrapper> queryWrappedMapListBySQL(RouteInfor RouteData,List<WhereClauseItem> WhereClauseItems,
														   String WhereSQL, String OrderSQL, List<ESParameter> Parameters,
														   Boolean BindRefVal,Boolean BindStsPrvData,JRTransaction Trans)  throws Exception
	{
		return queryWrappedMapListBySQL(RouteData,this.getDefaultSelectSQL(),WhereClauseItems,WhereSQL,OrderSQL,Parameters,BindRefVal,BindStsPrvData,Trans);
	}
	public List<JRSimpleModelWrapper> queryWrappedMapListBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
														   String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans)  throws Exception
	{
		return queryWrappedMapListBySQL(RouteData,ASQL,WhereClauseItems,WhereSQL,OrderSQL,Parameters,true,true,Trans);
	}
	public List<JRSimpleModelWrapper> queryWrappedMapListBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
												   String WhereSQL, String OrderSQL, List<ESParameter> Parameters,
													Boolean BindRefVal,Boolean BindStsPrvData,JRTransaction Trans)  throws Exception
	{
		List<Map<String,Object>> theMapList = dbContextBase.queryBySQL(RouteData,ASQL, WhereClauseItems, WhereSQL, OrderSQL, Parameters,Trans);
		List<JRSimpleModelWrapper> theRetWrappedModelList = new ArrayList<JRSimpleModelWrapper>();
		if(theMapList!=null)
		{
			for(Map<String,Object> theMap : theMapList)
			{
				JRSimpleModelWrapper theModelWrapper = new JRSimpleModelWrapper();
				theRetWrappedModelList.add(theModelWrapper);
				theModelWrapper.setModelMap(theMap);
				if(BindRefVal)
				{
					this.setWrappedMapReference(theModelWrapper);
				}
				if(BindStsPrvData)
				{
					this.setWrappedMapStatusPrvData(theModelWrapper);
				}
			}
		}
		return theRetWrappedModelList;
	}
    public int doInsertModel(RouteInfor RouteData,T Model,JRTransaction Trans) throws Exception
	{
		if(Model!=null) {
			String theInsertSQL = DbSQLBuilder.buildInsertSQL(PublicFunction.getFieldInfoMap(classOfT));
			List<ESParameter> theParams = DbSQLBuilder.buildSqlParameters(this.classOfT,Model);
			return dbContextBase.execUpdateSQL(RouteData,theInsertSQL,theParams,Trans);
		}
        return 0;
	}
	public int doUpdateModelWithKey(RouteInfor RouteData,T Model,JRTransaction Trans) throws Exception
	{
		if(Model!=null) {
			String theUpdateSQL = DbSQLBuilder.buildSTDUpdateSQL(PublicFunction.getFieldInfoMap(classOfT));
			List<ESParameter> theParams = DbSQLBuilder.buildSqlParameters(this.classOfT,Model);
			return dbContextBase.execUpdateSQL(RouteData,theUpdateSQL,theParams,Trans);
		}
		return 0;
	}
	public int doUpdateModelWithKey(RouteInfor RouteData,T Model, List<String> NotUpdatedFields,List<String> UpdateFields,JRTransaction Trans) throws Exception
	{
		if(Model!=null) {
			String theUpdateSQL = DbSQLBuilder.buildUpdateSQL(PublicFunction.getFieldInfoMap(classOfT),true,NotUpdatedFields,UpdateFields);
			List<ESParameter> theParams = DbSQLBuilder.buildSqlParameters(this.classOfT,Model);
			return dbContextBase.execUpdateSQL(RouteData,theUpdateSQL,theParams,Trans);
		}
		return 0;
	}
	public int doUpdateModelWithWhereClause(RouteInfor RouteData,T Model, List<String> NotUpdatedFields,
                                            List<String> UpdateFields,
							 List<WhereClauseItem> WhereClauseItems,JRTransaction Trans) throws Exception
	{
		if(Model!=null) {
			String theUpdateSQL = DbSQLBuilder.buildUpdateSQL(PublicFunction.getFieldInfoMap(classOfT),false,NotUpdatedFields,UpdateFields);
			List<ESParameter> theParams = DbSQLBuilder.buildSqlParameters(this.classOfT,Model);
			return dbContextBase.execUpdateSQL(RouteData,theUpdateSQL,WhereClauseItems,theParams,Trans);
		}
		return 0;
	}
	public int doDeleteModelWithKey(RouteInfor RouteData,T Model,JRTransaction Trans) throws Exception
	{
		if(Model!=null) {
			String theSQL = DbSQLBuilder.buildDeleteByKeySQL(PublicFunction.getFieldInfoMap(classOfT));
			List<ESParameter> theParams =DbSQLBuilder.buildKeyParameters(classOfT,Model);
			return dbContextBase.execUpdateSQL(RouteData,theSQL,theParams,Trans);
		}
		return 0;
	}
	public int doDeleteModelWithWhereClause(RouteInfor RouteData,T Model,List<WhereClauseItem> whereClauseItems,JRTransaction Trans) throws Exception
	{
		if(Model!=null) {
			String theSQL = DbSQLBuilder.buildDeleteSQL(PublicFunction.getFieldInfoMap(classOfT));
			List<ESParameter> theParams =DbSQLBuilder.buildKeyParameters(classOfT,Model);
			return dbContextBase.execUpdateSQL(RouteData,theSQL,whereClauseItems,theParams,Trans);
		}
		return 0;
	}
    public int doDeleteWithWhereClause(RouteInfor RouteData,String sql,List<WhereClauseItem> whereClauseItems,JRTransaction Trans) throws Exception
    {
        return dbContextBase.execUpdateSQL(RouteData,sql,whereClauseItems,null,Trans);
    }
	public int doBatchInsert(RouteInfor RouteData,List<T> Models,JRTransaction Trans) throws Exception
	{
		if(Models!=null && Models.size()>0) {
			BatchInsertSqlAndParam theSqlAndParam = DbSQLBuilder.buildBatchInsertSQL(PublicFunction.getFieldInfoMap(classOfT));
			List<Object[]> theArgs = new ArrayList<Object[]>();
			for (T theModel : Models) {
				Object[] theObjects = new Object[theSqlAndParam.getFields().size()];
				theArgs.add(theObjects);
				for (int i = 0; i < theSqlAndParam.getFields().size(); i++) {
					theObjects[i] = theSqlAndParam.getFields().get(i).GetMethod.invoke(theModel);
				}
			}
			return dbContextBase.batchUpdate(RouteData, theSqlAndParam.sql,theArgs,Trans);
		}
		return 0;
	}
	public int execUpdateSql(RouteInfor RouteData,String sql,List<ESParameter> parameters,JRTransaction Trans) throws Exception
	{
		return this.dbContextBase.execUpdateSQL(RouteData,sql,parameters,Trans);
	}
	public int execUpdateSql(RouteInfor RouteData,String sql,JRTransaction Trans) throws Exception
	{
		return this.dbContextBase.execUpdateSQL(RouteData,sql,null,Trans);
	}

	/**
	 * 批量保存实现
	 *

	 * @throws Exception
	 */
	@Transactional
	public void saveChanges(RouteInfor RouteData,List<T> theModels,String userId,JRTransaction Trans) throws Exception {
		for (T theModel : theModels) {
			EntityBase theModelBase=(EntityBase)theModel;
			switch (theModelBase.getEntityState()){
				case Unchanged:break;
				case Deleted:this.doDelete(RouteData,theModelBase.getKeyValue(),Trans);break;
				case Added: this.doSave(RouteData,theModel,Trans);break;
				case Modified:this.doSave(RouteData,theModel,Trans);break;
			}
		}
	}
}
