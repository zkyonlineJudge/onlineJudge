package com.yhw.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 项目名称：proto 类名称：DateUtil 类描述： 在于提供关于时间操作的一系列公用操作方法 创建人：chy36@51maidan.com
 * 创建时间：2010-8-26 上午09:46:53 修改人：chy36@51maidan.com 修改时间：2010-8-26 上午09:46:53
 * 修改备注：
 * 
 * @version
 */
public class DateUtil {

	public DateUtil() {

	}
	/**
	 * HH 24hours
	 * hh 12hours
	 */
	public static final SimpleDateFormat formater1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static final SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat formater3 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
	public static final SimpleDateFormat formater4 = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	public static final SimpleDateFormat formater_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd"); 
	public static final SimpleDateFormat formater_yyyyMMdd_readable = new SimpleDateFormat("yyyy年MM月dd日"); 
	public static final SimpleDateFormat formater_yyyyMMdd_dot = new SimpleDateFormat("yyyy.MM.dd");
	public static final SimpleDateFormat formater_yyyyMMdd_slash = new SimpleDateFormat("yyyy/MM/dd");
	public static final String format_yyyyMMdd = "yyyy-MM-dd";
	public static final String format_yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";
	public static final SimpleDateFormat formater_MMddyyyy = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat formater_ALL = new SimpleDateFormat("ssyyyySSShhddmmMM");
	public static final SimpleDateFormat Formater_ALL_NORMAL = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static final SimpleDateFormat Formater_ALL_NORMAL2 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat formater_hhmm = new SimpleDateFormat("hhmm");
	public static final SimpleDateFormat formater_hhmmss = new SimpleDateFormat("HH:mm:ss");
	


	public static String yyyyMMddRegex = "^((((19|20)\\d{2})-(0?[13-9]|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$";

	/**
	 * 5分钟 (毫秒)
	 */
	private static final long FIVE_MINUTES = 5*60*1000;
	/**
	 * 1小时 (毫秒)
	 */
	private static final long ONE_HOUR = 60*60*1000;
	/**
	 * 4小时 (毫秒)
	 */
	private static final long FOUR_HOURS = 4*60*60*1000;
	/**
	 * 一天 毫秒
	 */
	public static final int ONE_DAY = 1000 * 60 * 60 * 24;
	/**
	 * 4小时 (毫秒)
	 */
	private static final long MILLISECOND = 1000L;

	/**
	 * 
	 * @Title: stringToDate
	 * @Description:字符串转化为Date
	 * @param @param str str格式要求:yyyy,m(mm),d(dd);
	 *        如：2002-01-02，2002-1-2，2002-2-15
	 * @param @return
	 * @return Date 成功返回日期，失败返回null;
	 * @throws
	 */
	public static Date stringToDate(String str) {
		String strFormat = "yyyy-MM-dd HH:mm";
		if (str != null &&( str.length() == 10 || str.length() == 8 || str.length() == 9 )) {
			strFormat = "yyyy-MM-dd";
		}
		SimpleDateFormat sdFormat = new SimpleDateFormat(strFormat);
		Date date = new Date();
		try {
			date = sdFormat.parse(str);
		} catch (ParseException e) {
			 System.out.println("Error="+e);
			 try {
				sdFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
				date = sdFormat.parse(str);
				} catch (Exception e1) {
					 System.out.println("Error="+e1);
					return null;
				}
		} catch (NullPointerException e2){
			System.out.println("Error="+e2);
			return null;
		}
		return date;
	}

	/**
	 * 
	 * @Title: stringToDate
	 * @Description: 字符串以指定格式转化为Date
	 * @param @param str
	 * @param @param strFormat
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static Date stringToDate(String str, String strFormat) {
		SimpleDateFormat sdFormat = new SimpleDateFormat(strFormat);
		Date date = new Date();
		try {
			date = sdFormat.parse(str);
		} catch (Exception e) {
			return null;
		}
		return date;
	}

	/**
	 * 
	 * @Title: dateToYMD
	 * @Description: Date转换为字符串 yyyy-MM-dd
	 * @param @param dt
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String dateToYMD(Date dt) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String str = "";
		try {
			str = sdFormat.format(dt);
		} catch (Exception e) {
			return "";
		}
		if (str.equals("1900-01-01")) {
			str = "";
		}

		return str;
	}

	/**
	 * 
	 * @Title: dateToString
	 * @Description: Date转换为字符串 yyyy-MM-dd HH:mm
	 * @param @param dt
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String dateToString(Date dt) {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = "";
		try {
			str = sdFormat.format(dt);
		} catch (Exception e) {
			return "";
		}
		if (str.equals("1900-01-01 00:00")) {
			str = "";
		}

		return str;
	}

	/**
	 * 
	 * @Title: dateToString
	 * @Description: Date按指定格式转换为字符串
	 * @param @param dt
	 * @param @param strFormat
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String dateToString(Date dt, String strFormat) {
		SimpleDateFormat sdFormat = new SimpleDateFormat(strFormat);
		String str = "";
		try {
			str = sdFormat.format(dt);
		} catch (Exception e) {
			return "";
		}
		if (str.equals("1900-01-01 00:00")) {
			str = "";
		}

		return str;
	}

	/**
	 * 根据格式获得当前日期字符串
	 * 
	 * @param sFormat
	 * @return
	 */
	public static String getDateStr(String sFormat) {
		Calendar tCal = Calendar.getInstance();
		Timestamp ts = new Timestamp(tCal.getTime().getTime());
		java.util.Date date = new java.util.Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
		String tmpStr = formatter.format(date);
		return (tmpStr);
	}

	/**
	 * 根据给定格式获取特定Timestamp时间的格式化显示
	 * 
	 * @param ts
	 * @param sFormat
	 * @return
	 */
	public static String getDateFormat(Timestamp ts, String sFormat) {
		Date date = new Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
		String tmpStr = formatter.format(date);
		return tmpStr;
	}

	/**
	 * 按yyyy-MM-dd格式化Timestamp日期
	 * 
	 * @param ts
	 * @return
	 */
	public static String getSDate(Timestamp ts) {
		Date date = new Date(ts.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String tmpStr = formatter.format(date);

		return tmpStr;
	}

	/**
	 * 将String类型的日期转换为时间
	 * 
	 * @param dt
	 * @return
	 */
	public static Timestamp getTs(String dt) {
		Date date = java.sql.Date.valueOf(dt);
		Calendar tCal = Calendar.getInstance();
		tCal.setTime(date);
		Timestamp ts = new Timestamp(tCal.getTime().getTime());
		return ts;
	}

	/**
	 * 建议获得短日期的处理方式 例如: getShortDate(2004-10-10 10:10:10.123) = 2004-10-10
	 * 
	 * @param dt
	 * @return
	 */
	public static String getShortDate(String dt) {
		try {
			return dt.substring(0, dt.indexOf(" "));
		} catch (Exception e) {
			return dt;
		}
	}

	/**
	 * 
	 * 取得当前日期时间
	 * 
	 * @return
	 */
	public static Timestamp getCurrDateTime() {
		Calendar tCal = Calendar.getInstance();
		Timestamp createDate = new Timestamp(tCal.getTime().getTime());
		return createDate;
	}

	/**
	 * 
	 * 取得当前日期时间
	 * 
	 * @return
	 */
	public static long getMillSecondOfCurrnetDateTime() {
		return (new Date()).getTime();
	}

	/**
	 * 
	 * 取得当前日期时间
	 * 
	 * @return
	 */
	public static Timestamp getCurrDateTimeByFormat_yyyyMMdd(Date date) {
		return new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0, 0);
	}

	/**
	 * 获得最常见的日期格式内容 : 年-月-日 小时-分钟-秒
	 * 
	 * @param ts
	 * @return
	 */
	public static String getSDateTime(Timestamp ts) {
		return getDateFormat(ts, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @Title: getSTime
	 * @Description: 按HH:mm:ss格式化日期
	 * @param @param ts
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getSTime(Timestamp ts) {
		return getDateFormat(ts, "HH:mm:ss");
	}

	/**
	 * 获取当天的日期
	 * 
	 * @return
	 */
	public static String getToday() {
		java.sql.Timestamp ts = new java.sql.Timestamp(System
				.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(ts);
	}

	/**
	 * 获取明天的日期
	 * 
	 * @return
	 */
	public static String getTomorrow() {
		return getNextDay(getToday());
	}

	/**
	 * 获得当前日期的下一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextDay(String date) {
		if (date == null || date.trim().length() == 0) {
			return "";
		}
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(f.parse(date));
		} catch (ParseException ex) {
			return date;
		}
		calendar.add(5, 1);
		return f.format(calendar.getTime());
	}

	/**
	 * 
	 * @Title: getTheDateStr
	 * @Description: 获取几天前或后的时间
	 * @param @param num 为正:当前日期后num天是返回值;为负:当前日期前num天是返回值
	 * @param @return
	 * @return String 返回的日期的格式:yyyy-MM-dd的字符串
	 * @throws
	 */
	public static String getTheDateStr(int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc = new GregorianCalendar();
		if(num != 0){
			gc.add(GregorianCalendar.DATE, num);
		}
		Date theday = gc.getTime();
		return sdf.format(theday);
	}

	/**
	 * 
	 * @Title: getTheDate
	 * @Description: 得到几天前的时间
	 * @param @param d
	 * @param @param day 为正则是几天后的时间，为负则是几天前的时间
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static Date getTheDate(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 计算Timestamp日期之间的日差
	 * 
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static int dateDiff(Timestamp dt1, Timestamp dt2) {
		long ldate1 = dt1.getTime();
		long ldate2 = dt2.getTime();
		return (int) ((ldate1 - ldate2) / (1000 * 60 * 60 * 24));
	}
	
	public static int dateDiff2(Date dt1, Date dt2){
		long ldate1 = dt1.getTime();
		long ldate2 = dt2.getTime();
		return (int) ((ldate1 - ldate2) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 计算两个日期字符串之前的天数差值 仅考虑日期
	 * 格式应该只有年月日 yyyy-MM-dd
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 * @throws ParseException 
	 */
	public static int calDayDiffBetweenTwoDateStr(String dateStr1, String dateStr2) 
	throws ParseException {		
		Date date1 = formater_yyyyMMdd.parse(dateStr1);
		Date date2 = formater_yyyyMMdd.parse(dateStr2);
		long diffMiliSeconds = date1.getTime()-date2.getTime();
		return (int) (diffMiliSeconds / (1000 * 60 * 60 * 24));
	}

	public static String getDateStrFromTimestamp(Timestamp time){
		return formater_yyyyMMdd.format(time);
	}

	/**
	 * 根据所给日期返回两Date日期相差的秒数
	 * 
	 * @param d1
	 * @param d2
	 * @return 返回两个日期间隔的毫秒数
	 */
	public static long getSecond(Date d1, Date d2) {
		long a1 = d1.getTime();
		long a2 = d2.getTime();
		long a3 = (a1 - a2) / 1000;
		return a3;
	}

	/**
	 * 根据所给日期返回两日期相差的天数
	 * 
	 * @param d1
	 *            前面时间
	 * @param d2
	 *            后面时间
	 * @return 返回两个日期间隔的天数
	 */
	public static long getDayNum(Date d1, Date d2) {
		long a1 = d1.getTime();
		long a2 = d2.getTime();
		long a3 = (a2 - a1) / (24 * 60 * 60 * 1000);
		return a3;
	}

	/**
	 * 判断当前日期是星期几
	 * 
	 * @param pTime
	 *            修要判断的时间
	 * @return dayForWeek 判断结果
	 * @Exception 发生异常
	 */
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 根据所秒数,计算相差的时间并以**时**分**秒返回
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static String getBeapartDate(long m) {
		String beapartdate = "";
		int nDay = (int) m / (24 * 60 * 60);
		int nHour = (int) (m - nDay * 24 * 60 * 60) / (60 * 60);
		int nMinute = (int) (m - nDay * 24 * 60 * 60 - nHour * 60 * 60) / 60;
		beapartdate = java.lang.Math.abs(nDay) + "天"
		+ java.lang.Math.abs(nHour) + "小时"
		+ java.lang.Math.abs(nMinute) + "分";
		return beapartdate;
	}

	/**
	 * 
	 * @Title: checkDate
	 * @Description: 检查日期格式
	 * @param @param date
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean checkDate(String date) {
		boolean ret = true;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			ret = df.format(df.parse(date)).equals(date);
		} catch (ParseException e) {
			System.out.println("日期格式错误");
			ret = false;
			return ret;
		}
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean b = m.matches();
		if (b) {

			System.out.println(date + "格式正确");
		} else {
			System.out.println(date + "格式错误");
		}
		return b;

	}

	public static boolean isYesterday(Date date){

		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(new Date());
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(date);
		dateCal.add(Calendar.DAY_OF_YEAR, 1);

		return dateCal.get(Calendar.DAY_OF_YEAR)==todayCal.get(Calendar.DAY_OF_YEAR);

	}

	/** 
	 * 根据日期计算所在周的上一周的周一 
	 * @param time 指定的日期 
	 */ 
	public static Date getPreviousFirstDayOfWeek(Date time) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
		Calendar cal = Calendar.getInstance();  
		cal.setTime(time);  
		//判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		if(1 == dayWeek) {  
			cal.add(Calendar.DAY_OF_MONTH, -1);  
		}  
		//        System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
		int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  

		cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day-7);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
		String firstDay = sdf.format(cal.getTime()) + " 00:00:00";  
		sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式  
		//        System.out.println("所在周的上一周星期一的日期："+firstDay);  
		Date result = null;
		try {
			result = sdf.parse(firstDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 
	 * 根据日期计算所在周的上一周的周日
	 * @param time 指定的日期 
	 */ 
	public static Date getPreviousLastDayOfWeek(Date time) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
		Calendar cal = Calendar.getInstance();  
		cal.setTime(time);  
		//判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		if(1 == dayWeek) {  
			cal.add(Calendar.DAY_OF_MONTH, -1);  
		}  
		//        System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
		int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  

		cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day-1);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
		String lastDay = sdf.format(cal.getTime()) + " 23:59:59";  
		sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式  
		//        System.out.println("所在周的上一周星期日的日期："+lastDay);  
		Date result = null;
		try {
			result = sdf.parse(lastDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	//得到几天前的 最早的时间 YMD:00 00 00
	public static Date getEarlyTimeOfDay(Date time, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式

		String firstDay = DateUtil.dateToYMD(DateUtil.getTheDate(time, day)) + " 00:00:00";
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
		Date result = null;
		try {
			result = sdf.parse(firstDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	//得到几天前的 最迟的时间 YMD: 23:59:59
	public static Date getLatestTimeOfDay(Date time, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式

		String firstDay = DateUtil.dateToYMD(DateUtil.getTheDate(time, day)) + " 23:59:59";

		Date result = null;
		try {
			result = sdf.parse(firstDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取上个月的第一天  （yyyy-MM-dd HH:mm:ss）
	 * @return
	 */
	public static Date getPreviousMonthFirstDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式  
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1); //得到前一个月 
		int minDate = cal.getActualMinimum(Calendar.DATE);
		cal.set(Calendar.DATE, minDate);
		String firstDay = DateUtil.dateToYMD(cal.getTime()) + " 00:00:00";
		Date result = null;
		try {
			result = sdf.parse(firstDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取上个月的最后一天（yyyy-MM-dd HH:mm:ss）
	 * @return
	 */
	public static Date getPreviousMonthLastDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1); // 得到前一个月
		int maxDate = cal.getActualMaximum(Calendar.DATE);

		cal.set(Calendar.DATE, maxDate);

		String lastDay = DateUtil.dateToYMD(cal.getTime()) + " 23:59:59";
		Date result = null;
		try {
			result = sdf.parse(lastDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 
	 * 根据日期计算所在周的周一 
	 * @param time 指定的日期 
	 */ 
	public static Date getCurrentFirstDayOfWeek(Date time) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
		Calendar cal = Calendar.getInstance();  
		cal.setTime(time);  
		//判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
		if(1 == dayWeek) {  
			cal.add(Calendar.DAY_OF_MONTH, -1);  
		}  
		//        System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
		int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  

		cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
		String firstDay = sdf.format(cal.getTime()) + " 00:00:00";  
		sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式  
		//        System.out.println("所在周的上一周星期一的日期："+firstDay);  
		Date result = null;
		try {
			result = sdf.parse(firstDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 得到某一年周的总数
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime());
	}

	/**
	 * 取得当前日期是多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * @Title: getDateYear   
	 * @Description:获取日期的年份   
	 * @param @return      
	 * @return int     
	 * @throws
	 */
	public static int getDateYear(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.YEAR);
	}

	/**
	 * @Title: getWeeksList   
	 * @Description:获取周列表，最长20周
	 * 1:如果开始日期到当前日期的周数在一个年份里面，大于等于20周，则列最新的20周；
	 * 2：如果开始日期到当前日期的周数在一个年份里面，小于20周，则全部周列出来；
	 * 3：如果开始日期到当前日期的周数不在一个年份里面，大于等于20周，列最新的20周；
	 * 4：如果开始日期到当前日期的周数不在一个年份里面，小于20周，全部列出来。
	 * 
	 * 返回size项
	 * @param @param startDate 开始日期
	 * @param @return      
	 * @return List<String>     
	 * @throws
	 */
	public static List<String> getWeeksList(Date startDate, int size) {
		List<String> list = new ArrayList<String>();
		Date currentDate = new Date();
		/*开始日期的年份*/
		int startYear = getDateYear(startDate);
		/*当前日期的年份*/
		int currentYear = getDateYear(currentDate);
		/*开始日期的周数*/
		int startWeek = getWeekOfYear(startDate);
		/*当前日期的周数*/
		int currentWeek = getWeekOfYear(currentDate);

		if(startYear == currentYear) {
			if(currentWeek - startWeek + 1 >= size) {
				for(int i=currentWeek; i>=currentWeek-20; i--) {
					list.add(currentYear+"年 第"+i+"周");
				}
			}else {
				for(int i=currentWeek; i>=startWeek; i--) {
					list.add(currentYear+"年 第"+i+"周");
				}
			}
		}else {
			/*不在同个年份里面
			 * 先看开始日期所在年份的周到年底的周数，再加上当前日期所在年份的周数
			 * 如果大于20周取最新20周，否则全部
			 * */
			//开始日期所在年份周的总数
			int startYearWeekNum = getMaxWeekNumOfYear(startYear);
			int weeksNum = startYearWeekNum - startWeek + 1 + currentWeek;//周总数
			if(weeksNum >= size) {
				for(int i=currentWeek; i>=1; i--) {
					list.add(currentYear+"年 第"+i+"周");
				}
				for(int i=startYearWeekNum; i>startYearWeekNum - size + currentWeek; i--) {
					list.add(startYear+"年 第"+i+"周");
				}
			}else {
				for(int i=currentWeek; i>=1; i--) {
					list.add(currentYear+"年 第"+i+"周");
				}
				for(int i=startYearWeekNum; i>=startWeek; i--) {
					list.add(startYear+"年 第"+i+"周");
				}
			}

		}
		return list;
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 取得指定日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 取得指定日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 将时间对象转换为字符串 HH:mm
	 * @param time
	 * @return
	 */
	public static String getTimeFormatStr(Time time){
		if(time==null){
			return "";
		}
		String format="HH:mm";
		Date d=new Date(time.getTime());
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * 将字符串转换为Time对象
	 * @param time
	 * @return
	 */
	public static Time getTimeByStr(String str){
		if(str==null){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		String s=str.replaceAll("：", ":").replaceAll("[^0-9:]", "");
		try {
			Date d=sdf.parse(s);
			return new Time(d.getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * A、当前时间与操作时间在同一日期内
	 * “当前时间”—“操作时间”<5分钟，显示为“刚刚”；
	 * 5分钟≤“当前时间”—“操作时间”<60分钟，显示为“XX分钟前”；
	 * 60分钟≤“当前时间”—“操作时间”<4小时，显示为“X小时前”；
	 * 4小时≤“当前时间”—“操作时间”，显示操作具体时间，例如“今天 11：23”；
	 *  B、当前时间与操作时间不在同一日期内
	 *     “当前日期”—“操作日期”=1，显示为“昨天 ‘操作时间’”，例如“昨天 11：23”
	 *     “当前日期”—“操作日期”=2，显示为“前天 ‘操作时间’”，例如“前天 11：23”
	 *     “当前日期”—“操作日期”≥3，显示为“‘具体日期’ ‘操作时间’”，例如“08-03 11：23”
	 *  C、当前时间与操作时间不在同一年份内
	 *  显示为“‘年份’ ‘日期’ ‘操作时间’”，例如“2009-08-03 11：23”
	 * @param orgDate
	 * @return
	 */
	public static String formatIntervalTime(Date orgDate){
		if(orgDate != null){
			Calendar now = Calendar.getInstance();
			Calendar org = Calendar.getInstance();
			org.setTime(orgDate);
			long distanceTime = now.getTimeInMillis() - org.getTimeInMillis();
			if(org.get(Calendar.YEAR) == now.get(Calendar.YEAR) && org.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR)){
				if(distanceTime < FIVE_MINUTES){
					return "刚刚";
				}else if(FIVE_MINUTES <= distanceTime && distanceTime < ONE_HOUR){
					return distanceTime/1000/60+"分钟前";
				}else if(ONE_HOUR <= distanceTime && distanceTime < FOUR_HOURS){
					return distanceTime/1000/60/60+"小时前";
				}else{
					return "今天 "+DateUtil.dateToString(orgDate, "HH:mm");
				}
			}else if(org.get(Calendar.YEAR) == now.get(Calendar.YEAR)){
				switch(now.get(Calendar.DAY_OF_YEAR) - org.get(Calendar.DAY_OF_YEAR)){
				case 1:return "昨天 "+DateUtil.dateToString(orgDate, "HH:mm");
				case 2:return "前天 "+DateUtil.dateToString(orgDate, "HH:mm");
				default:return DateUtil.dateToString(orgDate, "MM-dd HH:mm");
				}
			}else{
				return DateUtil.dateToString(orgDate, "yyyy-MM-dd HH:mm");
			}

		}
		return null;
	}


	/**
	 * 获取当前日期后面days天的日期，只有年月日，没有时分秒
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(int days){
		Calendar c= java.util.Calendar.getInstance();
		c.add(Calendar.DATE,+days);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   //yyyy-MM-dd HH:mm:ss
		String dateString = formatter.format(c.getTime());
		Date date=null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取某个日期后面days天的日期
	 * @param days
	 * @return Timestamp
	 * @author ZJD
	 * @time 2012-12-7 22:00
	 */
	public static Timestamp getTsAfter(Timestamp timestamp,int days){
		timestamp.setDate(timestamp.getDate()+days);        
		return timestamp;
	}

	/**
	 * 
	 * 获取当前日期的前days天，只有年月日，没有时分秒
	 * @param days
	 * @return
	 */
	public static Date getDateBefore(int days){
		Calendar c= java.util.Calendar.getInstance();
		c.add(Calendar.DATE,-days);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   //yyyy-MM-dd HH:mm:ss
		String dateString = formatter.format(c.getTime());
		Date date=null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的两天后
	 * @param dateStr
	 * @return
	 */
	public static String getTwoDaysAfterDate(String dateStr){
		Calendar c= java.util.Calendar.getInstance();
		try {
			c.setTime(formater2.parse(dateStr));
			c.add(Calendar.DATE,+2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formater1.format(c.getTime());
	}

	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的N天后
	 * 比如 明天 是yyyy/MM/dd hh:mm:ss后的第1天
	 * @param dateStr
	 * @return
	 */
	public static String getNDaysAfterDate(String dateStr,int n){
		Calendar c= java.util.Calendar.getInstance();
		try {
			c.setTime(formater_yyyyMMdd.parse(dateStr));
			c.add(Calendar.DATE,+n);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formater_yyyyMMdd.format(c.getTime());
	}
	
	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的N天后
	 * 比如 明天 是yyyy/MM/dd hh:mm:ss后的第1天
	 * @param dateStr
	 * @return
	 */
	public static Date getNDaysAfterDate(Date date,int n){
		Calendar c= java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,+n);
		return c.getTime();
	}

	/**
	 * 获取一个 Date date 的两天后
	 * @param dateStr
	 * @return
	 * yyyy-MM-dd
	 */
	public static Date getTwoDaysAfterDate(Date date){
		Calendar c= java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,+2);
		return c.getTime();
	}

	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的两天后
	 * @param dateStr
	 * @return
	 */
	public static String getTwoDaysAfterTimestamp(Timestamp time){
		Calendar c= java.util.Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.DATE,+2);
		return formater1.format(c.getTime());
	}



	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的2天前
	 * @param dateStr
	 * @return
	 */
	public static String getTwoDaysBeforeDate(String dateStr){
		Calendar c= java.util.Calendar.getInstance();
		try {
			c.setTime(formater2.parse(dateStr));
			c.add(Calendar.DATE,-2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formater1.format(c.getTime());
	}

	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的n天前
	 * @param dateStr
	 * @return
	 */
	public static String getNDaysBeforeDate(String dateStr,int n){
		Calendar c= java.util.Calendar.getInstance();
		try {
			c.setTime(formater_yyyyMMdd.parse(dateStr));
			c.add(Calendar.DATE,-n);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formater_yyyyMMdd.format(c.getTime());
	}

	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的n天前
	 * @param dateStr
	 * @return
	 */
	public static Date getNDaysBeforeDate(Date date,int n){
		Calendar c= java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,-n);
		return c.getTime();
	}

	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的2天前
	 * @param dateStr
	 * @return
	 */
	public static String getTwoDaysBeforeTimestamp(Timestamp time){
		Calendar c= java.util.Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.DATE,-2);
		return formater1.format(c.getTime());
	}

	/**
	 * 获取一个 yyyy/MM/dd hh:mm:ss 的2天前
	 * @param dateStr
	 * @return
	 */
	public static Date getTwoDaysBeforeDate(Date date){
		Calendar c= java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,-2);
		return c.getTime();
	}


	/**
	 * 获取时期的秒数
	 * @param timestamp 日期的毫秒
	 * @return 时期的秒数
	 * @author ZJD
	 * @time 2012-12-5 19:00
	 */
	public static Long getSecondOfDate(Long timestamp){
		return timestamp/MILLISECOND;
	}

	/**
	 * 获取日期的String,可作为cache的后缀
	 * 例如 2013-1-1 -->20130101
	 * @param timestamp 毫秒
	 * @return String
	 * @author ZJD
	 * @time 2012-12-5 19:00
	 */
	public static String getStringOfDate(Long timestamp){
		SimpleDateFormat formatOfDay = new SimpleDateFormat("yyyyMMdd");
		return formatOfDay.format(new Date(timestamp));
	}

	/**
	 * 例如 2013-1-1 -->20130101
	 * @return String
	 * @author ZJD
	 * @throws ParseException 
	 * @time 2012-12-7 17:00
	 */
	public static long getTimeOfFormatDate(String dateString) throws ParseException{
		SimpleDateFormat formatOfDay = new SimpleDateFormat("yyyyMMdd");
		return formatOfDay.parse(dateString).getTime();
	}


	public static Boolean validateADateStr(String date){
		try{
			date = URLDecoder.decode(date, "UTF-8");
			//尝试转换日期格式
			formater_yyyyMMdd.parse(date);
		}catch(ParseException e){
			return false;
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取1个月前的那天的 Date
	 * @return
	 */
	public static Date getOneMonthBeforeToday(){
		Calendar c= java.util.Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH,-1);
		return c.getTime();
	}

	/**
	 * 返回一天的第一秒
	 * @param date
	 * @return
	 */
	public static String getFirstSecondStrOfDate(Date date){
		String returnDateStr = formater_yyyyMMdd.format(date);
		return returnDateStr + " 00:00:00";
	}
	
	/**
	 * 返回一天的第一秒
	 * @param date
	 * @return
	 */
	public static Date getFirstSecondOfDate(Date date){
		String returnDateStr = formater_yyyyMMdd.format(date);
		Date returnDate = null;
		try {
			returnDate = formater2.parse(returnDateStr + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	/**
	 * 返回这一天的最后一秒
	 * @param date
	 * @return
	 */
	public static String getLastSecondStrOfDate(Date date){
		String returnDateStr = formater_yyyyMMdd.format(date);
		return returnDateStr+" 23:59:59";
	}
	
	/**
	 * 返回这一天的最后一秒
	 * @param date
	 * @return
	 */
	public static Date getLastSecondOfDate(Date date){
		String returnDateStr = formater_yyyyMMdd.format(date);
		Date returnDate = null;
		try {
			returnDate = formater2.parse(returnDateStr + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	
	/**
	 * 两个时间在是同一天
	 * @return
	 */
	public static boolean isTheSameDate(Timestamp date1,Timestamp date2){
		String date1Str = formater_yyyyMMdd.format(date1);
		String date2Str = formater_yyyyMMdd.format(date2);
		if(date1Str.equals(date2Str)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 以给定的格式获取一个Date对应的日期部分 也就是 时分秒都是0
	 * @param date
	 * @param formater
	 * @return
	 * @throws ParseException 这个异常基本不会有的 因为时刚刚从Date format过来的
	 */
	public static Date getDate(Date date, SimpleDateFormat formater) throws ParseException{
		return formater.parse(formater.format(date));
	}
	
	/**
	 * yyyy-MM-dd格式获取一个Date对应的日期部分 也就是 时分秒都是0
	 * @param date
	 * @param formater
	 * @return
	 * @throws ParseException 这个异常基本不会有的 因为时刚刚从Date format过来的
	 */
	public static Date getDate(Date date){
		Date d = null;
		try {
			d = getDate(date,formater_yyyyMMdd);
		} catch (ParseException e) {
			//这个不会有问题的！
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * yyyy-MM-dd格式获取当前日期对应的日期部分 也就是 时分秒都是0
	 * @param date
	 * @param formater
	 * @return
	 * @throws ParseException 这个异常不会被抛出！根本不会有这个情况 除非虚拟机傻逼了。。
	 */
	public static Date getDate(){
		return getDate(new Date());
	}

	/**
	 * 判断d1是否比d2早 false
	 * @param d1 一个时间 Date
	 * @param d2 一个时间 Date
	 * @return
	 */
	public static Boolean isBefore(Date d1, Date d2){
		return d1.getTime()<d2.getTime();
	}

	/**
	 * 计算用于显示的 当前日期到下限时间的天数
	 * @param offTime
	 * @return
	 */
	public static String getTimeRemain(Date offTime) {
		if(offTime == null){
			return "暂未确定";
		}
		String offTimeStr = null;
		try {
			int remainTime = dateDiff2(offTime, DateUtil.formater_yyyyMMdd.parse(DateUtil.getToday()));
			if(remainTime < 0 ){
				remainTime = 0;
			}
			offTimeStr = "" + remainTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return offTimeStr;
	}
	/**
	 * 获取两日期的月份差值
	 * @author jhonwill
	 * @param startTime 开始日期（较小值）
	 * @param offTime 结束日期（较大值）
	 * @return
	 */
	public static Integer getDifferencInMonth(Date startTime, Date offTime) {
		if(startTime == null || offTime == null) return -1;
		Calendar c= java.util.Calendar.getInstance();
		c.setTime(startTime);
		int startYear = c.get(Calendar.YEAR);
		int startMonth = c.get(Calendar.MONTH);
		c.setTime(offTime);
		int offYear = c.get(Calendar.YEAR);
		int offMonth = c.get(Calendar.MONTH);
		return (offYear - startYear) * 12 + (offMonth - startMonth);
	}
	
	//	public static static void main(String[] args) {
	//		System.out.println(formatIntervalTime(DateUtil.stringToDate("2010-07-04 00:00:00","yyyy-MM-dd HH:mm:ss")));
	//		Calendar c = new GregorianCalendar();
	//		c.set(2011, 0, 5);
	////		c.setTime(new java.util.Date());
	//		Date d = c.getTime();
	//		System.out.println(dateToYMD(d));
	//		List<String> list = getWeeksList(d, 20);
	//		for(String str : list) {
	//			System.out.println(str);
	//		}
	//		System.out.println(getPreviousMonthFirstDay());
	//		System.out.println(getPreviousMonthLastDay());
	//		getTimeByStr("12getTimeByStr：30");
	//	}





}
