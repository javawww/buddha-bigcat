package com.buddha.component.base.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

/**
 * SS06基本字典表(SS06_BASE_DICT_NAMES)
 */
@Table(name = "SS06_BASE_DICT_NAMES")
public class Ss06BaseDictNames extends EntityBase {
    public Ss06BaseDictNames() {

    }

    /**
     * 字典编码
     */
    @Id
    @Column(name = "SS06_DICT_CODE")
    private String ss06DictCode;
    /**
     * 字典名称
     */

    @Column(name = "SS06_DICT_NAME")
    private String ss06DictName;
    /**
     * 用途
     */

    @Column(name = "SS06_DICT_DESC")
    private String ss06DictDesc;
    /**
     * 同步标志
     */

    @Column(name = "SS06_SYN_SIGN")
    private String ss06SynSign;
    /**
     * 值类型
     */

    @Column(name = "SS06_VALUE_STYLE")
    private String ss06ValueStyle;
    /**
     * 格式串
     */

    @Column(name = "SS06_FORMAT_STRING")
    private String ss06FormatString;
    /**
     * 字典分类
     */

    @Column(name = "SS06_CAT_CODE")
    private String ss06CatCode;
    /**
     * 取值下限
     */

    @Column(name = "SS06_FROM_VALUE")
    private float ss06FromValue;
    /**
     * 取值上限
     */

    @Column(name = "SS06_TO_VALUE")
    private float ss06ToValue;
    /**
     * 字典排序
     */

    @Column(name = "SS06_SORT_NO")
    private int ss06SortNo;
    /**
     * 创建人
     */

    @Column(name = "SS06_CRT_USR")
    private String ss06CrtUsr;
    /**
     * 创建时间
     */

    @Column(name = "SS06_CRT_TIME")
    private Date ss06CrtTime;
    /**
     * 最后修改人
     */

    @Column(name = "SS06_CHG_USR")
    private String ss06ChgUsr;
    /**
     * 最后修改时间
     */

    @Column(name = "SS06_CHG_TIME")
    private Date ss06ChgTime;
    /**
     * 组织ID
     */

    @Column(name = "SS06_ORG_ID")
    private String ss06OrgId;
    /**
     * 企业ID
     */

    @Column(name = "SS06_ENT_ID")
    private int ss06EntId;
    /**
     * 状态
     */

    @Column(name = "SS06_STATUS")
    private String ss06Status;


    public String getSs06DictCode() {
        return ss06DictCode;

    }

    public void setSs06DictCode(String ss06DictCode) {
        this.ss06DictCode = ss06DictCode;
        this.setKeyValue(ss06DictCode);
    }

    public String getSs06DictName() {
        return ss06DictName;
    }

    public void setSs06DictName(String ss06DictName) {
        this.ss06DictName = ss06DictName;
    }

    public String getSs06DictDesc() {
        return ss06DictDesc;
    }

    public void setSs06DictDesc(String ss06DictDesc) {
        this.ss06DictDesc = ss06DictDesc;
    }

    public String getSs06SynSign() {
        return ss06SynSign;
    }

    public void setSs06SynSign(String ss06SynSign) {
        this.ss06SynSign = ss06SynSign;
    }

    public String getSs06ValueStyle() {
        return ss06ValueStyle;
    }

    public void setSs06ValueStyle(String ss06ValueStyle) {
        this.ss06ValueStyle = ss06ValueStyle;
    }

    public String getSs06FormatString() {
        return ss06FormatString;
    }

    public void setSs06FormatString(String ss06FormatString) {
        this.ss06FormatString = ss06FormatString;
    }

    public String getSs06CatCode() {
        return ss06CatCode;
    }

    public void setSs06CatCode(String ss06CatCode) {
        this.ss06CatCode = ss06CatCode;
    }

    public float getSs06FromValue() {
        return ss06FromValue;
    }

    public void setSs06FromValue(float ss06FromValue) {
        this.ss06FromValue = ss06FromValue;
    }

    public float getSs06ToValue() {
        return ss06ToValue;
    }

    public void setSs06ToValue(float ss06ToValue) {
        this.ss06ToValue = ss06ToValue;
    }

    public int getSs06SortNo() {
        return ss06SortNo;
    }

    public void setSs06SortNo(int ss06SortNo) {
        this.ss06SortNo = ss06SortNo;
    }

    public String getSs06CrtUsr() {
        return ss06CrtUsr;
    }

    public void setSs06CrtUsr(String ss06CrtUsr) {
        this.ss06CrtUsr = ss06CrtUsr;
    }

    public Date getSs06CrtTime() {
        return ss06CrtTime;
    }

    public void setSs06CrtTime(Date ss06CrtTime) {
        this.ss06CrtTime = ss06CrtTime;
    }

    public String getSs06ChgUsr() {
        return ss06ChgUsr;
    }

    public void setSs06ChgUsr(String ss06ChgUsr) {
        this.ss06ChgUsr = ss06ChgUsr;
    }

    public Date getSs06ChgTime() {
        return ss06ChgTime;
    }

    public void setSs06ChgTime(Date ss06ChgTime) {
        this.ss06ChgTime = ss06ChgTime;
    }

    public String getSs06OrgId() {
        return ss06OrgId;
    }

    public void setSs06OrgId(String ss06OrgId) {
        this.ss06OrgId = ss06OrgId;
    }

    public int getSs06EntId() {
        return ss06EntId;
    }

    public void setSs06EntId(int ss06EntId) {
        this.ss06EntId = ss06EntId;
    }

    public String getSs06Status() {
        return ss06Status;
    }

    public void setSs06Status(String ss06Status) {
        this.ss06Status = ss06Status;
        this.setStatusValue(ss06Status);
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