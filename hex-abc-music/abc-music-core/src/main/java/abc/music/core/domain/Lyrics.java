package abc.music.core.domain;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created 2016-dec-08
 *
 * @author hl
 */
public class Lyrics {

    private final Map<Integer, List<String>> strophes = new TreeMap<>();
    private StringBuilder abcStringBuilder;
    private boolean firstLineRead;
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
        abcStringBuilder = new StringBuilder();
        getStrophes().keySet().stream().forEach((stropheNumber) -> {
            firstLineRead = false;
            abcStringBuilder.append("W: ").append(stropheNumber).append(".~");
            getStrophes().get(stropheNumber).forEach((line) -> {
                if (firstLineRead) {
                    abcStringBuilder.append("W: ");
                }
                abcStringBuilder.append(line).append("\n");
                firstLineRead = true;
            });
            abcStringBuilder.append("\n");
        });
        return abcStringBuilder.toString().trim();
    }

    public boolean isEmpty() {
        return getStrophes() != null && getStrophes().isEmpty();
    }
}
