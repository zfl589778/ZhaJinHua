package com.eric.components;

import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

public class ReadProperties {
	public static ResourceBundle bundle;
	static {
		try {
			bundle = ResourceBundle.getBundle("configures/kernel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param key
	 *            配置键
	 * @param defValue
	 *            默认值
	 * @return 如果配置不存在或为空字符串,则返回默认字符串
	 */
	public static String getPropDefIfBlank(String key, String defValue) {
		if (ReadProperties.bundle.containsKey(key)) {
			String ret = ReadProperties.bundle.getString(key);
			if (StringUtils.isNotBlank(ret)) {
				return ret;
			}
		}
		return defValue;
	}
}
