package com.buddha.mapper.market;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.buddha.pojo.market.MarketCarouselInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.buddha.component.common.dto.Options;
import com.buddha.param.MarketCarouselInfoParam;

  /**
 * 
 *  Mapper 接口
 *
 */
@DS("bigcat_loan")
public interface MarketCarouselInfoMapper extends BaseMapper<MarketCarouselInfo> {
 
	@Select("SELECT id AS 'key',name AS 'label' from market_carousel_info WHERE 1=1 AND is_del = 0 AND app_id = #{param.appId} ")
	List<Options> queryOptions(@Param("param") MarketCarouselInfoParam param);
	
}