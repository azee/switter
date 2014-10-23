package ru.yandex.qatools.blog.exceptions;

/**
 * Created by azee on 30.09.14.
 */
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super();
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
