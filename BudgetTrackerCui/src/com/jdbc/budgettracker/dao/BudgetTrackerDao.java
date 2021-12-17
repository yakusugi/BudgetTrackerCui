package com.jdbc.budgettracker.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jdbc.budgettracker.core.BudgetTrackerDto;

public class BudgetTrackerDao {
	BudgetTrackerDto btd;
	private Connection myConn;
	private PreparedStatement mySmt;

	private static final String SELECTALL = "select * from budget_table;";
	private static final String SELECTBYSTORENAME = "select * from budget_table where storeName = ?;";
	private static final String SELECTBYPRODUCTNAME = "select * from budget_table where productName = ?;";
	private static final String SELECTBYDATES = "SELECT * FROM budget_table WHERE Date >= ? AND Date <= ?;";
	private static final String INSERTINTOBUDGETTABLE = "INSERT INTO budget_table(id,Date,StoreName, ProductName, ProductType, Price) VALUE (?,?,?,?,?,?);";

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
	public List<BudgetTrackerDto> selectAll() throws FileNotFoundException, IOException {
		List<BudgetTrackerDto> budgetList = new ArrayList<>();

		try (Connection conn = BudgetTrackerDao.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECTALL)) {

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

	public List<BudgetTrackerDto> selectByStoreName(BudgetTrackerDto btd) throws FileNotFoundException, IOException {
		List<BudgetTrackerDto> budgetList = new ArrayList<>();

		try (Connection conn = BudgetTrackerDao.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECTBYSTORENAME)) {
			ps.setString(1, btd.getStoreName());
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
	
	public List<BudgetTrackerDto> selectByProductName(BudgetTrackerDto btd) throws FileNotFoundException, IOException {
		List<BudgetTrackerDto> budgetList = new ArrayList<>();

		try (Connection conn = BudgetTrackerDao.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECTBYPRODUCTNAME)) {
			ps.setString(1, btd.getProductName());
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
	
	public List<BudgetTrackerDto> selectByDates(BudgetTrackerDto btd) throws FileNotFoundException, IOException {
		List<BudgetTrackerDto> budgetList = new ArrayList<>();

		try (Connection conn = BudgetTrackerDao.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECTBYDATES)) {
			btd = new BudgetTrackerDto();
			Date date = btd.getDate();
			long timeInMilliSeconds = date.getTime();
			java.sql.Date sqlDate1 = new java.sql.Date(timeInMilliSeconds);
			ps.setDate(1, sqlDate1);
			Date date2 = btd.getDate2();
			long timeInMilliSeconds2 = date2.getTime();
			java.sql.Date sqlDate2 = new java.sql.Date(timeInMilliSeconds2);
			ps.setDate(2, sqlDate2);
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

	public int insertIntoTable(BudgetTrackerDto btd) throws SQLException {
		int rowsCount = 0;

		PreparedStatement pstmt;
		try {
			// DBに接続
			myConn = BudgetTrackerDao.getConnection();

			pstmt = myConn.prepareStatement(INSERTINTOBUDGETTABLE);
			pstmt.setInt(1, btd.getId());
			Date date = btd.getDate();
			long timeInMilliSeconds = date.getTime();
			java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
			pstmt.setDate(2, sqlDate);
			pstmt.setString(3, btd.getStoreName());
			pstmt.setString(4, btd.getProductName());
			pstmt.setString(5, btd.getProductType());
			pstmt.setInt(6, btd.getPrice());
			// SQL文発行
			pstmt.executeUpdate();
			System.out.println("Suucessfully added");
			pstmt.close();

			// rowsCount = pstmt.executeUpdate(sql);
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