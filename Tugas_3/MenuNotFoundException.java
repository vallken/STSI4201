public class MenuNotFoundException extends Exception {
    
    public MenuNotFoundException(String message) {
        super(message);
    }
    
    public MenuNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}