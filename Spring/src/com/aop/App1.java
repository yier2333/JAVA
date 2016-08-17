package com.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1 {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/aop/beans.xml");
		
		TestService testService = (TestService)ac.getBean("proxyFactoryBean");
		testService.sayHello();
		System.out.println(testService.toString());
		TestServiceInter testServiceInter = (TestServiceInter)testService;
		testServiceInter.sayBye();
		System.out.println(testServiceInter.toString());
	}

}
