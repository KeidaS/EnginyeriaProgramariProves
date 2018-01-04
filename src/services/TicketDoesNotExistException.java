package services;
public class TicketDoesNotExistException extends Exception{
    String text;

    public TicketDoesNotExistException (String text) {
        this.text = text;
    }
}
