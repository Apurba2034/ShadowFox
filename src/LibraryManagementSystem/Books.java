package LibraryManagementSystem;

public class Books {
    private String isbn;
    private String title;
    private String author;
    private boolean book_available;

    public Books(String isbn,String title,String author){
        this.isbn=isbn;
        this.title=title;
        this.author=author;
        this.book_available=true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBook_available() {
        return book_available;
    }
}
