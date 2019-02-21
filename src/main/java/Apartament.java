public class Apartament {
    private int id;
    private String district;
    private String adress;
    private int space;
    private int rooms;
    private int price;

    public Apartament(int id, String district, String adress, int space, int rooms, int price) {
        this.id = id;
        this.district = district;
        this.adress = adress;
        this.space = space;
        this.rooms = rooms;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String format = "%-11d%-30s%-45s%-11d%-11d%-11d";
        return String.format(format, id, district, adress, space, rooms, price);
    }
}
