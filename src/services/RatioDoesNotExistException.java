package services;

public class RatioDoesNotExistException extends Exception{
    String text;

    public RatioDoesNotExistException (String text) {
        this.text = text;
    }
}
