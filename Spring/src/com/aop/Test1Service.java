package com.aop;

public class Test1Service implements TestService,TestServiceInter {

	private String name;
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("Hi"+name);

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void sayBye() {
		// TODO Auto-generated method stub
		System.out.println("Bye"+name);
	}

}
