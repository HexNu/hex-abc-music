package nu.hex.abc.music.service.io;

/**
 * Created 2016-nov-28
 *
 * @author hl
 * @param <T>
 */
public interface Writer<T> {

    public String ABC_VERSION = "%%abc-version 2.0";
    public String ABC_CREATOR = "%%abc-creator hex-abc-music 1.0";
    public String DEFAULT_CHARSET = "%%abc-charset utf-8";

    T write();

}
