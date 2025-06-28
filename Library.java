import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== Library Management ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Books");
            System.out.println("6. View Users");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> registerUser();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> viewBooks();
                case 6 -> viewUsers();
                case 7 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 7);
    }

    private void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private void registerUser() {
        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();

        users.add(new User(id, name));
        System.out.println("User registered successfully!");
    }

    private void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int bookId = scanner.nextInt();
        Book bookToIssue = getBookById(bookId);

        if (bookToIssue == null || bookToIssue.isIssued()) {
            System.out.println("Book not available.");
            return;
        }

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        User user = getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        bookToIssue.setIssued(true);
        user.issueBook(bookToIssue);
        System.out.println("Book issued to " + user.getName());
    }

    private void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();
        Book book = getBookById(bookId);

        if (book == null || !book.isIssued()) {
            System.out.println("Book is not issued.");
            return;
        }

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        User user = getUserById(userId);

        if (user != null && user.getIssuedBooks().contains(book)) {
            book.setIssued(false);
            user.returnBook(book);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book was not issued to this user.");
        }
    }

    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (User user : users) {
            System.out.println(user);
            for (Book b : user.getIssuedBooks()) {
                System.out.println("   - " + b.getTitle());
            }
        }
    }

    private Book getBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    private User getUserById(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }
}
