package co.pragra.learning.newproductmanager.service;

import co.pragra.learning.newproductmanager.entity.Product;
import co.pragra.learning.newproductmanager.exception.InvalidProductException;
import co.pragra.learning.newproductmanager.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;

    public Product createProduct(Product product) {
        if(null == product) {
            throw  new InvalidProductException("Product can't be null");
        }
        if(product.getName() == null || product.getName().length() < 5) {
            throw  new InvalidProductException("Product name can't be null or less than 5 char");
        }
        product.setReviews(new ArrayList<>());
       return repo.save(product);
    }

    public Product update(Product product) {
        if(null == product) {
            throw  new InvalidProductException("Product can't be null");
        }
        Optional<Product> productOptional = repo.findById(product.getId());
        productOptional.orElseThrow(()->new InvalidProductException("Prouduct with Id not found"));
        Product dbProduct = productOptional.get();
        if(product.getName() != null) {
            dbProduct.setName(product.getName());
        }
        if(product.getDescription()!=null) {
            dbProduct.setDescription(product.getDescription());
        }
        if(product.getCost() != dbProduct.getCost() && product.getCost() !=0.0) {
            dbProduct.setCost(product.getCost());
        }
        if(product.getReviews() != null) {
            dbProduct.setReviews(product.getReviews());
        }
        return repo.save(dbProduct);


    }


    public List<Product> findAll() {
       return this.repo.findAll();
    }

    /**
     *  This is method to return individual product
     * @param uuidFromString
     * @return Product
     */
    public Optional<Product> findById(UUID uuidFromString) {
        return repo.findById(uuidFromString);
    }

    public List<Product> findProductLessThanPrice(double cost) {
        return repo.findCheaperProduct(cost);
    }

    public Product updateProduct(Product product) {
        if(null == product) {
            throw  new InvalidProductException("Product can't be null");
        }
        Optional<Product> productOptional = repo.findById(product.getId());
        productOptional.orElseThrow(()->new InvalidProductException("Product with Id not found"));
        Product dbProduct = productOptional.get();
        if(product.getDescription() != null && product.getDescription().length() >= 20) {
            dbProduct.setDescription(product.getDescription());
        }
        if( product.getCost() != dbProduct.getCost() && product.getCost()>=0){
            dbProduct.setCost(product.getCost());
        }
        return repo.save(dbProduct);
    }
}
