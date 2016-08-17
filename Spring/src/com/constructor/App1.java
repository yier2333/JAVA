package com.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1 {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/constructor/beans.xml");
		
		Employee employee = (Employee)ac.getBean("employee");
		System.out.println(employee.getAge()+' '+employee.getName());
	}

}
