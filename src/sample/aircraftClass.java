package sample;

public class aircraftClass {

    private int aircraftId;
    private String aircraftName;
    private int seats;
    private String ManuCompany;
    private String manuDate;

    public aircraftClass(int aircraftId, String aircraftName, int seats, String manuCompany, String manuDate) {
        this.aircraftId = aircraftId;
        this.aircraftName = aircraftName;
        this.seats = seats;
        ManuCompany = manuCompany;
        this.manuDate = manuDate;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getManuCompany() {
        return ManuCompany;
    }

    public void setManuCompany(String manuCompany) {
        ManuCompany = manuCompany;
    }

    public String getManuDate() {
        return manuDate;
    }

    public void setManuDate(String manuDate) {
        this.manuDate = manuDate;
    }
}
