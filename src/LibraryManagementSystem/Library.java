package LibraryManagementSystem;
import java.time.LocalDate;
import java.util.*;

public class Library {
    Scanner sc=new Scanner(System.in);
    BookDAO dao=new BookDAO();
    UserDao Udao=new UserDao();
    IssueDAO issue_dao=new IssueDAO();

    public void Add_book(){

        System.out.println("enter book isbn number: ");
        String isbn=sc.nextLine();
        System.out.println("enter book name: ");
        String title=sc.nextLine();
        System.out.println("enter book author name: ");
        String author=sc.nextLine();


        Books book=new Books(isbn,title,author);
        dao.addBook(book);

    }

    public void Delete_book(){
        System.out.print("Enter book isbn number to delete book: ");
        String isbn = sc.nextLine();
        dao.deleteBook(isbn);
    }

    public void Add_user(){
        System.out.print("Enter user name who take book from library: ");
        String name = sc.nextLine();
        System.out.print("Enter Adhaar Number: ");
        String adhaarNo = sc.nextLine();
        User user=new User(name,adhaarNo);
        Udao.addUser(user);
    }

    public void Delete_user(){
        System.out.print("Enter Adhaar Number to delete user: ");
        String adhaarNo = sc.nextLine();
        dao.deleteBook(adhaarNo);
    }

    public void Issue_Book(){
        System.out.println("enter book isbn number: ");
        String isbn=sc.nextLine();
        System.out.println("enter user adhaar number: ");
        String adhaar_no=sc.nextLine();
        issue_dao.issueBook(isbn,adhaar_no);

    }

    public void Calculate_Fine(){
        System.out.println("enter issue number(issue_id) for calculate fine: ");
        int issue_id=sc.nextInt();
        issue_dao.calculateFine(issue_id);
    }

    public void Return_Book(){
        System.out.println("enter issue number(issue_id) for Return Book: ");
        int issue_id=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter exact date(YYYY-MM-DD) when book actualy return: ");
        String Exact_Date = sc.nextLine();
        issue_dao.returnBook(issue_id,Exact_Date);
    }
}
