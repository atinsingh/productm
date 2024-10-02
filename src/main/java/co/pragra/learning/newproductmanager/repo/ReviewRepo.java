package co.pragra.learning.newproductmanager.repo;

import co.pragra.learning.newproductmanager.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
}
