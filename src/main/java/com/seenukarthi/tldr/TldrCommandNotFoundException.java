package com.seenukarthi.tldr;

@SuppressWarnings("unused")
public class TldrCommandNotFoundException extends RuntimeException{
    public TldrCommandNotFoundException() {
        super();
    }

    public TldrCommandNotFoundException(String message) {
        super(message);
    }

    public TldrCommandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TldrCommandNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TldrCommandNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
