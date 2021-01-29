package com.jolley.models;

import java.time.*;

public class Ticket {
	private int id;
	private int requester;
	private Integer responder;
	private double amount;
	private String expenseType;
	private String description;
	private LocalDate expenseDate;
	private LocalDate requestDate;
	private LocalDate responseDate;
	private Boolean approved;
	
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public LocalDate getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public int getRequester() {
		return requester;
	}
	public void setRequester(int requester) {
		this.requester = requester;
	}
	public Integer getResponder() {
		return responder;
	}
	public void setResponder(int responder) {
		this.responder = responder;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	public LocalDate getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(LocalDate responseDate) {
		this.responseDate = responseDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Ticket(int requester, String expenseType, double amount, String description, LocalDate expenseDate) {
		super();
		this.expenseType = expenseType;
		this.expenseDate = expenseDate;
		this.requester = requester;
		this.amount = amount;
		this.description = description;
		this.requestDate = LocalDate.now();
		this.approved = null;
	}
	public Ticket(int id, int requester, int responder, double amount, String expenseType, String description,
			LocalDate expenseDate, LocalDate responseDate, Boolean approved) {
		super();
		this.id = id;
		this.requester = requester;
		this.responder = responder;
		this.amount = amount;
		this.expenseType = expenseType;
		this.description = description;
		this.expenseDate = expenseDate;
		this.requestDate = LocalDate.now();
		this.responseDate = responseDate;
		this.approved = approved;
	}
	

	
}
