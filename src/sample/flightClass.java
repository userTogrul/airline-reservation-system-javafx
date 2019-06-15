package sample;

public class flightClass {

    private int flightId;
    private String From;
    private String To;
    private String Departure;
    private String Return;
    private int EcoPriceId;
    private int BusinessPriceId;
    private int AircraftId;

    public flightClass(int flightId, String from, String to, String departure, String aReturn, int ecoPriceId, int businessPriceId, int aircraftId) {
        this.flightId = flightId;
        From = from;
        To = to;
        Departure = departure;
        Return = aReturn;
        EcoPriceId = ecoPriceId;
        BusinessPriceId = businessPriceId;
        AircraftId = aircraftId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
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

    public int getEcoPriceId() {
        return EcoPriceId;
    }

    public void setEcoPriceId(int ecoPriceId) {
        EcoPriceId = ecoPriceId;
    }

    public int getBusinessPriceId() {
        return BusinessPriceId;
    }

    public void setBusinessPriceId(int businessPriceId) {
        BusinessPriceId = businessPriceId;
    }

    public int getAircraftId() {
        return AircraftId;
    }

    public void setAircraftId(int aircraftId) {
        AircraftId = aircraftId;
    }
}
