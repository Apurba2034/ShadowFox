package Shadofox_project;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}


public class ContactManagementSystem {
    static ArrayList<Contact> contacts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        while(true){
            System.out.println("\n*** Contact Management System ***");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addContact();
                case 2 -> viewContacts();
                case 3 -> searchContact();
                case 4 -> updateContact();
                case 5 -> deleteContact();
                case 0 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid choice!");
            }

        }
    }


    static void addContact() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        if (isDuplicatePhone(phone)) {
            System.out.println("Contact with this phone number already exists!");
            return;
        }

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Invalid email format!");
            return;
        }
        if (isDuplicateEmail(email)) {
            System.out.println("Contact with this email already exists!");
            return;
        }

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully.");
    }


    static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        for (Contact c : contacts) {
            System.out.println(c);
        }
    }


    static void searchContact() {
        System.out.print("Enter name to search: ");
        String searchName = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(searchName)) {
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Contact not found.");
        }
    }


    static void updateContact() {
        System.out.print("Enter phone number of contact to update: ");
        String phone = sc.nextLine();

        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                System.out.print("Enter new name: ");
                String new_name=sc.nextLine();
                c.setName(new_name);

                System.out.print("Enter new email: ");
                String email = sc.nextLine();

                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    System.out.println("Invalid email format or Email already exits with another contact!");
                    return;
                }
                if (isDuplicateEmail(email)) {
                    System.out.println("Contact with this email already exists!");
                    return;
                }

                c.setEmail(email);
                System.out.println("Contact updated successfully.");
                return;
            }
        }

        System.out.println("Contact not found.");
    }


    static void deleteContact() {
        System.out.print("Enter phone number to delete: ");
        String phone = sc.nextLine();

        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                contacts.remove(c);
                System.out.println("Contact deleted successfully.");
                return;
            }
        }

        System.out.println("Contact not found.");
    }


    static boolean isDuplicatePhone(String phone) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    static boolean isDuplicateEmail(String email) {
        for (Contact c : contacts) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}
