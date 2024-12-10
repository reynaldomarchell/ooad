package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connect.Connect;
import model.Product;

public class ProductController {

	private Connect con;
	
	public ProductController() {
	    con = Connect.getInstance();
	}
	
	public void addProduct(String name, int price, int stock) {
		try {
			PreparedStatement st = con.getConn().prepareStatement(
					"INSERT INTO products (name, price, stock) VALUES (?, ?, ?);");
			st.setString(1, name);
			st.setInt(2, price);
			st.setInt(3, stock);
			st.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ArrayList<Product> getProducts() {
		PreparedStatement st;
		
		try {
			st = con.getConn().prepareStatement("SELECT * FROM products;");
			ResultSet rs = st.executeQuery();
			
			ArrayList<Product> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				list.add(new Product(id, name, price, stock));
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
