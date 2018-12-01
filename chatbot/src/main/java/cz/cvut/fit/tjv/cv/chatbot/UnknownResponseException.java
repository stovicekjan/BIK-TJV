package cz.cvut.fit.tjv.cv.chatbot;

public class UnknownResponseException extends RuntimeException {

    public UnknownResponseException(String message) {
        super(message);
    }

    public UnknownResponseException(Throwable cause) {
        super(cause);
    }
    
}
