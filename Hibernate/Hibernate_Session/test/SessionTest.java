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
		// �������ö���
		Configuration config = new Configuration().configure();
		// ��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// �����Ự����
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// �Ự����
		// ��������session�����ж��Ƿ���ͬһ������
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		System.out.println(session1 == session2); // false
		if (session1 != null) {
			System.out.println("session�����ɹ�");
		} else {
			System.out.println("session����ʧ��");
		}
	}

	@Test
	public void testGetCurrentSession() {
		// �������ö���
		Configuration config = new Configuration().configure();
		// ��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// �����Ự����
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// �Ự����
		// ʹ�����ַ�ʽ��ȡsessionʱ��Ҫ��������
		Session session1 = sessionFactory.getCurrentSession();
		Session session2 = sessionFactory.getCurrentSession();
		System.out.println(session1 == session2); // true���ֵ��ǵ���ģʽ
		if (session1 != null) {
			System.out.println("getCurrentSession�����ɹ�");
		} else {
			System.out.println("getCurrentSession����ʧ��");
		}
	}

	@Test
	public void testSaveStudentWithOpenSession() {
		// �������ö���
		Configuration config = new Configuration().configure();
		// ��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		// �����Ự����
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.openSession();
		Transaction transaction = session1.beginTransaction();
		Students s = new Students(3, "����", "��", new Date(), "����");
		session1.doWork(new Work() {
			/*
			 * ��������session�Ự����ӡ���������Ӷ����hashcode
			 * ������εĲ�ͬ��˵�����λỰ��Ӧ�����������Ӷ���˵������ʾ�Ĺر�session,��ôconnection����Ҳ����ر�
			 * �п��ܻ�������ӳ����
			 */

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("connection1��hashcode:" + connection.hashCode());
				//connection2��hashcode:752001567
			}
		});
		session1.save(s);
		// session1.close();
		transaction.commit();
		
		/*
		 * ���ε�����connection����ͬ��������һ��sessionû���ͷ�����
		 * Ҳ����session�����Զ��ر�
		 */
		
		
		Session session2 = sessionFactory.openSession();		
		transaction = session2.beginTransaction();
		s = new Students(4, "����", "��", new Date(), "�Ϻ�");
		session2.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("connection2��hashcode:" + connection.hashCode());
				//connection2��hashcode:238357312
			}
		});
		session2.save(s);
		// session1.close();
		transaction.commit();
	}
	
	@Test
	public void testSaveStudentWithGetCurrentSession() {
		// �������ö���
		Configuration config = new Configuration().configure();
		// ��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		// �����Ự����
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.getCurrentSession();
		Transaction transaction = session1.beginTransaction();
		Students s = new Students(3, "����", "��", new Date(), "����");
		session1.doWork(new Work() {
			/*
			 * ��������session�Ự����ӡ���������Ӷ����hashcode
			 * ������εĲ�ͬ��˵�����λỰ��Ӧ�����������Ӷ���˵������ʾ�Ĺر�session,��ôconnection����Ҳ����ر�
			 * �п��ܻ�������ӳ����
			 */

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("getCurrentSession:connection1��hashcode:" + connection.hashCode());
				//getCurrentSession:connection1��hashcode:2003534796
			}
		});
		session1.save(s);
		// session1.close();
		transaction.commit();
		
		/*
		 * ��һ��session1�ύ�������ӻ��Զ��ر�
		 * Ȼ�󴴽�session2ʱ��ʹ���� session1�ͷŵ�������Դ������hashcode����һ����
		 * ��ӱ�����ǰһ��session1�Զ��ر�������
		 */
		
		
		Session session2 = sessionFactory.getCurrentSession();		
		transaction = session2.beginTransaction();
		s = new Students(4, "����", "��", new Date(), "�Ϻ�");
		session2.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				System.out.println("getCurrentSession:connection2��hashcode:" + connection.hashCode());
				//getCurrentSession:connection2��hashcode:2003534796
			}
		});
		session2.save(s);
		// session1.close();
		transaction.commit();
	}
}
