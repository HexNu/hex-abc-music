package abc.music.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Tune {

    private Integer id;
    private final List<String> titles = new ArrayList<>();
    private final List<PersonRole> creators = new ArrayList<>();
    private final List<Origin> origin = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private final List<History> history = new ArrayList<>();
    private String rythm;
    private Tempo tempo;
    private Meter meter;
    private Unit unitNoteLength;
    private Key key;
    private final List<Voice> voices = new ArrayList<>();

    public Tune() {
    }

    public Tune(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdField() {
        return "X: " + id;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getTitleFields() {
        List<String> result = new ArrayList<>();
        getTitles().forEach((title) -> {
            result.add("T: " + title);
        });
        return result;
    }

    public void setTitles(List<String> titles) {
        this.titles.clear();
        this.titles.addAll(titles);
    }

    public void addTitle(String title) {
        this.titles.add(title);
    }

    public List<PersonRole> getCreators() {
        return creators;
    }

    public void setCreators(List<PersonRole> creators) {
        this.creators.clear();
        this.creators.addAll(creators);
    }

    public void addCreator(PersonRole originator) {
        this.creators.add(originator);
    }

    public List<Origin> getOrigin() {
        return origin;
    }

    public void setOrigin(List<Origin> origin) {
        this.origin.clear();
        this.origin.addAll(origin);
    }

    public void addOrigin(Origin origin) {
        this.origin.add(origin);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history.clear();
        this.history.addAll(history);
    }

    public void addHistory(History history) {
        this.history.add(history);
    }

    public String getRythm() {
        return rythm;
    }

    public String getRythmField() {
        return "R: " + rythm;
    }

    public boolean hasRythmField() {
        return rythm != null && !rythm.isEmpty();
    }

    public void setRythm(String rythm) {
        this.rythm = rythm;
    }

    public Tempo getTempo() {
        return tempo;
    }

    public boolean hasTempo() {
        return tempo != null && !tempo.isEmpty();
    }

    public void setTempo(Tempo tempo) {
        this.tempo = tempo;
    }

    public Meter getMeter() {
        return meter;
    }

    public boolean hasMeter() {
        return meter != null && !meter.isEmpty();
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public Unit getUnitNoteLength() {
        return unitNoteLength;
    }

    public String getUnitField() {
        return "L: 1/" + unitNoteLength.getFraction();
    }

    public boolean hasUnit() {
        return unitNoteLength != null;
    }

    public void setUnitNoteLength(Unit unitNoteLength) {
        this.unitNoteLength = unitNoteLength;
    }

    public Key getKey() {
        return key;
    }
    
    public boolean hasKey() {
        return key != null && !key.isEmpty();
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices.clear();
        voices.stream().forEach(this::addVoice);
    }

    public void addVoice(Voice voice) {
        voice.setTune(this);
        this.voices.add(voice);
    }

    public enum Unit {
        HALF("2"),
        QUARTER("4"),
        EIGHTH("8"),
        SIXTEENTH("16"),
        THIRTY_SECOND("32");
        public static Unit DEFAULT_UNIT = EIGHTH;
        private final String fraction;

        private Unit(String fraction) {
            this.fraction = fraction;
        }

        public String getFraction() {
            return fraction;
        }

        @Override
        public String toString() {
            return "1/" + getFraction();
        }

        public static Unit find(String text) {
            if (text != null && text.isEmpty()) {
                for (Unit unit : values()) {
                    if (text.toUpperCase().equals(unit.name()) || text.toUpperCase().equals(unit.toString())) {
                        return unit;
                    }
                }
            }
            return DEFAULT_UNIT;
        }
    }

    public static void main(String[] args) {
        Tune tune = new Tune();
    }
}
