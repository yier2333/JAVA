import java.util.Date;

//ѧ���࣬����java bean��ԭ��
/*
 * ������
 * ����������Ĭ�Ϲ��췽��
 * ����˽��
 * ����setter/getter��װ
 */
public class Students {

	private int sid;
	private String sname;
	private String gender;
	private Date birthday;
	private Address address;
	public Students() {
		
	}
	public Students(int sid, String sname, String gender, Date birthday, Address address) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Students [sid=" + sid + ", sname=" + sname + ", gender=" + gender + ", birthday=" + birthday
				+ ", address=" + address + "]";
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	
}
