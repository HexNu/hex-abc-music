package abc.music.core.domain;

import java.util.Objects;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Key extends Field {

    private Pitch pitch = Pitch.DEFAULT_PITCH;
    private Signature signature = Signature.DEFAULT_SIGNATURE;
    private Mode mode = Mode.DEFAULT_MODE;
    private Modifier modifier = new Modifier();

    public Key() {
        super('K');
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
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

    @Override
    public boolean isEmpty() {
        return getPitch() == null && (modifier == null || modifier.isEmpty());
    }

    @Override
    public String get() {
        return getCode() + ": " + getPitch() + getSignature() + getMode();
    }

    @Override
    public String getContent() {
        return toString() + getModifier();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.pitch);
        hash = 59 * hash + Objects.hashCode(this.signature);
        hash = 59 * hash + Objects.hashCode(this.mode);
        hash = 59 * hash + Objects.hashCode(this.modifier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Key other = (Key) obj;
        if (this.pitch != other.pitch) {
            return false;
        }
        if (this.signature != other.signature) {
            return false;
        }
        if (this.mode != other.mode) {
            return false;
        }
        if (!Objects.equals(this.modifier, other.modifier)) {
            return false;
        }
        return true;
    }

    public enum Pitch {

        A, B, C, D, E, F, G;
        public static final Pitch DEFAULT_PITCH = C;

        public String getName() {
            return name();
        }

        public static Pitch find(String text) {
            if (text != null) {
                for (Pitch note : values()) {
                    if (text.equalsIgnoreCase(note.name())) {
                        return note;
                    }
                }
            }
            return C;
        }
    }

    public enum Signature {
        SHARP("#"),
        NATURAL(""),
        FLAT("b");
        public static final Signature DEFAULT_SIGNATURE = NATURAL;
        private final String sign;

        private Signature(String sign) {
            this.sign = sign;
        }

        public String getSign() {
            return sign;
        }

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase();
        }

        public static Signature find(String text) {
            if (text != null) {
                for (Signature signature : values()) {
                    if (text.toUpperCase().equals(signature.name()) || text.equals(signature.getSign())) {
                        return signature;
                    }
                }
            }
            return DEFAULT_SIGNATURE;
        }

        @Override
        public String toString() {
            return getSign();
        }
    }

    public enum Mode {
        MAJOR,
        MINOR,
        MIXOLYDIAN,
        DORIAN,
        PHRYGIAN,
        LYDIAN,
        LOCRIAN,
        IONIAN,
        AEOLIAN;
        public static final Mode DEFAULT_MODE = MAJOR;

        public static Mode find(String text) {
            if (text != null) {
                if (text.isEmpty()) {
                    return MAJOR;
                }
                if (text.equalsIgnoreCase("m")) {
                    return MINOR;
                }
                for (Mode m : values()) {
                    if (m.name().startsWith(text.toUpperCase().substring(0, 3))) {
                        return m;
                    }
                }
            }
            return DEFAULT_MODE;
        }

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase();
        }

        @Override
        public String toString() {
            switch (this) {
                case MAJOR:
                    return "";
                case MINOR:
                    return "m";
                default:
                    return name().toLowerCase().substring(0, 3);
            }
        }
    }

    public static void main(String[] args) {
        Key key = new Key();
        key.setPitch(Pitch.D);
        key.setMode(Mode.MINOR);
        key.getModifier().setClef(Modifier.Clef.TENOR);
        key.getModifier().setTranspose(-8);
        System.out.println(key.getContent());
    }
}
