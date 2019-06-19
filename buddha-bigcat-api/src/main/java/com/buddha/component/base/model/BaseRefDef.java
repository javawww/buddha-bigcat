package com.buddha.component.base.model;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

import java.util.Date;

/**
 * 参照定义表(BASE_REF_DEF) 
 */
@Table(name = "BASE_REF_DEF")
public class BaseRefDef extends EntityBase {
    public BaseRefDef() {

    }
		/**
	*参照代码
	*/
		@Id
		
    @Column(name = "REF_CODE")
    private String refCode;
		/**
	*参照名称
	*/
		
    @Column(name = "REF_NAME")
    private String refName;
		/**
	*取值视图
	*/
		
    @Column(name = "DB_VIEW")
    private String dbView;
		/**
	*是否缓存
	*/
		
    @Column(name = "CACHED")
    private int cached;
		/**
	*键字段
	*/
		
    @Column(name = "KEY_FIELD")
    private String keyField;
		/**
	*值字段
	*/
		
    @Column(name = "VAL_FIELD")
    private String valField;
		/**
	*逐条加载标志
	*/
		
    @Column(name = "ONE_BY_ONE")
    private int oneByOne;
		/**
	*备注
	*/
		
    @Column(name = "REF_MEMO")
    private String refMemo;
		/**
	*失效时间
	*/
		
    @Column(name = "TIME_OUT")
    private int timeOut;
		/**
	*资料状态
	*/
		
    @Column(name = "STATUS")
    private String status;
		/**
	*创建人
	*/
		
    @Column(name = "CRT_USR")
    private String crtUsr;
		/**
	*创建时间
	*/
		
    @Column(name = "CRT_TIME")
    private Date crtTime;
		/**
	*最后修改人
	*/
		
    @Column(name = "CHG_USR")
    private String chgUsr;
		/**
	*最后修改时间
	*/
		
    @Column(name = "CHG_TIME")
    private Date chgTime;
	

		 
	public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
				this.setKeyValue(refCode);
				    }
		 
	public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
				    }
		 
	public String getDbView() {
        return dbView;
    }

    public void setDbView(String dbView) {
        this.dbView = dbView;
				    }
		 
	public int getCached() {
        return cached;
    }

    public void setCached(int cached) {
        this.cached = cached;
				    }
		 
	public String getKeyField() {
        return keyField;
    }

    public void setKeyField(String keyField) {
        this.keyField = keyField;
				    }
		 
	public String getValField() {
        return valField;
    }

    public void setValField(String valField) {
        this.valField = valField;
				    }
		 
	public int getOneByOne() {
        return oneByOne;
    }

    public void setOneByOne(int oneByOne) {
        this.oneByOne = oneByOne;
				    }
		 
	public String getRefMemo() {
        return refMemo;
    }

    public void setRefMemo(String refMemo) {
        this.refMemo = refMemo;
				    }
		 
	public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
				    }
		 
	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.setStatusValue(status);
	    this.status = status;
	}
		 
	public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
				    }
		 
	public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
				    }
		 
	public String getChgUsr() {
        return chgUsr;
    }

    public void setChgUsr(String chgUsr) {
        this.chgUsr = chgUsr;
				    }
		 
	public Date getChgTime() {
        return chgTime;
    }

    public void setChgTime(Date chgTime) {
        this.chgTime = chgTime;
				    }
	    
    @Override
	public RouteType getModelRouteType() {
		return RouteType.ENT;
	}

	@Override
	public Integer getModelRouteId() {
		return 0;
	}

}