package MiraMaro.com.DTO;

public class ProductDTO {
    private int id;
    private double price;
    private String color;
    private String type;
    private int quantity;
    private String name;

    // No-args constructor, getters and setters
    public ProductDTO() {}

    public ProductDTO(int id, double price, String color, String type, int quantity, String name) {
        this.id = id;
        this.price = price;
        this.color = color;
        this.type = type;
        this.quantity = quantity;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

