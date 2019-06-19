package com.buddha.component.mybatis;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 
 * 数据库实体对象基础封装类<br />
 * 此类提供常用的对象属性映射
 * 
 * #############################################################################
 * CopyRight (C) 2018 ShenZhen ZhiZaoJianZhu Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 本软件由深圳市智造建筑信息科技有限公司设计开发；未经本公司正式书面同意或授权，<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * #############################################################################
 * 
 * 
 * 
 * @作者 李金亮(HOVER)
 * @时间 2018-7-24
 * @版权 深圳市智造建筑信息科技有限公司(www.icbi.xin)
 */
public abstract class PojoModel<T extends PojoModel<T>> extends Model<T> {

	private static final long serialVersionUID = 1L;
	
	
}
