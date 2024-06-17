package com.baolong.boot.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Servlet工具类
 * <p>
 * 描述：用于获取Request、Response相关信息
 *
 * @author Baolong 2024/6/6 0:10
 * @version 1.0
 * @since 1.8
 */
public class ServletUtils {
	private ServletUtils() {
	}

	/**
	 * 从 SpringBoot 中获取 Request 请求对象
	 *
	 * @return 返回当前请求的 Request 对象
	 */
	public static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {
			return null;
		}
		ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
		return attributes.getRequest();
	}

	/**
	 * 从 SpringBoot 中获取 Response 请求对象
	 *
	 * @return 返回当前请求的 Response 对象
	 */
	public static HttpServletResponse getResponse() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {
			return null;
		}
		ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
		return attributes.getResponse();
	}

	/**
	 * 获取 Session
	 *
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		return getRequest() == null ? null : getRequest().getSession();
	}

	/**
	 * 获取当前请求方式
	 *
	 * @return 方法名
	 */
	public static String getMethod() {
		return getRequest() == null ? null : getRequest().getMethod();
	}

	/**
	 * 获取当前请求路径
	 *
	 * @return 路径
	 */
	public static String getUri() {
		return getRequest() == null ? null : getRequest().getRequestURI();
	}

	/**
	 * 获取当前请求地址（全路径）
	 *
	 * @return 地址（全路径）
	 */
	public static String getUrl() {
		return getRequest() == null ? null : getRequest().getRequestURL().toString();
	}

	/**
	 * 获取请求协议
	 *
	 * @return 协议
	 */
	public static String getProtocol() {
		return getRequest() == null ? null : getRequest().getProtocol();
	}

	/**
	 * 获取请求IP地址
	 *
	 * @return IP地址
	 */
	public static String getRemoteAddr() {
		return getRequest() == null ? null : getRequest().getRemoteAddr();
	}

	/**
	 * 获取Cookie名字
	 *
	 * @return Cookie[]
	 */
	public static Cookie[] getCookieNames() {
		return getRequest() == null ? null : getRequest().getCookies();
	}

	/**
	 * 获取Cookie集合
	 *
	 * @return Map<String, Cookie>
	 */
	public static Map<String, Cookie> getCookieMap() {
		Map<String, Cookie> cookieMap = new HashMap<>();
		Cookie[] cookieNames = getCookieNames();
		if (null != cookieNames) {
			for (Cookie cookie : cookieNames) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 获取Cookie
	 *
	 * @param key 键
	 * @return Cookie 值
	 */
	public static Cookie getCookie(String key) {
		Map<String, Cookie> cookieMap = getCookieMap();
		return cookieMap.containsKey(key) ? (Cookie) cookieMap.get(key) : null;
	}

	/**
	 * 获取请求头名字
	 *
	 * @return Enumeration<String>
	 */
	public static Enumeration<String> getHeaderNames() {
		return getRequest() == null ? null : getRequest().getHeaderNames();
	}

	/**
	 * 获取请求头
	 *
	 * @param key 键
	 * @return String 值
	 */
	public static String getHeader(String key) {
		return getRequest() == null ? null : getRequest().getHeader(key);
	}

	/**
	 * 获取请求参数名字
	 *
	 * @return Enumeration<String>
	 */
	public static Enumeration<String> getParamNames() {
		return getRequest() == null ? null : getRequest().getParameterNames();
	}

	/**
	 * 获取请求参数
	 *
	 * @param key 键
	 * @return String 值
	 */
	public static String getParam(String key) {
		return getRequest() == null ? null : getRequest().getParameter(key);
	}

	/**
	 * 获取请求参数数组
	 *
	 * @param key 键
	 * @return String[]
	 */
	public static String[] getParams(String key) {
		return getRequest() == null ? null : getRequest().getParameterValues(key);
	}

	/**
	 * 获取所有请求参数集合
	 *
	 * @return Map<String, String [ ]>
	 */
	public static Map<String, String[]> getParamsMap() {
		return getRequest() == null ? null : getRequest().getParameterMap();
	}

	private static final String X_REQUESTED_FOR = "X-Requested-For";
	private static final String X_FORWARDED_FOR = "X-Forwarded-For";
	private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
	private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
	private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
	private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
	private static final String UNKNOWN = "unknown";
	private static final String IP_127 = "127.0.0.1";
	private static final String IP_000 = "0:0:0:0:0:0:0:1";
	private static final String IP_192 = "localhost";

	/**
	 * 获取IP地址
	 *
	 * @param request HttpServletRequest
	 * @return String IP地址
	 */
	public static String getIp(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String ip = request.getHeader(X_REQUESTED_FOR);
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(X_FORWARDED_FOR);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(PROXY_CLIENT_IP);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(WL_PROXY_CLIENT_IP);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(HTTP_CLIENT_IP);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(HTTP_X_FORWARDED_FOR);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 如果通过多个代理服务器，则获取第一个非代理IP地址
		if (ip != null && ip.contains(",")) {
			String[] ips = ip.split(",");
			for (String strIp : ips) {
				if (!Pattern.compile("^((\\d+\\.){3}\\d+)$").matcher(strIp).matches()) {
					continue;
				}
				ip = strIp;
				break;
			}
		}
		return ip;
	}

	/**
	 * 获取请求MAC地址
	 *
	 * @param request HttpServletRequest
	 * @return String MAC地址
	 */
	public static String getMac(HttpServletRequest request) {
		String ip = getIp(request);
		String macAddress = "";
		BufferedReader br = null;
		try {
			if (IP_127.equals(ip) || IP_000.equals(ip) || IP_192.equals(ip)) {
				byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
				//拼装
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					if (i != 0) {
						sb.append("-");
					}
					// mac[i] & 0xFF 是为了把byte转化为正整数
					String s = Integer.toHexString(mac[i] & 0xFF);
					sb.append(s.length() == 1 ? 0 + s : s);
				}
				macAddress = sb.toString();
			} else {
				// 获取非本地IP的MAC地址
				InputStreamReader isr = new InputStreamReader(Runtime.getRuntime().exec("arp -A " + ip).getInputStream());
				br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					int index = line.indexOf(ip);
					if (index != -1) {
						macAddress = line.substring(index + ip.length() + 10, index + ip.length() + 27);
					}
				}
			}
			return macAddress.trim().toUpperCase();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
