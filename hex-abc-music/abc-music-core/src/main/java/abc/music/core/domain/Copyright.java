package abc.music.core.domain;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Copyright extends Field {

    public Copyright() {
        this(null);
    }

    public Copyright(String content) {
        super('I', content);
    }

    @Override
    public String get() {
        return getCode() + ":copyright " + getContent();
    }
}
