package com.buddha.component.base.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

/**
 * SS29对象状态操作关系设置表(SS29_STATUS_OPT_RELATION) 
 */
@Table(name = "SS29_STATUS_OPT_RELATION")
public class Ss29StatusOptRelation extends EntityBase {
    public Ss29StatusOptRelation() {

    }
		/**
	*关系ID
	*/
		@Id
		
    @Column(name = "SS29_RELATION_ID")
    private String ss29RelationId;
		/**
	*企业ID
	*/
		
    @Column(name = "SS29_ENT_ID")
    private int ss29EntId;
		/**
	*组织ID
	*/
		
    @Column(name = "SS29_ORG_ID")
    private String ss29OrgId;
		/**
	*对象ID
	*/
		
    @Column(name = "SS29_OBJ_ID")
    private String ss29ObjId;
		/**
	*状态ID
	*/
		
    @Column(name = "SS29_STATUS_ID")
    private String ss29StatusId;
		/**
	*操作ID
	*/
		
    @Column(name = "SS29_OPT_ID")
    private String ss29OptId;
		/**
	*备注
	*/
		
    @Column(name = "SS29_MEMO")
    private String ss29Memo;
		/**
	*成功后状态
	*/
		
    @Column(name = "SS29_NEXT_STATUS_1")
    private String ss29NextStatus1;
		/**
	*失败后状态
	*/
		
    @Column(name = "SS29_NEXT_STATUS_2")
    private String ss29NextStatus2;
		/**
	*下一个状态3
	*/
		
    @Column(name = "SS29_NEXT_STATUS_3")
    private String ss29NextStatus3;
		/**
	*下一个状态4
	*/
		
    @Column(name = "SS29_NEXT_STATUS_4")
    private String ss29NextStatus4;
		/**
	*创建人
	*/
		
    @Column(name = "SS29_CRT_USR")
    private String ss29CrtUsr;
		/**
	*创建时间
	*/
		
    @Column(name = "SS29_CRT_TIME")
    private Date ss29CrtTime;
		/**
	*最后修改人
	*/
		
    @Column(name = "SS29_CHG_USR")
    private String ss29ChgUsr;
		/**
	*最后修改时间
	*/
		
    @Column(name = "SS29_CHG_TIME")
    private Date ss29ChgTime;
		/**
	*有效标志
	*/
		
    @Column(name = "SS29_IS_VALID")
    private int ss29IsValid;
	

		 
	public String getSs29RelationId() {
        return ss29RelationId;
    }

    public void setSs29RelationId(String ss29RelationId) {
        this.ss29RelationId = ss29RelationId;
				this.setKeyValue(ss29RelationId);
				    }
		 
	public int getSs29EntId() {
        return ss29EntId;
    }

    public void setSs29EntId(int ss29EntId) {
        this.ss29EntId = ss29EntId;
				    }
		 
	public String getSs29OrgId() {
        return ss29OrgId;
    }

    public void setSs29OrgId(String ss29OrgId) {
        this.ss29OrgId = ss29OrgId;
				    }
		 
	public String getSs29ObjId() {
        return ss29ObjId;
    }

    public void setSs29ObjId(String ss29ObjId) {
        this.ss29ObjId = ss29ObjId;
				    }
		 
	public String getSs29StatusId() {
        return ss29StatusId;
    }

    public void setSs29StatusId(String ss29StatusId) {
        this.ss29StatusId = ss29StatusId;
						this.setStatusValue(ss29StatusId);
		    }
		 
	public String getSs29OptId() {
        return ss29OptId;
    }

    public void setSs29OptId(String ss29OptId) {
        this.ss29OptId = ss29OptId;
				    }
		 
	public String getSs29Memo() {
        return ss29Memo;
    }

    public void setSs29Memo(String ss29Memo) {
        this.ss29Memo = ss29Memo;
				    }
		 
	public String getSs29NextStatus1() {
        return ss29NextStatus1;
    }

    public void setSs29NextStatus1(String ss29NextStatus1) {
        this.ss29NextStatus1 = ss29NextStatus1;
						this.setStatusValue(ss29NextStatus1);
		    }
		 
	public String getSs29NextStatus2() {
        return ss29NextStatus2;
    }

    public void setSs29NextStatus2(String ss29NextStatus2) {
        this.ss29NextStatus2 = ss29NextStatus2;
						this.setStatusValue(ss29NextStatus2);
		    }
		 
	public String getSs29NextStatus3() {
        return ss29NextStatus3;
    }

    public void setSs29NextStatus3(String ss29NextStatus3) {
        this.ss29NextStatus3 = ss29NextStatus3;
						this.setStatusValue(ss29NextStatus3);
		    }
		 
	public String getSs29NextStatus4() {
        return ss29NextStatus4;
    }

    public void setSs29NextStatus4(String ss29NextStatus4) {
        this.ss29NextStatus4 = ss29NextStatus4;
						this.setStatusValue(ss29NextStatus4);
		    }
		 
	public String getSs29CrtUsr() {
        return ss29CrtUsr;
    }

    public void setSs29CrtUsr(String ss29CrtUsr) {
        this.ss29CrtUsr = ss29CrtUsr;
				    }
		 
	public Date getSs29CrtTime() {
        return ss29CrtTime;
    }

    public void setSs29CrtTime(Date ss29CrtTime) {
        this.ss29CrtTime = ss29CrtTime;
				    }
		 
	public String getSs29ChgUsr() {
        return ss29ChgUsr;
    }

    public void setSs29ChgUsr(String ss29ChgUsr) {
        this.ss29ChgUsr = ss29ChgUsr;
				    }
		 
	public Date getSs29ChgTime() {
        return ss29ChgTime;
    }

    public void setSs29ChgTime(Date ss29ChgTime) {
        this.ss29ChgTime = ss29ChgTime;
				    }
		 
	public int getSs29IsValid() {
        return ss29IsValid;
    }

    public void setSs29IsValid(int ss29IsValid) {
        this.ss29IsValid = ss29IsValid;
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