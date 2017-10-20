import java.io.Serializable;

/**
 * Models a message from source to sink about a message update
 */
public class Notification implements Serializable {

    private String author;
    private String message;

    public Notification(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}