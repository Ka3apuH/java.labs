package ExceptionsOfMyList;

public class NotFoundElementOfListException extends NullPointerException{

    public NotFoundElementOfListException() {
    }

    public NotFoundElementOfListException(String s) {
        super(s);
    }
}
