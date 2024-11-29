package vn.com.anhTuan.commons.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message,String fieldName, Object fieldValue) {
        super(String.format("%s not found with: %s : %s", message, fieldName, fieldValue));
    }
}
