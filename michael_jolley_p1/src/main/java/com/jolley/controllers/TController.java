package com.jolley.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.jolley.models.Ticket;
import com.jolley.models.TicketView;
import com.jolley.models.User;
import com.jolley.services.TicketServices;

public class TController {
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private static TicketServices ts = new TicketServices();
	
	public static void getMyTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
	
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		List<Ticket> tics = ts.getByRequester(user.getId());
		List<TicketView> tickets = new ArrayList<>();
		for (Ticket t : tics)
		{
			tickets.add(new TicketView(t));
		}
		String ticketsString = new Gson().toJson(tickets);
		out.print(ticketsString);
		out.flush();
	}

	public static void getUnresponded(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
	
//		HttpSession session = req.getSession();
		
		List<Ticket> tics = ts.getNotResponded();
		List<TicketView> tickets = new ArrayList<>();
		for (Ticket t : tics)
		{
			tickets.add(new TicketView(t));
		}
		String ticketsString = new Gson().toJson(tickets);
		out.print(ticketsString);
		out.flush();
		
	}

	public static void postRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Preparing Ticket Object
		HttpSession ses = req.getSession();
		User u = null;
		try {
			u = (User) ses.getAttribute("user");
		} catch (Exception e)
		{
			resp.sendRedirect("/ERP/me/login");
		}
		
		String expDate = req.getParameter("date");
		System.out.println(expDate);
		LocalDate expenseDate = LocalDate.parse(expDate, dtf);
		Ticket entity = new Ticket(u.getId(), req.getParameter("type"),
				Double.parseDouble(req.getParameter("expense")), req.getParameter("description"), expenseDate);
		// Use service layer to push object to db.
		ts.create(entity);
		// redirect user
//		resp.sendRedirect("/ERP/me/ticket/view");
	}
	
	public static void putAccept(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession ses = req.getSession();
		User u = null;
		Integer id = null;
		try {
			u = (User) ses.getAttribute("user");
			id = Integer.parseInt(req.getParameter("id"));
		} catch (Exception e)
		{
			resp.sendRedirect("/ERP/me/login");
		}
		
		LocalDate respDate = LocalDate.now();
		Ticket entity = ts.getById(id);
		entity.setResponseDate(respDate);
		entity.setResponder(u.getId());
		// Use service layer to push object to db.
		ts.approve(entity);
		// redirect user?
		RequestDispatcher view = req.getRequestDispatcher("/respond.html");
		view.forward(req, resp);
	}
	public static void putDecline(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession ses = req.getSession();
		User u = null;
		Integer id = null;
		try {
			u = (User) ses.getAttribute("user");
			id = Integer.parseInt(req.getParameter("id"));
		} catch (Exception e)
		{
			resp.sendRedirect("/ERP/me/login");
		}
		
		LocalDate respDate = LocalDate.now();
		Ticket entity = ts.getById(id);
		entity.setResponseDate(respDate);
		entity.setResponder(u.getId());
		// Use service layer to push object to db.
		ts.deny(entity);
		// redirect user?
		RequestDispatcher view = req.getRequestDispatcher("/respond.html");
		view.forward(req, resp);
	}
	
	
	
	
	
	
	
	
	
	
}
