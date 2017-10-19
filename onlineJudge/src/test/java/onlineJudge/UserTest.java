package onlineJudge;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yhw.daoImpl.HighLadderDao;
import com.yhw.serviceImpl.UserService;

public class UserTest extends BaseTest{
	@Autowired
	private UserService userService;
	@Autowired
	private HighLadderDao highLadderDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	@Test
	@Rollback(false)
	public void testA() {
	}
}
