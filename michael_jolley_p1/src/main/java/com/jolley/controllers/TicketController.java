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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jolley.models.Ticket;
import com.jolley.models.TicketView;
import com.jolley.models.User;
import com.jolley.services.TicketServices;
import com.jolley.services.UserServices;

public class TicketController {
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private static TicketServices ts = new TicketServices();
	private static UserServices us = new UserServices();
	private static ObjectMapper om = new ObjectMapper();

	// Insert new ticket into DBng 
	public static void createTicketPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Preparing Ticket Object
		HttpSession ses = req.getSession();
		String expDate = req.getParameter("date");
		System.out.println(expDate);
		LocalDate expenseDate = LocalDate.parse(expDate, dtf);
		User user = (User) ses.getAttribute("user");
		Ticket entity = new Ticket(user.getId(), req.getParameter("type"),
				Double.parseDouble(req.getParameter("expense")), req.getParameter("description"), expenseDate);
		// Use service layer to push object to db.
		ts.create(entity);
		// redirect user
		RequestDispatcher view = req.getRequestDispatcher("/api/tickets");
		view.forward(req, resp);
	}

	public static void createTicketGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/request.html");
		view.forward(req, resp);
	}

	public static void tableUnresponded(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Ticket> tickets = ts.getNotResponded();
		resp.getWriter().write(om.writeValueAsString(tickets)); // Time to stringify our tickets.
	}

	public static void tableUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
		User user = (User) session.getAttribute("user");
		List<Ticket> tickets = ts.getByRequester(user.getId());
		resp.getWriter().write(om.writeValueAsString(tickets)); // Time to stringify our tickets.
		} catch (Exception e) {
			RequestDispatcher view = req.getRequestDispatcher("/login.html");
			view.forward(req, resp);
		}
	}

	public static void getTicketData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		List<Ticket> tics = ts.getByRequester(user.getId());
		List<TicketView> tickets = new ArrayList<>();
		for (Ticket t : tics)
		{
			tickets.add(new TicketView(t));
		}
//		String html = "";
//		for (Ticket t : tickets)
//		{
//			User u = us.getById(t.getRequester());
//			html = html + "<tr><td>" + t.getRequestDate() + "<td><td>" + u.getEmail() + "<td><td>" + t.getAmount() + "<td><td>" + t.getExpenseType() + "<td><td>" + t.getDescription() + "<td><td>" + t.getApproved().toString() + "<td><tr>"; 
//		}
		// Time to stringify our tickets.
		String ticketsString = new Gson().toJson(tickets);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(ticketsString);
		out.flush();
		
//		om.writeValue(resp.getOutputStream(), tickets);  
//		resp.getWriter().write(om.writeValueAsString(tickets)); 
		
	}

}
