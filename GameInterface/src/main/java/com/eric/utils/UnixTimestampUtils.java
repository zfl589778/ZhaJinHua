package com.eric.utils;

public class UnixTimestampUtils {

	public static long getUnixTimestamp(){
		return System.currentTimeMillis()/1000;
	}
}
