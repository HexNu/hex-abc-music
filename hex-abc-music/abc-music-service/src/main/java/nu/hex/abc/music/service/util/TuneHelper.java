package nu.hex.abc.music.service.util;

import abc.music.core.domain.Field;
import abc.music.core.domain.Tune;
import java.util.ArrayList;
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

    public List<String> getMusicNotesAsSearchString() {
        List<String> result = new ArrayList<>();
        tune.getVoices().stream().forEach((voice) -> {
            result.add(musicNotesToSearchString(voice.getNotes()));
        });
        return result;
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

    public static String musicNotesToSearchString(String notes) {
        String latestLetter = "";
        String result = "";
        for (String letter : notes
                .toLowerCase()
                .replaceAll("\\s*[\\[\"][^\\)]*[\\]\"]\\s*", "")
                .replaceAll("[^a-g]", "")
                .split("")) {
            if (!letter.equals(latestLetter)) {
                result += letter;
                latestLetter = letter;
            }
        }
        return result;
    }
}
