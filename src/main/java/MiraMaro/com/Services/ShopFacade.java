package MiraMaro.com.Services;

import MiraMaro.com.DTO.CustomerDTO;
import MiraMaro.com.DTO.ProductDTO;

public class ShopFacade {
    private ProductService productService;
    private CartService cartService;
    private CustomerService customerService;

    public ShopFacade() {
        this.productService = ProductService.getInstance();
        this.cartService = CartService.getInstance();
       this.customerService=CustomerService.getInstance();
    }

    public void addProductToCart(int productId,int quantity, int customerId) {
        ProductDTO product = productService.findProductById(productId);
        CustomerDTO customer=customerService.findCustomerById(customerId);
        cartService.addItemToCart(product.getId(),customer.getCurrentCartId(),quantity);
    }

}
