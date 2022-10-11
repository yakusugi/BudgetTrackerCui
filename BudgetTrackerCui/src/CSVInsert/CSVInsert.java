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
        props.load(new FileInputStream("C:\\workspace_budget_tracker_cui_project\\BudgetTrackerCui\\BudgetTrackerCui\\sql\\config_budgettracker.properties"));

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");
    	
//        String jdbcUrl="jdbc:mysql://192.168.0.45:3306/testdb";
//        String username="host_user";
//        String password="Unity0216Access!";

        String filePath="C:\\Users\\deeps\\Documents\\Java\\LAMP2022\\csv_excel\\uszips.csv";

        int batchSize=20;

        Connection connection=null;


        try{
            connection= DriverManager.getConnection(dburl,user,password);
            connection.setAutoCommit(false);

            String sql="insert into locations_tbl(city,province,country,postal_code) values(?,?,?,?)";

            PreparedStatement statement=connection.prepareStatement(sql);

            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

            String lineText=null;
            int count=0;

            lineReader.readLine();
            while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");

                String city=data[0];
                String province=data[1];
                String country=data[2];
                String postal_code=data[3];

                statement.setString(1,city);
                statement.setString(2,province);
                statement.setString(3,country);
                statement.setInt(4,parseInt(postal_code));
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