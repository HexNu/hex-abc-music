package nu.hex.abc.music.service.exception;

/**
 * Created 2016-nov-24
 *
 * @author hl
 */
public class ProjectNotSavedException extends ServiceException {

    public ProjectNotSavedException(String name, Throwable e) {
        super("Project \"" + name + "\" could not be saved", e);
    }
}
