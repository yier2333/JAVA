package com.hsp.inter;

//��Сд��ĸ��Ϊ��д
public class LowerLetter implements ChangeLetter {

	private String str;

	private String getStr() {
		return str;
	}

	private void setStr(String str) {
		this.str = str;
	}

	@Override
	public String change() {
		return str.toLowerCase();
	}

}
