package com.jdbc.budgettracker.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jdbc.budgettracker.core.BudgetTrackerDto;
import com.jdbc.budgettracker.dao.BudgetTrackerDao;

public class BudgetTrackerMain {

	@SuppressWarnings("null")
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, SQLException {
		// TODO Auto-generated method stub
		BudgetTrackerDao budgetTrackerDao = null;
		BudgetTrackerDto budgetTrackerDto = null;

		Map<Integer, String> initialSwitchMap = new HashMap<>();
		initialSwitchMap.put(1, "Select");
		initialSwitchMap.put(2, "Insert");
		initialSwitchMap.put(3, "Update");
		initialSwitchMap.put(4, "Delete");
		initialSwitchMap.put(5, "Test");
		Scanner initialSwitchScanner = new Scanner(System.in);
		int initialNumInt = 0;
		do {
			// String[] initialList = new String[] {initialSwitchMap.get(1),
			// initialSwitchMap.get(2), initialSwitchMap.get(3), initialSwitchMap.get(4)};

			for (Map.Entry<Integer, String> list : initialSwitchMap.entrySet()) {
				System.out.println(list.getKey() + ":" + list.getValue());
			}

			System.out.print("Select a number: ");
			String initialNumStr = initialSwitchScanner.next();
			initialNumInt = Integer.parseInt(initialNumStr);

		} while (initialNumInt >= 5 || initialNumInt <= 0);

		switch (initialNumInt) {
		case 1:
			// Select
			System.out.println("You chose " + initialSwitchMap.get(1));
			budgetTrackerDao = new BudgetTrackerDao();
			Map<Integer, String> selectSwitchMap = new HashMap<>();
			selectSwitchMap.put(1, "Select All");
			selectSwitchMap.put(2, "Select by Date");
			selectSwitchMap.put(3, "Select by Store");
			selectSwitchMap.put(4, "Select by Product");
			selectSwitchMap.put(5, "Select by Type");
			selectSwitchMap.put(6, "Select by Price");

			Scanner selectScanner = new Scanner(System.in);
			int selectScannerNumInt = 0;
			do {
				// String[] selectScanList = new String[] {selectSwitchMap.get(1),
				// selectSwitchMap.get(2), selectSwitchMap.get(3), selectSwitchMap.get(4),
				// selectSwitchMap.get(5)};

				for (Map.Entry<Integer, String> selectScanlist : selectSwitchMap.entrySet()) {
					System.out.println(selectScanlist.getKey() + ":" + selectScanlist.getValue());
				}

				System.out.print("Select a number: ");
				String selectScannerNumStr = selectScanner.next();
				selectScannerNumInt = Integer.parseInt(selectScannerNumStr);

			} while (selectScannerNumInt > 7 || selectScannerNumInt <= 0);

			do {
				if (selectScannerNumInt == 1) {
					System.out.println("Select All----------");
					List<BudgetTrackerDto> btd = budgetTrackerDao.selectAll();
					for (BudgetTrackerDto b : btd) {
						System.out.println(b.getId() + ", " + b.getDate() + ", " + b.getStoreName() + ", "
								+ b.getProductName() + ", " + b.getProductType() + ", " + b.getPrice());
					}

				} else if (selectScannerNumInt == 2) {
					System.out.println("Select by date ----------");
					System.out.print("Input Date 1 (yyyy-MM-dd): ");
					Scanner selectByDateScanner = new Scanner(System.in);
					String selectByDateScannerStr1 = selectByDateScanner.next();
//					Date selectByDateScannerDate1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(selectByDateScannerStr1);
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(selectByDateScannerStr1);
					System.out.println(selectByDateScannerStr1);
					budgetTrackerDto = new BudgetTrackerDto();
					budgetTrackerDto.setDate(date1);

					System.out.print("Input Date 2 (yyyy-MM-dd): ");
//					Scanner selectByDateScanner = new Scanner(System.in);
					String selectByDateScannerStr2 = selectByDateScanner.next();
//					Date selectByDateScannerDate2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(selectByDateScannerStr2);
					Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(selectByDateScannerStr2);
					System.out.println(selectByDateScannerStr2);
					budgetTrackerDto.setDate2(date2);
					budgetTrackerDao = new BudgetTrackerDao();
					budgetTrackerDao.selectByDates(budgetTrackerDto);

//					budgetTrackerDto.setDate(insertDate);

				} else if (selectScannerNumInt == 3) {
					System.out.println("Select by Store ----------");
					Scanner storeSelectScanner = new Scanner(System.in);
					System.out.print("Input a product name: ");
					String storeSelectScannerStr = storeSelectScanner.next();
					budgetTrackerDto = new BudgetTrackerDto();
					budgetTrackerDto.setStoreName(storeSelectScannerStr);
					List<BudgetTrackerDto> btd = budgetTrackerDao.selectByStoreName(budgetTrackerDto);
					for (BudgetTrackerDto b : btd) {
						System.out.println(b.getId() + ", " + b.getDate() + ", " + b.getStoreName() + ", "
								+ b.getProductName() + ", " + b.getProductType() + ", " + b.getPrice());
					}

				} else if (selectScannerNumInt == 4) {
					System.out.println("Select by Product ----------");
					Scanner productSelectScanner = new Scanner(System.in);
					System.out.print("Input a product name: ");
					String productSelectScannerStr = productSelectScanner.next();
					budgetTrackerDto = new BudgetTrackerDto();
					budgetTrackerDto.setProductName(productSelectScannerStr);
					List<BudgetTrackerDto> btd = budgetTrackerDao.selectByProductName(budgetTrackerDto);
					for (BudgetTrackerDto b : btd) {
						System.out.println(b.getId() + ", " + b.getDate() + ", " + b.getStoreName() + ", "
								+ b.getProductName() + ", " + b.getProductType() + ", " + b.getPrice());
					}

				}

			} while (selectScannerNumInt > 7 || selectScannerNumInt <= 0);
			break;
		case 2:
			// Insert
			int insertcannerInt = 0;
			System.out.println("You chose " + initialSwitchMap.get(2));
			budgetTrackerDto = new BudgetTrackerDto();

			Scanner insertScanner = new Scanner(System.in);
			System.out.print("Input an ID: ");
			int insertScannerInt = insertScanner.nextInt();
			budgetTrackerDto.setId(insertScannerInt);

			System.out.print("Input Date (yyyy-MM-dd): ");
			String insertScannerStr = insertScanner.next();
			Date insertDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(insertScannerStr);
			budgetTrackerDto.setDate(insertDate);

			System.out.print("Input a store name: ");
			insertScannerStr = insertScanner.next();
			budgetTrackerDto.setStoreName(insertScannerStr);

			System.out.print("Input a product name: ");
			insertScannerStr = insertScanner.next();
			budgetTrackerDto.setProductName(insertScannerStr);

			System.out.print("Input a product type: ");
			insertScannerStr = insertScanner.next();
			budgetTrackerDto.setProductType(insertScannerStr);

			System.out.print("Input price: ");
			insertScannerStr = insertScanner.next();
			insertcannerInt = Integer.parseInt(insertScannerStr);
			budgetTrackerDto.setPrice(insertcannerInt);

			budgetTrackerDao = new BudgetTrackerDao();
			budgetTrackerDao.insertIntoTable(budgetTrackerDto);

		}

	}
}