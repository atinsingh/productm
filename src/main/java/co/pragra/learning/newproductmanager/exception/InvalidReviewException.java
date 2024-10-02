package co.pragra.learning.newproductmanager.exception;

public class InvalidReviewException extends RuntimeException {
    public InvalidReviewException(String reviewNotFound) {
        super(reviewNotFound);
    }
}
