package com.buddha.service.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.buddha.component.common.constant.Constant;
import com.buddha.component.common.utils.HttpResult;

import lombok.extern.log4j.Log4j2;
/**
 * 
 * @作者 chuck
 */
@Service
@Log4j2
public class HttpService {

	@Autowired
	protected static RestTemplate restTemplate;

	/**
	 * 注入实现类
	 * 
	 * @param client
	 */
	public static void setRestTemplate(RestTemplate client) {
		restTemplate = client;
	}

	/**
	 * 发送GET请求
	 * 
	 * @param url 请求地址
	 * @return
	 */
	public HttpResult doGet(String url, Map<String, ?> params) {
		try {
			// 获取parameter信息
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, params);
			return new HttpResult(responseEntity.getStatusCodeValue(), responseEntity.getBody());
		} catch (Exception e) {
			log.error("http请求异常，请检查", e);
		}
		return new HttpResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求异常");
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url 请求地址
	 * @return
	 */
	public HttpResult doPost(String url, Object jsonObject) {
		try {
			String json = null;
			if (jsonObject != null) {
				json = JSON.toJSONString(jsonObject, Constant.JSON_DATE_FORMAT_CONF);
			} else {
				json = "{}";
			}
			log.debug("发送请求参数为：" + json);
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
			headers.setContentType(type);
			headers.add("Accept", MediaType.APPLICATION_JSON.toString());
			HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, formEntity, String.class);
			return new HttpResult(responseEntity.getStatusCodeValue(), responseEntity.getBody());
		} catch (Exception e) {
			log.error("http请求异常，请检查", e);
		}
		return new HttpResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求异常");
	}

}
