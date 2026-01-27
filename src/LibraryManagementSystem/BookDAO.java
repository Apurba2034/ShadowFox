package LibraryManagementSystem;
import java.sql.*;

public class BookDAO {
    public void addBook(Books book) {
        String sql = "insert into books(isbn,title,author,book_available) values(?,?,?,?)";
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setBoolean(4, book.isBook_available());


            ps.executeUpdate();
            System.out.println("Book Added Succesfuly...");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteBook(String isbn) {
        String sql = "delete from books where isbn=?";
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,isbn);

            ps.executeUpdate();
            System.out.println("Book Deleted Successfully");
        }catch (Exception e){
            e.getMessage();
        }
    }
}
