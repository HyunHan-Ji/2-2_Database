package week10;

import java.sql.*;

public class Ex3 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433";
		String query = "Select OrderID, CustomerID, EmployeeID from orders";
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, "20165164", "gusgks12#$");
			con.setCatalog("Northwind");

			System.out.println("Connected to DB...");

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(query);
			rs.afterLast();

			if (rs.isAfterLast() == true) {
				while (rs.previous()) {
					int col1 = rs.getInt(1);
					String col2 = rs.getString(2);
					int col3 = rs.getInt(3);

					System.out.println("   " + col1 + " : " + col2 + " : " + col3);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}
