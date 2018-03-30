package com.bola.nwcl.common.util.file;

import org.apache.commons.lang3.RandomStringUtils;

public class SerialUtil {
	
	public static String buildSerialNum(){
		String serial = "";
		serial = RandomStringUtils.randomNumeric(8);
		return serial;
	}
	
}
