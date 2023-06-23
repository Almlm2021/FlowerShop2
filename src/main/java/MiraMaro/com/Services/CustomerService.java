package MiraMaro.com.Services;

import MiraMaro.com.DTO.*;
import MiraMaro.com.Entities.Cart;
import MiraMaro.com.Entities.Customer;
import MiraMaro.com.Entities.Product;
import Repository.CartRepository;
import Repository.CustomerRepository;
import Repository.ProductRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomerService {
    CartRepository cp = CartRepository.getInstance();
    CustomerRepository cup = CustomerRepository.getInstance();
    CartService cartService = CartService.getInstance();
    ProductRepository productRepository = ProductRepository.getInstance();


    private static CustomerService instance;

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }


    private CustomerService() {

    }

    public CustomerDTO register(CustomerCreationDTO customerCreationDTO) {
        Customer customer = new Customer();
        customer.setCarts(new ArrayList<Cart>());
        customer.setName(customerCreationDTO.getName());
        customer.setEmail(customerCreationDTO.getEmail());
        customer.setPassword(customerCreationDTO.getPassword());
        customer.setFavorite(new ArrayList<Product>());
        cup.save(customer);
        //By register a new customer , a new cart will be created and added to this customer
        cartService.createCart(customer.getId());
        return Mapper.customerEntityToDTO(customer);


    }

    public void changeName(int customerId, String name) {
        Customer customer = cup.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found!");
        }
        customer.setName(name);
        cup.update(customer);
    }

    public void changePassword(int customerId, String password) {
        Customer customer = cup.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found!");
        }
        customer.setPassword(password);
        cup.update(customer);
    }

    public void changeEmail(int customerId, String email) {
        Customer customer = cup.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found!");
        }
        customer.setEmail(email);
        cup.update(customer);
    }


    public CustomerDTO login(String customerPassword, String customerEmail) {
        Customer customer = cup.findByEmail(customerEmail);
        if (customer == null) {
            throw new RuntimeException("Customer not found!");
        }
        if (!customer.getPassword().equals(customerPassword)) {
            throw new RuntimeException("Customer password not correct!");
        }
        CustomerDTO customerDTO = Mapper.customerEntityToDTO(customer);

        return customerDTO;
    }

    public void addToFavorite(int productId, int customerId) {
        Customer customer = cup.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("No customer found!");
        }
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new RuntimeException("No Product found!");
        }
        customer.getFavorite().add(product);
        cup.update(customer);

    }

    public List<ProductDTO> getFavorites(int customerId) {
        Customer customer = cup.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("No customer found!");
        }
        return customer.getFavorite().stream().map(Mapper::ProductEntityToDTO).toList();
    }

    public void removeFromFavorite(int productId, int customerId) {
        Customer customer = cup.findById(customerId);
        if (customer == null) {
            throw new RuntimeException("No customer found!");
        }
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new RuntimeException("No Product found!");
        }
        customer.getFavorite().remove(product);
        cup.update(customer);

    }

    public CustomerDTO findCustomerById(int id) {
        Customer customer = cup.findById(id);
        if (customer == null) {
            throw new RuntimeException("No customer found!");
        }
        return Mapper.customerEntityToDTO(customer);
    }


}
