package nu.hex.abc.music.service.exception;

/**
 * Created 2016-nov-24
 *
 * @author hl
 */
public class NoProjectException extends ServiceException {

    public NoProjectException() {
        super("No project is open");
    }

}
