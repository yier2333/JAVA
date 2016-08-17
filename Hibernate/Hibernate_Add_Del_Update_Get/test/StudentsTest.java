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

//������
public class StudentsTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		//�������ö���
		Configuration config = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//�����Ự����
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//�Ự����
		session = sessionFactory.openSession();
		//��������
		transaction = session.beginTransaction();		
	}
	/*
	 * ����save
	 */
	@Test
	public void testSaveStudents() {
		
		//����ѧ������
		Students s=new Students(1, "����", "Ů", new Date(), "����");	    
	    //����ʹ��sql���������棬����ʹ��hibernate�����浽���ݿ�
	    session.save(s);
	    System.out.println(s);
	    System.out.println("testSaveStudentsִ����");
	}
	/*
	 * ��ѯGet
	 */
	@Test 
	public void testGetStudents() {
		//��һ���������÷�����ƻ�ȡ�����͵õ�һ����ʵ������
		//�ڶ�������ָ��ʵ����������һ��Ҫȷ��������ļ�¼���������ݿ⣩�������������һ��id=1
		Students s = (Students)session.get(Students.class, 1);
		//�������ֱ�Ӵ�ӡһ����������Ϊ����Students������д������toString����
		System.out.println(s);
		System.out.println("testGetStudentsִ����");
	}
	/*
	 * ��ѯLoad����get����һ����
	 */
	@Test 
	public void testLoadStudents() {	
		Students s = (Students)session.load(Students.class, 1);		
		System.out.println(s);
		System.out.println("testLoadStudentsִ����");
	}
	/*
	 * ����Update
	 */
	@Test 
	public void testUpdateStudents() {
		Students s = (Students)session.get(Students.class, 1);
		s.setAddress("���");
		//ʹ��session��update������������ʵ������
		session.update(s);
		System.out.println("testUpdateStudentsִ����");
	}
	/*
	 *ɾ��delete
	 */
	@Test 
	public void testDeleteStudents() {
		Students s = (Students)session.get(Students.class, 1);
		//ʹ��session��deleteɾ������ʵ������
		session.delete(s);
		System.out.println("testDeleteStudentsִ����");
	}
	@After
	public void destroy() {
		transaction.commit();//�ύ����
		session.close();//�رջỰ
		sessionFactory.close();//�رջỰ����
	}

}
