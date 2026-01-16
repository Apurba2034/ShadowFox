package LibraryManagementSystem;

public class User {
    private String name;
    private String adhaar_no;

    public User(String name,String adhaar_no){
        this.name=name;
        this.adhaar_no=adhaar_no;
    }

    public String getName() {
        return name;
    }

    public String getAdhaar_no() {
        return adhaar_no;
    }
}
