package com.yhw.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;


public class SystemUtil {
	
	private static Logger logger = Logger.getLogger(SystemUtil.class);
	
	public static final String MESSAGE_FILE = "message";
	public static final String APPLICATION_FILE = "env";
	private static final int BYTE_ARRAY_SIZE = 8 * 1024;
	
	//本机host信息
	private static InetAddress inetAddress = null;
	//客户端所处环境
	public static String client = "Unknown";
	static {
		//获取host信息
		if (null == inetAddress) {
			try {
				inetAddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				logger.error("unknown host!");
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 获得超类的参数类型，取第一个参数类型
	 * @param <T> 类型参数
	 * @param clazz 超类类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassGenricType(final Class<?> clazz) {
		return getClassGenricType(clazz, 0);
	}
	
	/**
	 * 根据索引获得超类的参数类型
	 * @param clazz 超类类型
	 * @param index 索引
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**
	 * 从message文件中取得消息
	 * @param messageCode	消息号码
	 * @param args			参数数组
	 * @return
	 */
	public static String getAppMessage(String messageCode, Object[] args){
		
		ResourceBundle resourceBundle =
		      ResourceBundle.getBundle(MESSAGE_FILE);

		String pattern = resourceBundle.getString(messageCode);
		
		if(pattern != null) {
			return MessageFormat.format(pattern, args);
		}
		return null;
	}
	
	/**
	 * 取得系统配置信息
	 * @param configItem	配置项
	 * @return
	 */
	public static String getAppConfig(String configItem){
		
		ResourceBundle resourceBundle =
		      ResourceBundle.getBundle(APPLICATION_FILE);

		return resourceBundle.getString(configItem);
	}
	
	/**
	 * 创建指定目录
	 * @param dir	目录路径
	 */
	public static void createDirectory(File dir){
		
		if(!dir.exists()) {
			dir.mkdirs();
		}else if(dir.exists() && !dir.isDirectory()){
			dir.delete();
			dir.mkdirs();
		}
		
	}
	
	/**
	 * 列表对象深拷贝
	 * @param <T>	列表实体类型
	 * @param src	拷贝源
	 * @return		拷贝目标
	 */
    @SuppressWarnings("unchecked")
	public static <T> List<T> deepCopy(List<T> src) {

		 try{
			 
				 ByteArrayOutputStream byteOut = new ByteArrayOutputStream();   
				 ObjectOutputStream out = new ObjectOutputStream(byteOut);   
				 out.writeObject(src);   
			
				 ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());   
				 ObjectInputStream in =new ObjectInputStream(byteIn);   
				 List<T> dest = (List<T>)in.readObject();   
				 return dest;   		 
		
			 }catch(Exception e){
		
				return null;
			 }
	
		 }   
    
    /**
     * 将对象序列化
     * 
     * @param o
     * @return
     */
    public static byte[] fromObjectToBinary(Object o) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(
                    BYTE_ARRAY_SIZE);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            try {
                oos.writeObject(o);
            } finally {
                oos.close();
            }
            return baos.toByteArray();
        } catch (IOException ex) {
        	ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * 序列对象化
     * 
     * @param binary
     * @return
     */
    public static Object fromBinaryToObject(byte[] binary) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(binary);
            ObjectInputStream ois = new ObjectInputStream(bais);
            try {
                return ois.readObject();
            } finally {
                ois.close();
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * 获取当前时间的工具方法，类型为Timestamp
     * @return Timestamp对象
     * */
    public static Timestamp currentTimestamp(){
    	return new Timestamp(System.currentTimeMillis());
    }
    
    public static Long LongToSeconds(Long value) throws ParseException{
    	Timestamp timestamp = new Timestamp(value);
    	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");;
    	Long seconds = timeFormat.parse(timeFormat.format(timestamp)).getTime();
    	return seconds;
    }
	
	/**
	 * 获取本机ip
	 * @return
	 */
	public static String getHostIp() {
		if (null == inetAddress) {
			return null;
		}
		return inetAddress.getHostAddress();
	}
	
	/**
	 * 获取本机hostName
	 * @return
	 */
	public static String getHostName() {
		if (null == inetAddress) {
			return null;
		}
		return inetAddress.getHostName();
	}
}
