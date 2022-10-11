package CSVInsertBudgetTracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class CSVInsert {
    public static void main(String[] args) {
        String jdbcUrl="jdbc:mysql://192.168.0.45:3306/budgettracker";
        String username="host_user";
        String password="Unity0216Access!";

        String filePath="C:\\Users\\deeps\\Documents\\Java\\LAMP2022\\csv_excel\\uszips.csv";

        int batchSize=20;

        Connection connection=null;


        try{
            connection= DriverManager.getConnection(jdbcUrl,username,password);
            connection.setAutoCommit(false);

            String sql="insert into gdp_table(country_code,ranking,country_name,gdp) values(?,?,?,?)";

            PreparedStatement statement=connection.prepareStatement(sql);

            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

            String lineText=null;
            int count=0;

            lineReader.readLine();
            while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");

                String country_code=data[0];
                String ranking=data[1];
                String country_name=data[2];
                String gdp=data[3];

                statement.setString(1,country_code);
                statement.setInt(2,parseInt(ranking));
                statement.setString(3,country_name);
                statement.setInt(4,parseInt(gdp));
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
}