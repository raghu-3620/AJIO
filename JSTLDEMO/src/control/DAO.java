package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import control.ConnectionFactory;
import control.User;

public class DAO {
	public static List<Item> itemList() throws ClassNotFoundException, SQLException {
		List<Item> list = new ArrayList<Item>();
		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement stmt = con.prepareStatement("select * from fruits");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Item li = new Item(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
			list.add(li);
		}
		return list;
	}
	public static TreeSet<String> catList() throws ClassNotFoundException, SQLException{
		TreeSet<String> s= new TreeSet<String>();
		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement stmt = con.prepareStatement("select distinct(category) from fruits");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			
			s.add(rs.getString(1));
		}
		return s;
		
	}
	public static List<Item> itemListByCat(String str) throws ClassNotFoundException, SQLException {
		List<Item> list = new ArrayList<Item>();
		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement stmt = con.prepareStatement("select * from fruits where category = ?");
		stmt.setString(1, str);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Item li = new Item(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
			list.add(li);
		}
		return list;
	
	}
	public static User isValidUser(String un, String pwd) throws ClassNotFoundException, SQLException {
		User u = null;
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = con.prepareStatement("select * from users where name = ? and pwd =?");
		stmt.setString(1, un);
		stmt.setString(2, pwd);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

		}
		con.close();
		return u;
	}

}
