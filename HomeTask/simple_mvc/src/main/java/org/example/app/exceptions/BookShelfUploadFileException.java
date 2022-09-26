package org.example.app.exceptions;

public class BookShelfUploadFileException extends Exception {

    private final String message;

    public BookShelfUploadFileException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
