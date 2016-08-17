package com.inherit;

import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1 {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/inherit/beans.xml");
		Graduate graduate = (Graduate)ac.getBean("graduate");
		System.out.println(graduate.getName()+" "+graduate.getAge()+" "+graduate .getDegree());
	}

}
