package abc.music.core.domain;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Origin extends Field {

    public Origin() {
        this(null);
    }

    public Origin(String content) {
        super('O', content);
    }
}
