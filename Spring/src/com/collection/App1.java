package com.collection;

import java.util.Properties;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1 {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/collection/beans.xml");
		Department d = (Department)ac.getBean("department");		
		System.out.println(d.getName());
		for(String empName:d.getEmpName()){
			System.out.println(empName);
		}
		for(Employee e:d.getEmpList()){
			System.out.println(e.getName());
		}
		for(Employee e:d.getEmpSet()){
			System.out.println(e.getName());
		}
		for(Entry<Integer, Employee> entry:d.getEmpMap().entrySet()){
			System.out.println(entry.getKey());
		}
		Properties properties = d.getProperties();
		for(Entry<Object, Object> entry:properties.entrySet()){
			System.out.println(entry.getKey().toString()+entry.getValue().toString());
		}
	}

}
