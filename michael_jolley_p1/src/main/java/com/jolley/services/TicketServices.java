package com.jolley.services;

import java.util.ArrayList;
import java.util.List;

import com.jolley.dao.TicketRepo;
import com.jolley.erp.Driver;
import com.jolley.models.Ticket;

public class TicketServices {
	TicketRepo tr = new TicketRepo();
	
	public void create(Ticket t) {
		Log.LOG.info("New ticket added to repo. -- " + t.getId());
		tr.create(t);
	}
	
	public List<Ticket> getAll(){
		return tr.getAll();
	}
	
	public List<Ticket> getByRequester(int rId){
		List<Ticket> query = new ArrayList<>();
		for (Ticket t : tr.getAll()) {
			if (t.getRequester()==rId)
			{
				query.add(t);
			}
		}
		return query;
	}
	
	public List<Ticket> getNotResponded(){
		List<Ticket> query = new ArrayList<>();
		for (Ticket t : tr.getAll()) {
			if (t.getResponder()==0)
			{
				query.add(t);
			}
		}
		return query;
	}
	
	public Ticket getById(int id)
	{
		return tr.getById(id);
	}

	public Boolean update(Ticket t) {
		return tr.update(t);
	}
	
	public Boolean delete(int id) {
		return tr.delete(getById(id));
	}

	public Boolean approve(Ticket t) {
		t.setApproved(true);
		return tr.update(t);
	}
	
	public Boolean deny(Ticket t) {
		t.setApproved(false);
		return tr.update(t);
	}
	
}
