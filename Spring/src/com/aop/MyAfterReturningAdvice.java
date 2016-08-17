package com.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class MyAfterReturningAdvice implements AfterReturningAdvice {

	/*
	 * returnValue:就是你的sayHello的返回值（如果有），因为是后置通知，所以有必要获取你的函数返回值
	 * method：指明是哪个方法启动了通知
	 * args：给你个机会让你获取method的参数
	 * target：目标对象,就是实例test1Service
	 */
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("关闭资源");

	}

}
