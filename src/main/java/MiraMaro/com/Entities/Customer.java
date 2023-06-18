package MiraMaro.com.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends User {
    /*   @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id")
      private int id;

    @Column(nullable=false)
      private String name;

      @Column(nullable=false)
      private String email;

      @Column(nullable=false)
      private String password;
  */
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

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public int getCurrentCartId() {
        return currentCartId;
    }

    public void setCurrentCartId(int currentCartId) {
        this.currentCartId = currentCartId;
    }

   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
*/
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
