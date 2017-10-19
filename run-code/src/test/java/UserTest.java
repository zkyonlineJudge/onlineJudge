

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yhw.dao.QuestionDao;
import com.yhw.model.QuestionDTO;
import com.yhw.util.FileUtil;


public class UserTest extends BaseTest{
	@Autowired
	QuestionDao dao;
	
	@Test
	@Rollback(false)
	public void testA() {
		QuestionDTO dto = new QuestionDTO();
		dto.setCodeId(1);
		dto.setSizeLimit(10000L);
		dto.setTimeLimit(1000L);
		dto.setContent("import java.io.*;\r\n" + 
				"import java.util.*;\r\n" + 
				"public class Main\r\n" + 
				"{\r\n" + 
				"public static void main(String args[]) throws Exception\r\n" + 
				"{\r\n" + 
				"Scanner cin=new Scanner(System.in);\r\n" + 
				"int a=cin.nextInt(),b=cin.nextInt();\r\n" + 
				"System.out.println(a+b);\r\n" + 
				"}\r\n" + 
				"}");
		dto.setLanguage("java");
		
	}
}
