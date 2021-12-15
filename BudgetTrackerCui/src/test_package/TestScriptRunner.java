package test_package;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jdbc.budgettracker.core.BudgetTrackerDto;
import com.jdbc.budgettracker.dao.BudgetTrackerDao;

public class TestScriptRunner {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		selectAll();
	}
	
	private static Connection getConnection() {

		try {
			// get db properties
			Properties props = new Properties();
			props.load(new FileInputStream(
					"/home/yosuke/git/BudgetTrackerCui/BudgetTrackerCui/sql/config_budgettracker.properties"));

			String user = props.getProperty("user");
			String password = props.getProperty("password");
			String dburl = props.getProperty("dburl");
//			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(dburl, user, password);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	// select all
		public static List<BudgetTrackerDto> selectAll() throws FileNotFoundException, IOException {
			List<BudgetTrackerDto> budgetList = new ArrayList<>();
			Reader reader = new BufferedReader(new FileReader("/home/yosuke/git/BudgetTrackerCui/BudgetTrackerCui/sql/selectAll.sql"));
			
			

			try (Connection conn = TestScriptRunner.getConnection();
					PreparedStatement ps = conn.prepareStatement(reader)) {
				ScriptRunner sr = new ScriptRunner(con);
				

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						btd = new BudgetTrackerDto();
						btd.setId(rs.getInt("ID"));
						btd.setDate(rs.getDate("Date"));
						btd.setStoreName(rs.getString("StoreName"));
						btd.setProductName(rs.getString("ProductName"));
						btd.setProductType(rs.getString("ProductType"));
						btd.setPrice(rs.getInt("Price"));

						budgetList.add(btd);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
			return budgetList;
		}

}
