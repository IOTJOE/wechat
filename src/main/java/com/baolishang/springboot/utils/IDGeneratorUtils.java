package com.baolishang.springboot.utils;

import com.baolishang.springboot.configs.SystemConfiguration;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

public class IDGeneratorUtils {

	private static int requestCounter = (short) 0;

	public static int getRequestCount() {
		synchronized (IDGeneratorUtils.class) {
			if (requestCounter < 0 || requestCounter > 999)
				requestCounter = 0;
			return requestCounter++;
		}
	}

	public static String generateRequestNo() {

		// GUID的方式索引将无法充分有效使用，所以才用有规律的id来作为请求号
		// return getGUID();
		String nodeId = SafeUtils.getString(SystemConfiguration.get("NodeID"));// 也可以用ip
		StringBuffer buf = new StringBuffer();
		buf.append(nodeId);// 2位节点ID
		buf.append(getFullTimeStamp()); // 时间 12位 yyMMddHHmmss
		buf.append(SafeUtils.formatInt(getRequestCount(), 3));
		Random rand = new Random();
		for (int i = 0; i < 4; i++) { // 四位随机数
			buf.append(rand.nextInt(10));
		}
		return buf.toString();

	}

	// 获得日期
	public static String getFullTimeStamp() {
		String temp = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		temp = sdf.format(new java.util.Date());
		return temp;
	}

	public static String getTimeStamp() {
		String temp = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		temp = sdf.format(new java.util.Date());
		return temp;
	}

	private static int counter = (short) 0;

	public static int getCount() {
		synchronized (IDGeneratorUtils.class) {
			if (counter < 0 || counter > 999)
				counter = 0;
			return counter++;
		}
	}

	private static final int IP;

	public static int IptoInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + bytes[i];
		}
		return result;
	}

	static {
		int ipadd;
		try {
			ipadd = IptoInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}

	public static int getIP() {
		return IP;
	}

	public static String generateOrderId(String nodeId) {

		StringBuffer buf = new StringBuffer();
		buf.append(nodeId);// 2位节点ID
		buf.append(getTimeStamp()); // 时间 12位 yyMMddHHmmss
		buf.append(SafeUtils.formatInt(getCount(), 3));
		Random rand = new Random();
		for (int i = 0; i < 3; i++) { // 四位随机数
			buf.append(rand.nextInt(10));
		}

		return buf.toString();
	}

	public static String getGUID() {
		UUID uuid = UUID.randomUUID();
		String tmpid = uuid.toString();
		tmpid = tmpid.replace("-", "");
		return tmpid;
	}


}
