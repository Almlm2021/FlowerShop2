package MiraMaro.com.Services;

import MiraMaro.com.DTO.Mapper;
import MiraMaro.com.DTO.ProductCreationDTO;
import MiraMaro.com.DTO.ProductDTO;
import MiraMaro.com.Entities.Product;
import Repository.CustomerRepository;
import Repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    ProductRepository productRepository = ProductRepository.getInstance();
    private static ProductService instance;
    public static ProductService getInstance(){
        if(instance==null){
            instance=new ProductService();
        }
        return instance;
    }


    private ProductService(){
    }

    public ProductDTO findProductById(int id){
        Product p = productRepository.findById(id);
        if (p == null) {
            throw new RuntimeException("product not found");
        }
        return Mapper.ProductEntityToDTO(p);
    }
    public ProductDTO UpdateQuantity(int productId, int quantity) {
        Product p = productRepository.findById(productId);
        if (p == null) {
            throw new RuntimeException("product not found");
        }

        if (quantity + p.getQuantity() < 0) {
            throw new RuntimeException("Invalid Quantity");
        }

        p.setQuantity(quantity + p.getQuantity());

        productRepository.update(p);
        logger.info("the Admin update the quantity of the product "+p.getName());

        return Mapper.ProductEntityToDTO(p);
    }


    public ProductDTO addProduct(ProductCreationDTO pcd) {
        Product p1 = Mapper.ProductDtoToEntity(pcd);
        productRepository.save(p1);
        logger.info("the Admin add the product"+p1.getName());

        return Mapper.ProductEntityToDTO(p1);
    }

    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productDTOList.add(Mapper.ProductEntityToDTO(product));
        }
        return productDTOList;
    }
    public void deleteProduct(int id){
        Product p = productRepository.findById(id);
        if (p != null) {
            productRepository.delete(p);
        } else {
            throw new RuntimeException("Product not found");
        }
    }


    public void updateProduct(int id, ProductCreationDTO productUpdate) {
        Product p = productRepository.findById(id);
        if (p == null) {
            throw new RuntimeException("Product not found");
        }
        p.setName(productUpdate.getName());
        p.setPrice(productUpdate.getPrice());
        p.setColor(productUpdate.getColor());
        p.setType(productUpdate.getType());
        p.setQuantity(productUpdate.getQuantity());
        productRepository.update(p);
    }



}