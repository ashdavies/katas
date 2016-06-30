package com.chaos.turnticketdispenser;

public class TicketDispenser {
    public TurnTicket getTurnTicket() {
        int newTurnNumber = TurnNumberSequence.getNextTurnNumber();
        TurnTicket newTurnTicket = new TurnTicket(newTurnNumber);

        return newTurnTicket;
    }
}