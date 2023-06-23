package MiraMaro.com.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends User {

    @Column(nullable = false)
    private int currentCartId;

    @OneToMany(mappedBy ="customer")
    private List<Cart> carts;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "favorite",
            joinColumns = { @JoinColumn(name = "customerId") },
            inverseJoinColumns = { @JoinColumn(name = "ProductId") }
    )
    private List<Product> favorite;

    public Customer(){
        super();
    }

    public Customer( String name, String email, String password) {
       super(name,email,password);
        this.favorite=new ArrayList<Product>();
    }



    public int getCurrentCartId() {
        return currentCartId;
    }

    public void setCurrentCartId(int currentCartId) {
        this.currentCartId = currentCartId;
    }


    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Product> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Product> favorite) {
        this.favorite = favorite;
    }
}
