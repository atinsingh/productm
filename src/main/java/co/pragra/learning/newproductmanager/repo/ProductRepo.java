package co.pragra.learning.newproductmanager.repo;

import co.pragra.learning.newproductmanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {

    //HQL
    //@Query(value = "SELECT * from product p where p.cost < :cost", nativeQuery = true)
    @Query("SELECT p from Product p where p.cost < :cost ")
    List<Product> findCheaperProduct(double cost);
}
