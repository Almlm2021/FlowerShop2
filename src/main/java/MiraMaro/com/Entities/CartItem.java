package MiraMaro.com.Entities;

import javax.persistence.*;

@Entity
@Table(name="cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(nullable=false)
    private int quantity;
    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name="cart_id",nullable = false)
    private Cart cart;
    public CartItem(){}

    public CartItem( int quantity, Product product) {

        this.quantity = quantity;
        this.product = product;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
