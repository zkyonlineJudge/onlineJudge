

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath*:/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
@Transactional  
public abstract class BaseTest extends TestCase{
	
}