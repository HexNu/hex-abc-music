package abc.music.core;

/**
 * Created 2016-nov-28
 *
 * @author hl
 */
public class AbcException extends RuntimeException {

    public AbcException(String message) {
        super(message);
    }

    public AbcException(String message, Throwable cause) {
        super(message, cause);
    }

}
