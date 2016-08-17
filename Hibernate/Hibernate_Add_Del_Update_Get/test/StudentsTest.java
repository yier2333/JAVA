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
	/*
	 * 增加save
	 */
	@Test
	public void testSaveStudents() {
		
		//生成学生对象
		Students s=new Students(1, "朱曼", "女", new Date(), "华科");	    
	    //无需使用sql语言来保存，而是使用hibernate来保存到数据库
	    session.save(s);
	    System.out.println(s);
	    System.out.println("testSaveStudents执行了");
	}
	/*
	 * 查询Get
	 */
	@Test 
	public void testGetStudents() {
		//第一个参数采用反射机制获取类类型得到一个类实例对象
		//第二个参数指定实例的主键（一定要确保这个键的记录存在于数据库），我上面添加了一个id=1
		Students s = (Students)session.get(Students.class, 1);
		//这里可以直接打印一个对象，是因为我自Students类中重写了它的toString方法
		System.out.println(s);
		System.out.println("testGetStudents执行了");
	}
	/*
	 * 查询Load（与get方法一样）
	 */
	@Test 
	public void testLoadStudents() {	
		Students s = (Students)session.load(Students.class, 1);		
		System.out.println(s);
		System.out.println("testLoadStudents执行了");
	}
	/*
	 * 更新Update
	 */
	@Test 
	public void testUpdateStudents() {
		Students s = (Students)session.get(Students.class, 1);
		s.setAddress("武大");
		//使用session的update方法更新它的实例对象
		session.update(s);
		System.out.println("testUpdateStudents执行了");
	}
	/*
	 *删除delete
	 */
	@Test 
	public void testDeleteStudents() {
		Students s = (Students)session.get(Students.class, 1);
		//使用session的delete删除它的实例对象
		session.delete(s);
		System.out.println("testDeleteStudents执行了");
	}
	@After
	public void destroy() {
		transaction.commit();//提交事务
		session.close();//关闭会话
		sessionFactory.close();//关闭会话工厂
	}

}
