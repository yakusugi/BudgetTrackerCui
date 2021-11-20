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

    private static final String SQL = "select * from data2020;";

    public List<BudgetTrackerDto> selectAll() throws FileNotFoundException, IOException {
        List<BudgetTrackerDto> budgetList = new ArrayList<>();



        // get db properties
        Properties props = new Properties();
        props.load(new FileInputStream("/home/yosuke/git/BudgetTrackerCui/BudgetTrackerCui/sql/config.properties"));

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");

        try (Connection conn = DriverManager.getConnection(dburl, user,
                password); PreparedStatement ps = conn.prepareStatement(SQL)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    btd = new BudgetTrackerDto();
                    btd.setDate(rs.getDate("Date"));
                    btd.setStoreName(rs.getString("StoreName"));
                    btd.setProductName(rs.getString("ProductName"));
                    btd.setProductType(rs.getString("Type"));
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