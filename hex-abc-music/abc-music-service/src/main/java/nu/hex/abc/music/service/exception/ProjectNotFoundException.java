package nu.hex.abc.music.service.exception;

import java.io.File;

/**
 * Created 2016-nov-24
 *
 * @author hl
 */
public class ProjectNotFoundException extends ServiceException {
    
    public ProjectNotFoundException(File file) {
        super("Could not finde project file: " + file.getAbsolutePath());
    }
    
}
