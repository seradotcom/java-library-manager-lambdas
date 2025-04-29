import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        // Create a new Library instance
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Create and add some Book instances
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        library.addBook(new Book("1984", "George Orwell", 1949));
        library.addBook(new Book("Moby Dick", "Herman Melville", 1851));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", 1951));

        // Create and add some EBook instances
        library.addBook(new EBook("Clean Code", "Robert C. Martin", 2008, 15.5)); // Add an EBook instance
        library.addBook(new EBook("Effective Java", "Joshua Bloch", 2018, 10.0)); // Add another EBook instance

        int choice;

        do {
            printMenu();
            System.out.print("Enter your choice: ");
            try {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    choice = -1;
                }
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addBook(library, scanner);
                        break;
                    case 2:
                        findBooksByAuthor(library, scanner);
                        break;
                    case 3:
                        findBooksByTitleSubstring(library, scanner);
                        break;
                    case 4:
                        findBooksBeforeYear(library, scanner);
                        break;
                    case 5:
                        sortBooks(library, scanner);
                        break;
                    case 6:
                        library.printAllBooks();
                        break;
                    case 0:
                        System.out.println("Exiting application. \n" +
                                "Authors Team 44: \n" +
                                "Sergio Emmanuel Ramirez Anaya \n" +
                                "Diego Rodriguez Romero \n" +
                                "Diego Alejandro Calvario Acaves \n" +
                                "Santiago Lopez Campos \n" +
                                "Gael Venegas Gomez.");
                        break;
                    default:
                         if (choice != -1) {
                           System.out.println("Invalid choice number. Please try again.");
                         }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please enter a number for the choice.");
                scanner.nextLine();
                choice = -1;
            }
            System.out.println();

        } while (choice != 0);

        scanner.close(); 
    }

    private static void printMenu() {
        System.out.println("--- Library Menu ---");
        System.out.println("1. Add New Book/EBook");
        System.out.println("2. Find Books by Author");
        System.out.println("3. Find Books by Title Containing");
        System.out.println("4. Find Books Published Before Year");
        System.out.println("5. Sort Books");
        System.out.println("6. Print All Books");
        System.out.println("0. Exit");
        System.out.println("--------------------");
    }

    private static void addBook(BookManager library, Scanner scanner) {
        try {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            int year = -1;
            while (year < 0) {
                System.out.print("Enter publication year (e.g., 1991): ");
                if (scanner.hasNextInt()) {
                    year = scanner.nextInt();
                    if (year < 0) System.out.println("Year cannot be negative.");
                } else {
                    System.out.println("Invalid year. Please enter a number.");
                }
                scanner.nextLine(); 
            }

            System.out.print("Is this an EBook? (yes/no): ");
            String isEbook = scanner.nextLine();

            if (isEbook.equalsIgnoreCase("yes")) {
                double fileSize = -1.0;
                while (fileSize < 0) {
                    System.out.print("Enter file size in MB (e.g., 10.5): ");
                     if (scanner.hasNextDouble()) {
                         fileSize = scanner.nextDouble();
                          if (fileSize < 0) System.out.println("File size cannot be negative.");
                     } else {
                         System.out.println("Invalid file size. Please enter a number.");
                     }
                    scanner.nextLine();
                }
                library.addBook(new EBook(title, author, year, fileSize));
                System.out.println("EBook added successfully!");
            } else {
                library.addBook(new Book(title, author, year));
                System.out.println("Book added successfully!");
            }
        } catch (Exception e) { 
             System.out.println("An error occurred while adding the book. Please try again.");
             if(scanner.hasNextLine()) scanner.nextLine();
        }
    }

    private static void findBooksByAuthor(BookManager library, Scanner scanner) {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        List<Book> booksByAuthor = library.findBooksByAuthor(author);
        System.out.println("\n--- Books by '" + author + "' ---");
        printBookList(booksByAuthor, "No books found by this author.");
        System.out.println("---------------------------------------");
    }

     private static void findBooksByTitleSubstring(BookManager library, Scanner scanner) {
        System.out.print("Enter title substring to search for: ");
        String substring = scanner.nextLine();
        List<Book> booksContaining = library.findBooksByTitleContaining(substring);
        System.out.println("\n--- Books with title containing '" + substring + "' ---");
        printBookList(booksContaining, "No books found with this title substring.");
        System.out.println("---------------------------------------");
    }

    private static void findBooksBeforeYear(BookManager library, Scanner scanner) {
         int year = -1;
         while (year < 0) {
             System.out.print("Enter cutoff year (find books published BEFORE this year): ");
              if (scanner.hasNextInt()) {
                 year = scanner.nextInt();
                 if (year < 0) System.out.println("Year cannot be negative.");
             } else {
                 System.out.println("Invalid year. Please enter a number.");
             }
             scanner.nextLine();
         }

        List<Book> booksBefore = library.findBooksPublishedBefore(year);
        System.out.println("\n--- Books published before " + year + " ---");
        printBookList(booksBefore, "No books found published before this year.");
        System.out.println("----------------------------------");
    }


    private static void sortBooks(BookManager library, Scanner scanner) {
        System.out.println("\n--- Sort Options ---");
        System.out.println("1. Sort by Title (Asc)");
        System.out.println("2. Sort by Year (Asc)");
        System.out.println("3. Sort by Year (Desc)");
        System.out.print("Enter sort choice: ");

        int sortChoice = -1;
         if (scanner.hasNextInt()) {
             sortChoice = scanner.nextInt();
         } else {
              System.out.println("Invalid input. Please enter a number.");
         }
         scanner.nextLine();

        List<Book> sortedBooks = null;
        String sortDescription = "";

        switch (sortChoice) {
            case 1:
                sortedBooks = library.sortBooksByTitle();
                sortDescription = "--- Books Sorted by Title (Asc) ---";
                break;
            case 2:
                sortedBooks = library.sortBooksByYearAsc();
                sortDescription = "--- Books Sorted by Year (Asc) ---";
                break;
            case 3:
                sortedBooks = library.sortBooksByYearDesc();
                sortDescription = "--- Books Sorted by Year (Desc) ---";
                break;
            default:
                System.out.println("Invalid sort choice.");
                return; 
        }

        System.out.println("\n" + sortDescription);
        printBookList(sortedBooks, "The library is empty, nothing to sort.");
         System.out.println("-----------------------------------");
    }

    private static void printBookList(List<Book> books, String emptyMessage) {
         if (books == null || books.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            books.forEach(System.out::println); // Method reference for printing
        }
    }
}