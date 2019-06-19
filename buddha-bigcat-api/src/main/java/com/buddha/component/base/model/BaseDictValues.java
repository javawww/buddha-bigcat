package com.buddha.component.base.model;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

import java.util.Date;

/**
 * 基本字典值表(BASE_DICT_VALUES) 
 */
@Table(name = "BASE_DICT_VALUES")
public class BaseDictValues extends EntityBase {
    public BaseDictValues() {

    }
		/**
	*值代码
	*/
		@Id
		
    @Column(name = "VAL_CODE")
    private String valCode;
		/**
	*字典编码
	*/
		
    @Column(name = "DICT_CODE")
    private String dictCode;
		/**
	*值名称
	*/
		
    @Column(name = "VAL_NAME")
    private String valName;
		/**
	*企业编码
	*/
		
    @Column(name = "NO1")
    private String no1;
		/**
	*行业编码
	*/
		
    @Column(name = "NO2")
    private String no2;
		/**
	*国家编码
	*/
		
    @Column(name = "NO3")
    private String no3;
		/**
	*国际编码
	*/
		
    @Column(name = "NO4")
    private String no4;
		/**
	*排序序号
	*/
		
    @Column(name = "ORDER_NO")
    private int orderNo;
		/**
	*定量值2
	*/
		
    @Column(name = "DICT_VALUE2")
    private float dictValue2;
		/**
	*定量值1
	*/
		
    @Column(name = "DICT_VALUE1")
    private float dictValue1;
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
	*备注
	*/
		
    @Column(name = "DICT_DESC")
    private String dictDesc;
		/**
	*有效标志
	*/
		
    @Column(name = "IS_VALID")
    private int isValid;
	

		 
	public String getValCode() {
        return valCode;
    }

    public void setValCode(String valCode) {
        this.valCode = valCode;
				this.setKeyValue(valCode);
				    }
		 
	public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
				    }
		 
	public String getValName() {
        return valName;
    }

    public void setValName(String valName) {
        this.valName = valName;
				    }
		 
	public String getNo1() {
        return no1;
    }

    public void setNo1(String no1) {
        this.no1 = no1;
				    }
		 
	public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
				    }
		 
	public String getNo3() {
        return no3;
    }

    public void setNo3(String no3) {
        this.no3 = no3;
				    }
		 
	public String getNo4() {
        return no4;
    }

    public void setNo4(String no4) {
        this.no4 = no4;
				    }
		 
	public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
				    }
		 
	public float getDictValue2() {
        return dictValue2;
    }

    public void setDictValue2(float dictValue2) {
        this.dictValue2 = dictValue2;
				    }
		 
	public float getDictValue1() {
        return dictValue1;
    }

    public void setDictValue1(float dictValue1) {
        this.dictValue1 = dictValue1;
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
		 
	public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
				    }
		 
	public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
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