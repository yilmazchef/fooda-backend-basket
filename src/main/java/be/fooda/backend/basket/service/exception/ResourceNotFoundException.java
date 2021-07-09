package be.fooda.backend.basket.service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String product_not_found) {
    }
}
