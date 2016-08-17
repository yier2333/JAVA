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
		 * 我们首先使用传统的方法来调用UserService中的sayHello方法
		 * UserService userService = new UserService();
		 * userService.setName("朱曼")；
		 * userService.sayHello();
		 */
				
		// 下面使用spring来完成上面的任务
		//1.得到spring的applicationContext对象（容器对象）：applicationContext对象其实就等同于applicationContext.xml
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");//如果你取名为xxx,那么这里传参“xxx”
		UserService us = (UserService)ac.getBean("userService"); //取出一个bean实例(按对象名字提取)
		us.sayHello();*/
		/*
		 * 在上面这整个过程中我们都没有new一个对象，而是在xml中配置了一个bean,并且注入了属性
		 * 在这里通过一些方法就可以取出这个bean
		 */
		/*ByeService bs = (ByeService)ac.getBean("byeService"); 
		bs.sayBye();
		*/
		/*
		 * 当我们创建了一个ApplicationContextUtil工具类后，我们使用这个applicationContext对象就更方便了
		 * 我们在工具类中调用了ClassPathXmlApplicationContext("applicationContext.xml")得到了整个xml中的bean
		 * 并且将加载xml文件的代码设置成一个static静态代码块，使用类名就可以使用这个静态方法
		 * 这样我们在测试类中使用getApplicationContext()就可以得到ac对象了
		 */
		/*UserService userService = (UserService)ApplicationContextUtil.getApplicationContext().getBean("userService");
		userService.sayHello();*/
		
		//bean工厂与ApplicationContext的区别
		/*
		 * new ClassPathXmlApplicationContext一执行就会创建bean实例
		 */
		//ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*
		 * 使用bean工厂时，new XmlBeanFactory不会实例化对象，只有在调用getBean时
		 * 才会实例化一个你指定的实例对象，类似延时加载
		 */
		/*BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		factory.getBean("userService");*/
		
		
		//验证bean的作用于，是单例模式singleton:一个定义只对应一个实例，prototype一个定义对应多个实例：打印这个对象看地址即可	
		/*ApplicationContext ac = new FileSystemXmlApplicationContext("C:/Users/Administrator/Desktop/applicationContext.xml");
		UserService userService1 = (UserService)ac.getBean("userService");
		UserService userService2 = (UserService)ac.getBean("userService");
		System.out.println(userService1+""+userService2); */
		
		/* singleton单例模式下：
		UserService的构造方法被执行了...实例化了一个对象
		ByeService的构造方法被执行了...实例化了一个对象
		com.service.UserService@273305com.service.UserService@273305
		*/
		
		/* prototype模式下：由于我们的ByeService还是单例模式，所以只打印了一次
		ByeService的构造方法被执行了...实例化了一个对象
		UserService的构造方法被执行了...实例化了一个对象
		UserService的构造方法被执行了...实例化了一个对象
		com.service.UserService@273305com.service.UserService@11d2887
		*/

		
		//测试BeanPostProcessor
		/*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ByeService bs = (ByeService)ac.getBean("byeService"); 
		bs.sayBye();*/
		
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		ByeService bs = (ByeService)factory.getBean("byeService");
		bs.sayBye();
	}

}
