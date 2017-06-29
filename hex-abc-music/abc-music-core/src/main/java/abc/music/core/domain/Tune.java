package abc.music.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Tune {

    private Integer id;
    private Project project;
    private final List<String> titles = new ArrayList<>();
    private final List<PersonRole> creators = new ArrayList<>();
    private final List<Origin> origin = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private final List<History> history = new ArrayList<>();
    private final List<Copyright> copyright = new ArrayList<>();
    private String rythm;
    private Tempo tempo = new Tempo();
    private Meter meter = new Meter();
    private TimeValue timeValue = TimeValue.DEFAULT_TIME_VALUE;
    private Key key = new Key();
    private Modifier modifier = new Modifier();
    private final Map<String, Voice> voices = new HashMap<>();
    private Lyrics lyrics = new Lyrics();
    private String scoreLayout;

    public Tune() {
        this.project = null;
        this.id = 1;
    }

    public Tune(Project project) {
        this.project = project;
        this.id = project.getNextTuneId();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public boolean hasTitles() {
        return titles != null && !titles.isEmpty();
    }

    public List<String> getTitles() {
        return titles;
    }

    public String getName() {
        if (hasTitles() && titles.get(0) != null && !titles.get(0).isEmpty()) {
            return getTitles().get(0);
        }
        return "Tune " + id;
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

    public void removeCreator(PersonRole creator) {
        for (Iterator<PersonRole> it = creators.iterator(); it.hasNext();) {
            PersonRole pr = it.next();
            if (pr.equals(creator)) {
                it.remove();
            }
        }
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

    public boolean hasHistory() {
        return history != null && !history.isEmpty();
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

    public List<Copyright> getCopyright() {
        return copyright;
    }

    public void setCopyright(List<Copyright> copyright) {
        this.copyright.clear();
        this.copyright.addAll(copyright);
    }

    public void addCopyright(Copyright copyright) {
        this.copyright.add(copyright);
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

    public TimeValue getTimeValue() {
        return timeValue;
    }

    public String getTimeValueField() {
        return "L: 1/" + timeValue.getFraction();
    }

    public boolean hasTimeValue() {
        return timeValue != null;
    }

    public void setTimeValue(TimeValue timeValue) {
        this.timeValue = timeValue;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public boolean hasModifier() {
        return modifier != null && !modifier.isEmpty();
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
        List<Voice> result = new ArrayList<>(voices.values());
        try {
            Collections.sort(result, (a, b) -> Integer.valueOf(a.getVoiceId()).compareTo(Integer.valueOf(b.getVoiceId())));
        } catch (NumberFormatException e) {
        }
        return result;
    }

    public boolean hasVoiceWithId(String id) {
        return voices.containsKey(id);
    }

    public void setVoices(List<Voice> voices) {
        this.voices.clear();
        voices.stream().forEach(this::addVoice);
    }

    public void addVoice(Voice voice) {
        voice.setTune(this);
        this.voices.put(voice.getVoiceId(), voice);
    }

    public Lyrics getLyrics() {
        return lyrics;
    }

    public boolean hasLyrics() {
        return getLyrics() != null && !getLyrics().isEmpty();
    }

    public void setLyrics(Lyrics lyrics) {
        lyrics.setTune(this);
        this.lyrics = lyrics;
    }

    public String getScoreLayout() {
        return scoreLayout;
    }

    public boolean hasScoreLayout() {
        return scoreLayout != null && !scoreLayout.isEmpty();
    }

    public void setScoreLayout(String scoreLayout) {
        this.scoreLayout = scoreLayout;
    }

    public String getStart() {
        String result = "X: " + id + "\n";
//        result += "T: " + getName() + "\n";
        result += getMeter().get() + "\n";
        result += "L: " + getTimeValue().toString() + "\n";
        result += getKey().get() + "\n";
        result += getVoices().get(0).getStartOfNotes();
        return result;
    }

    public enum TimeValue {

        HALF("2"),
        QUARTER("4"),
        EIGHTH("8"),
        SIXTEENTH("16"),
        THIRTY_SECOND("32"),
        SIXTY_FORTH("64");
        public static TimeValue DEFAULT_TIME_VALUE = EIGHTH;
        private final String fraction;

        private TimeValue(String fraction) {
            this.fraction = fraction;
        }

        public String getFraction() {
            return fraction;
        }

        @Override
        public String toString() {
            return "1/" + getFraction();
        }

        public static TimeValue find(String text) {
            if (text != null && !text.isEmpty()) {
                for (TimeValue unit : values()) {
                    if (text.equalsIgnoreCase(unit.name()) || text.equals(unit.toString())) {
                        return unit;
                    }
                }
            }
            return DEFAULT_TIME_VALUE;
        }
    }
}
