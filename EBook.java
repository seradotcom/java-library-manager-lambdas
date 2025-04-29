import java.util.Objects;

public class EBook extends Book {
    private double fileSizeMB; // File size in Megabytes

    public EBook(String title, String author, int year, double fileSizeMB) {
        super(title, author, year); // Call the constructor of the superclass (Book)
        this.fileSizeMB = fileSizeMB;
    }

    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public void setFileSizeMB(double fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public String toString() {
        // Enhance the base toString() method
        return "EBook: " +
               getTitle() + " by " + getAuthor() + " (" + getYear() + ") " +
                " [File Size: " + fileSizeMB + " MB]";
        }
}