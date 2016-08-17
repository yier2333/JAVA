package com.hsp.inter;

//把小写字母转换为大写
public class UpperLetter implements ChangeLetter{
	
	private String str;

	@Override
	public String change() {		
		return str.toUpperCase();			
	}

	private String getStr() {
		return str;
	}

	private void setStr(String str) {
		this.str = str;
	}

}
