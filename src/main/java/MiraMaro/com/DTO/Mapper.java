package MiraMaro.com.DTO;

import MiraMaro.com.Entities.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Product ProductDtoToEntity(ProductCreationDTO productCreationDTO) {
        Product product = new Product();

        product.setName(productCreationDTO.getName());
        product.setPrice(productCreationDTO.getPrice());
        product.setColor(productCreationDTO.getColor());
        product.setType(productCreationDTO.getType());
        product.setQuantity(productCreationDTO.getQuantity());

        return product;
    }

    public static ProductDTO ProductEntityToDTO(Product product) {
        ProductDTO pdto= new ProductDTO( );
        pdto.setId(product.getId());
        pdto.setName(product.getName());
        pdto.setColor(product.getColor());
        pdto.setPrice(product.getPrice());
        pdto.setQuantity(product.getQuantity());
        pdto.setType(product.getType());
        return pdto;
    }

    public static CartItemDTO cartItemEntityToDTO(CartItem cartItem){
        CartItemDTO cdto=new CartItemDTO();
        cdto.setId(cartItem.getId());
        cdto.setQuantity(cartItem.getQuantity());
        cdto.setPrice(cartItem.getProduct().getPrice());
        cdto.setProductName(cartItem.getProduct().getName());
        return cdto;
    }

    public static CartDTO cartEntityToDTO(Cart cart){
        CartDTO cartdto=new CartDTO();
        cartdto.setId(cart.getId());
        cartdto.setBouquet(cart.isBouquet());
        cartdto.setStatus(cart.getStatus());
        List<CartItemDTO> itemDTOList=new ArrayList<>();
        for(CartItem cartItems:cart.getItems()) {
           itemDTOList.add(cartItemEntityToDTO(cartItems));
        }
        cartdto.setItems(itemDTOList);
        cartdto.setCustomerId(cart.getCustomer().getId());
        return cartdto;
    }

    public static CustomerDTO customerEntityToDTO(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setCurrentCartId(customer.getCurrentCartId());
        return customerDTO;
    }



}
