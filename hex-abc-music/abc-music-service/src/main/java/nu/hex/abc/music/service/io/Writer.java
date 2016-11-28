package nu.hex.abc.music.service.io;

/**
 * Created 2016-nov-28
 *
 * @author hl
 * @param <T>
 */
public interface Writer<T> {

    public String DEFAULT_CHARSET = "%%abc-charset utf-8";
    public String ABC_VERSION = "%%abc-version 2.0";
    public String ABC_CREATOR = "%%abc-creator hex-abc-music " + Writer.class.getPackage().getImplementationVersion();

    T write();

}
