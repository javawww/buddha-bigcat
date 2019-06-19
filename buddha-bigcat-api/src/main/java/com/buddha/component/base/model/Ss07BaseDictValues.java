package com.buddha.component.base.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

/**
 * SS07基本字典值表(SS07_BASE_DICT_VALUES) 
 */
@Table(name = "SS07_BASE_DICT_VALUES")
public class Ss07BaseDictValues extends EntityBase {
    public Ss07BaseDictValues() {

    }
		/**
	*值代码
	*/
		@Id
		
    @Column(name = "SS07_VAL_CODE")
    private String ss07ValCode;
		/**
	*值名称
	*/
		
    @Column(name = "SS07_VAL_NAME")
    private String ss07ValName;
		/**
	*字典编码
	*/
		
    @Column(name = "SS07_DICT_CODE")
    private String ss07DictCode;
		/**
	*企业编码
	*/
		
    @Column(name = "SS07_NO1")
    private String ss07No1;
		/**
	*行业编码
	*/
		
    @Column(name = "SS07_NO2")
    private String ss07No2;
		/**
	*国际编码
	*/
		
    @Column(name = "SS07_NO4")
    private String ss07No4;
		/**
	*国家编码
	*/
		
    @Column(name = "SS07_NO3")
    private String ss07No3;
		/**
	*排序序号
	*/
		
    @Column(name = "SS07_ORDER_NO")
    private int ss07OrderNo;
		/**
	*有效标志
	*/
		
    @Column(name = "SS07_IS_VALID")
    private int ss07IsValid;
		/**
	*定量值2
	*/
		
    @Column(name = "SS07_DICT_VALUE2")
    private float ss07DictValue2;
		/**
	*定量值1
	*/
		
    @Column(name = "SS07_DICT_VALUE1")
    private float ss07DictValue1;
		/**
	*创建人
	*/
		
    @Column(name = "SS07_CRT_USR")
    private String ss07CrtUsr;
		/**
	*创建时间
	*/
		
    @Column(name = "SS07_CRT_TIME")
    private Date ss07CrtTime;
		/**
	*最后修改人
	*/
		
    @Column(name = "SS07_CHG_USR")
    private String ss07ChgUsr;
		/**
	*最后修改时间
	*/
		
    @Column(name = "SS07_CHG_TIME")
    private Date ss07ChgTime;
		/**
	*备注
	*/
		
    @Column(name = "SS07_DICT_DESC")
    private String ss07DictDesc;
		/**
	*组织ID
	*/
		
    @Column(name = "SS07_ORG_ID")
    private String ss07OrgId;
		/**
	*企业ID
	*/
		
    @Column(name = "SS07_ENT_ID")
    private int ss07EntId;
		/**
	*仅系统可用
	*/
		
    @Column(name = "SS07_SYS_ONLY")
    private int ss07SysOnly;
		/**
	*内部ID
	*/
		
    @Column(name = "SS07_INNER_ID")
    private int ss07InnerId;
		/**
	*图标1
	*/
		
    @Column(name = "SS07_LOGO_URL_1")
    private String ss07LogoUrl1;
		/**
	*图标2
	*/
		
    @Column(name = "SS07_LOGO_URL_2")
    private String ss07LogoUrl2;
		/**
	*图标3
	*/
		
    @Column(name = "SS07_LOGO_URL_3")
    private String ss07LogoUrl3;
		/**
	*缺省值
	*/
		
    @Column(name = "SS07_IS_DEFAULT")
    private int ss07IsDefault;
	

		 
	public String getSs07ValCode() {
        return ss07ValCode;
    }

    public void setSs07ValCode(String ss07ValCode) {
        this.ss07ValCode = ss07ValCode;
				this.setKeyValue(ss07ValCode);
				    }
		 
	public String getSs07ValName() {
        return ss07ValName;
    }

    public void setSs07ValName(String ss07ValName) {
        this.ss07ValName = ss07ValName;
				    }
		 
	public String getSs07DictCode() {
        return ss07DictCode;
    }

    public void setSs07DictCode(String ss07DictCode) {
        this.ss07DictCode = ss07DictCode;
				    }
		 
	public String getSs07No1() {
        return ss07No1;
    }

    public void setSs07No1(String ss07No1) {
        this.ss07No1 = ss07No1;
				    }
		 
	public String getSs07No2() {
        return ss07No2;
    }

    public void setSs07No2(String ss07No2) {
        this.ss07No2 = ss07No2;
				    }
		 
	public String getSs07No4() {
        return ss07No4;
    }

    public void setSs07No4(String ss07No4) {
        this.ss07No4 = ss07No4;
				    }
		 
	public String getSs07No3() {
        return ss07No3;
    }

    public void setSs07No3(String ss07No3) {
        this.ss07No3 = ss07No3;
				    }
		 
	public int getSs07OrderNo() {
        return ss07OrderNo;
    }

    public void setSs07OrderNo(int ss07OrderNo) {
        this.ss07OrderNo = ss07OrderNo;
				    }
		 
	public int getSs07IsValid() {
        return ss07IsValid;
    }

    public void setSs07IsValid(int ss07IsValid) {
        this.ss07IsValid = ss07IsValid;
				    }
		 
	public float getSs07DictValue2() {
        return ss07DictValue2;
    }

    public void setSs07DictValue2(float ss07DictValue2) {
        this.ss07DictValue2 = ss07DictValue2;
				    }
		 
	public float getSs07DictValue1() {
        return ss07DictValue1;
    }

    public void setSs07DictValue1(float ss07DictValue1) {
        this.ss07DictValue1 = ss07DictValue1;
				    }
		 
	public String getSs07CrtUsr() {
        return ss07CrtUsr;
    }

    public void setSs07CrtUsr(String ss07CrtUsr) {
        this.ss07CrtUsr = ss07CrtUsr;
				    }
		 
	public Date getSs07CrtTime() {
        return ss07CrtTime;
    }

    public void setSs07CrtTime(Date ss07CrtTime) {
        this.ss07CrtTime = ss07CrtTime;
				    }
		 
	public String getSs07ChgUsr() {
        return ss07ChgUsr;
    }

    public void setSs07ChgUsr(String ss07ChgUsr) {
        this.ss07ChgUsr = ss07ChgUsr;
				    }
		 
	public Date getSs07ChgTime() {
        return ss07ChgTime;
    }

    public void setSs07ChgTime(Date ss07ChgTime) {
        this.ss07ChgTime = ss07ChgTime;
				    }
		 
	public String getSs07DictDesc() {
        return ss07DictDesc;
    }

    public void setSs07DictDesc(String ss07DictDesc) {
        this.ss07DictDesc = ss07DictDesc;
				    }
		 
	public String getSs07OrgId() {
        return ss07OrgId;
    }

    public void setSs07OrgId(String ss07OrgId) {
        this.ss07OrgId = ss07OrgId;
				    }
		 
	public int getSs07EntId() {
        return ss07EntId;
    }

    public void setSs07EntId(int ss07EntId) {
        this.ss07EntId = ss07EntId;
				    }
		 
	public int getSs07SysOnly() {
        return ss07SysOnly;
    }

    public void setSs07SysOnly(int ss07SysOnly) {
        this.ss07SysOnly = ss07SysOnly;
				    }
		 
	public int getSs07InnerId() {
        return ss07InnerId;
    }

    public void setSs07InnerId(int ss07InnerId) {
        this.ss07InnerId = ss07InnerId;
				    }
		 
	public String getSs07LogoUrl1() {
        return ss07LogoUrl1;
    }

    public void setSs07LogoUrl1(String ss07LogoUrl1) {
        this.ss07LogoUrl1 = ss07LogoUrl1;
				    }
		 
	public String getSs07LogoUrl2() {
        return ss07LogoUrl2;
    }

    public void setSs07LogoUrl2(String ss07LogoUrl2) {
        this.ss07LogoUrl2 = ss07LogoUrl2;
				    }
		 
	public String getSs07LogoUrl3() {
        return ss07LogoUrl3;
    }

    public void setSs07LogoUrl3(String ss07LogoUrl3) {
        this.ss07LogoUrl3 = ss07LogoUrl3;
				    }
		 
	public int getSs07IsDefault() {
        return ss07IsDefault;
    }

    public void setSs07IsDefault(int ss07IsDefault) {
        this.ss07IsDefault = ss07IsDefault;
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