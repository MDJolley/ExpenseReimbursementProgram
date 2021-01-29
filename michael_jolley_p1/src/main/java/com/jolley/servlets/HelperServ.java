package com.jolley.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jolley.controllers.AccountController;
import com.jolley.controllers.TController;
import com.jolley.controllers.TicketController;
import com.jolley.controllers.UController;

public class HelperServ {
	public static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String endpoint = req.getRequestURI();

		System.out.println(endpoint);
		switch (endpoint)

		{
		case "/ERP/me/newUser":
			switch (req.getMethod()) {
			case "GET":
				UController.getNewUser(req, resp);
				break;
			case "POST":
				UController.postNewUser(req, resp);
				break;
			}
		case "/ERP/me/home":
			UController.getHome(req, resp);
			break;
		case "/ERP/me/login":
			switch (req.getMethod()) {
			case "GET":
				UController.getLogin(req, resp);
				break;
			case "POST":
				UController.postLogin(req, resp);
				break;
			}
			break;
		case "/ERP/me/logout":
			UController.postLogout(req, resp);
			break;
		case "/ERP/me/welcome/user":
			UController.getWelcome(req, resp);
			break;
		case "/ERP/me/ticket/view":
			UController.getMyTickets(req, resp);
			break;
		case "/ERP/me/ticket/request":
			switch (req.getMethod()) {
			case "POST":
				TController.postRequest(req, resp);
				break;
			case "GET":
				UController.getRequest(req, resp);
				break;
			}
		case "/ERP/me/ticket/respond":
			UController.getRespond(req, resp);
			break;
		case "/ERP/me/ticket/approve":
			TController.putAccept(req, resp);
			break;
		case "/ERP/me/ticket/deny":
			TController.putDecline(req, resp);
			break;
		case "/ERP/me/ticket/getUnresponded":
			TController.getUnresponded(req, resp);
			break;
		case "/ERP/me/ticket/getMyTickets":
			TController.getMyTickets(req, resp);
			break;
		case "/ERP/me/getCurrentUser":
			UController.getCurrentUser(req, resp);
			break;
		}
	}
}
