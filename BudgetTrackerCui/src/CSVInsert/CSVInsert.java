package CSVInsert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class CSVInsert {
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	
    	Properties props = new Properties();
        props.load(new FileInputStream("/home/yosuke/git/BudgetTrackerCui/BudgetTrackerCui/sql/config_budgettracker.properties"));

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");
    	
//        String jdbcUrl="jdbc:mysql://192.168.0.45:3306/testdb";
//        String username="host_user";
//        String password="Unity0216Access!";

        String filePath="/home/yosuke/Downloads/budget_table.csv";

        int batchSize=20;

        Connection connection=null;


        try{
            connection= DriverManager.getConnection(dburl,user,password);
            connection.setAutoCommit(false);

            String sql="insert into budget_table(id,Date,StoreName,ProductName, ProductType, Price) values(?,?,?,?,?,?)";

            PreparedStatement statement=connection.prepareStatement(sql);

            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

            String lineText=null;
            int count=0;

            lineReader.readLine();
            while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");

                String id=data[0];
                String Date=data[1];
                String StoreName=data[2];
                String ProductName=data[3];
                String ProductType=data[4];
                String Price=data[5];

                statement.setInt(1,parseInt(id));
                statement.setString(2,parseString(Date));
                statement.setString(3,StoreName);
                statement.setString(4,ProductName);
                statement.setString(5,ProductType);
                statement.setInt(6,parseInt(Price));
                statement.addBatch();
                
                if(count%batchSize==0){
                    statement.executeBatch();
                }
                
            }
            
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data has been inserted successfully.");

        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }

	private static String parseDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String parseString(String date) {
		// TODO Auto-generated method stub
		return null;
	}
}