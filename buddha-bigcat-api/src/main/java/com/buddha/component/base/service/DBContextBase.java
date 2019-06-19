package com.buddha.component.base.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import com.buddha.component.base.ParameterConvert;
import com.buddha.component.base.model.ESParameter;
import com.buddha.component.base.model.PagedResult;
import com.buddha.component.base.model.WhereClauseItem;
import com.buddha.component.base.pubdef.DbParamType;
import com.buddha.component.base.pubdef.JRTransConnGroup;
import com.buddha.component.base.pubdef.JRTransConnection;
import com.buddha.component.base.pubdef.JRTransaction;
import com.buddha.component.base.pubdef.LikeOperater;
import com.buddha.component.base.pubdef.RouteInfor;
import com.buddha.component.base.pubdef.WhereClauseSearchType;
import com.buddha.component.common.utils.DatasourceHelper;

@Repository
public class DBContextBase{

	private Map<String,DataSource> _Datasources;

	//private static String _OralcePageSQL = "SELECT * FROM (SELECT PAGEXX.*,ROWNUM AS ROW_NUM, COUNT(1) OVER() AS RECORDCOUNT__ FROM (%s) PAGEXX ) PAGEALL WHERE PAGEALL.ROW_NUM >= %d and PAGEALL.ROW_NUM <= %d";
    private static String _MySqlPageSQL = "%s LIMIT %d OFFSET %d";
    public  DBContextBase()
	{
		_Datasources = new HashMap<String, DataSource>();
	}
	/*
	  Execute Update,Insert,Delete SQL.
	 */
	public  int execUpdateSQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
							  List<ESParameter> Parameters,JRTransaction Trans)  throws Exception
	{
		List<ESParameter> theParameterAll = new ArrayList<ESParameter>();
		List<ESParameter> theParameterWhs = ParameterConvert.toESParameter(WhereClauseItems);
		if(Parameters!=null)
		{
			theParameterAll.addAll(Parameters);
		}
		theParameterAll.addAll(theParameterWhs);
		String theWhereCluaseSQL = handlingWhereClauseItems(theParameterAll);
		String theNativeSQL = ASQL + theWhereCluaseSQL;
		return this.execUpdateSQL(RouteData,theNativeSQL,theParameterAll,Trans);
	}
	/*
	  Batch update.
	 */
	public int batchUpdate(RouteInfor RouteData,String sql,List<Object[]> Args,JRTransaction Trans) throws Exception
	{
		if(Trans!=null)
		{
			throw  new Exception("No implement for this method!");
		}
		int theResultTotal =0;
		List<String> theDBNodeIds = getDbNodeIdByRouteInfor(RouteData);
		for(String theDbNodeId : theDBNodeIds) {
			DataSource theDataSource = getDataSource(theDbNodeId,Trans);
			if(theDataSource!=null)
			{
				JdbcTemplate theJdbcTemplate = new JdbcTemplate(theDataSource);
				int[] theRets= theJdbcTemplate.batchUpdate(sql,Args);
				theResultTotal = theResultTotal +sumIntArray(theRets);
			}
		}
		return theResultTotal;
	}
	//执行Insert,Update,Delete语句.
	public  int execUpdateSQL(RouteInfor RouteData,String sql,List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		return execUpdateSQLLocal(RouteData,sql,Parameters,Trans);
		/*
		int theResult=0;
		if(Trans!=null)
		{
			theResult = execUpdateSQLWithConn(RouteData,sql,Parameters,Trans);
		}
		else
		{
			DataSource dataSource = JRRuntimeHelper.getCurrentAppContext().getBean(DataSource.class);
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			if(Parameters==null || Parameters.size()<=0)
			{
				theResult = jdbcTemplate.update(sql);
			}
			else
			{
				TowWrapper theRet = esParameter2SqlParameterValues(sql,Parameters);
				if(theRet.SqlPrams.length>0)
				{

					theResult = jdbcTemplate.update(theRet.NewSQL,theRet.SqlPrams);
				}
				else
				{
					theResult = jdbcTemplate.update(theRet.NewSQL);
				}
			}
		}
		return  theResult;*/
	}
	//执行定义语句.
	public void executeDDLSql(RouteInfor RouteData,String sql) throws Exception {
		executeDDLSqlLocal(RouteData,sql);
	}
	public PagedResult queryBySQLPage(RouteInfor RouteData,String Sql,int PageSize,int PageIndex,List<WhereClauseItem> WhereClauseItems,
									  String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans) throws Exception {

		List<ESParameter> theParameterAll = new ArrayList<ESParameter>();
		List<ESParameter> theParameterWhs = ParameterConvert.toESParameter(WhereClauseItems);
		if(Parameters!=null)
		{
			theParameterAll.addAll(Parameters);
		}
		theParameterAll.addAll(theParameterWhs);

		String theWhereCluaseSQL = WhereSQL+" "+ handlingWhereClauseItems(theParameterAll);
		String theOrder = OrderSQL;
		String theNativeSQL = Sql;
		String theOrderByString = getOrderSQLString(theNativeSQL);
		String theCountTotalSQL="";
		int thePosIndex = -1;
		if (theOrderByString.isEmpty()==false)
		{
			thePosIndex = theNativeSQL.toLowerCase().indexOf(theOrderByString);
		}
		if (thePosIndex < 0)
		{
			theNativeSQL += " " + theWhereCluaseSQL;
			theCountTotalSQL = theNativeSQL;
			if(theOrder!=null && theOrder.trim().isEmpty()==false) {
				theNativeSQL += " ORDER BY " + theOrder;
			}

		}
		else
		{
			String theOrderBy = theNativeSQL.substring(thePosIndex);
			String thePreSQL = theNativeSQL.substring(0, thePosIndex);
			theNativeSQL = thePreSQL + " " + theWhereCluaseSQL + " " + theOrderBy;
			theCountTotalSQL = thePreSQL + " " + theWhereCluaseSQL;
		}
		//handle count sql
		theCountTotalSQL = theCountTotalSQL.toLowerCase();
		int theWPos = theCountTotalSQL.indexOf("from");
		theCountTotalSQL="select count(1) as recordcount "+theCountTotalSQL.substring(theWPos);
		int iStart = (PageIndex - 1) * PageSize;
		int iEnd = iStart + PageSize - 1;
		String theNewSQL = String.format(_MySqlPageSQL, theNativeSQL,PageSize,iStart);
		List<Map<String, Object>> theData = this.queryBySQLNative(RouteData, theNewSQL,theParameterAll,Trans);
		PagedResult theResult = new PagedResult();
		theResult.setPageSize(PageSize);
		theResult.setPageIndex(PageIndex);
		theResult.setData(theData);
		theResult.setTotalCount(0);
		theResult.setPageCount(0);
		if(theData!=null && theData.isEmpty()==false)
		{
			List<Map<String,Object>> theCountData = this.queryBySQLNative(RouteData, theCountTotalSQL,theParameterAll,Trans);
			if(theCountData!=null && theCountData.isEmpty()==false) {
				Long theRecordCount = (Long) (theCountData.get(0).get("recordcount"));
				int theRecordCountInt = theRecordCount.intValue();
				theResult.setTotalCount(theRecordCountInt);
				int thePageCount = (theRecordCountInt + PageSize - 1) / PageSize;
				theResult.setPageCount(thePageCount);
			}
		}
		return theResult;
	}

	public  List<Map<String, Object>> queryBySQL(RouteInfor RouteData,String ASQL, List<WhereClauseItem> WhereClauseItems,
												 String WhereSQL, String OrderSQL, List<ESParameter> Parameters,JRTransaction Trans)  throws Exception
	{
		List<ESParameter> theParameterAll = new ArrayList<ESParameter>();
		List<ESParameter> theParameterWhs = ParameterConvert.toESParameter(WhereClauseItems);
		if(Parameters!=null)
		{
			theParameterAll.addAll(Parameters);
		}
		theParameterAll.addAll(theParameterWhs);

		String theWhereCluaseSQL = WhereSQL+" "+ handlingWhereClauseItems(theParameterAll);
		String theOrder = OrderSQL;
		String theNativeSQL = ASQL;
		String theOrderByString = getOrderSQLString(theNativeSQL);
		int thePosIndex = -1;
		if (theOrderByString.isEmpty()==false)
		{
			thePosIndex = theNativeSQL.toLowerCase().indexOf(theOrderByString);
		}
		if (thePosIndex < 0)
		{
			theNativeSQL += " " + theWhereCluaseSQL;
			if(theOrder.trim().isEmpty()==false)
			{
				theNativeSQL += " ORDER BY " + theOrder;
			}
		}
		else
		{
			String theOrderBy = theNativeSQL.substring(thePosIndex);
			String thePreSQL = theNativeSQL.substring(0, thePosIndex);
			theNativeSQL = thePreSQL + " " + theWhereCluaseSQL + " " + theOrderBy;
		}
		List<Map<String, Object>> theData = this.queryBySQLNative(RouteData, theNativeSQL,theParameterAll,Trans);
		return theData;
	}
	private List<String> getDbNodeIdByRouteInfor(RouteInfor RouteData)
	{
		List<String> theDbNodeIds = new ArrayList<String>();
		if(RouteData==null || RouteData.getRouteIds().size()<=0 || RouteData.isRouteAll())
		{
			theDbNodeIds = RouteInforMgr.getInstantce().getAllDbNodeIds();
		}
		else if(RouteData.isRouteMasterNode())
		{
			theDbNodeIds.add("MASTER_NODE");
		}
		else
		{
			theDbNodeIds= RouteInforMgr.getInstantce().getDbNodeIds(RouteData);
		}
		return  theDbNodeIds;
	}

	private  DataSource getDataSource(String DbNodeId,JRTransaction Trans)
	{
		boolean theIsCachedDatasource = false;
		DataSource theDataSource = null;
		if(Trans!=null)
		{
			theIsCachedDatasource=true;
		}
        if(theIsCachedDatasource)
		{
			theDataSource = _Datasources.get(DbNodeId);
		}
		if(theDataSource==null)
		{
			if(DbNodeId.equals("MASTER_NODE"))
			{
				theDataSource = RouteInforMgr.createMasterNodeDataSource();
				if(theIsCachedDatasource)
				{
					_Datasources.put(DbNodeId, theDataSource);
				}
			}
			else
			{
				Map<String,Object> theDbCfgMap = RouteInforMgr.getInstantce().getDbNodeCfgMap(DbNodeId);
				if(theDbCfgMap!=null) {
					theDataSource = new DataSource();
					theDataSource.setUrl(theDbCfgMap.get("DC04_DB_URL").toString());
					theDataSource.setPassword(theDbCfgMap.get("DC04_USR_PWD").toString());
					theDataSource.setUsername(theDbCfgMap.get("DC04_USR_NAME").toString());
					theDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
					theDataSource = RouteInforMgr.createMasterNodeDataSource();
					if(theIsCachedDatasource)
					{
						_Datasources.put(DbNodeId, theDataSource);
					}
				}
			}
		}
		return theDataSource;
	}
    private List<Connection> getContextConn(RouteInfor RouteData,JRTransaction Trans) throws Exception
	{
		 List<String> theDBNodeIds = getDbNodeIdByRouteInfor(RouteData);
		List<Connection> theConns = new ArrayList<Connection>();
		 if(Trans!=null)
		 {
			 for(String theDbNodeId : theDBNodeIds) {
				 JRTransConnGroup theConnGroup = Trans.getTransConnGroup(theDbNodeId);
				 if(theConnGroup==null || theConnGroup.getTransConns().size()<=0)
				 {
					 if(theConnGroup==null)
					 {
						 theConnGroup = new JRTransConnGroup();
						 Trans.getLocalConns().put(theDbNodeId,theConnGroup);
					 }
					 DataSource theDataSource = getDataSource(theDbNodeId,Trans);
					 if(theDataSource!=null)
					 {
						 JRTransConnection theTranConn = new JRTransConnection();
						 Connection theConn = theDataSource.getConnection();
						 theConn.setAutoCommit(false);
						 theTranConn.setDataSource(theDataSource);
						 theTranConn.setConnection(theConn);
						 theConnGroup.getTransConns().add(theTranConn);
					 }
				 }
			 }

			 if(Trans.getLocalConns().size()>0)
			 {
				 JRTransConnGroup theConnGroup = null;
				 for (String theKey : Trans.getLocalConns().keySet()) {
					 theConnGroup = Trans.getLocalConns().get(theKey);
					 theConns.add(theConnGroup.getTransConns().get(0).getConnection());
				 }
			 }
		 }
		 else
		 {
			 for(String theDbNodeId : theDBNodeIds) {
				 DataSource theDataSource = getDataSource(theDbNodeId,Trans);
				 if(theDataSource!=null)
				 {
					 Connection theConn = theDataSource.getConnection();
					 theConns.add(theConn);
				 }
			 }
		 }
		return theConns;
	}
	//@作者 James.You
	class TaskWithResult_Query implements Callable<List<Map<String, Object>>> {

		private int id;
		private Connection theConn;
		private String sql;
		private List<ESParameter> parameters;
		private JRTransaction trans;


		private TaskWithResult_Query(int id, Connection connection, String sql, List<ESParameter> parameters, JRTransaction trans) {
			this.id = id;
			this.theConn = connection;
			this.sql = sql;
			this.parameters = parameters;
			this.trans = trans;
		}

		@Override
		public List<Map<String, Object>> call() throws Exception {
			System.out.println("Execute Thread Task ID : "+ id);
			List<Map<String, Object>> theResult = null;
			PreparedStatement thePStatement = null;
			try {
				TowWrapper theRet = esParameter2SqlParameterValues(this.sql, this.parameters);
				ResultSet theResultDataset = null;
				if (theRet.SqlPrams.length <= 0) {
					thePStatement = theConn.prepareStatement(theRet.NewSQL);
					theResultDataset = thePStatement.executeQuery();

				} else {
					thePStatement = theConn.prepareStatement(theRet.NewSQL);
					for (int i = 0; i < theRet.SqlPrams.length; i++) {
						SqlParameterValue theP = (SqlParameterValue) (theRet.SqlPrams[i]);
						DatasourceHelper.setPreparedStatementParam(thePStatement, i + 1, theP.getValue(), theP.getSqlType());
					}
					theResultDataset = thePStatement.executeQuery();
				}
				if (theResultDataset != null) {
					theResult = DatasourceHelper.ResultSetToList(theResultDataset);
					JdbcUtils.closeResultSet(theResultDataset);
				}
			} finally {
				JdbcUtils.closeStatement(thePStatement);
				if (trans == null) {
					JdbcUtils.closeConnection(theConn);
				}
			}
     		return theResult;
		}
	}

	class TaskWithResult_Update implements Callable<String> {

		private int id;
		private Connection theConn;
		private String sql;
		private List<ESParameter> parameters;
		private JRTransaction trans;
		private TaskWithResult_Update(int id, Connection connection, String sql, List<ESParameter> parameters, JRTransaction trans) {
			this.id = id;
			this.theConn = connection;
			this.sql = sql;
			this.parameters = parameters;
			this.trans = trans;
		}
		@Override
		public String call() throws Exception {
			System.out.println("Execute Thread Task ID : "+ id);
			String theResult = "0";
			PreparedStatement thePStatement = null;
			try {
				TowWrapper theRet = esParameter2SqlParameterValues(sql,parameters);
				if(theRet.SqlPrams.length>0)
				{
					thePStatement=theConn.prepareStatement(theRet.NewSQL);
					for(int i=0;i<theRet.SqlPrams.length;i++)
					{
						SqlParameterValue theP = (SqlParameterValue)(theRet.SqlPrams[i]);
						DatasourceHelper.setPreparedStatementParam(thePStatement,i+1,theP.getValue(),theP.getSqlType());
					}
					int theResultInt = thePStatement.executeUpdate();
					theResult = String.valueOf(theResultInt);
				}
				else
				{
					thePStatement=theConn.prepareStatement(theRet.NewSQL);
					int theResultInt = thePStatement.executeUpdate();
					theResult = String.valueOf(theResultInt);
				}

			} finally {
				JdbcUtils.closeStatement(thePStatement);
				if (trans == null) {
					JdbcUtils.closeConnection(theConn);
				}
			}
			return theResult;
		}
	}
	class TaskWithResult_Execute implements Callable<Boolean> {

		private int id;
		private Connection theConn;
		private String sql;

		private TaskWithResult_Execute(int id, Connection connection, String sql) {
			this.id = id;
			this.theConn = connection;
			this.sql = sql;
		}

		@Override
		public Boolean call() throws Exception {
			System.out.println("Execute Thread Task ID : "+ id);
			Boolean theResult;
			PreparedStatement thePStatement = null;
			try {
				thePStatement=theConn.prepareStatement(sql);
				theResult =  thePStatement.execute();
			} finally {
				JdbcUtils.closeStatement(thePStatement);
				JdbcUtils.closeConnection(theConn);

			}
			return theResult;
		}
	}
	private List<Map<String, Object>> queryByLocal_Thread(List<Connection> Conns, String sql, List<ESParameter> parameters, JRTransaction trans) throws Exception {
		List<Connection> theConns = Conns;

			List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
			DBContextBase dbc = new DBContextBase();
			ExecutorService exec = Executors.newCachedThreadPool();
			List<Future<List<Map<String, Object>>>> results = new ArrayList<Future<List<Map<String, Object>>>>();

			//根据路由连接数创建对应线程并提交任务分批处理
			for (int i = 0; i < theConns.size(); i++) {
				Connection theConn = theConns.get(i);
				results.add(exec.submit(dbc.new TaskWithResult_Query(i, theConn, sql, parameters, trans)));
			}

			while (results.size()>0) {
				List<Future<List<Map<String, Object>>>> theTmpResults = new ArrayList<Future<List<Map<String, Object>>>>();
				theTmpResults.addAll(results);
				for (Future<List<Map<String, Object>>> future : theTmpResults) {
					try {
						//判断任务执行是否已完成
						if (future.isDone()) {
							//进行阻塞获取结果集
							List<Map<String, Object>> result = future.get();
							list.add(result);
							results.remove(future);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(results.size()>0)
				{
					Thread.sleep(100);
				}
			}
			exec.shutdown();
			List<Map<String, Object>> theResultList = new ArrayList<Map<String, Object>>();
			for (List<Map<String, Object>> theList : list)
			{
				theResultList.addAll(theList);
			}

			return theResultList;


	}
	private List<Map<String, Object>> queryByLocal_Normal(Connection Conn, String sql, List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		List<Map<String, Object>> theResult=null;
		PreparedStatement thePStatement=null;
		Connection theConn = Conn;
		try {
			ResultSet theDataset;
			TowWrapper theRet1 = esParameter2SqlParameterValues(sql,Parameters);
			if(theRet1.SqlPrams.length<=0)
			{
				String theSQL = theRet1.NewSQL;
				thePStatement=theConn.prepareStatement(theSQL);
				theDataset = thePStatement.executeQuery();
			}
			else
			{
				thePStatement=theConn.prepareStatement(theRet1.NewSQL);
				for(int i=0;i<theRet1.SqlPrams.length;i++)
				{
					SqlParameterValue theSP = (SqlParameterValue)(theRet1.SqlPrams[i]);
					DatasourceHelper.setPreparedStatementParam(thePStatement,i+1,theSP.getValue(),theSP.getSqlType());
				}
				theDataset = thePStatement.executeQuery();

			}
			if(theDataset!=null)
			{
				theResult = DatasourceHelper.ResultSetToList(theDataset);
				JdbcUtils.closeResultSet(theDataset);
			}
		}
		finally {
			JdbcUtils.closeStatement(thePStatement);
			if(Trans==null) {
				JdbcUtils.closeConnection(theConn);
			}
		}
		return theResult;
	}
	private List<Map<String, Object>> queryBySQLNative(RouteInfor RouteData, String sql, List<ESParameter> Parameters,JRTransaction Trans) throws Exception {
		List<Connection> theConns = getContextConn(RouteData, Trans);
		if(theConns.size()==1)
		{
			return queryByLocal_Normal(theConns.get(0),sql,Parameters,Trans);
		}
		else
		{
			return  queryByLocal_Thread(theConns,sql,Parameters,Trans);
		}
    	/*
    	List<Map<String, Object>> theResult=null;

		if(Trans!=null)
		{
			//return queryByLocalConn(RouteData,sql,Parameters,Trans);
		}
		else
		{
			DataSource dataSource = JRRuntimeHelper.getCurrentAppContext().getBean(DataSource.class);
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			if(Parameters==null || Parameters.size()<=0)
			{
				theResult = jdbcTemplate.queryForList(sql);
			}
			else
			{
				TowWrapper theRet = esParameter2SqlParameterValues(sql,Parameters);
				if(theRet.SqlPrams.length>0)
				{
					theResult = jdbcTemplate.queryForList(theRet.NewSQL,theRet.SqlPrams);
				}
				else
				{
					theResult = jdbcTemplate.queryForList(theRet.NewSQL);
				}
			}
		}
		return theResult;*/
	}

	private int sumIntArray(int[] values)
	{
		int theResult =0;
		if(values!=null)
		{
			for (int value : values) {
				theResult = theResult+value;
			}
		}
		return theResult;
	}
	private   int execUpdateSQLLocal_Thread(List<Connection> Conns,String sql,List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		int theResultTotal=0;
		List<Connection> theConns = Conns;
		List<String> list = new ArrayList<String>();
		DBContextBase dbc = new DBContextBase();
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<String>> results = new ArrayList<Future<String>>();
		//根据路由连接数创建对应线程并提交任务分批处理
		for (int i = 0; i < theConns.size(); i++) {
			Connection theConn = theConns.get(i);
			results.add(exec.submit(dbc.new TaskWithResult_Update(i, theConn, sql, Parameters, Trans)));
		}
		while (results.size()>0) {
			List<Future<String>> theTmpResults = new ArrayList<Future<String>>();
			theTmpResults.addAll(results);
			for (Future<String> future : theTmpResults) {
				try {
					//判断任务执行是否已完成
					if (future.isDone()) {
						//进行阻塞获取结果集
						String result = future.get();
						list.add(result);
						results.remove(future);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(results.size()>0)
			{
				Thread.sleep(100);
			}
		}
		exec.shutdown();
		for (String theV : list)
		{
			theResultTotal+=Integer.parseInt(theV);
		}
		return theResultTotal;
	}
	private   int execUpdateSQLLocal_Normal(Connection Conn,String sql,List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		int theResultTotal=0;
		Connection theConn = Conn;
		PreparedStatement thePStatement = null;
		try {
			TowWrapper theRet = esParameter2SqlParameterValues(sql, Parameters);
			if (theRet.SqlPrams.length > 0) {
				thePStatement = theConn.prepareStatement(theRet.NewSQL);
				for (int i = 0; i < theRet.SqlPrams.length; i++) {
					SqlParameterValue theP = (SqlParameterValue) (theRet.SqlPrams[i]);
					DatasourceHelper.setPreparedStatementParam(thePStatement, i + 1, theP.getValue(), theP.getSqlType());
				}
				theResultTotal = thePStatement.executeUpdate();
			} else {
				thePStatement = theConn.prepareStatement(theRet.NewSQL);
				theResultTotal = thePStatement.executeUpdate();
			}

		} finally {
			JdbcUtils.closeStatement(thePStatement);
			if (Trans == null) {
				JdbcUtils.closeConnection(theConn);
			}
		}
		return theResultTotal;
	}
	private   int execUpdateSQLLocal(RouteInfor RouteData,String sql,List<ESParameter> Parameters,JRTransaction Trans) throws Exception
	{
		int theResultTotal=0;
		List<Connection> theConns = getContextConn(RouteData,Trans);
		if(theConns.size()==1) {
			theResultTotal= execUpdateSQLLocal_Normal(theConns.get(0),sql,Parameters,Trans);
		}
		else
		{
			theResultTotal = execUpdateSQLLocal_Thread(theConns,sql,Parameters,Trans);
		}
		return theResultTotal;
	}

	//执行定义语句.
	private void executeDDLSqlLocal(RouteInfor RouteData,String sql) throws Exception {
		List<Connection> theConns = getContextConn(RouteData,null);
		if(theConns.size()==1) {
			executeDDLSqlLocal_Normal(theConns.get(0),sql);
		}
		else
		{
			executeDDLSqlLocal_Thread(theConns,sql);
		}
	}
	private void executeDDLSqlLocal_Normal(Connection Conn,String sql) throws Exception {

		Connection theConn =Conn;
		PreparedStatement thePStatement=null;
		try {
			thePStatement=theConn.prepareStatement(sql);
			thePStatement.execute();
		}
		finally {
			JdbcUtils.closeStatement(thePStatement);
			JdbcUtils.closeConnection(theConn);
		}

	}
	private void executeDDLSqlLocal_Thread(List<Connection> Conns,String sql) throws Exception {
		List<Connection> theConns = Conns;
		DBContextBase dbc = new DBContextBase();
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
		//根据路由连接数创建对应线程并提交任务分批处理
		for (int i = 0; i < theConns.size(); i++) {
			Connection theConn = theConns.get(i);
			results.add(exec.submit(dbc.new TaskWithResult_Execute(i, theConn, sql)));
		}
		while (results.size()>0) {
			List<Future<Boolean>> theTmpResults = new ArrayList<Future<Boolean>>();
			theTmpResults.addAll(results);
			for (Future<Boolean> future : theTmpResults) {
				try {
					//判断任务执行是否已完成
					if (future.isDone()) {
						//进行阻塞获取结果集
						future.get();
						results.remove(future);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(results.size()>0)
			{
				Thread.sleep(100);
			}
		}

	}
	private class TowWrapper
	{
		public Object[] SqlPrams;
		public String NewSQL;
	}
	private TowWrapper esParameter2SqlParameterValues(String Sql, List<ESParameter> Parameters)
	{
		    TowWrapper theResult = new TowWrapper();
		    theResult.NewSQL = Sql;
		    List<SqlParameterValue> theParameterValues = new ArrayList<SqlParameterValue>();
		    String theSQL = Sql;
	        String pattern = "\\:\\w+";
	        Pattern r = Pattern.compile(pattern);
	        Matcher m = r.matcher(theSQL);
	        while(m.find())
	        {
	        	
	        		String theField = m.group();
	        		ESParameter thePM = getParameterByName(theField,Parameters);
	        		if(thePM!=null)
	        		{
	        			SqlParameter theParam = new SqlParameter(thePM.ParamName,thePM.DataType);
	        			SqlParameterValue theParamVal = new SqlParameterValue(theParam,thePM.Value);
	        			theParameterValues.add(theParamVal);
	        			theResult.NewSQL = theResult.NewSQL.replaceFirst(""+theField+"", "?");
	        		}
	        	
	        }
	    theResult.SqlPrams = theParameterValues.toArray();
		return theResult;
	}
	private ESParameter getParameterByName(String Name,List<ESParameter> Parameters)
	{
		ESParameter theResult = null;
		for(ESParameter theP: Parameters)
		{
			if(theP.ParamName.toLowerCase().equals(Name.toLowerCase())
					|| (":"+theP.ParamName).toLowerCase().equals(Name.toLowerCase()))
			{
				theResult = theP;
				break;
			}
		}
		return theResult;
	}

    private String getOrderSQLString(String SQL)
    {
        String theSQL = SQL.toLowerCase();
        String pattern = "\\border\\s+by\\b";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(theSQL);

        if(m.find())
        {
            return m.group(0);
        }
        return "";
    }
	private String getFieldName(String FieldName)
	{
		String theFieldName = FieldName;
        int theIndex = theFieldName.indexOf(".");
        if (theIndex >= 0)
        {
            theFieldName = theFieldName.substring(theIndex + 1);
        }
      return theFieldName;
	}
	private String handlingWhereClauseItems(List<ESParameter> ESOutParams)
    {
		String theWhereSQL = "";
		List<ESParameter> theNormalParams = new ArrayList<ESParameter>();
		List<ESParameter> theKeyParams = new ArrayList<ESParameter>();
		if (ESOutParams != null)
        {
			for(ESParameter theParam :  ESOutParams)
			{
				if(theParam.IsOnlyParam==false)
				{
					if(theParam.IsKeyWordSearch)
					{
						theKeyParams.add(theParam);
					}
					else
					{
					   theNormalParams.add(theParam);	
					}
				}
			}
        
        }
		HandleResult theResult1 = handlingWhereClauseItems1(theNormalParams,0);
		HandleResult theResult2 = handlingWhereClauseItems2(theKeyParams,theResult1.Index+1);
		theWhereSQL=theResult1.SQL+" "+theResult2.SQL;
		return theWhereSQL;
		
    }
	
	private String formatLikeValue(ESParameter Param)
	{
		String theVale = Param.Value.toString();
		switch(Param.Like)
        {
            case FullLike:
            	theVale ="%"+theVale+"%";
                break;
            case LeftLike:
            	theVale ="%"+theVale+"";
                break;
            case RightLike:
            	theVale =""+theVale+"%";
                break;
        }
		return theVale;
	}
	
	
	private Class<?> getListGenericType(List<Object> Val)
	{
		return Val.get(0).getClass();
	}
	private boolean isArrayListType(Object Value)
	{
		if(Value instanceof List)
		{
			return true;
		}
		return false;
	}
	
	private HandleResult handlingWhereClauseItems1(List<ESParameter> ESOutParams,int StartIndex)
    {
		HandleResult theResult = new HandleResult();
        String theWhereSQL = "";
        int index = StartIndex;
        if (ESOutParams != null)
        {
            for(ESParameter theClause : ESOutParams)
            {
                if (theClause.Value == null || theClause.Value.toString().trim().isEmpty()==true)
                {
                    switch(theClause.NullToDo)
                    {
                        case None:
                            break;
                        case IsNull:
                            String theFieldName = getFieldName(theClause.ParamName);
                            theWhereSQL += " AND "+theFieldName+" IS NULL ";
                            break;
                        case IsNotNull:
                            String theFieldName1 = getFieldName(theClause.ParamName);
                            theWhereSQL += " AND "+theFieldName1+" IS NOT NULL ";
                            break;
                        case SayNo:
                            theWhereSQL += " AND 2<1 ";
                            break;
                    }
                    continue;
                }
                if (theClause.PassParamType== DbParamType.Where && theClause.Value != null)
                {
                   
                    String theFieldName = getFieldName(theClause.ParamName);
                    //处理In
                    if(theClause.Like == LikeOperater.In)
                    {
                    	if(isArrayListType(theClause.Value))
                    	{
                    		List<Object> theValues = (List<Object>)(theClause.Value);
                    		if(theValues.size()>0)
                    		{
	                    		Class<?> theGenericType = this.getListGenericType(theValues);
		                        if (theGenericType==String.class)
		                        {
		                            String theValueString = toSplitStringStr(theValues,"'");
		                            if(theValueString.isEmpty()==false)
		                            { 
		                               theWhereSQL += String.format(" and %s in (%s) ", theClause.ParamName, theValueString);
		                              
		                            }
		                        }
		                        else
		                        {
		                            String theValueString = toSplitStringStr(theValues,"");
		                            if (theValueString.isEmpty()==false)
		                            {
		                                theWhereSQL += String.format(" and %s in (%s) ", theClause.ParamName, theValueString);
		                            }
		                        }
                    		}
                    	}
                        continue;
                    }
                    else
                    {
                        if (theClause.Value.toString().isEmpty())
                        {
                            continue;
                        }
                        //处理普通类型及子查询
                        if (theClause.SearchType == WhereClauseSearchType.SearchInMaster)
                        {
                        	if(theClause.DataType== Types.VARCHAR
                        			|| theClause.DataType== Types.NVARCHAR)
                        	{
                        	    String theValue = this.formatLikeValue(theClause);
                        	    theClause.Value = theValue;
                        	}
                            theWhereSQL += String.format(" and %s %s :%s ", theClause.ParamName, theClause.Operator, theFieldName + index);
                            theClause.ParamName = theFieldName + index;
                        }
                        else
                        {
                            theWhereSQL += " AND " + theFieldName + " IN(" + theClause.SubSearchSQL + ")";
                        }
                    }
                }
                index++;
            }           
        }
        theResult.SQL = theWhereSQL;
        theResult.Index = index;
        return theResult;
    }
	
	 private HandleResult handlingWhereClauseItems2(List<ESParameter> ESOutParams,int StartIndex)
     {
		 HandleResult theResult = new HandleResult();
	     String theWhereSQL = "";
	     int index = StartIndex;
        
         if (ESOutParams != null)
         {
             if(ESOutParams.size()>0)
             {
                 String theSql = " and (1>1 ";
                 int theCount = 0;
                 for(ESParameter thePm : ESOutParams)
                 {
                     if (thePm.PassParamType == DbParamType.Where && thePm.Value != null)
                     {
                         if (thePm.Value.toString().isEmpty())
                         {
                             continue;
                         }
                         theCount++;
                         String theFieldName = getFieldName(thePm.ParamName);
                         if (thePm.SearchType == WhereClauseSearchType.SearchInMaster)
                         {
 
                             String theValue = "%"+thePm.Value+"%";
                             thePm.Value = theValue;
                             theSql += String.format(" OR %s  %s  :%s  ", thePm.ParamName, thePm.Operator, theFieldName + index);
                             thePm.ParamName = theFieldName + index;
                         }
                         else
                         {
                             theSql += " OR " + theFieldName + " IN(" + thePm.SubSearchSQL + ")";
                         }
                     }
                     index++;
                 }
                 theSql += ")";
                 if (theCount > 0)
                 {
                     theWhereSQL += " " + theSql;
                 }
             }
         }
         theResult.SQL = theWhereSQL;
         theResult.Index = index;
         return theResult;
     }
	 
	private class HandleResult
	{
		public String SQL;
		public int Index;
	}
	
	public String toSplitStringStr(List<Object> Values,String SplitStr)
	{
		if(Values==null)
		{
			return "";
		}
		String theResult ="";
		for(Object obj : Values)
		{
			if(theResult.isEmpty())
			{
				theResult = SplitStr+ obj+SplitStr;
			}
			else
			{
				theResult = theResult+","+SplitStr+ obj+SplitStr;
			}
		}
		return theResult;
	}
}
