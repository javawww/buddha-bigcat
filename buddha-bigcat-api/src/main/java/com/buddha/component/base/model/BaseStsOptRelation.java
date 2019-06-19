package com.buddha.component.base.model;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

import java.util.Date; 


/**
 * 状态操作关系设置表(BASE_STS_OPT_RELATION) 
 */
@Table(name = "BASE_STS_OPT_RELATION")
public class BaseStsOptRelation extends EntityBase {
    public BaseStsOptRelation() {

    }
		/**
	*关系ID
	*/
		@Id
		
    @Column(name = "RELATION_ID")
    private String relationId;
		/**
	*状态对象代码
	*/
		
    @Column(name = "OBJ_CODE")
    private String objCode;
		/**
	*状态ID
	*/
		
    @Column(name = "STS_CODE")
    private String stsCode;
		/**
	*操作ID
	*/
		
    @Column(name = "OPT_CODE")
    private String optCode;
		/**
	*备注
	*/
		
    @Column(name = "MEMO")
    private String memo;
		/**
	*成功后状态
	*/
		
    @Column(name = "NEXT_STS_S")
    private String nextStsS;
		/**
	*失败后状态
	*/
		
    @Column(name = "NEXT_STS_F")
    private String nextStsF;
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
	

		 
	public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
				this.setKeyValue(relationId);
				    }
		 
	public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
				    }
		 
	public String getStsCode() {
        return stsCode;
    }

    public void setStsCode(String stsCode) {
        this.stsCode = stsCode;
				    }
		 
	public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
				    }
		 
	public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
				    }
		 
	public String getNextStsS() {
        return nextStsS;
    }

    public void setNextStsS(String nextStsS) {
        this.nextStsS = nextStsS;
				    }
		 
	public String getNextStsF() {
        return nextStsF;
    }

    public void setNextStsF(String nextStsF) {
        this.nextStsF = nextStsF;
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