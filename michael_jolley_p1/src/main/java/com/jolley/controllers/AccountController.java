package com.jolley.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jolley.erp.Driver;
import com.jolley.models.Ticket;
import com.jolley.models.User;
import com.jolley.services.TicketServices;
import com.jolley.services.UserServices;

public class AccountController {
	private static TicketServices ts = new TicketServices();
	private static UserServices us = new UserServices();
	private static ObjectMapper om = new ObjectMapper();

	public static void createGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Controller @ get new");
		RequestDispatcher view = req.getRequestDispatcher("/newUser.html");
		view.forward(req, resp);
	}

	public static void createPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User entity = new User(req.getParameter("fname"), req.getParameter("lname"), req.getParameter("email"),
				req.getParameter("password"), "Employee", req.getParameter("address"), req.getParameter("city"),
				req.getParameter("state"), Integer.parseInt(req.getParameter("zip")));
		System.out.println("controller b4 doa");
		us.newUser(entity);
		System.out.println("conroller after dao");
		RequestDispatcher view = req.getRequestDispatcher("/login.html");
		view.forward(req, resp);

	}

	public static void loginGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher view = req.getRequestDispatcher("/login.html");
		view.forward(req, resp);
	}

	public static void loginPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Attempting to log in.");
		String email = req.getParameter("email");
		String pass = req.getParameter("password");

		HttpSession session = req.getSession();

		if (us.exists(email) && us.verify(email, pass)) {
			System.out.println("Verification success!");
			User user = us.getByEmail(email);
			session.setAttribute("user", user);

			resp.sendRedirect("/ERP/api/home");

		} else {
			System.out.println("Failed to log in.  No session created.");
			RequestDispatcher view = req.getRequestDispatcher("/api/login");
			view.forward(req, resp);
		}

	}

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		RequestDispatcher view = req.getRequestDispatcher("/login.html");
		view.forward(req, resp);
	}

	public static void goHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/index.html");
		view.forward(req, resp);
	}

	public static void tickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	   catch (Exception e) {
//		RequestDispatcher view = req.getRequestDispatcher("/login.html");
//		view.forward(req, resp);

		RequestDispatcher view = req.getRequestDispatcher("/tickets.html");
		view.forward(req, resp);

	}

	public static void logCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		if (session.getAttribute("user")!=null) {
		User user = (User) session.getAttribute("user");

		String userJSON = new Gson().toJson(user);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(userJSON);
		out.flush();
		} 
		else {
			RequestDispatcher view = req.getRequestDispatcher("/ERP/api/login");
			view.forward(req, resp);
		}
	}

}
