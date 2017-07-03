package com.eric.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigureUtil {
	
	private static Map<String, Properties> propMap = new HashMap<String, Properties>();
	
	public static int getIntProp(String propFileName, String key){
		return Integer.valueOf(getStringPro(getProperties(propFileName), key));
	}

	public static double getDoubleProp(String propFileName,String key){
		return Double.valueOf(getStringPro(getProperties(propFileName), key));
	}
	
	public static String getProp(String propFileName,String key){
		return getStringPro(getProperties(propFileName), key);
	}

	private static Properties getProperties(String propFileName){
		if(!propMap.containsKey(propFileName)){
			synchronized (propMap) {
				if(!propMap.containsKey(propFileName)){
				    try {
				    	Properties prop =  new Properties();
				    	prop.load(ConfigureUtil.class.getResourceAsStream(propFileName));
				    	propMap.put(propFileName, prop);
				    } catch (Exception e) {
				        throw new RuntimeException("error to load ' " + propFileName + " ',pls check it.",e);
				    }
				
				}
			}
		}
		return propMap.get(propFileName);
	}
	
	private static String getStringPro(Properties prop,String key){
		
		if(null==prop) throw new IllegalStateException("' properties ' has not been initialized.");
		
		String str = prop.getProperty(key);
		
		if(null==str) throw new RuntimeException("' " + key + " ' was not configured.");

		return str.trim();
	}
	
	public static void setProp(String propFileName,String key, String value){
		if(null == propMap || propMap.get(propFileName) == null)getProperties(propFileName).get(key);
		propMap.get(propFileName).put(key, value);
	}

}
