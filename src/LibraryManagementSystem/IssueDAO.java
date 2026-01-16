package LibraryManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class IssueDAO {

    public int getBookID(String isbn){
        String sql="select book_id from books where isbn=?";

        try(Connection con=Database_Connection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);){

            ps.setString(1,isbn);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                return rs.getInt("book_id");
            }else {
                System.out.println("No book for this isbn number...");
            }
        }catch(Exception e){
            e.getMessage();
        }
        return -1;
    }
    public int getUserID(String adhaar_no){
        String sql="select user_id from users where adhaar_no=?";

        try(Connection con=Database_Connection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);){

            ps.setString(1,adhaar_no);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                return rs.getInt("user_id");
            }else{
                System.out.println("User not exits for this adhaar number... ");
            }
        }catch(Exception e){
            e.getMessage();
        }
        return -1;
    }

    public void issueBook(String isbn,String adhaar_no){
        int book_id=getBookID(isbn);
        int user_id=getUserID(adhaar_no);

        if(book_id<=0 || user_id<=0){
            System.out.println("invalid book or user");
            return;
        }

        String sql="insert into issues(book_id,user_id,issue_date,return_date) values(?,?,?,?)";
        try(Connection con=Database_Connection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql);){
            LocalDate issueDate=LocalDate.now();
            LocalDate returnDate=issueDate.plusDays(7);

            ps.setInt(1,book_id);
            ps.setInt(2,user_id);
            ps.setString(3,issueDate.toString());
            ps.setString(4,returnDate.toString());

            ps.executeUpdate();
            System.out.println("Book issued for the user successfully");

        }catch (Exception e){
            e.getMessage();
        }
    }

    public void returnBook(int issue_id,String exact_returnDate){

        String sql="update issues set exact_return_date=? where issue_id=?";
        try(Connection con=Database_Connection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);){


            ps.setString(1,LocalDate.parse(exact_returnDate).toString());
            ps.setInt(2,issue_id);


            ps.executeUpdate();
            System.out.println("Book return Succesfully...");

        }catch (Exception e){
            e.getMessage();
        }
    }
    public void calculateFine(int issue_id){
        String sql="select return_date,exact_return_date from issues where issue_id=?";

        try(Connection con=Database_Connection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);){

            ps.setInt(1,issue_id);
            ResultSet rs=ps.executeQuery();

            if(!rs.next()){
                System.out.println("invalid issue id");
                return;
            }

            String exactReturn=rs.getString("exact_return_date");

            if (exactReturn == null) {
                System.out.println("Book not returned yet");
                return;
            }
            LocalDate ReturnDate = LocalDate.parse(rs.getString("return_date"));
            LocalDate ExactReturnDate = LocalDate.parse(exactReturn);

            long dueDays= ChronoUnit.DAYS.between(ReturnDate,ExactReturnDate);
            if (dueDays > 0) {
                int fine = (int) dueDays * 5;
                System.out.println("Overdue Days : " + dueDays);
                System.out.println("Fine Amount : ₹" + fine);
            } else {
                System.out.println("No fine. Book returned on time.");
            }


        }catch (Exception e){
            e.getMessage();
        }
    }
}
