package com.wenjiaxi.oa.core.common.web;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月19日 上午11:15:42
 * @version 1.0
 */

public final class CookieUtil {
	/**
	 * 获取Cookie
	 * @param cookie名称
	 * @return Cookie
	 */
	public static Cookie getCookie(String cookieName){
		/** 获取所有的Cookie */
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		/** 迭代所有cookie */
		if (cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies){
				if (cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
		}
		return null;
	}
	/**
	 * 添加Cookie
	 * @param cookieName 名称
	 * @param cookieValue 值
	 * @param maxAge 存活时间
	 */
	public static void addCookie(String cookieName, String cookieValue, int maxAge){
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (cookie == null){
			/** 创建Cookie */
			cookie = new Cookie(cookieName, cookieValue);
		}
		/** 设置Cookie存活时间 (按秒计算) */
		cookie.setMaxAge(maxAge);
		/** 设置cookie的访问路径 http://localhost:8080/ */
		cookie.setPath("/");
		/** 设置可以跨子域可以访问cookie http://www.itcast.cn http://t.itcast.cn */
		//cookie.setDomain(".itcast.cn");
		/** 向用户的浏览器中写入cookie */
		ServletActionContext.getResponse().addCookie(cookie);
	}
	/**
	 * 删除cookie
	 * @param cookieName 名称
	 */
	public static void removeCookie(String cookieName){
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (cookie != null){
			/** 设置Cookie存活时间 (按秒计算) */
			cookie.setMaxAge(0); // 立即失效
			/** 设置cookie的访问路径 http://localhost:8080/ */
			cookie.setPath("/");
			/** 向用户的浏览器中写入cookie */
			ServletActionContext.getResponse().addCookie(cookie);
		}
	}
}
