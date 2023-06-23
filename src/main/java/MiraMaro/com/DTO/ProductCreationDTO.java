package MiraMaro.com.DTO;

public class ProductCreationDTO {

    private String name;
    private  double price;
    private  String color;
    private  String type;
    private  int quantity;
    // No-args constructor
    public ProductCreationDTO() {}

    public ProductCreationDTO(String name, double price, String color, String type, int quantity) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.type = type;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
