package MiraMaro.com;


import MiraMaro.com.DTO.CustomerCreationDTO;
import MiraMaro.com.DTO.CustomerDTO;
import MiraMaro.com.DTO.ProductCreationDTO;
import MiraMaro.com.DTO.ProductDTO;
import MiraMaro.com.Entities.CartItem;
import MiraMaro.com.Entities.Product;
import MiraMaro.com.Services.CartService;
import MiraMaro.com.Services.CustomerService;
import MiraMaro.com.Services.ProductService;
import Repository.CartItemRepository;
import Repository.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

    ProductService productService=new ProductService();
    CartService cartService=new CartService();
    CustomerService customerService=new CustomerService();

    ProductCreationDTO product1 = new ProductCreationDTO("Sunflower", 1.99, "Yellow", "Annual", 50);
    ProductCreationDTO product2 = new ProductCreationDTO("Tulip", 2.75, "Pink", "Perennial", 25);
    ProductDTO productDTO=productService.addProduct(product1);
    ProductDTO productDTO1=productService.addProduct(product2);
    CustomerCreationDTO customer1 = new CustomerCreationDTO("Maro", "johndoe@example.com", "mypassword123");
    CustomerDTO customerDTO=customerService.register(customer1);
    System.out.println(productService.getAllProduct());
    cartService.addItemToCart(productDTO.getId(),customerDTO.getCurrentCartId(),3 );
    cartService.placeOrder(customerDTO.getCurrentCartId(),true);
    customerService.addToFavorite(productDTO1.getId(),customerDTO.getId());

    productService.UpdateQuantity(1,10);
    //productService.UpdateQuantity(1,-100);
   /// productService.UpdateQuantity(4,-100);
    cartService.cancelOrder(1);



    }
}
