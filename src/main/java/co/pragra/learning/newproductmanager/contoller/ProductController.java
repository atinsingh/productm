package co.pragra.learning.newproductmanager.contoller;

import co.pragra.learning.newproductmanager.entity.Product;
import co.pragra.learning.newproductmanager.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController  // = @Controller + @ResponseBody
public class ProductController {

    @Autowired
    private  ProductRepo repo ;

    @PostMapping("/api/product")
    public Product createProduct(@RequestBody Product product){
        return this.repo.save(product);
    }


}
