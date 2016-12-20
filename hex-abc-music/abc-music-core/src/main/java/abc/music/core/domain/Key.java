package abc.music.core.domain;

import abc.music.core.util.KeyList;
import java.util.Objects;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Key extends Field {

    private Pitch pitch = Pitch.DEFAULT_PITCH;
    private Accidental accidental = Accidental.DEFAULT_ACCIDENTAL;
    private Mode mode = Mode.DEFAULT_MODE;

    public Key() {
        super('K');
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Accidental getAccidental() {
        return accidental;
    }

    public void setAccidental(Accidental accidental) {
        this.accidental = accidental;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public boolean isEmpty() {
        return getPitch() == null;
    }

    @Override
    public String get() {
        return getCode() + ": " + getPitch() + getAccidental() + getMode();
    }

    @Override
    public String getContent() {
        return toString();
    }

    public static Key valueOf(String key) {
        Key result = new Key();
        result.setPitch(getPitchFromString(key));
        result.setAccidental(getAccidentalFromString(key));
        result.setMode(getModeFromString(key));
        return result;
    }

    public static Pitch getPitchFromString(String keyString) {
        return Pitch.find(keyString.substring(0, 1));
    }

    public static Accidental getAccidentalFromString(String keyString) {
        return Accidental.find(keyString.replaceAll("[A-Zac-z]", ""));
    }

    public static Mode getModeFromString(String keyString) {
        return Mode.find(keyString.substring(1).replaceAll("[b#]", ""));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.pitch);
        hash = 59 * hash + Objects.hashCode(this.accidental);
        hash = 59 * hash + Objects.hashCode(this.mode);
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
        if (this.accidental != other.accidental) {
            return false;
        }
        if (this.mode != other.mode) {
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

        @Override
        public String toString() {
            return name();
        }
    }

    public enum Accidental {
        SHARP("#", "♯"),
        NATURAL("", ""),
        FLAT("b", "♭");
        public static final Accidental DEFAULT_ACCIDENTAL = NATURAL;
        private final String sign;
        private final String symbol;

        private Accidental(String sign, String symbol) {
            this.sign = sign;
            this.symbol = symbol;
        }

        /**
         * The sign representing the accidental in abc-files.
         * <br>
         * #, b or empty string
         *
         * @return
         */
        public String getSign() {
            return sign;
        }

        /**
         * The utf-symbol for the accidental.
         * <br>
         * ♯, ♭ or empty string.
         *
         * @return
         */
        public String getSymbol() {
            return symbol;
        }

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase();
        }

        public static Accidental find(String text) {
            if (text != null) {
                for (Accidental signature : values()) {
                    if (text.toUpperCase().equals(signature.name())
                            || text.equalsIgnoreCase(signature.getSign())
                            || text.equals(signature.getSymbol())) {
                        return signature;
                    }
                }
            }
            return DEFAULT_ACCIDENTAL;
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

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase();
        }

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
        String[] modi = KeyList.getModi().toArray(new String[KeyList.getModi().size()]);
        new KeyList(modi).getSelectedModi().stream().forEach((k) -> {
            System.out.println(Key.valueOf(k.toString()).get());
        });
    }
}
