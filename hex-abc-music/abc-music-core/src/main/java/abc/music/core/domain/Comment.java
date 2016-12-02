package abc.music.core.domain;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Comment extends Field {

    public Comment() {
        this(null);
    }

    public Comment(String content) {
        super('N', content);
    }
}
