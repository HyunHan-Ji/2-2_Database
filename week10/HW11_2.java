package week10;

import java.sql.*;
import java.util.*;

public class HW11_2 {
	public static void main(String[] args) {
		String url = "jdbc:sqlserver://210.115.229.77:2433;DatabaseName=Northwind";
		String query = "select CompanyName, ContactName, Phone from Customers where Country='";

		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 나라 입력: ");
		String s = scan.next();
		query += s + "'";

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, "20165164", "123123");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				String col3 = rs.getString(3);
				System.out.println("   " + col1 + " : " + col2 + " : " + col3);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
		}
	}
}
