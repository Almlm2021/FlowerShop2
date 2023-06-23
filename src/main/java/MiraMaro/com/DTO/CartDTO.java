package MiraMaro.com.DTO;

import MiraMaro.com.Entities.CartStatus;

import java.util.List;

public class CartDTO {

    private int id;
    private CartStatus status;
    private boolean isBouquet;
    private List<CartItemDTO> items;
    private int customerId;
    private String customerName;

    public CartDTO(){}

    public CartDTO(int id, CartStatus status, boolean isBouquet, List<CartItemDTO> items, int customerId,String customerName) {
        this.id = id;
        this.status = status;
        this.isBouquet = isBouquet;
        this.items = items;
        this.customerId = customerId;
        this.customerName=customerName;
    }

    public int getId() {
        return id;
    }

    public CartStatus getStatus() {
        return status;
    }

    public boolean isBouquet() {
        return isBouquet;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public void setBouquet(boolean bouquet) {
        isBouquet = bouquet;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

