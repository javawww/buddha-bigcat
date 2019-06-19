package com.buddha.component.base.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

/**
 * SS19参照定义表(SS19_REFERENCE_DEF) 
 */
@Table(name = "SS19_REFERENCE_DEF")
public class Ss19ReferenceDef extends EntityBase {
    public Ss19ReferenceDef() {

    }
		/**
	*参照代码
	*/
		@Id
		
    @Column(name = "SS19_CODE")
    private String ss19Code;
		/**
	*所属系统
	*/
		
    @Column(name = "SS19_SYS_CODE")
    private String ss19SysCode;
		/**
	*设定类别
	*/
		
    @Column(name = "SS19_SET_TYPE")
    private String ss19SetType;
		/**
	*参照名称
	*/
		
    @Column(name = "SS19_NAME")
    private String ss19Name;
		/**
	*取值方式
	*/
		
    @Column(name = "SS19_VAL_TYPE")
    private String ss19ValType;
		/**
	*取值参数
	*/
		
    @Column(name = "SS19_PARAM")
    private String ss19Param;
		/**
	*取值视图
	*/
		
    @Column(name = "SS19_VIEW")
    private String ss19View;
		/**
	*资料状态
	*/
		
    @Column(name = "SS19_STATUS")
    private String ss19Status;
		/**
	*状态对象
	*/
		
    @Column(name = "SS19_STATUS_TYPE")
    private String ss19StatusType;
		/**
	*状态字段
	*/
		
    @Column(name = "SS19_STATUS_FIELD")
    private String ss19StatusField;
		/**
	*备注
	*/
		
    @Column(name = "SS19_MEMO")
    private String ss19Memo;
		/**
	*是否缓存
	*/
		
    @Column(name = "SS19_CACHED")
    private int ss19Cached;
		/**
	*键字段
	*/
		
    @Column(name = "SS19_KEY_FIELD")
    private String ss19KeyField;
		/**
	*值字段
	*/
		
    @Column(name = "SS19_VAL_FIELD")
    private String ss19ValField;
		/**
	*显示格式
	*/
		
    @Column(name = "SS19_VIEW_FORMAT")
    private String ss19ViewFormat;
		/**
	*创建人
	*/
		
    @Column(name = "SS19_CRT_USR")
    private String ss19CrtUsr;
		/**
	*创建时间
	*/
		
    @Column(name = "SS19_CRT_TIME")
    private Date ss19CrtTime;
		/**
	*最后修改人
	*/
		
    @Column(name = "SS19_CHG_USR")
    private String ss19ChgUsr;
		/**
	*最后修改时间
	*/
		
    @Column(name = "SS19_CHG_TIME")
    private Date ss19ChgTime;
		/**
	*组织ID
	*/
		
    @Column(name = "SS19_ORG_ID")
    private String ss19OrgId;
		/**
	*企业ID
	*/
		
    @Column(name = "SS19_ENT_ID")
    private int ss19EntId;
		/**
	*组织字段
	*/
		
    @Column(name = "SS19_ORG_FIELD")
    private String ss19OrgField;
		/**
	*企业字段
	*/
		
    @Column(name = "SS19_ENT_FIELD")
    private String ss19EntField;
		/**
	*逐条加载标志
	*/
		
    @Column(name = "SS19_ONE_BY_ONE")
    private int ss19OneByOne;
		/**
	*超时
	*/
		
    @Column(name = "SS19_TIME_OUT")
    private int ss19TimeOut;
	

		 
	public String getSs19Code() {
        return ss19Code;
    }

    public void setSs19Code(String ss19Code) {
        this.ss19Code = ss19Code;
				this.setKeyValue(ss19Code);
				    }
		 
	public String getSs19SysCode() {
        return ss19SysCode;
    }

    public void setSs19SysCode(String ss19SysCode) {
        this.ss19SysCode = ss19SysCode;
				    }
		 
	public String getSs19SetType() {
        return ss19SetType;
    }

    public void setSs19SetType(String ss19SetType) {
        this.ss19SetType = ss19SetType;
				    }
		 
	public String getSs19Name() {
        return ss19Name;
    }

    public void setSs19Name(String ss19Name) {
        this.ss19Name = ss19Name;
				    }
		 
	public String getSs19ValType() {
        return ss19ValType;
    }

    public void setSs19ValType(String ss19ValType) {
        this.ss19ValType = ss19ValType;
				    }
		 
	public String getSs19Param() {
        return ss19Param;
    }

    public void setSs19Param(String ss19Param) {
        this.ss19Param = ss19Param;
				    }
		 
	public String getSs19View() {
        return ss19View;
    }

    public void setSs19View(String ss19View) {
        this.ss19View = ss19View;
				    }
		 
	public String getSs19Status() {
        return ss19Status;
    }

    public void setSs19Status(String ss19Status) {
        this.ss19Status = ss19Status;
						this.setStatusValue(ss19Status);
		    }
		 
	public String getSs19StatusType() {
        return ss19StatusType;
    }

    public void setSs19StatusType(String ss19StatusType) {
        this.ss19StatusType = ss19StatusType;
						this.setStatusValue(ss19StatusType);
		    }
		 
	public String getSs19StatusField() {
        return ss19StatusField;
    }

    public void setSs19StatusField(String ss19StatusField) {
        this.ss19StatusField = ss19StatusField;
						this.setStatusValue(ss19StatusField);
		    }
		 
	public String getSs19Memo() {
        return ss19Memo;
    }

    public void setSs19Memo(String ss19Memo) {
        this.ss19Memo = ss19Memo;
				    }
		 
	public int getSs19Cached() {
        return ss19Cached;
    }

    public void setSs19Cached(int ss19Cached) {
        this.ss19Cached = ss19Cached;
				    }
		 
	public String getSs19KeyField() {
        return ss19KeyField;
    }

    public void setSs19KeyField(String ss19KeyField) {
        this.ss19KeyField = ss19KeyField;
				    }
		 
	public String getSs19ValField() {
        return ss19ValField;
    }

    public void setSs19ValField(String ss19ValField) {
        this.ss19ValField = ss19ValField;
				    }
		 
	public String getSs19ViewFormat() {
        return ss19ViewFormat;
    }

    public void setSs19ViewFormat(String ss19ViewFormat) {
        this.ss19ViewFormat = ss19ViewFormat;
				    }
		 
	public String getSs19CrtUsr() {
        return ss19CrtUsr;
    }

    public void setSs19CrtUsr(String ss19CrtUsr) {
        this.ss19CrtUsr = ss19CrtUsr;
				    }
		 
	public Date getSs19CrtTime() {
        return ss19CrtTime;
    }

    public void setSs19CrtTime(Date ss19CrtTime) {
        this.ss19CrtTime = ss19CrtTime;
				    }
		 
	public String getSs19ChgUsr() {
        return ss19ChgUsr;
    }

    public void setSs19ChgUsr(String ss19ChgUsr) {
        this.ss19ChgUsr = ss19ChgUsr;
				    }
		 
	public Date getSs19ChgTime() {
        return ss19ChgTime;
    }

    public void setSs19ChgTime(Date ss19ChgTime) {
        this.ss19ChgTime = ss19ChgTime;
				    }
		 
	public String getSs19OrgId() {
        return ss19OrgId;
    }

    public void setSs19OrgId(String ss19OrgId) {
        this.ss19OrgId = ss19OrgId;
				    }
		 
	public int getSs19EntId() {
        return ss19EntId;
    }

    public void setSs19EntId(int ss19EntId) {
        this.ss19EntId = ss19EntId;
				    }
		 
	public String getSs19OrgField() {
        return ss19OrgField;
    }

    public void setSs19OrgField(String ss19OrgField) {
        this.ss19OrgField = ss19OrgField;
				    }
		 
	public String getSs19EntField() {
        return ss19EntField;
    }

    public void setSs19EntField(String ss19EntField) {
        this.ss19EntField = ss19EntField;
				    }
		 
	public int getSs19OneByOne() {
        return ss19OneByOne;
    }

    public void setSs19OneByOne(int ss19OneByOne) {
        this.ss19OneByOne = ss19OneByOne;
				    }
		 
	public int getSs19TimeOut() {
        return ss19TimeOut;
    }

    public void setSs19TimeOut(int ss19TimeOut) {
        this.ss19TimeOut = ss19TimeOut;
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