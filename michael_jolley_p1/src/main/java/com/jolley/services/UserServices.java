package com.jolley.services;

import com.jolley.dao.UserRepo;
import com.jolley.models.User;

public class UserServices {
	UserRepo ud = new UserRepo();
	
	
	public void newUser(User user) {
		
		ud.create(user);
	}
	
	public Boolean verify(String email, String pass) {
		return(
				ud.getByEmail(email).getPass().equals(pass)
				);
	}
	
	public User getByEmail(String email) {
		return ud.getByEmail(email);
	}
	
	public User getById(int id)
	{
		return ud.getById(id);
	}
	
	public Boolean exists(String email) {
		return (ud.getByEmail(email)!=null);
	}
}
