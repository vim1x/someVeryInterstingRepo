package DTO;

public class ApartmentSearchDTO {
    private String city;
    private String startDate;
    private String endDate;
    private int stars;

    public ApartmentSearchDTO(String city, String startDate, String endDate) {
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ApartmentSearchDTO(String city, String startDate, String endDate, int stars) {
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stars = stars;
    }

    public String getCity() {
        return city;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getStars() {
        return stars;
    }
}
