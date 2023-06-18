package MiraMaro.com.Entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User{
    private String role;

    public Admin(String name, String password, String email, String role) {
        super(name, password, email);
        this.role = role;
    }

    public Admin() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
