package com.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.service.ByeService;
import com.service.ByeService2;
import com.service.UserService;
import com.util.ApplicationContextUtil;

public class Test {

	public static void main(String[] args) {
		/*
		 * ��������ʹ�ô�ͳ�ķ���������UserService�е�sayHello����
		 * UserService userService = new UserService();
		 * userService.setName("����")��
		 * userService.sayHello();
		 */
				
		// ����ʹ��spring��������������
		//1.�õ�spring��applicationContext�����������󣩣�applicationContext������ʵ�͵�ͬ��applicationContext.xml
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");//�����ȡ��Ϊxxx,��ô���ﴫ�Ρ�xxx��
		UserService us = (UserService)ac.getBean("userService"); //ȡ��һ��beanʵ��(������������ȡ)
		us.sayHello();*/
		/*
		 * ���������������������Ƕ�û��newһ�����󣬶�����xml��������һ��bean,����ע��������
		 * ������ͨ��һЩ�����Ϳ���ȡ�����bean
		 */
		/*ByeService bs = (ByeService)ac.getBean("byeService"); 
		bs.sayBye();
		*/
		/*
		 * �����Ǵ�����һ��ApplicationContextUtil�����������ʹ�����applicationContext����͸�������
		 * �����ڹ������е�����ClassPathXmlApplicationContext("applicationContext.xml")�õ�������xml�е�bean
		 * ���ҽ�����xml�ļ��Ĵ������ó�һ��static��̬����飬ʹ�������Ϳ���ʹ�������̬����
		 * ���������ڲ�������ʹ��getApplicationContext()�Ϳ��Եõ�ac������
		 */
		/*UserService userService = (UserService)ApplicationContextUtil.getApplicationContext().getBean("userService");
		userService.sayHello();*/
		
		//bean������ApplicationContext������
		/*
		 * new ClassPathXmlApplicationContextһִ�оͻᴴ��beanʵ��
		 */
		//ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*
		 * ʹ��bean����ʱ��new XmlBeanFactory����ʵ��������ֻ���ڵ���getBeanʱ
		 * �Ż�ʵ����һ����ָ����ʵ������������ʱ����
		 */
		/*BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		factory.getBean("userService");*/
		
		
		//��֤bean�������ڣ��ǵ���ģʽsingleton:һ������ֻ��Ӧһ��ʵ����prototypeһ�������Ӧ���ʵ������ӡ������󿴵�ַ����	
		/*ApplicationContext ac = new FileSystemXmlApplicationContext("C:/Users/Administrator/Desktop/applicationContext.xml");
		UserService userService1 = (UserService)ac.getBean("userService");
		UserService userService2 = (UserService)ac.getBean("userService");
		System.out.println(userService1+""+userService2); */
		
		/* singleton����ģʽ�£�
		UserService�Ĺ��췽����ִ����...ʵ������һ������
		ByeService�Ĺ��췽����ִ����...ʵ������һ������
		com.service.UserService@273305com.service.UserService@273305
		*/
		
		/* prototypeģʽ�£��������ǵ�ByeService���ǵ���ģʽ������ֻ��ӡ��һ��
		ByeService�Ĺ��췽����ִ����...ʵ������һ������
		UserService�Ĺ��췽����ִ����...ʵ������һ������
		UserService�Ĺ��췽����ִ����...ʵ������һ������
		com.service.UserService@273305com.service.UserService@11d2887
		*/

		
		//����BeanPostProcessor
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ByeService bs = (ByeService)ac.getBean("byeService"); 
		bs.sayBye();*/
		
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		ByeService bs = (ByeService)factory.getBean("byeService");
		bs.sayBye();
	}

}
