package LibraryManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDao {
    public void addUser(User user){
        String sql="insert into users(name,adhaar_no) values(?,?)";

        try(Connection con=Database_Connection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setString(1,user.getName());
            ps.setString(2,user.getAdhaar_no());

            ps.executeUpdate(); System.out.println("User Added Succesfuly...");

        }catch (Exception e){
            e.getMessage();
        }
    }

    public void deleteUser(String adhaar_no) {
        String sql = "delete from users where adhaar_no=?";
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,adhaar_no);

            ps.executeUpdate();
            System.out.println("user Deleted Successfully");
        }catch (Exception e){
            e.getMessage();
        }
    }
}
