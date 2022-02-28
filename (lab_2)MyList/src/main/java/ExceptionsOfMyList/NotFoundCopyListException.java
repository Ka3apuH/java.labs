package ExceptionsOfMyList;

public class NotFoundCopyListException extends NullPointerException{

    public NotFoundCopyListException() {
    }

    public NotFoundCopyListException(String s) {
        super(s);
    }
}

