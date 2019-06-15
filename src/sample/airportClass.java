package sample;

public class airportClass {

    private int airportId;
    private String airportName;
    private String airportCountry;
    private String airportCity;
    private int flightId;

    public airportClass(int airportId, String airportName, String airportCountry, String airportCity, int flightId) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCountry = airportCountry;
        this.airportCity = airportCity;
        this.flightId = flightId;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCountry() {
        return airportCountry;
    }

    public void setAirportCountry(String airportCountry) {
        this.airportCountry = airportCountry;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}
