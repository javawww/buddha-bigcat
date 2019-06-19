package com.buddha.component.base.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

/**
 * 基本字典表(BASE_DICT_NAMES) 
 */
@Table(name = "BASE_DICT_NAMES")
public class BaseDictNames extends EntityBase {
    public BaseDictNames() {

    }
		/**
	*字典编码
	*/
		@Id
		
    @Column(name = "DICT_CODE")
    private String dictCode;
		/**
	*字典名称
	*/
		
    @Column(name = "DICT_NAME")
    private String dictName;
		/**
	*用途
	*/
		
    @Column(name = "DICT_DESC")
    private String dictDesc;
		/**
	*字典排序
	*/
		
    @Column(name = "SORT_NO")
    private int sortNo;
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
		/**
	*状态
	*/
		
    @Column(name = "STATUS")
    private String status;
	

		 
	public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
				this.setKeyValue(dictCode);
				    }
		 
	public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
				    }
		 
	public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
				    }
		 
	public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
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
		 
	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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