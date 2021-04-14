package Sign;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

public class DataAccessObject {

	public static boolean create(DataTransferObject dto) throws Exception { // account create

		Connection con = null;
		Statement st = null;

		boolean createFlag = false;

		String name = dto.getName();
		String password = dto.getPassword();
		String address = dto.getAddress();
		String sql = "INSERT INTO member(name, password, address) VALUES";

		try {
			sql += "('" + new String(name.getBytes(), "ISO-8859-1") + "','"
					+ new String(password.getBytes(), "ISO-8859-1") + "','"
					+ new String(address.getBytes(), "ISO-8859-1") + "')";

			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql driver manager.
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account?serverTimezone=Asia/Seoul", "root",
					"yourface1");
			st = (Statement) con.createStatement();
			st.executeUpdate(sql); // execute sql.

			// create success...
			createFlag = true;
			System.out.println("DB Connection success... and Create Success...");

		} catch (Exception e) {
			createFlag = false;
			System.out.println("Connection Error... " + e.getMessage());
		} finally {
			// close statement and connection
			try {
				if (st != null) {
					st.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				System.out.println("Close error... " + e.getMessage());
			}
		}

		return createFlag;
	}

	public static int loginCheck(DataTransferObject dto) throws Exception { // check login

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String name = dto.getName();
		String password = dto.getPassword();
		String sql = "SELECT * FROM member WHERE name = '" + name + "'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account?serverTimezone=Asia/Seoul", "root",
					"yourface1");

			st = (Statement) con.createStatement();
			rs = st.executeQuery(sql);

			if (rs.next()) {
				if (rs.getString(2).equals(password)) { // column
					return 1; // login success
				} else {
					// System.out.println(password + ", " + rs.getString(2));
					return 0; // password error
				}
			}

			return -1; // none ID
		} catch (Exception e) {
			System.out.println("Login check error... " + e.getMessage());
			e.printStackTrace();
		}

		return -2; // DB error
	}

	public static boolean nameCheck(String id) { // check name

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String name = id;
		String sql = "SELECT * FROM member WHERE name = '" + name + "'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account?serverTimezone=Asia/Seoul", "root",
					"yourface1");
			st = (Statement) con.createStatement();
			rs = st.executeQuery(sql);

			if (rs.next()) {
				if (rs.getString(1).equals(id)) { // column
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("Name check error... " + e.getMessage());
			e.printStackTrace();
		}
		return true;
	}
}
