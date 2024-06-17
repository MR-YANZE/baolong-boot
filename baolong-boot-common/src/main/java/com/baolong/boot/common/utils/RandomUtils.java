package com.baolong.boot.common.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具类
 * <p>
 * 描述：随机字符、随机数字
 *
 * @author Baolong 2024/6/6 0:12
 * @version 1.0
 * @since 1.8
 */
public class RandomUtils {
	private RandomUtils() {
	}

	/**
	 * 字符：大小写字母
	 */
	private static final String LETTER_CHAR = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

	/**
	 * 字符：数字
	 */
	private static final String NUM_CHAR = "0123456789";

	/**
	 * 字符：符号
	 */
	private static final String SYMBOL_CHAR = "~!@#$%^&*()_+";

	/**
	 * 随机数字，10位
	 *
	 * @return Long
	 */
	public static Long getNumber() {
		return getNumber(10);
	}

	/**
	 * 随机数据，指定位数
	 *
	 * @param length 位数，不能大于18位
	 * @return Long
	 */
	public static Long getNumber(int length) {
		length = Math.min(length, 18);
		StringBuilder str = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			str.append(NUM_CHAR.charAt(ThreadLocalRandom.current().nextInt(NUM_CHAR.length())));
		}
		return Long.valueOf(str.toString());
	}

	/**
	 * 随机字母，10位
	 *
	 * @return String
	 */
	public static String getLetter() {
		return getLetter(10);
	}

	/**
	 * 随机字母，指定位数
	 *
	 * @param length 位数
	 * @return String
	 */
	public static String getLetter(int length) {
		StringBuilder str = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			str.append(LETTER_CHAR.charAt(ThreadLocalRandom.current().nextInt(LETTER_CHAR.length())));
		}
		return str.toString();
	}

	/**
	 * 随机符号，10位
	 *
	 * @return String
	 */
	public static String getSymbol() {
		return getSymbol(10);
	}

	/**
	 * 随机符号，指定位数
	 *
	 * @param length 位数
	 * @return String
	 */
	public static String getSymbol(int length) {
		StringBuilder str = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			str.append(SYMBOL_CHAR.charAt(ThreadLocalRandom.current().nextInt(SYMBOL_CHAR.length())));
		}
		return str.toString();
	}

	/**
	 * 随机字符串，大小写字母、数字，指定位数
	 *
	 * @param length 位数
	 * @return String
	 */
	public static String getStr(int length) {
		String s = LETTER_CHAR + NUM_CHAR;
		StringBuilder str = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			str.append(s.charAt(ThreadLocalRandom.current().nextInt(s.length())));
		}
		return str.toString();
	}

	/**
	 * 随机字符串，大小写字母、数字、符号，指定位数
	 *
	 * @param length 位数
	 * @return String
	 */
	public static String getStrAndSymbol(int length) {
		String s = LETTER_CHAR + NUM_CHAR + SYMBOL_CHAR;
		StringBuilder str = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			str.append(s.charAt(ThreadLocalRandom.current().nextInt(s.length())));
		}
		return str.toString();
	}

}
