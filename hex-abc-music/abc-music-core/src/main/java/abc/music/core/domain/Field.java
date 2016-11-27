package abc.music.core.domain;

/**
 * Created 2016-nov-27
 *
 * Represents an ABC-notation Field.
 *
 * @author hl
 */
public abstract class Field {

    private String content;
    private final char code;

    public Field(char code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setFieldContent(String content) {
        this.content = content;
    }

    public char getCode() {
        return code;
    }

    public String get() {
        return getCode() + ": " + getContent();
    }

    public boolean isEmpty() {
        return content == null || content.isEmpty();
    }
//    @Override
//    public String toString() {
////        return getCode() + ": " + getContent();
//    }
}
