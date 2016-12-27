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
    private Boolean useVoiceKey = false;
    private Modifier modifier = new Modifier();
    private Boolean useVoiceModifiers = false;
    private String notes;
    private VoiceMidiChannel midiChannel;

    public Voice() {
        super('V');
    }

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

    public void setVoiceId(String voiceId) {
        this.voiceId = voiceId.replaceAll("\\s", "");
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

    public Boolean getUseVoiceKey() {
        return useVoiceKey;
    }

    public void setUseVoiceKey(Boolean useVoiceKey) {
        this.useVoiceKey = useVoiceKey;
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

    public Boolean getUseVoiceModifiers() {
        return useVoiceModifiers;
    }

    public void setUseVoiceModifiers(Boolean useVoiceModifiers) {
        this.useVoiceModifiers = useVoiceModifiers;
    }

    public String getNotes() {
        return notes != null ? notes.trim() : "";
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public VoiceMidiChannel getMidiChannel() {
        return midiChannel;
    }

    public boolean hasMidiChannel() {
        return midiChannel != null;
    }

    public void setMidiChannel(VoiceMidiChannel midiChannel) {
        this.midiChannel = midiChannel;
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
        if (hasModifier()) {
            result += getModifier().get();
        }
        if (hasKey() && !tune.getKey().get().equals(key.get())) {
            result += "\n[" + getKey().get() + "]";
        }
        if (hasMidiChannel()) {
            result += "\n%%MIDI program " + getMidiChannel().getIndex() + " " + getMidiChannel().getChannel().getProgram() + " % " + getMidiChannel().getChannel().getName();
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
