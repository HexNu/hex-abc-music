package nu.hex.abc.music.editor.util;

import abc.music.core.domain.Field;
import abc.music.core.domain.Tune;
import java.util.List;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class TuneHelper {

    private final Tune tune;

    public TuneHelper(Tune tune) {
        this.tune = tune;
    }

    public String getTitlesAsString() {
        String result = "";
        int index = 0;
        for (String t : tune.getTitles()) {
            if (index++ > 0) {
                result += "\n";
            }
            result += t;
        }
        return result;
    }

    public String getHistoryAsString() {
        return getFieldAsString(tune.getHistory());
    }

    public String getCommentsAsString() {
        return getFieldAsString(tune.getComments());
    }

    public String getOriginAsString() {
        return getFieldAsString(tune.getOrigin());
    }

    public String getCopyrightAsString() {
        return getFieldAsString(tune.getCopyright());
    }

    private String getFieldAsString(List<? extends Field> field) {
        String result = "";
        int index = 0;
        for (Field f : field) {
            if (index++ > 0) {
                result += "\n";
            }
            result += f.getContent();
        }
        return result;
    }
}
