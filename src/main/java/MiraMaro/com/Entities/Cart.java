package MiraMaro.com.Entities;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private CartStatus status;

    @Column(nullable = true)
    private boolean isBouquet;

    @OneToMany(mappedBy="cart")
    private List<CartItem> items;

    @ManyToOne
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;
    public Cart(){}

    public Cart( List<CartItem> items, Customer customer) {
        this.items = items;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBouquet() {
        return isBouquet;
    }

    public void setBouquet(boolean bouquet) {
        isBouquet = bouquet;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
