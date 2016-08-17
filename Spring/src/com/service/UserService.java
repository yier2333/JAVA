package com.service;

public class UserService {
	private String name;
	
	private ByeService byeService;	

	public ByeService getByeService() {
		return byeService;
	}
	
	//如果我们想知道一个类有没有实例化，在其构造函数输出一句话即可，若打印，则实例化了
	public UserService(){
		System.out.println("UserService的构造方法被执行了...实例化了一个对象");
	}

	public void setByeService(ByeService byeService) {
		this.byeService = byeService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	public void sayHello(){
		System.out.println("Hello"+name);
		byeService.sayBye();
	}
}
