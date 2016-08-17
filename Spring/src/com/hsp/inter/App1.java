package com.hsp.inter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1 {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hsp/inter/beans.xml");
		
		//不用借口获取对象
		/*UpperLetter changeLetter = (UpperLetter) ac.getBean("changeLetter");
		System.out.println(changeLetter.change());*/
		
		//使用接口来访问bean
		ChangeLetter changeLetter = (ChangeLetter)ac.getBean("changeLetter");
		System.out.println(changeLetter.change());
	}

}
