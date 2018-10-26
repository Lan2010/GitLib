/**
 * Copyright(c) 2010-2013 by gmsdtech Inc.
 * All Rights Reserved
 */
package com.tianzhixing.common.auth.utils;

import java.util.Map;


/**
 * 
 * 
 * @author lyq
 */
public class OSTools {
	 public static boolean isLinux() {
	        if (System.getProperty("os.name").contains("linux") || System.getProperty("os.name").contains("Linux")) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	 
	   public static boolean isMacOSX() {
	        return System.getProperty("os.name").startsWith("Mac OS X");
	    }
	   
	    /**
	     * 获得一个文件的后缀名
	     * lyq
	     * @param fileName
	     * @return
	     */ 
	   
	   public static String getExtention(String fileName) {
	        int pos = fileName.lastIndexOf(".");
	        if (pos < 0) {
	            return "";
	        }
	        return fileName.substring(pos);
	    }
	   
	   /**
	    * map to strint
	    */
	   public static String mapToString(Map map){
		   String v = "";
		   for(Object key : map.keySet()){
			   if(key != null && map.get(key) != null)
				   v=v+key.toString()+":"+map.get(key).toString()+",";
		   }
		   return v;
	   }

}
