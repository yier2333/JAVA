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
	@Test
	public void testSaveStudents() {
		
		//�������������sid��ֵ���۲��Ƿ�ᱣ�浽���ݿ�
		Students s=new Students();
	    s.setAddress("����");
	    s.setBirthday(new Date());
	    s.setGender("��");
	    s.setSname("����");
	    
	    /*
	     * �����±��������ݿ��У�����sid��Ĭ������Ϊ0������Ҫ����Ϊint���͵�����Ĭ����0
	     * ����������java�Զ���sid��ֵ
	     * 
	     * ��create��Ϊupdate��������������򣬱�����Ϊ������ͬ
	     * ��˱���Ҫ�ֹ���������ֵ
	     */
	    //s.setSid(3);
	    /*
	     * ��assigned����native�󣬲���������ֵ���۲�Ч��
	     * ��ɾ����ԭ���ı��
	     * ���ֵ�һ����idΪ1���������У�idΪ2
	     * ����ʹ����native�����Զ�ʹ��increment��
	     */
	    session.save(s);
	}
	@After
	public void destroy() {
		transaction.commit();//�ύ����
		session.close();//�رջỰ
		sessionFactory.close();//�رջỰ����
	}

}
