package com.service;

public class UserService {
	private String name;
	
	private ByeService byeService;	

	public ByeService getByeService() {
		return byeService;
	}
	
	//���������֪��һ������û��ʵ���������乹�캯�����һ�仰���ɣ�����ӡ����ʵ������
	public UserService(){
		System.out.println("UserService�Ĺ��췽����ִ����...ʵ������һ������");
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
