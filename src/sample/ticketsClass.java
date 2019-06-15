package sample;

public class ticketsClass {

    private int ticketId;
    private int passengerId;
    private int ticketPrice;
    private String From;
    private String To;
    private String Departure;
    private String Return;

    public ticketsClass(int ticketId, int passengerId, int ticketPrice, String from, String to, String departure, String aReturn) {
        this.ticketId = ticketId;
        this.passengerId = passengerId;
        this.ticketPrice = ticketPrice;
        From = from;
        To = to;
        Departure = departure;
        Return = aReturn;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public String getReturn() {
        return Return;
    }

    public void setReturn(String aReturn) {
        Return = aReturn;
    }
}
