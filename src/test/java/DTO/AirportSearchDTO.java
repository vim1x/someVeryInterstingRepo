package DTO;

public class AirportSearchDTO {
    public String airport0;
    public String airport1;
    public boolean isOneWay;
    public AirportSearchDTO(String airport0, String airport1, boolean isOneWay) {
        this.airport0 = airport0;
        this.airport1 = airport1;
        this.isOneWay = isOneWay;
    }
}
