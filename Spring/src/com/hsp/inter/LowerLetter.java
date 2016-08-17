package com.hsp.inter;

//把小写字母变为大写
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
