package com.ktdsuniversity.edu.hellospringhomework.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public final class RequestUtils {

	/**
	 * 요청자의 요청 정보를 가져온다.
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes request =  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		
		return request.getRequest();
	}
	
	/**
	 * 요청자의 IP 를 가져온다.
	 * @return
	 */
	public static String getIp() {
		return getRequest().getRemoteAddr();
	}
}
