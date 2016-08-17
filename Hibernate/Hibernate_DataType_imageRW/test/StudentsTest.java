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
		
		//生成学生对象
		Students s=new Students(2, "朱曼", "女", new Date(), "华科");
	    System.out.println(s.getSname()+"--"+s.getAddress());
	    //无需使用sql语言来保存，而是使用hibernate来保存到数据库
	    session.save(s);
	}
	@Test
	public void testWriteBlob() throws IOException {
		
		//测试blob数据类型（图片）
		Students s=new Students(1, "朱曼", "女", new Date(), "华科");
	    //先获得照片文件
		File f = new File("c:"+File.separator+"Cat.jpg");
	    //获得照片文件的输入流
		InputStream input = new FileInputStream(f);
		//创建一个Blob对象
		//input.available()是输入流的长度
		Blob image = Hibernate.getLobCreator(session).createBlob(input,input.available());
		//设置照片属性
		s.setPicture(image);
		//保存学生
	    session.save(s);
	    /*
	     * 将照片存入到数据库中
	     * 可以打开看哟
	     */
	}
	@Test
	public void testReadBlob() throws Exception {
		//get这个参数第一个是类名，第二个是标识符，注意1才有blob对象，2没有
		Students s = (Students)session.get(Students.class, 1);
		//获得Blob对象
		Blob image = s.getPicture();
		//获取照片输入流
		InputStream input = image.getBinaryStream();
		//创建输出流
		File f = new File("c:"+File.separator+"newCat.jpg");
		//获得输出流
		OutputStream output = new FileOutputStream(f);
		//创建缓冲区
		byte[] buff = new byte[input.available()];
		input.read(buff);
		output.write(buff);
		input.close();
		output.close();
	    
	    /*
	     * 将照片读出来存储到指定目录
	     */
	}
	@After
	public void destroy() {
		transaction.commit();//提交事务
		session.close();//关闭会话
		sessionFactory.close();//关闭会话工厂
	}

}
