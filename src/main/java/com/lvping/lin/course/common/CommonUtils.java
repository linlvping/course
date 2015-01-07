package com.lvping.lin.course.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 
 * @author beifeng
 * @Date 2014-4-30 下午2:42:17
 * 
 */
public class CommonUtils {

	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s.trim())
				|| "null".equalsIgnoreCase(s.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 格式化时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime(long time) {
		return time == 0 ? "0" : DATE_FORMAT.format(new Date(time * 1000));
	}
	
	/**
	 * 2014-06-12 => 20140612
	 * @param date
	 * @return
	 */
	public static int formatDate(String date) {
	    if (isEmpty(date)) {
	        return 0;
	    }
	    return Integer.parseInt(date.replaceAll("-", ""));
	}

	/**
	 * 根据日期获取时间戳，如果参数为空，则返回0
	 * 
	 * @param date
	 * @param begin
	 * @return
	 * @throws ParseException
	 */
	public static int getTime(String date, boolean begin) throws ParseException {
		if (StringUtils.isEmpty(date)) {
			return 0;
		}
		if (!date.contains(":")) {
			if (begin) {
				date = date.trim() + " 00:00:00";
			} else {
				date = date.trim() + " 23:59:59";
			}
		}
		long time = DATE_FORMAT.parse(date).getTime();
		return (int) (time / 1000);
	}

	/**
	 * 获取当前的时间戳
	 * 
	 * @return
	 */
	public static int getCurrentTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 格式化给定日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	/**
	 * 将数字类型的字符串转成int，如果参数为空，则返回0
	 * 
	 * @param value
	 * @return
	 */
	public static int stringToInt(String value) {
		return StringUtils.isEmpty(value) ? 0 : Integer.parseInt(value);
	}
	
	/**
	 * 获取距离今天间隔多少天的日期，格式2014-05-20
	 * @param interval
	 * @return
	 */
	public static String getSpecificDate(int interval) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, interval);
		return DAY_FORMAT.format(cal.getTime());
	}

	/**
	 * 获取指定字符串的MD5值
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public final static String MD5(String str) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(str.getBytes());
		StringBuilder result = new StringBuilder(bytes.length << 1);
		for (int i = 0; i < bytes.length; i++) {
			result.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
			result.append(Character.forDigit(bytes[i] & 0xf, 16));
		}
		return result.toString();
	}

	/**
	 * 保留两位小数
	 * @param f
	 * @return
	 */
	public static String formatFloat(Float f) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(f);
	}
	
	/**
	 * 我们的数据库表里面一般以分为单位，所以需要转换为元
	 * @param price
	 * @return
	 */
	public static String commonFormat(int price) {
	    if (price < 0) {
	        return String.valueOf((float)price/100);
	    } else if(price == 0){
			return "0";
		} else if (price < 10) {
		    return "0.0"+price;
		} else if(price < 100) {
		    return "0."+price;
		} else {
		    String s = String.valueOf(price);
		    int index = s.length()-2;
		    return s.substring(0, index)+"."+s.substring(index);
		}
	}
	
	/**
     * 转换json_encode出来的字符串
     * @param str
     * @return
     */
	public static String jsonDecode(String str) {
	    StringBuilder sb = new StringBuilder();
        Matcher m = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(str);
        while(m.find()) {
            sb.append((char)Integer.parseInt(m.group(1), 16));
        }
        return sb.toString();
	}
	
	public static int hourToMin(int hour) {
	    return hour * 60;
	}
	
	public static String minToHour(int min) {
	    return String.valueOf((float)min/60);
	}
	
	public static String getName(HttpServletRequest request) {
	    String name = request.getParameter("name");
        try {
            name = new String(name.getBytes("iso8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
	}
}
