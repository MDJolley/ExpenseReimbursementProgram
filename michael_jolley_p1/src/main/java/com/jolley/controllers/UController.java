package com.jolley.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.jolley.models.User;
import com.jolley.services.Log;
import com.jolley.services.UserServices;

public class UController {
	private static UserServices us = new UserServices();
	
	public static void getNewUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Controller @ get new");
		RequestDispatcher view = req.getRequestDispatcher("/newUser.html");
		view.forward(req, resp);
	}
	public static void postNewUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User entity = new User(req.getParameter("fname"), req.getParameter("lname"), req.getParameter("email"),
				req.getParameter("password"), "Employee", req.getParameter("address"), req.getParameter("city"),
				req.getParameter("state"), Integer.parseInt(req.getParameter("zip")));
		us.newUser(entity);
//		RequestDispatcher view = req.getRequestDispatcher("/login.html");
//		view.forward(req, resp);

	}
	public static void getHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/index.html");
		view.forward(req, resp);
	}
	public static void getLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/login.html");
		view.forward(req, resp);
		resp.setStatus(200);
	}
	public static void postLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		
		if (us.exists(email) && us.verify(email, pass)) {
			User user = us.getByEmail(email);
			Log.LOG.info(user.getEmail() + " has logged in.");
			session.setAttribute("user", user);

			resp.sendRedirect("/ERP/me/welcome/user");
//			RequestDispatcher view = req.getRequestDispatcher("/me/welcome/user");
//			view.forward(req, resp);

		} else {
			RequestDispatcher view = req.getRequestDispatcher("/me/login");
			view.forward(req, resp);
		}
	}
	public static void postLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		Log.LOG.info(u.getEmail() + " has logged out.");
		session.invalidate();
		RequestDispatcher view = req.getRequestDispatcher("/login.html");
		view.forward(req, resp);
		resp.setStatus(200);
	}
	public static void getWelcome(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		User user = null;
		try {
			user = (User) session.getAttribute("user");
			System.out.println(user.getfName() + " is looking at HOME.");
		} catch (Exception e)
		{
			resp.sendRedirect("/me/login");
		}
		if (user!=null)
		{
			switch (user.getPosition().toLowerCase()) {
			case "employee":
				RequestDispatcher view = req.getRequestDispatcher("/uHome.html");
				view.forward(req, resp);
				break;
			case "finance_manager":
				RequestDispatcher viewMan = req.getRequestDispatcher("/mHome.html");
				viewMan.forward(req, resp);
				break;
			}
		} else {
			resp.sendRedirect("/me/login");
			
			RequestDispatcher viewMan = req.getRequestDispatcher("/me/login");
			viewMan.forward(req, resp);
		}
	}
	public static void getCurrentUser(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		User user = null;
		try {
			user = (User) session.getAttribute("user");
			String userJSON = new Gson().toJson(user);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(userJSON);
			out.flush();
		} catch (Exception e)
		{
			//Just do nothing, I guess.
		}
		
	}
	public static void getMyTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/tickets.html");
		view.forward(req, resp);
	}
	public static void getRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/request.html");
		view.forward(req, resp);
	}
	public static void getRespond(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/respond.html");
		view.forward(req, resp);
	}
}
