package io.github.shorv.urlshortener;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HashUnknownException extends Exception {
    public HashUnknownException(String errorMessage) {
        super(errorMessage);
    }
}
