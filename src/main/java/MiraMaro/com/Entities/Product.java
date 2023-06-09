package MiraMaro.com.Entities;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(nullable=false)
    private double price;
    @Column(nullable=false)
    private String color;
    @Column(nullable=false)
    private String type;
    @Column(nullable=false)
    private int quantity;
    @Column(nullable=false)
    private String name;
    public Product(){}

    public Product( double price, String color, String type, int quantity, String name) {
        this.price = price;
        this.color = color;
        this.type = type;
        this.quantity = quantity;
        this.name = name;
    }

    /*@Column(name = "name")
    private String name;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

