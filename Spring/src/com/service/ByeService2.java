package com.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ByeService2 implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,InitializingBean,DisposableBean{
	private String name;
	
	public ByeService2(){
		System.out.println("ByeService2�Ĺ��췽����ִ����...ʵ������һ������");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void sayBye(){
		System.out.println("Bye"+name);
	}
	
	@Override
	public void setBeanName(String arg0) {
		System.out.println("setBeanName������");
		
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("setBeanFactory������");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("setApplicationContext������");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet������");		
	}
	
	@PostConstruct
	public void myinit(){
		System.out.println("myinit������");		
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy������");	
		
	}
	
	public void  mydestroy(){
		System.out.println("mydestroy������");	
	}
	/*
	 * ʹ��bean��������һ��bean���ԵĽ��
	ByeService�Ĺ��췽����ִ����...ʵ������һ������
	setBeanName������
	setBeanFactory������
	afterPropertiesSet������
	myinit������
	Bye����
	*/

}
