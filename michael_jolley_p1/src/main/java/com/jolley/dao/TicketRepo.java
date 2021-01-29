package com.jolley.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jolley.models.Ticket;
import com.jolley.utils.ConnectionGenerator;

public class TicketRepo implements TicketDao<Ticket> {

	@Override
	public ArrayList<Ticket> getAll() {
		ArrayList<Ticket> tickets = new ArrayList<>();
		String sql = "SELECT * FROM r_ticket;";
		try (Connection conn = ConnectionGenerator.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LocalDate respDate = null;
				try {
					respDate = rs.getDate(9).toLocalDate();
				} catch (NullPointerException e) {
					respDate = null;
				} finally {
					tickets.add(new Ticket(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getString(6),
							rs.getString(5), rs.getDate(7).toLocalDate(), respDate, rs.getBoolean(10)));
				}
			}
			if (tickets.size() == 0) {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public Ticket getById(int id) {
		String sql = "SELECT * FROM r_ticket WHERE id=?;";
		try (Connection conn = ConnectionGenerator.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ticket entity = null;
				LocalDate respDate = null;
				try {
					respDate = rs.getDate(9).toLocalDate();
				} catch (NullPointerException e) {
					respDate = null;
				} finally {
					entity = new Ticket(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4),
							rs.getString(6), rs.getString(5), rs.getDate(7).toLocalDate(), respDate, rs.getBoolean(10));
				}
				return entity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean create(Ticket t) {
		String sql = "INSERT INTO r_ticket (requester_id, amount, description, type, expense_date, request_date, approved) VALUES (?,?,?,?,?,?,?);";
		try (Connection con = ConnectionGenerator.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, t.getRequester());
			ps.setDouble(2, t.getAmount());
			ps.setString(3, t.getDescription());
			ps.setString(4, t.getExpenseType());
			ps.setDate(5, Date.valueOf(t.getExpenseDate()));
			ps.setDate(6, Date.valueOf(LocalDate.now()));
			ps.setBoolean(7, false);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean update(Ticket t) {
		String sql = "UPDATE r_ticket SET " + "requester_id=?, " + "responder_id=?, " + "amount=?, " + "description=?, "
				+ "type=?, " + "expense_date=?, " + "request_date=?, " + "respond_date=?, " + "approved=? WHERE id=?;";
		try (Connection con = ConnectionGenerator.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, t.getRequester());
			ps.setInt(2, t.getResponder());
			ps.setDouble(3, t.getAmount());
			ps.setString(4, t.getDescription());
			ps.setString(5, t.getExpenseType());
			ps.setDate(6, java.sql.Date.valueOf(t.getExpenseDate()));
			ps.setDate(7, java.sql.Date.valueOf(t.getRequestDate()));
			ps.setDate(8, java.sql.Date.valueOf(t.getResponseDate()));
			ps.setBoolean(9, t.getApproved());
			ps.setInt(10, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean delete(Ticket t) {
		String sql = "DELETE FROM r_ticket WHERE id=?;";
		try (Connection con = ConnectionGenerator.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
