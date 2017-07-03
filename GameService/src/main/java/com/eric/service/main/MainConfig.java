package com.eric.service.main;

import com.eric.components.ReadProperties;

public class MainConfig {
	
   public static String getContextPath() {
	   return ReadProperties.bundle.getString("service.context.path");
   }
   
   public static int getPort() {
	   return Integer.parseInt(ReadProperties.bundle.getString("service.port"));
   }
}