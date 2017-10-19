package com.yhw.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yhw.dao.QuestionDao;
import com.yhw.entity.Code;
import com.yhw.entity.Result;
import com.yhw.model.QuestionDTO;
import com.yhw.util.FileUtil;
/**
 * 编译运行code类
 * python 不用编译
 * 
 * 
 * 运行指定的cmd命令
 * 总共有3个文件路径 输入路径 输出路径 用户代码输出路径
 * 输入输出文件 暂定为多个文件 从0.in开始
 * 输出为0.out
 * 用户输出为 0.out
 * 
 * @author Administrator
 *
 */
public class Runner {
	private Map<String , String> compileMap = new HashMap();
	private Map<String , String> runMap = new HashMap();
	
	public static  Integer SUCCESS = 0; 
	public static  Integer COMPILE_FAIL = 1; 
	public static  Integer RUN_FAIL = 2; 
	public static Integer TIME_OUT = 3;
	public static Integer SIZE_OUT = 4;
	public static Integer WRONG_ANSWER = 5;
	public static Integer SYSTEM_ERROR = 6;
	public static Integer Presentation_Error = 7;
	
	public static Integer TYPE_RUN = 0;
	public static Integer TYPE_COMPILE = 1;
	
	
	public static String IN_SUFFIX = "in";
	public static String OUT_SUFFIX = "out";
	
	public static Integer TOLERATE = 1000;
	public static Integer THREAD_SIZE = 4;
	
    
    public static ExecutorService pool = Executors.newFixedThreadPool(THREAD_SIZE);
//    @Autowired
	private QuestionDao questionDao;
	
	private static final String sep = File.separator;
	
	private static final Integer addScore = 20;
	private static final Integer decScore = -20;
	
	public static String workSpace = "/home/fxy/data/"; 
	
//	public static String workSpace = "E:" + sep + "test" +sep; //window测试路径
	@Deprecated
	public void init() {
		compileMap.put("gcc", "gcc main.c -o main -Wall -lm -O2 -std=c99 --static -DONLINE_JUDGE");
		compileMap.put("g++", "g++ main.cpp -O2 -Wall -lm --static -DONLINE_JUDGE -o main");
//		if(new String("Windows 7").equals(SystemInfo.getInstance().getSystemName())) {
//			
//		}else {
//			
//		}
		
		compileMap.put("java", "javac -d " + workSpace + "" + " E:/学习/python/Lo-runner-master/Test.java");
		compileMap.put("ruby", "ruby -c main.rb");
		compileMap.put("perl", "perl -c main.pl");
		compileMap.put("pascal", "fpc main.pas -O2 -Co -Ct -Ci");
		compileMap.put("go", "/opt/golang/bin/go build -ldflags \"-s -w\"  main.go");
		compileMap.put("lua", "luac -o main main.lua");
		compileMap.put("python2", "python2 -m py_compile main.py");
		compileMap.put("python3", "python3 -m py_compile main.py");
		compileMap.put("haskell", "ghc -o main main.hs");
		
		runMap.put("java", "java E:\\学习\\python\\Lo-runner-master\\Test");
	}
	public Runner() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		questionDao = (QuestionDao) ctx.getBean("questionDao");
	}
	public String getComplileCMD(Integer id, String language) {
		if("java".equals(language)) {
			return "javac -d " + getCodePath(id) + " " +getCodePath(id) + "/Main.java";
		}else if("gcc".equals(language)) {
			return "gcc " + getCodePath(id) + "main.c -o " + getCodePath(id) + "main";
		}else if("g++".equals(language)) {
			return "g++ " + getCodePath(id) + "main.cpp -o " + getCodePath(id) + "main";
		}
		return null;
	}
	private void writeCodeToFile(QuestionDTO dto) {
		// TODO Auto-generated method stub
		String str = null;
		if("java".equals(dto.getLanguage())) {
			str =  getCodePath(dto.getId())  + "Main.java";
		}else if("gcc".equals(dto.getLanguage())) {
			str = getCodePath(dto.getId())  + "main.c";
		}else if("g++".equals(dto.getLanguage())) {
			str = getCodePath(dto.getId()) + "main.cpp";
		}
		try {
			System.out.println("生成路径：" + str);
			System.out.println("写入代码：" + dto.getContent());
			FileUtil.writeToFile(str, dto.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getCodePath(Integer id) {
		return workSpace + "code" + sep + id + sep;
	}
	/**
	 *  
	 * @param id
	 * @return
	 */
	public Integer compileCode(QuestionDTO dto) {
		Runtime runtime =Runtime.getRuntime();
		BufferedReader es = null;
		BufferedWriter os = null;
		BufferedReader is = null;
		Process exec = null;
		try {
			exec = runtime.exec(getComplileCMD(dto.getId(), dto.getLanguage()));
			is = new BufferedReader(new InputStreamReader(exec.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()));
			es = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
			String str = null;
			StringBuffer errorsb =new StringBuffer();
			while((str = es.readLine()) != null) {
				errorsb.append(str);
			}
			
			if(errorsb.toString().length() > 0) {
				System.out.println("error : " + errorsb.toString());
				//把错误信息保存到数据库中
				questionDao.updateResult(dto.getCodeId(), 7, errorsb.toString());
				return COMPILE_FAIL;
			}
			str = null;
			StringBuffer inputsb =new StringBuffer();
			while((str = is.readLine()) != null ) {
				inputsb.append(str);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				os.close();
				is.close();
				es.close();
				exec.destroy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	/**
	 * 运行指定的cmd命令
	 * 输入输出文件 暂定为多个文件 从0.in开始
	 * 输出为0.out
	 * 用户输出为 0.out
	 * @return
	 * @throws InterruptedException 
	 */
	public Integer runCode(QuestionDTO dto, Map retMap) throws InterruptedException {
		Integer errorCode = SUCCESS;
		// 运行时前应该把输入数据都读取出来
		// 获取测试数据的总数
		int len = FileUtil.getInputCount(getInputPath(dto.getId()));
		Long time = Long.MIN_VALUE;
		Long size = Long.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			String ip = getInputPath(dto.getId()) + i + "." + IN_SUFFIX;
			if (!FileUtil.exitFile(ip)) {
				questionDao.updateResult(dto.getCodeId(), Result.System_Error, "执行到第" + (i+1) + "组数据发生了错误，对应的文件不存在");
				return SYSTEM_ERROR;
			}
			String input = FileUtil.getStringFromFile(ip);

			retMap = runCodeInThread(dto.getId(), dto.getLanguage(), i, input, dto.getTimeLimit(), dto.getSizeLimit());
			if (TIME_OUT.equals(retMap.get("result"))) {
				// 插入超时结果
				questionDao.updateResult(dto.getCodeId(), Result.Time_Limit_Exceeded, "执行到第" + (i+1) + "组数据发生了错误，该组输入数据为：\n" + FileUtil.getStringFromFileN(getInputPath(dto.getId()) + i + ".in") 
				+ "\n输出数据为:\n" + FileUtil.getStringFromFileN(getOutputPath(dto.getId()) + i + ".out"));
				System.out.println("超时了");
				errorCode = TIME_OUT;
				break;
			} else if (SUCCESS.equals(retMap.get("result"))) {
				time = time > (Long)retMap.get("runTime") ? time : (Long)retMap.get("runTime");
				size = size > (Long)retMap.get("runSize") ? size : (Long)retMap.get("runSize");
			} else if (SIZE_OUT.equals(retMap.get("result"))) {
				System.out.println("内存溢出了");
				questionDao.updateResult(dto.getCodeId(), Result.Memory_Limit_Exceeded, "执行到第" + (i+1) + "组数据发生了错误，该组输入数据为：\n" + FileUtil.getStringFromFileN(getInputPath(dto.getId()) + i + ".in") 
				+ "\n输出数据为:\n" + FileUtil.getStringFromFileN(getOutputPath(dto.getId()) + i + ".out"));
				errorCode = SIZE_OUT;
				break;
			} else if(WRONG_ANSWER.equals(retMap.get("result"))) {
				questionDao.updateResult(dto.getCodeId(), Result.Wrong_Answer, "执行到第" + (i+1) + "组数据发生了错误，该组输入数据为：\n" + FileUtil.getStringFromFileN(getInputPath(dto.getId()) + i + ".in") 
				+ "\n输出数据为:\n" + FileUtil.getStringFromFileN(getOutputPath(dto.getId()) + i + ".out"));
				errorCode = WRONG_ANSWER;
				break;
			} else if(Presentation_Error.equals(retMap.get("result"))) {
				questionDao.updateResult(dto.getCodeId(), Result.Presentation_Error, "执行到第" + (i+1) + "组数据发生了错误，输出格式有误，该组输入数据为：\n" + FileUtil.getStringFromFileN(getInputPath(dto.getId()) + i + ".in") 
				+ "\n输出数据为:\n" + FileUtil.getStringFromFileN(getOutputPath(dto.getId()) + i + ".out"));
				errorCode = WRONG_ANSWER;
				break;
			}
		}
		if(errorCode.equals(SUCCESS)) {
			//到这里就是运行成功了，保存所有执行时间中的最大时间和最大内存
			System.out.println("时间为：" + time + ", 内存空间：" + size);
			//成功 对天梯模式的用户计分
//			questionDao.
			if(Code.TYPE_LADDER.equals(dto.getType())) {
				questionDao.updateUser(dto.getCodeId(), addScore, dto.getLanguage());
			}
			questionDao.updateResult(dto.getCodeId(), 1, Long.MIN_VALUE == time ? 0 : time, Long.MIN_VALUE == size ? 0 : size);
			questionDao.updateResult(dto.getCodeId(), true);
		}else {
			//失败 对天梯模式的用户计分
			if(Code.TYPE_LADDER.equals(dto.getType())) {
				questionDao.updateUser(dto.getCodeId(), decScore, dto.getLanguage());
			}
			questionDao.updateResult(dto.getCodeId(), false);
		}
		return errorCode;
	}
	public Map runCodeInThread(final Integer id, final String language, final Integer index, final String input,
			final Long timeLimit, final Long sizeLimit ) {

		Callable cal = new Callable() {

			public Map call() throws Exception {
				Map retMap = new HashMap();
				// TODO Auto-generated method stub
				Runtime runtime = Runtime.getRuntime();
				Long runTime = Long.MIN_VALUE;
				Long runSize = Long.MIN_VALUE;

				BufferedReader es = null;
				BufferedWriter os = null;
				BufferedReader is = null;
				Process exec = null;

				try {

					// 在这里计时
					Date begin = new Date();
					Long beginMem = runtime.totalMemory() - runtime.freeMemory();
					exec = runtime.exec(getRunCMD(id, language));
					is = new BufferedReader(new InputStreamReader(exec.getInputStream()));
					os = new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()));
					es = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
					String str = null;
					StringBuffer errorsb = new StringBuffer();
					os.write(input);
					os.newLine();
					os.flush();
					str = null;
					StringBuffer inputsb = new StringBuffer();
					while ((str = is.readLine()) != null) {
						inputsb.append(str);
					}
					if (inputsb.toString().length() > 0) {
						// 到这里把运行结果保存到运行结果路径
						FileUtil.writeToFile(getResultOutputPath(id) + index + "." + OUT_SUFFIX, inputsb.toString());
					}
					exec.waitFor();
					Date end = new Date();
					Long endMem = runtime.totalMemory() - runtime.freeMemory();

					if (end.getTime() - begin.getTime() <= timeLimit) {
						runTime = end.getTime() - begin.getTime() > runTime ? end.getTime() - begin.getTime() : runTime;
					} else {
						retMap.put("result", TIME_OUT);
						return retMap;
					}
					Long tempSize = (endMem - beginMem) / 1024;

					if (tempSize <= sizeLimit) {
						runSize = tempSize > runSize ? tempSize : runSize;
					} else {
						retMap.put("result", SIZE_OUT);
						return retMap;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						os.close();
						is.close();
						es.close();
						exec.destroy();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				retMap.put("runTime", runTime);
				retMap.put("runSize", runSize);
				retMap.put("result", SUCCESS);

				// TODO Auto-generated method stub
				return retMap;
			}
		};
		Future<Object> future = pool.submit(cal);

		Map retMap = null;
		try {
			retMap = (Map) future.get(timeLimit + TOLERATE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			retMap.put("result", TIME_OUT);
			return retMap;
		}
		//比对用户答案和真实答案的差距
		String result = FileUtil.getStringFromFileN(getResultOutputPath(id) + index + "." + OUT_SUFFIX);
		String real = FileUtil.getStringFromFileN(getOutputPath(id) + index + "." + OUT_SUFFIX);
//		System.out.println(result.replaceAll("\r\n", "\n"));
//		System.out.println("------------");
//		System.out.println(real.replaceAll("\r\n", "\n"));
		result = result.replaceAll("\r\n", "\n");
		real = real.replaceAll("\r\n", "\n");
		if(result.equals(real)){
//			System.out.println(index + " 相同");
			retMap.put("result", SUCCESS);
		}else {
//			System.out.println(index + " 不相同");
			if(result.trim().replaceAll("\n", "").equals(real.trim().replaceAll("\n", ""))) {
				retMap.put("result", Presentation_Error);
			}else {
				retMap.put("result", WRONG_ANSWER);
			}
		}
		return retMap;
	}
	public String getInputPath(Integer id) {
		// TODO Auto-generated method stub
		return workSpace + "in" + sep + id + sep;
		
	}
	public String getOutputPath(Integer id) {
		// TODO Auto-generated method stub
		return workSpace + "out" + sep + id + sep;
	}
	public String getResultOutputPath(Integer id) {
		// TODO Auto-generated method stub
		return workSpace + "result" + sep + id + sep;
	}
	/**
	 * 获取运行代码cmd命令
	 * @param id
	 * @param language
	 * @return
	 */
	public String getRunCMD(Integer id, String language) {
		if("java".equals(language)) {
			return "java -cp " +  getCodePath(id) + " Main";
		}else if("gcc".equals(language)) {
			return  getCodePath(id) + "main";
		}else if("g++".equals(language)) {
			return "g++ " + getCodePath(id) + "main";
		}else if("python".equals(language)) {
			return "python " +getCodePath(id) + "main.py";
		}
		return null;
	}
	
	public void compileAndRun(QuestionDTO dto) {
		Map retMap = new HashMap();
		//在指定路径下写入目标源码
		writeCodeToFile(dto);
		
		Integer compileCode = compileCode(dto);
		if(COMPILE_FAIL.equals(compileCode)) {
			System.out.println("compileCode :" + compileCode);
		}else {
			Integer runCode = null;
			try {
				runCode = runCode(dto, retMap);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(runCode); 
		}
	}
	public static void main(String[] args) {
//		compileAndRun();
		Runner run = new Runner();
		run.questionDao.updateUser(1, decScore,"java");
	}
	
	public Map<String, String> getCompileMap() {
		return compileMap;
	}
	public void setCompileMap(Map<String, String> compileMap) {
		this.compileMap = compileMap;
	}
	public Map<String, String> getRunMap() {
		return runMap;
	}
	public void setRunMap(Map<String, String> runMap) {
		this.runMap = runMap;
	}
	
}
