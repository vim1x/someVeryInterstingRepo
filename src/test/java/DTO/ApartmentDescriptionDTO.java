package DTO;

public class ApartmentDescriptionDTO {
    private String name;
    private int price;
    private int rating;
    private String address;

    public ApartmentDescriptionDTO(String name, int price, int rating, String address) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }
    public static boolean areEqual(ApartmentDescriptionDTO dto1, ApartmentDescriptionDTO dto2) {
        if (dto1 == dto2) return true; // Check for reference equality
        if (dto1 == null || dto2 == null) return false; // Check for nulls
        return dto1.price == dto2.price &&
                dto1.rating == dto2.rating &&
                dto1.name.equals(dto2.name) &&
                dto1.address.equals(dto2.address);
    }

}

