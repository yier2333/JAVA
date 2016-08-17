import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Date;

import org.hibernate.Hibernate;
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
		
		//����ѧ������
		Students s=new Students(2, "����", "Ů", new Date(), "����");
	    System.out.println(s.getSname()+"--"+s.getAddress());
	    //����ʹ��sql���������棬����ʹ��hibernate�����浽���ݿ�
	    session.save(s);
	}
	@Test
	public void testWriteBlob() throws IOException {
		
		//����blob�������ͣ�ͼƬ��
		Students s=new Students(1, "����", "Ů", new Date(), "����");
	    //�Ȼ����Ƭ�ļ�
		File f = new File("c:"+File.separator+"Cat.jpg");
	    //�����Ƭ�ļ���������
		InputStream input = new FileInputStream(f);
		//����һ��Blob����
		//input.available()���������ĳ���
		Blob image = Hibernate.getLobCreator(session).createBlob(input,input.available());
		//������Ƭ����
		s.setPicture(image);
		//����ѧ��
	    session.save(s);
	    /*
	     * ����Ƭ���뵽���ݿ���
	     * ���Դ򿪿�Ӵ
	     */
	}
	@Test
	public void testReadBlob() throws Exception {
		//get���������һ�����������ڶ����Ǳ�ʶ����ע��1����blob����2û��
		Students s = (Students)session.get(Students.class, 1);
		//���Blob����
		Blob image = s.getPicture();
		//��ȡ��Ƭ������
		InputStream input = image.getBinaryStream();
		//���������
		File f = new File("c:"+File.separator+"newCat.jpg");
		//��������
		OutputStream output = new FileOutputStream(f);
		//����������
		byte[] buff = new byte[input.available()];
		input.read(buff);
		output.write(buff);
		input.close();
		output.close();
	    
	    /*
	     * ����Ƭ�������洢��ָ��Ŀ¼
	     */
	}
	@After
	public void destroy() {
		transaction.commit();//�ύ����
		session.close();//�رջỰ
		sessionFactory.close();//�رջỰ����
	}

}
