import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Library implements BookManager {
	@Override
	public void addBook(Book book) {
		books.add(book);
	}

	@Override
	public List<Book> getBooks() {
		return books;
	}
    
	@Override
	public List<Book> findBooksByAuthor(String author) {
        // Using a Lambda expression to filter books by author
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
	}
    
    @Override
    public List<Book> sortBooksByTitle() {
        // Using a Lambda expression to sort books by title
        return books.stream()
                .sorted((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> sortBooksByYearAsc() {
        // Using a Lambda expression to sort books by year in ascending order
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getYear))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> sortBooksByYearDesc() {
        // Using a Lambda expression to sort books by year in descending order
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getYear).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksPublishedBefore(int year) {
        // Using a Lambda expression to filter books published before a certain year
        return books.stream()
                .filter(book -> book.getYear() < year)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByTitleContaining(String substring) {
        // Using a Lambda expression to filter books by title containing a substring
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(substring.toLowerCase()))
                .collect(Collectors.toList());
    }
}
