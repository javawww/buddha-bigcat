package com.buddha.mapper.loan;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buddha.component.common.dto.Options;
import com.buddha.param.LoanMarketProductParam;
import com.buddha.pojo.loan.LoanMarketProduct;

  /**
 * 
 * 贷款超市 Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface LoanMarketProductMapper extends BaseMapper<LoanMarketProduct> {

	@Select("SELECT id AS 'key',title AS 'label' from loan_market_product WHERE 1=1 AND is_del = 0 AND create_id = #{param.createId} ")
	List<Options> queryOptions(@Param("param") LoanMarketProductParam param);
}