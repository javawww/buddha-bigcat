package com.buddha.service.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buddha.component.common.dto.Options;
import com.buddha.mapper.loan.LoanMarketProductMapper;
import com.buddha.param.LoanMarketProductParam;
import com.buddha.pojo.loan.LoanMarketProduct;

 /**
 * 
 * 贷款超市 服务实现类
 *
 * ${jinghao}
 *
 * CopyRight (C) 2018 ShenZhen LoveJava Information Technology Co.Ltd All
 * Rights Reserved.<br />
 * 小程序开发，企业系统开发，安卓苹果APP开发，服务器部署<br />
 * 其他任何个人、公司不得使用、复制、传播、修改或商业使用。 <br />
 * ${jinghao}
 * 
 * 
 * 
 * @作者 系统生成
 * @时间 2019-06-08
 * @版权 一群热爱技术的程序猿
 * @邮箱 1003632308@qq.com
 * @QQ技术群  327947585
 * @商务微信号  javawww
 */
@Service
@DS("bigcat_loan")
public class LoanMarketProductService extends ServiceImpl<LoanMarketProductMapper, LoanMarketProduct> {
	
	@Autowired
	private LoanMarketProductMapper loanMarketProductMapper;
	
	public List<Options> queryOptions(LoanMarketProductParam param) {
		return loanMarketProductMapper.queryOptions(param);
	}
}
