package com.hsp.inter;

//��Сд��ĸת��Ϊ��д
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
