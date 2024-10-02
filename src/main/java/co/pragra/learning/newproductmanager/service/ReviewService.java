package co.pragra.learning.newproductmanager.service;

import co.pragra.learning.newproductmanager.entity.Product;
import co.pragra.learning.newproductmanager.entity.Review;
import co.pragra.learning.newproductmanager.entity.User;
import co.pragra.learning.newproductmanager.exception.InvalidProductException;
import co.pragra.learning.newproductmanager.exception.InvalidReviewException;
import co.pragra.learning.newproductmanager.exception.InvalidUserDataException;
import co.pragra.learning.newproductmanager.repo.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ProductService productService;
    private final ReviewRepo reviewRepo;
    private final UserService userService;

    public Review addReview(Review review, UUID productId) {
        if(null == review){
            throw new RuntimeException("Review cannot be null");
        }

        if(null!=productId){
            Optional<Product> productOptional = productService.findById(productId);
            if(productOptional.isPresent()){

                if( review.getUser()!=null) {
                    Optional<User> userOptional = userService.findById(review.getUser().getId());
                    User user = userOptional.orElseThrow(() -> new InvalidUserDataException("User not found"));
                    review.setUser(user);
                }
                Review savedReview = reviewRepo.save(review);
                Product dbProduct = productOptional.get();
                List<Review> reviews = dbProduct.getReviews();
                reviews.add(savedReview);
                dbProduct.setReviews(reviews);
                productService.update(dbProduct);
                return savedReview;
            }

        }else {
            throw new InvalidProductException("Invalid product id"+ productId.toString());
        }
        return null;
    }


    public Review updateReview(Review review) {

        if (null == review ||  review.getReviews().isEmpty() ||  review.getReviews().length() <= 20) {
            throw new InvalidReviewException("Review can not be null or empty or less than 20 chars");
        }



        Optional<Review> reviewOptional = reviewRepo.findById(review.getId());
        Review dbReview = reviewOptional.orElseThrow(() -> new InvalidReviewException("Review not found"));
        dbReview.setReviews(review.getReviews());
        return reviewRepo.save(dbReview);
    }

}
