package nu.hex.abc.music.service.io;

import nu.hex.abc.music.service.meta.MetaService;

/**
 * Created 2016-nov-28
 *
 * @author hl
 * @param <T>
 */
public interface Writer<T> {

    public String DEFAULT_CHARSET = "I:abc-charset utf-8";
    public String ABC_VERSION = "I:abc-version 2.2";
    public String ABC_CREATOR = "I:abc-creator hex-abc-music " + MetaService.getAppInfo().getVersion();

    T write();

}
