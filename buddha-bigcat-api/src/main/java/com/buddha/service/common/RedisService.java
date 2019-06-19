package com.buddha.service.common;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

@Service
public class RedisService{

	@Autowired(required = false)
	private StringRedisTemplate stringRedisTemplate;
	
	/** -------------------key相关操作--------------------- */
 
	/**
	 * 获取指定 key 的值
	 * 
	 * @param key
	 * @return
	 */
	public <T> T get(final String key, final Class<T> clazz) {
		 String jsonResultStr = stringRedisTemplate.opsForValue().get(key);
		 if (StringUtils.isEmpty(jsonResultStr)) {
				return null;
			}
		return JSONObject.parseObject(jsonResultStr, clazz);
	}
	
	/**
	 * 获取指定 key 的值
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		 String jsonResultStr = stringRedisTemplate.opsForValue().get(key);
		 if (StringUtils.isEmpty(jsonResultStr)) {
				return null;
			}
		return jsonResultStr;
	}
	/**
	 * 模糊匹配查询
	 * @param likeKey
	 */
	public Set<String> getKeysLike(final String likeKey){
		Set<String> keys = stringRedisTemplate.keys(likeKey + "*");
		return keys;
	}
	/**
	 * 模糊匹配删除
	 * @param likeKey
	 */
	public void delKeysLike(final String likeKey) {
		Set<String> keys = stringRedisTemplate.keys(likeKey + "*");
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String keyStr = it.next();
			stringRedisTemplate.delete(keyStr);
		}
	}
	
	/**
	 * 设置String类型的值 
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存内容
	 * @return
	 */
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * 设置String类型的值，并且指定生存时间，单位为：秒
	 * 
	 * @param key
	 *            保存的key
	 * @param value
	 *            保存内容
	 * @param seconds
	 *            生存时间：单位：秒（-1为长期有效）
	 * @return
	 */
	public void set(String key, String value,Long seconds) {
		stringRedisTemplate.opsForValue().set(key, value,seconds,TimeUnit.SECONDS);
	}
}
