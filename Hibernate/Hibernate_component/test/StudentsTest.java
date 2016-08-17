import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//测试类
public class StudentsTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会话工厂
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction();		
	}
	@Test
	public void testSaveStudents() {
		
		Students s=new Students();	
		s.setSid(15);
	    s.setBirthday(new Date());
	    s.setGender("男");
	    s.setSname("哈哈");
	    Address address = new Address("432100","123456789","武汉");
	    s.setAddress(address);
	    session.save(s);
	    /*
	     * 可以在数据库中发现一条记录的address属性变成三个子属性。
	     */
	}
	@After
	public void destroy() {
		transaction.commit();//提交事务
		session.close();//关闭会话
		sessionFactory.close();//关闭会话工厂
	}

}
