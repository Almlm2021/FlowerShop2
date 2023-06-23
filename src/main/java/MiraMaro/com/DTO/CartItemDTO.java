package MiraMaro.com.DTO;

public class CartItemDTO {
    private int id;
    private int quantity;
    private String productName;
    private double price;
    public CartItemDTO(){}

    public CartItemDTO(int id, int quantity, String productName, double price) {
        this.id = id;
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
