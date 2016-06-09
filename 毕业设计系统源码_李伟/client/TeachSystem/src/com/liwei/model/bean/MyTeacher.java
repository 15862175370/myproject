package com.liwei.model.bean;

import java.io.Serializable;

import android.R.integer;

public class MyTeacher implements Serializable {
	public MyTeacher(int cno, String cname, int tno, String tname, String tsex,
			String prof, String ticon, String phone, String dname, int tage) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.tno = tno;
		this.tname = tname;
		this.tsex = tsex;
		this.prof = prof;
		this.ticon = ticon;
		this.phone = phone;
		this.dname = dname;
		this.tage = tage;
	}
	public MyTeacher(int tno, String tname, String tsex,
			String prof, String ticon, String phone, String dname, int tage) {
		super();
		
		this.tno = tno;
		this.tname = tname;
		this.tsex = tsex;
		this.prof = prof;
		this.ticon = ticon;
		this.phone = phone;
		this.dname = dname;
		this.tage = tage;
	}
	private int cno;
	private String cname;
	private int tno;
	private String tname;
private String tsex;
private String prof;
private String ticon;
private String phone;
private String dname;
private int tage;
public int getCno() {
	return cno;
}
public void setCno(int cno) {
	this.cno = cno;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public int getTno() {
	return tno;
}
public void setTno(int tno) {
	this.tno = tno;
}
public String getTname() {
	return tname;
}
public void setTname(String tname) {
	this.tname = tname;
}
public String getTsex() {
	return tsex;
}
public void setTsex(String tsex) {
	this.tsex = tsex;
}
public String getProf() {
	return prof;
}
public void setProf(String prof) {
	this.prof = prof;
}
public String getTicon() {
	return ticon;
}
public void setTicon(String ticon) {
	this.ticon = ticon;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getDname() {
	return dname;
}
public void setDname(String dname) {
	this.dname = dname;
}
public int getTage() {
	return tage;
}
public void setTage(int tage) {
	this.tage = tage;
}

}
