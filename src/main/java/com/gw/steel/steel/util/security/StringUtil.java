package com.gw.steel.steel.util.security;

public class StringUtil {

	/**
	 * 对手机号进行加*处理 eg: 13815425699 ---> 1381****699
	 * @param phoneNo
	 * @return
	 */
	public static String maskMobilePhone(String phoneNo) {
		return mask(phoneNo, 4, 3);
	}
	
	private static String mask(String source, Integer start, Integer end) {
		if (source == null) {
			return "";
		}
		int length = source.length();
		StringBuilder sourceSb = new StringBuilder(source);
		StringBuilder maskSb = new StringBuilder(length);
		for (int i = 0; i < length - start - end; i++) {
			maskSb.append('*');
		}
		return sourceSb.replace(start, length - end, maskSb.toString())
				.toString();
	}
}
