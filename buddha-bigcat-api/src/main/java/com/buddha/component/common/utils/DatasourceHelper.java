package com.buddha.component.common.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.support.JdbcUtils;

public class DatasourceHelper {
    public static List<Map<String,Object>> ResultSetToList(ResultSet rs) throws SQLException {
        List<Map<String,Object>> results=new ArrayList<Map<String,Object>>();
        ResultSetMetaData theRsmd = rs.getMetaData();
        int colCount=theRsmd.getColumnCount();

        List<String> colNameList=new ArrayList<String>();
        for(int i=0;i<colCount;i++){
            colNameList.add(theRsmd.getColumnLabel(i+1));
            //colNameList.add(theRsmd.getColumnName(i+1));
        }
        while(rs.next()){
            Map theMap=new HashMap<String, Object>();
            results.add(theMap);
            for(int i=0;i<colCount;i++){
                String theKey=colNameList.get(i);
                Object theValue=JdbcUtils.getResultSetValue(rs,i+1);
                theMap.put(theKey, theValue);
            }
        }
        return results;
    }
    public static void setPreparedStatementParam(PreparedStatement ps, int ParamIndex, Object ParamVal, int SqlType) throws Exception
    {
        StatementCreatorUtils.setParameterValue(ps,ParamIndex,SqlType,ParamVal);
        /*
        if(ParamVal==null)
        {
            ps.setNull(ParamIndex,SqlType);
            return;
        }
        switch (SqlType)
        {
            case Types.ARRAY:
                ps.setArray(ParamIndex,(Array)ParamVal);
                break;
            case Types.BIGINT:
                ps.setBigDecimal(ParamIndex, (BigDecimal) ParamVal);
                break;
            case Types.BINARY:
                byte[] theVal=(byte[])ParamVal;
                InputStream theIS = new ByteInputStream(theVal,theVal.length);
                ps.setBinaryStream(ParamIndex,theIS);
                break;
            case Types.BLOB:
                byte[] theVal2=(byte[])ParamVal;
                InputStream theIS2 = new ByteInputStream(theVal2,theVal2.length);
                ps.setBinaryStream(ParamIndex,theIS2);
                break;
            case Types.BOOLEAN:
                ps.setBoolean(ParamIndex,(boolean)ParamVal);
                break;
            case Types.CLOB:
                ps.setClob(ParamIndex,new StringReader(ParamVal.toString()));
                break;
            case Types.DATE:
                ps.setDate(ParamIndex,ConvertUtil.utilDateToSqlDate((java.util.Date)ParamVal));
                break;
            case Types.DECIMAL:
                ps.setFloat(ParamIndex,(float) ParamVal);
                break;
            case Types.DOUBLE:
                ps.setDouble(ParamIndex,(double) ParamVal);
                break;
            case Types.FLOAT:
                ps.setFloat(ParamIndex,(float) ParamVal);
                break;
            case Types.INTEGER:
                ps.setInt(ParamIndex,(int) ParamVal);
                break;
            case Types.NUMERIC:
                ps.setFloat(ParamIndex,(float) ParamVal);
                break;
            case Types.TIME:
                ps.setTime(ParamIndex,ConvertUtil.utilTimeToSqlTime((java.util.Date)ParamVal));
                break;
            case Types.TIMESTAMP:
                ps.setTimestamp(ParamIndex,ConvertUtil.utilTimeToSqlTimespan((java.util.Date)ParamVal));
                break;
            default:
                ps.setString(ParamIndex,ParamVal.toString());
                break;
        }
        */
    }
}
