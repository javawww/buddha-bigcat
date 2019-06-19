package com.buddha.mapper.loan;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.buddha.pojo.loan.LoanApplyProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.buddha.component.common.dto.Options;
import com.buddha.param.LoanApplyProductParam;

  /**
 * 
 *  Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface LoanApplyProductMapper extends BaseMapper<LoanApplyProduct> {
 
	@Select("SELECT id AS 'key',name AS 'label' from loan_apply_product WHERE 1=1 AND is_del = 0 AND app_id = #{param.appId} ")
	List<Options> queryOptions(@Param("param") LoanApplyProductParam param);
	
}