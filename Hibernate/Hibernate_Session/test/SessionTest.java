import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class SessionTest {

	@Test
	public void testOpenSession() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 创建会话工厂
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// 会话对象
		// 创建两个session对象，判断是否是同一个对象
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		System.out.println(session1 == session2); // false
		if (session1 != null) {
			System.out.println("session创建成功");
		} else {
			System.out.println("session创建失败");
		}
	}

	@Test
	public void testGetCurrentSession() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 创建会话工厂
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// 会话对象
		// 使用这种方式获取session时需要进行配置
		Session session1 = sessionFactory.getCurrentSession();
		Session session2 = sessionFactory.getCurrentSession();
		System.out.println(session1 == session2); // true体现的是单例模式
		if (session1 != null) {
			System.out.println("getCurrentSession创建成功");
		} else {
			System.out.println("getCurrentSession创建失败");
		}
	}

	@Test
	public void testSaveStudentWithOpenSession() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		// 创建会话工厂
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.openSession();
		Transaction transaction = session1.beginTransaction();
		Students s = new Students(3, "张三", "男", new Date(), "北京");
		session1.doWork(new Work() {
			/*
			 * 创建两个session会话，打印这两个连接对象的hashcode
			 * 如果两次的不同，说明两次会话对应的是两个连接对象，说明不显示的关闭session,那么connection对象也不会关闭
			 * 有可能会造成连接池溢出
			 */

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("connection1的hashcode:" + connection.hashCode());
				//connection2的hashcode:752001567
			}
		});
		session1.save(s);
		// session1.close();
		transaction.commit();
		
		/*
		 * 两次的连接connection对象不同，表明第一个session没有释放连接
		 * 也就是session不会自动关闭
		 */
		
		
		Session session2 = sessionFactory.openSession();		
		transaction = session2.beginTransaction();
		s = new Students(4, "李四", "男", new Date(), "上海");
		session2.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("connection2的hashcode:" + connection.hashCode());
				//connection2的hashcode:238357312
			}
		});
		session2.save(s);
		// session1.close();
		transaction.commit();
	}
	
	@Test
	public void testSaveStudentWithGetCurrentSession() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		// 创建会话工厂
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.getCurrentSession();
		Transaction transaction = session1.beginTransaction();
		Students s = new Students(3, "张三", "男", new Date(), "北京");
		session1.doWork(new Work() {
			/*
			 * 创建两个session会话，打印这两个连接对象的hashcode
			 * 如果两次的不同，说明两次会话对应的是两个连接对象，说明不显示的关闭session,那么connection对象也不会关闭
			 * 有可能会造成连接池溢出
			 */

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("getCurrentSession:connection1的hashcode:" + connection.hashCode());
				//getCurrentSession:connection1的hashcode:2003534796
			}
		});
		session1.save(s);
		// session1.close();
		transaction.commit();
		
		/*
		 * 上一个session1提交后，其连接会自动关闭
		 * 然后创建session2时，使用了 session1释放的连接资源，所以hashcode才是一样的
		 * 间接表明，前一个session1自动关闭了连接
		 */
		
		
		Session session2 = sessionFactory.getCurrentSession();		
		transaction = session2.beginTransaction();
		s = new Students(4, "李四", "男", new Date(), "上海");
		session2.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("getCurrentSession:connection2的hashcode:" + connection.hashCode());
				//getCurrentSession:connection2的hashcode:2003534796
			}
		});
		session2.save(s);
		// session1.close();
		transaction.commit();
	}
}
