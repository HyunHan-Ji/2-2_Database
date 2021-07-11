package week10;

import java.sql.*;

public class HW11_1 {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		HW11_1 ex4 = new HW11_1();
		ex4.Execute2();
		ex4.Execute();
	}

	public HW11_1() {
		try {
			con = DriverManager.getConnection("jdbc:inetdae7://210.115.229.77:2433", "20165164", "gusgks12#$");
			con.setCatalog("20165164");
			System.out.println("Connected");
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

	public void Execute2() {
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("alter table Products add SumPrice int");
		} catch (SQLException se) {
		}
	}

	public void Execute() {
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from Products where productName LIKE 'c%'");

			if (rs.first()) {
				do {
					rs.updateInt("SumPrice", rs.getInt(6) * rs.getInt(7));
					rs.updateRow();

					String col2 = rs.getString(2);
					int col6 = rs.getInt(6);
					int col7 = rs.getInt(7);
					int col11 = rs.getInt(11);

					System.out.println(col2 + " : " + col6 + " : " + col7 + " : " + col11);
				} while (rs.next());
			}
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

	public void close() {
		try {
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		System.out.println("Disconnected ...");
	}

}
