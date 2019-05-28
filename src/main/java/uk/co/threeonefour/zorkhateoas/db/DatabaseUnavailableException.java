package uk.co.threeonefour.zorkhateoas.db;

public class DatabaseUnavailableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DatabaseUnavailableException(Throwable cause) {
        super("Database unavaliable", cause);
    }
}
