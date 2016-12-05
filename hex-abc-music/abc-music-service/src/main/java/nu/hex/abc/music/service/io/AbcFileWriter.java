package nu.hex.abc.music.service.io;

import abc.music.core.domain.Tune;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created 2016-nov-28
 *
 * @author hl
 */
class AbcFileWriter implements Writer<File> {

    private final List<Tune> tunes;
    private final File file;

    public AbcFileWriter(Tune tune, File file) {
        this(Arrays.asList(tune), file);
    }

    public AbcFileWriter(List<Tune> tunes, File file) {
        this.tunes = tunes;
        this.file = file;
    }

    @Override
    public File write() {
        String abcDoc = "%abc-2.2\n\n";
        abcDoc += new AbcWriter(tunes).write();
        return new SimpleFileWriter(file, abcDoc).write();
    }
}
