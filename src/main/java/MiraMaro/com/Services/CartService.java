package MiraMaro.com.Services;

import MiraMaro.com.DTO.CartDTO;
import MiraMaro.com.DTO.CartItemDTO;
import MiraMaro.com.DTO.Mapper;
import MiraMaro.com.Entities.*;
import Repository.CartItemRepository;
import Repository.CartRepository;
import Repository.CustomerRepository;
import Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    CartItemRepository cr = CartItemRepository.getInstance();
    CartRepository cp = CartRepository.getInstance();
    CustomerRepository cup = CustomerRepository.getInstance();
    ProductRepository pp = ProductRepository.getInstance();
    ProductService productService = ProductService.getInstance();
    private static final Logger logger = LogManager.getLogger(CustomerService.class);
    private static CartService instance;

    public static CartService getInstance(){
        if(instance==null){
            instance=new CartService();
        }
        return instance;
    }


    private CartService(){


    }
    public void addItemToCart(int productId, int cartId, int quantity) {
        Cart cart = cp.findById(cartId);
        Product product = pp.findById(productId);
        if (cart == null || product == null) {
            throw new RuntimeException("not found");
        }
        if (quantity > product.getQuantity()) {
            throw new RuntimeException("invalid quantity");
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cr.save(cartItem);
        cart.getItems().add(cartItem);
        cp.update(cart);
        logger.info("a new Item was added to  the Cart"+product.getName());
    }

    public void updateQuantity(int cartItemId, int quantity) {
        CartItem cartItem = cr.findById(cartItemId);
        Product product = cartItem.getProduct();
        if (cartItem == null) {
            throw new RuntimeException("cartItem not found");
        }
        if (quantity + cartItem.getQuantity() > product.getQuantity() || quantity + cartItem.getQuantity() < 0) {
            throw new RuntimeException("invalid quantity");
        }
        cartItem.setQuantity(quantity + cartItem.getQuantity());
        cr.update(cartItem);
        logger.info("a cart Items quantity was updated"+cartItem.getCart().getCustomer().getName()+cartItem.getProduct().getName());

    }

    public void deleteItem(int cartItemId) {
        CartItem cartItem = cr.findById(cartItemId);
        if (cartItem == null) {
            throw new RuntimeException("cartItem not found!");
        }
        cr.delete(cartItem);
        logger.info("an Item was deleted from the Cart from the customer : "+cartItem.getProduct().getName()+cartItem.getCart().getCustomer().getName());

    }

    double getTotal(int cartId) {
        Cart cart = cp.findById(cartId);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        double total = 0;
        for (CartItem cartItem : cart.getItems()) {
            double price = cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();
            total += price * quantity;
        }
        return total;
    }

    public List<CartItemDTO> viewCart(int cartId) {
        List<CartItemDTO> items = new ArrayList<CartItemDTO>();
        Cart cart = cp.findById(cartId);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        for (CartItem cartItem : cart.getItems()) {
            items.add(Mapper.cartItemEntityToDTO(cartItem));
        }
        return items;
    }

    public List<CartDTO> viewCarts(int customerid) {
        List<CartDTO> carts = new ArrayList<>();
        Customer customer = cup.findById(customerid);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        for (Cart carten : customer.getCarts()) {
            if (carten.getStatus() != CartStatus.CURRENT) {
                carts.add(Mapper.cartEntityToDTO(carten));
            }
        }
        return carts;
    }

    public void placeOrder(int cartId, boolean isBoquete) {
        Cart cart = cp.findById(cartId);
        if (cart == null) {
            throw new RuntimeException("No Cart yet");
        }
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("No CartItems yet");
        }

        for (CartItem items : cart.getItems()) {
            Product product = items.getProduct();
            int quantity = items.getQuantity();
            productService.UpdateQuantity(product.getId(), -quantity);
        }
        cart.setStatus(CartStatus.PLACED);
        cart.setBouquet(isBoquete);
        cp.update(cart);
        createCart(cart.getCustomer().getId());
        logger.info("a new Item was placed to the customer "+cart.getCustomer().getName());

    }

    public CartDTO createCart(int customerid) {
        Customer customer = cup.findById(customerid);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setStatus(CartStatus.CURRENT);
        cart.setItems(new ArrayList<CartItem>());
        cp.save(cart);
        //add to customer a new cart
        customer.getCarts().add(cart);
        //set the new cart Id by bringing the cartEntitty ID
        customer.setCurrentCartId(cart.getId());
        //update the customer ,that we add a cart to the customer
        cup.update(customer);
        CartDTO cartDTO = Mapper.cartEntityToDTO(cart);
        logger.info("a new Cart was added to the Customer"+customer.getName());

        return cartDTO;
    }


    public void cancelOrder(int cartId) {
        Cart cart = cp.findById(cartId);
        if (cart == null) {
            throw new RuntimeException("No Cart yet");
        }
        for (CartItem items : cart.getItems()) {
            Product product = items.getProduct();
            int quantity = items.getQuantity();
            productService.UpdateQuantity(product.getId(), +quantity);
        }
        cart.setStatus(CartStatus.CANCELED);
        logger.info("a Cart was deleted from the Customer"+cart.getCustomer().getName());


    }

}




