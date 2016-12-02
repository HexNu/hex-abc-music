package abc.music.core.domain;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Copyright extends Field {

    public Copyright() {
        super('I');
    }

    @Override
    public String get() {
        return getCode() + ":copyright " + getContent();
    }
}
