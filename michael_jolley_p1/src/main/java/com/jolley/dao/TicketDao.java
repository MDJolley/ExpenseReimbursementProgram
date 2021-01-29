package com.jolley.dao;

import java.util.ArrayList;


public interface TicketDao<T> {
	public ArrayList<T> getAll();
	public T getById(int id);
	public Boolean create(T t);
	public Boolean update(T t);
	public Boolean delete(T t);
}
