package com.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyMethodBeforeAdvice implements MethodBeforeAdvice {

	/*
	 * method:被调用的方法名字
	 * args:给method传递的参数
	 * target:目标对象（谁调用了这个method）
	 */
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("记录日志..."+method.getName());
	}

}
