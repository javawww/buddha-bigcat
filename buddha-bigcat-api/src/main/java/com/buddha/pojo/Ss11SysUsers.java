package com.buddha.pojo;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.model.EntityBase;
import com.buddha.component.base.pubdef.RouteType;

/**
 * SS11系统用户表(SS11_SYS_USERS) 
 */
@Table(name = "SS11_SYS_USERS")
public class Ss11SysUsers extends EntityBase {
    public Ss11SysUsers() {

    }
		/**
	*用户ID
	*/
		@Id
		
    @Column(name = "SS11_USR_ID")
    private String ss11UsrId;
		/**
	*用户类型
	*/
		
    @Column(name = "SS11_USR_TYPE")
    private String ss11UsrType;
		/**
	*用户姓名
	*/
		
    @Column(name = "SS11_USR_NAME")
    private String ss11UsrName;
		/**
	*登录名
	*/
		
    @Column(name = "SS11_LOGIN_NAME")
    private String ss11LoginName;
		/**
	*密码方式
	*/
		
    @Column(name = "SS11_PWD_TYPE")
    private String ss11PwdType;
		/**
	*密码
	*/
		
    @Column(name = "SS11_USR_PWD")
    private String ss11UsrPwd;
		/**
	*起始日期
	*/
		
    @Column(name = "SS11_START_DATE")
    private Date ss11StartDate;
		/**
	*截止日期
	*/
		
    @Column(name = "SS11_END_DATE")
    private Date ss11EndDate;
		/**
	*电子信箱
	*/
		
    @Column(name = "SS11_USR_EMAIL")
    private String ss11UsrEmail;
		/**
	*用户识别卡
	*/
		
    @Column(name = "SS11_IDENTIFY_CARD")
    private String ss11IdentifyCard;
		/**
	*印章类型
	*/
		
    @Column(name = "SS11_STAMP_TYPE")
    private String ss11StampType;
		/**
	*印章地址
	*/
		
    @Column(name = "SS11_STAMP_URL")
    private String ss11StampUrl;
		/**
	*用户描述
	*/
		
    @Column(name = "SS11_USR_DESC")
    private String ss11UsrDesc;
		/**
	*用户状态
	*/
		
    @Column(name = "SS11_STATUS")
    private String ss11Status;
		/**
	*用户手机
	*/
		
    @Column(name = "SS11_PHONE")
    private String ss11Phone;
		/**
	*备注
	*/
		
    @Column(name = "SS11_MEMO")
    private String ss11Memo;
		/**
	*创建人
	*/
		
    @Column(name = "SS11_CRT_USR")
    private String ss11CrtUsr;
		/**
	*创建时间
	*/
		
    @Column(name = "SS11_CRT_TIME")
    private Date ss11CrtTime;
		/**
	*最后修改人
	*/
		
    @Column(name = "SS11_CHG_USR")
    private String ss11ChgUsr;
		/**
	*最后修改时间
	*/
		
    @Column(name = "SS11_CHG_TIME")
    private Date ss11ChgTime;
		/**
	*同步标志
	*/
		
    @Column(name = "SS11_SYN_SIGN")
    private String ss11SynSign;
		/**
	*企业ID
	*/
		
    @Column(name = "SS11_ENT_ID")
    private int ss11EntId;
		/**
	*组织ID
	*/
		
    @Column(name = "SS11_ORG_ID")
    private String ss11OrgId;
		/**
	*在线标志
	*/
		
    @Column(name = "SS11_ONLINE_SIGN")
    private int ss11OnlineSign;
		/**
	*手机验证
	*/
		
    @Column(name = "SS11_PHONE_VERIFY")
    private int ss11PhoneVerify;
		/**
	*账户ID
	*/
		
    @Column(name = "SS11_ACCT_ID")
    private String ss11AcctId;
		/**
	*头像地址
	*/
		
    @Column(name = "SS11_LOGO_URL")
    private String ss11LogoUrl;
		/**
	*公司名称
	*/
		
    @Column(name = "SS11_COMPANY_NAME")
    private String ss11CompanyName;
		/**
	*职位名称
	*/
		
    @Column(name = "SS11_POSITION_NAME")
    private String ss11PositionName;
		/**
	*用户级别
	*/
		
    @Column(name = "SS11_USR_CLASS")
    private String ss11UsrClass;
		/**
	*部门ID
	*/
		
    @Column(name = "SS11_DEPT_ID")
    private String ss11DeptId;
		/**
	*微信ID
	*/
		
    @Column(name = "SS11_WX_OPENER_ID")
    private String ss11WxOpenerId;
		/**
	*AD目录标识
	*/
		
    @Column(name = "SS11_AD_ID")
    private String ss11AdId;
		/**
	*AD部门
	*/
		
    @Column(name = "SS11_AD_DEPT_NAME")
    private String ss11AdDeptName;
		/**
	*AD对象id
	*/
		
    @Column(name = "SS11_AD_OBJECTSID")
    private String ss11AdObjectsid;
		/**
	*微信账户id
	*/
		
    @Column(name = "SS11_WX_ACCT_ID")
    private String ss11WxAcctId;
	

		 
	public String getSs11UsrId() {
        return ss11UsrId;
    }

    public void setSs11UsrId(String ss11UsrId) {
        this.ss11UsrId = ss11UsrId;
				this.setKeyValue(ss11UsrId);
				    }
		 
	public String getSs11UsrType() {
        return ss11UsrType;
    }

    public void setSs11UsrType(String ss11UsrType) {
        this.ss11UsrType = ss11UsrType;
				    }
		 
	public String getSs11UsrName() {
        return ss11UsrName;
    }

    public void setSs11UsrName(String ss11UsrName) {
        this.ss11UsrName = ss11UsrName;
				    }
		 
	public String getSs11LoginName() {
        return ss11LoginName;
    }

    public void setSs11LoginName(String ss11LoginName) {
        this.ss11LoginName = ss11LoginName;
				    }
		 
	public String getSs11PwdType() {
        return ss11PwdType;
    }

    public void setSs11PwdType(String ss11PwdType) {
        this.ss11PwdType = ss11PwdType;
				    }
		 
	public String getSs11UsrPwd() {
        return ss11UsrPwd;
    }

    public void setSs11UsrPwd(String ss11UsrPwd) {
        this.ss11UsrPwd = ss11UsrPwd;
				    }
		 
	public Date getSs11StartDate() {
        return ss11StartDate;
    }

    public void setSs11StartDate(Date ss11StartDate) {
        this.ss11StartDate = ss11StartDate;
				    }
		 
	public Date getSs11EndDate() {
        return ss11EndDate;
    }

    public void setSs11EndDate(Date ss11EndDate) {
        this.ss11EndDate = ss11EndDate;
				    }
		 
	public String getSs11UsrEmail() {
        return ss11UsrEmail;
    }

    public void setSs11UsrEmail(String ss11UsrEmail) {
        this.ss11UsrEmail = ss11UsrEmail;
				    }
		 
	public String getSs11IdentifyCard() {
        return ss11IdentifyCard;
    }

    public void setSs11IdentifyCard(String ss11IdentifyCard) {
        this.ss11IdentifyCard = ss11IdentifyCard;
				    }
		 
	public String getSs11StampType() {
        return ss11StampType;
    }

    public void setSs11StampType(String ss11StampType) {
        this.ss11StampType = ss11StampType;
				    }
		 
	public String getSs11StampUrl() {
        return ss11StampUrl;
    }

    public void setSs11StampUrl(String ss11StampUrl) {
        this.ss11StampUrl = ss11StampUrl;
				    }
		 
	public String getSs11UsrDesc() {
        return ss11UsrDesc;
    }

    public void setSs11UsrDesc(String ss11UsrDesc) {
        this.ss11UsrDesc = ss11UsrDesc;
				    }
		 
	public String getSs11Status() {
        return ss11Status;
    }

    public void setSs11Status(String ss11Status) {
        this.ss11Status = ss11Status;
						this.setStatusValue(ss11Status);
		    }
		 
	public String getSs11Phone() {
        return ss11Phone;
    }

    public void setSs11Phone(String ss11Phone) {
        this.ss11Phone = ss11Phone;
				    }
		 
	public String getSs11Memo() {
        return ss11Memo;
    }

    public void setSs11Memo(String ss11Memo) {
        this.ss11Memo = ss11Memo;
				    }
		 
	public String getSs11CrtUsr() {
        return ss11CrtUsr;
    }

    public void setSs11CrtUsr(String ss11CrtUsr) {
        this.ss11CrtUsr = ss11CrtUsr;
				    }
		 
	public Date getSs11CrtTime() {
        return ss11CrtTime;
    }

    public void setSs11CrtTime(Date ss11CrtTime) {
        this.ss11CrtTime = ss11CrtTime;
				    }
		 
	public String getSs11ChgUsr() {
        return ss11ChgUsr;
    }

    public void setSs11ChgUsr(String ss11ChgUsr) {
        this.ss11ChgUsr = ss11ChgUsr;
				    }
		 
	public Date getSs11ChgTime() {
        return ss11ChgTime;
    }

    public void setSs11ChgTime(Date ss11ChgTime) {
        this.ss11ChgTime = ss11ChgTime;
				    }
		 
	public String getSs11SynSign() {
        return ss11SynSign;
    }

    public void setSs11SynSign(String ss11SynSign) {
        this.ss11SynSign = ss11SynSign;
				    }
		 
	public int getSs11EntId() {
        return ss11EntId;
    }

    public void setSs11EntId(int ss11EntId) {
        this.ss11EntId = ss11EntId;
				    }
		 
	public String getSs11OrgId() {
        return ss11OrgId;
    }

    public void setSs11OrgId(String ss11OrgId) {
        this.ss11OrgId = ss11OrgId;
				    }
		 
	public int getSs11OnlineSign() {
        return ss11OnlineSign;
    }

    public void setSs11OnlineSign(int ss11OnlineSign) {
        this.ss11OnlineSign = ss11OnlineSign;
				    }
		 
	public int getSs11PhoneVerify() {
        return ss11PhoneVerify;
    }

    public void setSs11PhoneVerify(int ss11PhoneVerify) {
        this.ss11PhoneVerify = ss11PhoneVerify;
				    }
		 
	public String getSs11AcctId() {
        return ss11AcctId;
    }

    public void setSs11AcctId(String ss11AcctId) {
        this.ss11AcctId = ss11AcctId;
				    }
		 
	public String getSs11LogoUrl() {
        return ss11LogoUrl;
    }

    public void setSs11LogoUrl(String ss11LogoUrl) {
        this.ss11LogoUrl = ss11LogoUrl;
				    }
		 
	public String getSs11CompanyName() {
        return ss11CompanyName;
    }

    public void setSs11CompanyName(String ss11CompanyName) {
        this.ss11CompanyName = ss11CompanyName;
				    }
		 
	public String getSs11PositionName() {
        return ss11PositionName;
    }

    public void setSs11PositionName(String ss11PositionName) {
        this.ss11PositionName = ss11PositionName;
				    }
		 
	public String getSs11UsrClass() {
        return ss11UsrClass;
    }

    public void setSs11UsrClass(String ss11UsrClass) {
        this.ss11UsrClass = ss11UsrClass;
				    }
		 
	public String getSs11DeptId() {
        return ss11DeptId;
    }

    public void setSs11DeptId(String ss11DeptId) {
        this.ss11DeptId = ss11DeptId;
				    }
		 
	public String getSs11WxOpenerId() {
        return ss11WxOpenerId;
    }

    public void setSs11WxOpenerId(String ss11WxOpenerId) {
        this.ss11WxOpenerId = ss11WxOpenerId;
				    }
		 
	public String getSs11AdId() {
        return ss11AdId;
    }

    public void setSs11AdId(String ss11AdId) {
        this.ss11AdId = ss11AdId;
				    }
		 
	public String getSs11AdDeptName() {
        return ss11AdDeptName;
    }

    public void setSs11AdDeptName(String ss11AdDeptName) {
        this.ss11AdDeptName = ss11AdDeptName;
				    }
		 
	public String getSs11AdObjectsid() {
        return ss11AdObjectsid;
    }

    public void setSs11AdObjectsid(String ss11AdObjectsid) {
        this.ss11AdObjectsid = ss11AdObjectsid;
				    }
		 
	public String getSs11WxAcctId() {
        return ss11WxAcctId;
    }

    public void setSs11WxAcctId(String ss11WxAcctId) {
        this.ss11WxAcctId = ss11WxAcctId;
				    }
	    

	@Override
    public RouteType getModelRouteType() {
        return RouteType.ENT;
    }

    @Override
    public Integer getModelRouteId() {
        return (Integer)this.ss11EntId;
    }
}