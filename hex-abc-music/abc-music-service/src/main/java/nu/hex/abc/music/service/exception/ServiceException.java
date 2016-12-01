package nu.hex.abc.music.service.exception;

/**
 * Created 2016-nov-14
 *
 * @author hl
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
