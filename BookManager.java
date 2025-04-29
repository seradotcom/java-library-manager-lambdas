import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface BookManager {
    List<Book> books = new ArrayList<>();
    void addBook(Book book);
    List<Book> getBooks();
    List<Book> findBooksByAuthor(String author);
    List<Book> sortBooksByTitle();
    
    default void printAllBooks() {
        getBooks().forEach(System.out::println) ;
    }
    List<Book> sortBooksByYearAsc();
    List<Book> sortBooksByYearDesc();
    List<Book> findBooksPublishedBefore(int year);
    List<Book> findBooksByTitleContaining(String substring);
}