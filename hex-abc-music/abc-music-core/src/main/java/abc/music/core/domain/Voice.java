package abc.music.core.domain;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Voice extends Field {

    private Tune tune;
    private String voiceId;
    private String name;
    private String shortName;
    private Stem stem = Stem.DEFAULT_STEM;
    private Key key = new Key();
    private String notes;

    public Voice(Tune tune, String voiceId) {
        super('V');
        if (voiceId == null || voiceId.isEmpty()) {
            throw new NullPointerException("Voice ID can not be null or empty");
        }
        this.tune = tune;
        this.voiceId = voiceId.replaceAll("\\s", "");
        name = "Voice " + this.voiceId;
    }

    public void setTune(Tune tune) {
        this.tune = tune;
    }

    public Tune getTune() {
        return tune;
    }

    public String getVoiceId() {
        return voiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Stem getStem() {
        return stem;
    }

    public void setStem(Stem stem) {
        this.stem = stem;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public boolean hasKey() {
        return key != null && !key.isEmpty();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String get() {
        String result = "";
        result += getCode() + ": " + getVoiceId();
        if (getName() != null) {
            result += " nm=\"" + getName() + "\"";
        }
        if (getShortName() != null) {
            result += " snm=\"" + getShortName() + "\"";
        }
        if (hasKey() && getKey().hasModifier()) {
            result += getKey().getModifier().get();
        }
        if (hasKey() && !tune.getKey().get().equals(key.get())) {
            result += "\n[" + getKey().get() + "]";
        }
        return result;
    }

    public enum Stem {
        UP, DOWN, NORMAL;
        public static final Stem DEFAULT_STEM = NORMAL;

        public static Stem find(String text) {
            if (text != null) {
                if (text.isEmpty()) {
                    return NORMAL;
                } else {
                    for (Stem stem : values()) {
                        if (text.toUpperCase().equals(stem.name())) {
                            return stem;
                        }
                    }
                }
            }
            return DEFAULT_STEM;
        }
        
        public String getName() {
            return this.equals(NORMAL) ? "" : name().toLowerCase();
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
