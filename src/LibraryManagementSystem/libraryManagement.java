package LibraryManagementSystem;
import java.util.*;
import java.sql.*;

public class libraryManagement {
    public static void main(String[] args) {
        Library lib=new Library();
        Scanner sc=new Scanner(System.in);

        while(true){
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Add User");
            System.out.println("4. Delete User");
            System.out.println("5. Issue Book to an User");
            System.out.println("6. Return Book to the library");
            System.out.println("7. Calculate Fine");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1-> lib.Add_book();
                case 2-> lib.Delete_book();
                case 3-> lib.Add_user();
                case 4->lib.Delete_user();
                case 5->lib.Issue_Book();
                case 6->lib.Return_Book();
                case 7->lib.Calculate_Fine();
                case 8-> {
                    System.out.println("Exiting System....");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
