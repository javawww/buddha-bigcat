package com.buddha.param;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色参数
 * @author chuck
 *
 */
@Getter
@Setter
public class SystemRolesParam extends BaseParam {

	/**
	 * 角色名称
	 */
	private String rolesName;
    /**
     * 角色描述
     */
	private String content;
	/**
	 * 角色CODE
	 */
	private String roleCode;
	/**
	 * 资源数组
	 */
	private Integer[] resourcesIdArr;
	
	/**
     * 删除
     */
	private Integer isDel;
    /**
     * 创建人id
     */
	private String createId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;
}
