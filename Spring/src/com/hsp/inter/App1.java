package com.hsp.inter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1 {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hsp/inter/beans.xml");
		
		//���ý�ڻ�ȡ����
		/*UpperLetter changeLetter = (UpperLetter) ac.getBean("changeLetter");
		System.out.println(changeLetter.change());*/
		
		//ʹ�ýӿ�������bean
		ChangeLetter changeLetter = (ChangeLetter)ac.getBean("changeLetter");
		System.out.println(changeLetter.change());
	}

}
