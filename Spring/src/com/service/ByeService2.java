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
		System.out.println("ByeService2的构造方法被执行了...实例化了一个对象");
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
		System.out.println("setBeanName被调用");
		
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("setBeanFactory被调用");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("setApplicationContext被调用");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet被调用");		
	}
	
	@PostConstruct
	public void myinit(){
		System.out.println("myinit被调用");		
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy被调用");	
		
	}
	
	public void  mydestroy(){
		System.out.println("mydestroy被调用");	
	}
	/*
	 * 使用bean工厂加载一个bean测试的结果
	ByeService的构造方法被执行了...实例化了一个对象
	setBeanName被调用
	setBeanFactory被调用
	afterPropertiesSet被调用
	myinit被调用
	Bye曼曼
	*/

}
