package MiraMaro.com.Services;

import MiraMaro.com.DTO.CartDTO;
import MiraMaro.com.DTO.CustomerCreationDTO;
import MiraMaro.com.DTO.CustomerDTO;
import MiraMaro.com.DTO.Mapper;
import MiraMaro.com.Entities.Cart;
import MiraMaro.com.Entities.Customer;
import MiraMaro.com.Entities.Product;
import Repository.CartRepository;
import Repository.CustomerRepository;
import Repository.ProductRepository;

import java.util.ArrayList;
import java.util.Comparator;

public class CustomerService {
    CartRepository cp=new CartRepository();
    CustomerRepository cup=new CustomerRepository();
    CartService cartService=new CartService();
    ProductRepository productRepository=new ProductRepository();

    public CustomerDTO register(CustomerCreationDTO customerCreationDTO){
        Customer customer=new Customer();
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
    public void changeName(int customerId,String name){
        Customer customer=cup.findById(customerId);
        if(customer==null){
            throw new RuntimeException("Customer not found!");
        }
        customer.setName(name);
        cup.update(customer);
    }
    public void changePassword(int customerId,String password){
        Customer customer=cup.findById(customerId);
        if(customer==null){
            throw new RuntimeException("Customer not found!");
        }
        customer.setPassword(password);
        cup.update(customer);
    }

    public void changeEmail(int customerId,String email){
        Customer customer=cup.findById(customerId);
        if(customer==null){
            throw new RuntimeException("Customer not found!");
        }
        customer.setEmail(email);
        cup.update(customer);
    }


    public CustomerDTO login(String customerPassword,String customerEmail){
            Customer customer=cup.findByEmail(customerEmail);
            if(customer==null){
                throw new RuntimeException("Customer not found!");
            }
            if(!customer.getPassword().equals(customerPassword)){
                throw new RuntimeException("Customer password not correct!");
            }
           CustomerDTO customerDTO=Mapper.customerEntityToDTO(customer);

            return customerDTO;
    }

    public void addToFavorite(int productId,int customerId){
        Customer customer=cup.findById(customerId);
        if(customer==null){
            throw new RuntimeException("No customer found!");
        }
        Product product=productRepository.findById(productId);
        if(product==null){
            throw new RuntimeException("No Product found!");
        }
        customer.getFavorite().add(product);
        cup.update(customer);

    }








}
