package com.jolley.models;

import java.time.LocalDate;

import com.jolley.controllers.TicketController;
import com.jolley.services.UserServices;

public class TicketView {
	private static UserServices us = new UserServices();

	private int id;
	private String reqDate;
	private String name;
	private double amount;
	private String type;
	private String description;
	private String approved;

	public TicketView() {
	}

	public TicketView(Ticket t) {
		User u = us.getById(t.getRequester());
		this.id = t.getId();
		this.name = u.getfName() + " " + u.getlName();
		this.amount = t.getAmount();
		this.type = t.getExpenseType();
		this.description = t.getDescription();
		
		if (t.getResponder() != 0) {
			if (t.getApproved()) {
				this.approved = "Approved";
			} else {
				this.approved = "Declined";
			}
		} else {
			this.approved = "Pending";
		}
		this.reqDate = t.getExpenseDate().format(TicketController.dtf);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getrEmail() {
		return name;
	}

	public void setrEmail(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(LocalDate reqDate) {
		this.reqDate = reqDate.format(TicketController.dtf);
	}

}
