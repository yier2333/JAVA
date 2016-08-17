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
		
		//不给对象的主键sid赋值，观察是否会保存到数据库
		Students s=new Students();
	    s.setAddress("北京");
	    s.setBirthday(new Date());
	    s.setGender("男");
	    s.setSname("哈哈");
	    
	    /*
	     * 对象呗保存在数据库中，但是sid被默认设置为0，这主要是因为int类型的数据默认是0
	     * 而不代表是java自动给sid赋值
	     * 
	     * 将create改为update，再运行这个程序，报错，因为主键相同
	     * 因此必须要手工给主键赋值
	     */
	    //s.setSid(3);
	    /*
	     * 把assigned换成native后，不给主键赋值，观察效果
	     * 先删除掉原来的表格
	     * 发现第一个的id为1，继续运行，id为2
	     * 这是使用了native（它自动使用increment）
	     */
	    session.save(s);
	}
	@After
	public void destroy() {
		transaction.commit();//提交事务
		session.close();//关闭会话
		sessionFactory.close();//关闭会话工厂
	}

}
