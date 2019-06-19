package com.buddha.component.common.utils;

import java.math.BigDecimal;


/**
 * 
 * 数学相关工具类
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
 * @时间 2018-7-11
 * @版权 深圳市智造建筑信息科技有限公司(www.icbi.xin)
 */
public class NumberUtils {


	/**
	 * 两个double相加
	 * 
	 * @param valA
	 * @param valB
	 * @return
	 */
	public static Double add(Double valA, Double valB) {
		if (valA == null) {
			valA = 0D;
		}
		if (valB == null) {
			valB = 0D;
		}
		return new BigDecimal(valA).add(new BigDecimal(valB)).doubleValue();
	}

	/**
	 * 两个double相减
	 * 
	 * @param valA
	 * @param valB
	 * @return
	 */
	public static Double subtract(Double valA, Double valB) {
		if (valA == null) {
			valA = 0D;
		}
		if (valB == null) {
			valB = 0D;
		}
		return new BigDecimal(valA).subtract(new BigDecimal(valB)).doubleValue();
	}

	/**
	 * 两个double相乘
	 * 
	 * @param valA
	 * @param valB
	 * @param scale
	 *            保留小数位数（默认两位，不进行四舍五入）
	 * @return
	 */
	public static Double multiply(Double valA, Double valB, Integer scale) {
		if (valA == null) {
			valA = 0D;
		}
		if (valB == null) {
			valB = 0D;
		}
		if (scale == null || scale < 0) {
			scale = 2;
		}
		return new BigDecimal(valA).multiply(new BigDecimal(valB)).setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * valA除以valB
	 * 
	 * @param valA
	 * @param valB
	 * @param scale
	 *            保留小数位数（默认两位，不进行四舍五入）
	 * @return
	 */
	public static Double divide(Double valA, Double valB, Integer scale) {
		if (valA == null) {
			valA = 0D;
		}
		if (valB == null) {
			valB = 0D;
		}
		BigDecimal bigValA = new BigDecimal(valA);
		BigDecimal bigValB = new BigDecimal(valB);
		if (bigValB.compareTo(BigDecimal.ZERO) == 0) {
			throw new IllegalArgumentException("除数不能为0");
		}
		if (scale == null || scale < 0) {
			scale = 2;
		}
		return bigValA.divide(bigValB, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 求多个double数字的和
	 * 
	 * @param scale
	 *            保留小数位数（默认两位，不进行四舍五入）
	 * @param vals
	 * @return
	 */
	public static Double sum(Integer scale, Double... vals) {
		if (vals == null || vals.length == 0) {
			throw new IllegalArgumentException("vals参数不能为空");
		}
		if (scale == null || scale < 0) {
			scale = 2;
		}
		BigDecimal bigVal = new BigDecimal(0D);
		for (Double d : vals) {
			if (d == null || d.doubleValue() == 0) {
				continue;
			}
			bigVal = bigVal.add(new BigDecimal(d));
		}
		return bigVal.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 获取double数组的平均值
	 * 
	 * @param scale
	 *            保留小数位数（默认两位，不进行四舍五入）
	 * @param vals
	 *            double数组
	 * @return
	 */
	public static Double avg(Integer scale, Double... vals) {
		Double total = sum(2, vals);
		System.out.println(total);
		return divide(total, new Double(vals.length), scale);
	}

	/**
	 * 获取数组的最大值
	 * 
	 * @param vals
	 *            double数组
	 * @return
	 */
	public static Double max(Double... vals) {
		if (vals == null || vals.length == 0) {
			throw new IllegalArgumentException("vals参数不能为空");
		}
		Double maxVal = vals[0];
		for (Double d : vals) {
			if (d != null && d.compareTo(maxVal) > 0) {
				maxVal = d;
			}
		}
		return maxVal;
	}

	/**
	 * 获取数组的最小值
	 * 
	 * @param vals
	 *            double数组
	 * @return
	 */
	public static Double min(Double... vals) {
		if (vals == null || vals.length == 0) {
			throw new IllegalArgumentException("vals参数不能为空");
		}
		Double maxVal = vals[0];
		for (Double d : vals) {
			if (d != null && maxVal.compareTo(d) > 0) {
				maxVal = d;
			}
		}
		return maxVal;
	}

	/**
	 * 计算面积
	 * 
	 * @param attrLong
	 *            长
	 * @param attrWidth
	 *            宽
	 * @return
	 */
	public static Double m2(Double attrLong, Double attrWidth) {
		return new Double(attrLong * attrWidth);
	}

	/**
	 * 体积计算
	 * 
	 * @param attrLong
	 *            长
	 * @param attrWidth
	 *            宽
	 * @param attrHeight
	 *            高
	 * @return
	 */
	public static Double m3(Double attrLong, Double attrWidth, Double attrHeight) {
		return new Double(attrLong * attrWidth * attrHeight);
	}
}
