package com.seenukarthi.tldr;

public class TldrException extends RuntimeException{
    public TldrException() {
        super();
    }

    public TldrException(String message) {
        super(message);
    }

    public TldrException(String message, Throwable cause) {
        super(message, cause);
    }

    public TldrException(Throwable cause) {
        super(cause);
    }

    protected TldrException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
