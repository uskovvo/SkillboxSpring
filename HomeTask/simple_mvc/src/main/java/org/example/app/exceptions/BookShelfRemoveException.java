package org.example.app.exceptions;

public class BookShelfRemoveException extends Exception {

    private final String message;

    public BookShelfRemoveException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
