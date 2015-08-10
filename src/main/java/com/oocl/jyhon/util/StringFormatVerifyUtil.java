/**
 * 
 */
package com.oocl.jyhon.util;

/**
 * 
 * 该类对输入内容的格式进行校验 如 电话号码 日期
 * 
 * @author whitesaber
 *
 */
public class StringFormatVerifyUtil {
	final static String DATE_REGEX = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

	final static String TEL_REGEX = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\\d{8}$)";

	/**
	 * 校验日期
	 * 样式1992-05-17
	 * @param value
	 * @return Boolean
	 */
	public static Boolean verifyDateFormat(String value) {
		if (value.matches(DATE_REGEX)) {
			return true;
		}
		return false;
	}

	/**
	 * 校验电话号码
	 * 
	 * @param value
	 * @return Boolean
	 */
	public static Boolean verifyTelFormat(String value) {
		if (value.matches(TEL_REGEX)) {
			return true;
		}
		return false;
	}

}
