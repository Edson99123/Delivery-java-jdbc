package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("select * from tb_order");
			
		while (rs.next()) {
			

			Order order = instantiateOrder(rs);
			
			System.out.println(order);
			//System.out.println(rs.getLong("Id") + ", " + rs.getString("Name"));
		}
	}
	private static Order instantiateOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setLatitude(rs.getDouble("latitude"));
		order.setLongitude(rs.getDouble("longitude"));
		order.setMoment(rs.getTimestamp("moment").toInstant());
		order.setStatus(OrderStatus.values()[rs.getInt("status")]);
		
		return order;
	}
	
	//M�todo que retorna uma instancia da classe produto
	private static Product instantiateProduct(ResultSet rs) throws SQLException {

		Product p = new Product();
		p.setId(rs.getLong("Id"));
		p.setDescription(rs.getString("description"));
		p.setName(rs.getString("name"));
		p.setImageUri(rs.getString("image_uri"));;
		p.setPrice(rs.getDouble("price"));
		return p;
	}
}
