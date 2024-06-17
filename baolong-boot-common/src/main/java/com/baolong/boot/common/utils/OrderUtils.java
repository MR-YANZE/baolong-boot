package com.baolong.boot.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单工具类
 * <p>
 * 描述：订单号生成
 *
 * @author Baolong 2024/6/6 0:13
 * @version 1.0
 * @since 1.8
 */
public class OrderUtils {
	private OrderUtils() {
	}

	/**
	 * 全局自增数
	 */
	private static int count = 0;
	/**
	 * 每毫秒最多生成多少订单，最好是像9999这种准备进位的值
	 */
	private static final int total = 9999;
	/**
	 * 记录上一次的时间，用来判断是否需要递增全局数
	 */
	private static String now = null;

	private static OrderUtils orderNo = null;

	/**
	 * 单例模式--懒汉模式
	 *
	 * @return GenerateOrderNum
	 */
	public static synchronized OrderUtils getInstance() {
		if (orderNo == null) {
			orderNo = new OrderUtils();
		}
		return orderNo;
	}

	/**
	 * 生成一个订单号
	 *
	 * @return 订单号
	 */
	public synchronized String getOrderNo() {
		String dataStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		if (dataStr.equals(now)) {
			count++;
		} else {
			count = 1;
			now = dataStr;
		}
		int countInteger = String.valueOf(total).length() - String.valueOf(count).length();
		// 算补位
		String bu = "";
		// 补字符串
		for (int i = 0; i < countInteger; i++) {
			bu += "0";
		}
		bu += String.valueOf(count);
		if (count >= total) {
			count = 0;
		}
		return dataStr + bu;
	}

	/**
	 * 生成一个订单号
	 *
	 * @return 订单号
	 */
	public synchronized String getOrderNo(String prefix) {
		return prefix + getOrderNo();
	}
}
