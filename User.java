import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private ArrayList<Book> issuedBooks = new ArrayList<>();

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void issueBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }

    public ArrayList<Book> getIssuedBooks() {
        return issuedBooks;
    }

    @Override
    public String toString() {
        return "User ID: " + id + ", Name: " + name + ", Issued Books: " + issuedBooks.size();
    }
}
