package MiraMaro.com.Services;

import MiraMaro.com.Entities.Admin;
import MiraMaro.com.Entities.Customer;
import MiraMaro.com.Entities.User;

public class UserFactory {

    public static User createUser(String type) {
        if ("Owner".equalsIgnoreCase(type)) {
            return new Admin();
        } else if ("Customer".equalsIgnoreCase(type)) {
            return new Customer();
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
    }
}

