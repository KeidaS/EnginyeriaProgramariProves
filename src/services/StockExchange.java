package services;

import data.*;

public interface StockExchange {
    Money value (Ticket ticket) throws TicketDoesNotExistException;
}
