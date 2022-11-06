package exceptions;

public class ServiceException extends Exception {
    /**
     * excepție aruncată în cazuri de eroare la service
     * @param message mesajul erorii
     */
    public ServiceException(String message) {
        super(message);
    }
}
