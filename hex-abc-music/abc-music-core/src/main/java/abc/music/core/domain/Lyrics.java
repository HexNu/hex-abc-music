package abc.music.core.domain;

import abc.music.core.util.TextUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created 2016-dec-08
 *
 * @author hl
 */
public class Lyrics {

    private static final String NEW_LINE = "\n";
    private static final String TEXT_INDICATOR = "TEXT:";
    private final Map<Integer, List<String>> strophes = new TreeMap<>();
    private StringBuilder abcStringBuilder;
    private Tune tune;

    public Lyrics() {
    }

    public Lyrics(Tune tune) {
        this.tune = tune;
    }

    public Tune getTune() {
        return tune;
    }

    public void setTune(Tune tune) {
        this.tune = tune;
    }

    public List<String> getStrophe(Integer number) {
        return strophes.get(number);
    }

    public Map<Integer, List<String>> getStrophes() {
        return strophes;
    }

    public void clearStrophes() {
        this.strophes.clear();
    }

    public void addStrophe(List<String> strophe) {
        addStrophe(strophes.size(), strophe);
    }

    public void addStrophe(Integer number, List<String> strophe) {
        strophes.put(number, strophe);
    }

    public void setStrophes(Map<Integer, List<String>> strophes) {
        this.strophes.clear();
        this.strophes.putAll(strophes);
    }

    public String getAbcString() {
        abcStringBuilder = new StringBuilder("% Lyrics:").append(NEW_LINE).append("%").append(NEW_LINE);
        getStrophes().keySet().stream().forEach((stropheNumber) -> {
            abcStringBuilder.append("W: ").append(stropheNumber + 1).append(".").append(NEW_LINE);
            getStrophes().get(stropheNumber).forEach((line) -> {
                if (line.startsWith(TEXT_INDICATOR)) {
                    abcStringBuilder.append("%%textfont Times-Italic 14\n")
                            .append("%%vskip 1.2cm\n")
                            .append("%%begintext\n")
                            .append("%%").append(NEW_LINE);
                    String[] para = new TextUtil(line.substring(TEXT_INDICATOR.length()).trim()).createLines(75).split("\n");
                    for (String l : para) {
                        abcStringBuilder.append("%%").append(l).append(NEW_LINE);
                    }
                    abcStringBuilder.append("%%").append(NEW_LINE)
                            .append("%%endtext\n")
                            .append("%%vskip 1.2cm\n");
                } else {
                    abcStringBuilder.append("W: ");
                    abcStringBuilder.append(line).append(NEW_LINE);
                }
            });
            abcStringBuilder.append("%").append(NEW_LINE);
        });
        return abcStringBuilder.toString().trim();
    }

    public boolean isEmpty() {
        return getStrophes() != null && getStrophes().isEmpty();
    }
}
