package LibraryManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connection {
    private static final String url = "jdbc:mysql://localhost:3306/library_db";
    private static final String username = "root";
    private static final String password = "Apurba@2003";

   static {
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           System.out.println(e.getMessage());
       }
   }
   public static Connection getConnection() throws Exception{
       return DriverManager.getConnection(url,username,password);
   }

}
