package MiraMaro.com.DTO;

public class CustomerDTO {
    private int id;
    private String name;
    private String email;
    private int currentCartId;

    public CustomerDTO(){}

    public CustomerDTO(int id, String name, String email, int currenCartId) {
        this.id = id;
        this.name = name;
        this.email = email;

        this.currentCartId = currenCartId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getCurrentCartId() {
        return currentCartId;
    }

    public void setCurrentCartId(int currentCartId) {
        this.currentCartId = currentCartId;
    }
}
