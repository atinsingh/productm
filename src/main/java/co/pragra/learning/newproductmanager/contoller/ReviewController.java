package co.pragra.learning.newproductmanager.contoller;

import co.pragra.learning.newproductmanager.dto.ErrorDto;
import co.pragra.learning.newproductmanager.entity.Review;
import co.pragra.learning.newproductmanager.exception.InvalidReviewException;
import co.pragra.learning.newproductmanager.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/api/product/{id}/review")
    public Review addReview(@RequestBody Review review, @PathVariable("id") UUID productId) {
        return reviewService.addReview(review, productId);
    }

    @PutMapping("/api/review")
    public ResponseEntity<Review> updateReview(@RequestBody Review review, HttpServletRequest request) {
        log.info("HttpRequest -> {}", request.getMethod());
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReview(review));

    }
}
