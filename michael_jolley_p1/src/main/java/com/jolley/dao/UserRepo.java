package com.jolley.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jolley.models.User;
import com.jolley.utils.ConnectionGenerator;

public class UserRepo implements UserDao<User>{
	
	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> users = new ArrayList<>();
		String sql = "SELECT * FROM r_user;";
		try(Connection conn = ConnectionGenerator.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				users.add(new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10)));
			}
			if (users.size()==0) {return null;}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}


	@Override
	public User getById(int id) {
		String sql = "SELECT * FROM r_user WHERE id = ?;";
		User entity = null;
		try(Connection conn = ConnectionGenerator.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				entity = new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@Override
	public User getByEmail(String email) {
		String sql = "SELECT * FROM r_user WHERE email = ?;";
		User entity = null;
		try(Connection conn = ConnectionGenerator.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				entity = new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Boolean create(User t) {
		String sql ="INSERT INTO r_user (fname, lname, email, password, position, address, town, state, zip) VALUES (?,?,?,?,?,?,?,?,?);";
		try(Connection con = ConnectionGenerator.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, t.getfName());
			ps.setString(2, t.getlName());
			ps.setString(3, t.getEmail());
			ps.setString(4, t.getPass());
			ps.setString(5, t.getPosition());
			ps.setString(6, t.getAddress());
			ps.setString(7, t.getCity());
			ps.setString(8, t.getState());
			ps.setInt(9, t.getZip());
			ps.execute();
			return true;
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean update(User t) {
		String sql = "UPDATE r_user SET fname=?, lname=?, email=?, password=?, position=?, address=?, town=?, state=?, zip=? WHERE id=?;";
		try(Connection con = ConnectionGenerator.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, t.getfName());
			ps.setString(2, t.getlName());
			ps.setString(3, t.getEmail());
			ps.setString(4, t.getPass());
			ps.setString(5, t.getPosition());
			ps.setString(6, t.getAddress());
			ps.setString(7, t.getCity());
			ps.setString(8, t.getState());
			ps.setInt(9, t.getZip());
			ps.setInt(10, t.getId());
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean delete(User t) {
		String sql = "DELETE FROM r_user WHERE id=?;";
		try (Connection con = ConnectionGenerator.getConnection()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, t.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
