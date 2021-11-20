package test_package;

import java.sql.*;

public class TestMain {
	public static void main(String[] args) throws SQLException {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://192.168.0.45:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false", "host_user", "Unity0216Access!");
		
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from phone_book where address = 'Yokohama'");
		
		while(myRs.next()) {
			System.out.println(myRs.getString("first_name") + ", " + myRs.getString("last_name"));
			
			for (int y = 1; y <=5; y++) {
				
			}
		}
	}
}