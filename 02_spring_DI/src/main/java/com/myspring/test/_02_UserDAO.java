package com.myspring.test;

public class _02_UserDAO {
	
	private _02_User user;
	
	public _02_UserDAO() {}
	public _02_UserDAO(_02_User user) {
		this.user = user;
	}
	
	public void print() {
		user.printUser();
	}
	
}
