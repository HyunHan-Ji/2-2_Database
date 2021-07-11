package week10;

import java.sql.*;

public class Ex1 {

	public static void main(String[] args) {
		String url = "jdbc:sqlserver://210.115.229.77:2433;DatabaseName=Northwind";
		String query = "Select OrderID, CustomerID, EmployeeID from orders";

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, "20165164", "gusgks12#$");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int col1 = rs.getInt(1);
				String col2 = rs.getString(2);
				int col3 = rs.getInt(3);
				System.out.println("   " + col1 + " : " + col2 + " : " + col3);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
		}
	}
}
