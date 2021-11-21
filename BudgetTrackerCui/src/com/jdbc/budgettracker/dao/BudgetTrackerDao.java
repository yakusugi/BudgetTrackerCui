package com.jdbc.budgettracker.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jdbc.budgettracker.core.BudgetTrackerDto;

public class BudgetTrackerDao {
	BudgetTrackerDto btd;
	private Connection myConn;
	private Statement mySmt;

	private static final String SQL = "select * from budget_table;";

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

	public List<BudgetTrackerDto> selectAll() throws FileNotFoundException, IOException {
		List<BudgetTrackerDto> budgetList = new ArrayList<>();

		try (Connection conn = BudgetTrackerDao.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL)) {

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

	// Insert
	public int insertIntoTable(BudgetTrackerDto budgetTrackerDto) {
		int rowsCount = 0;

		String sql = "INSERT INTO budget_table(id,Date,StoreName, ProductName, ProductType, Price) " + "VALUES('"
				+ btd.getId() + "','" + btd.getStoreName() + "'," + btd.getProductName() + "'," + btd.getProductType()
				+ "'," + btd.getPrice() + ")";

		try {
			// DBに接続
			myConn = BudgetTrackerDao.getConnection();
			mySmt = myConn.createStatement();
			// SQL文発行
			rowsCount = mySmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Errorが発生しました！\n" + e + "\n");
		} finally {
			// リソースの開放
			if (mySmt != null) {
				try {
					mySmt.close();
				} catch (SQLException ignore) {
				}
			}
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException ignore) {
				}
			}
		}

		return 0;

	}

}