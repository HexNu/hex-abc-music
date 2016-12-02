package abc.music.core.domain;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class History extends Field {

    public History() {
        this(null);
    }

    public History(String content) {
        super('H', content);
    }
}
