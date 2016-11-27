package abc.music.core.domain;

import java.util.Objects;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Modifier {

    private Clef clef;
    private Octave octave;
    private Integer transpose;

    public Modifier() {
        this(null);
    }

    public Modifier(Integer transpose) {
        this(Clef.DEFAULT_CLEF, Octave.DEFAULT_OCTAVE, transpose);
    }

    public Modifier(Clef clef, Octave octave, Integer transpose) {
        this.clef = clef;
        this.octave = octave;
        this.transpose = transpose;
    }

    public Clef getClef() {
        return clef;
    }

    public void setClef(Clef clef) {
        this.clef = clef;
    }

    public Octave getOctave() {
        return octave;
    }

    public void setOctave(Octave octave) {
        this.octave = octave;
    }

    public Integer getTranspose() {
        return transpose;
    }

    public void setTranspose(Integer transpose) {
        this.transpose = transpose;
    }

    public boolean isEmpty() {
        return get().isEmpty();
    }

    public String get() {
        String result = "";
        if (getClef() != null) {
            if (getClef().equals(Clef.DEFAULT_CLEF)) {
                if (getOctave() != null && !getOctave().equals(Octave.DEFAULT_OCTAVE)) {
                    result += " clef=" + getClef();
                }
            } else {
                result += " clef=" + getClef();
            }
        }
        if (getOctave() != null && !getOctave().equals(Octave.DEFAULT_OCTAVE)) {
            result += getOctave();
        }
        if (transpose != null && transpose != 0) {
            result += " transpose=" + transpose;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.clef);
        hash = 47 * hash + Objects.hashCode(this.octave);
        hash = 47 * hash + Objects.hashCode(this.transpose);
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
        final Modifier other = (Modifier) obj;
        if (this.clef != other.clef) {
            return false;
        }
        if (this.octave != other.octave) {
            return false;
        }
        if (!Objects.equals(this.transpose, other.transpose)) {
            return false;
        }
        return true;
    }

    public enum Clef {
        TREBLE("treble", 2),
        BASS("bass", 4),
        BARITONE("bass", 3),
        TENOR("tenor", 4),
        ALTO("alto", 3),
        MEZZOSOPRANO("alto", 2),
        SOPRANO("alto", 1);
        private final String specifier;
        public static final Clef DEFAULT_CLEF = TREBLE;
        private final int lineNumber;

        private Clef(String specifier, int lineNumber) {
            this.specifier = specifier;
            this.lineNumber = lineNumber;
        }

        public String getSpecifier() {
            return specifier;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase();
        }

        public static Clef find(String text) {
            if (text != null) {
                if (text.isEmpty()) {
                    return TREBLE;
                }
                for (Clef clef : values()) {
                    if (text.equalsIgnoreCase(clef.name()) || text.equalsIgnoreCase(clef.getSpecifier())) {
                        return clef;
                    }
                }
            }
            return DEFAULT_CLEF;
        }

        @Override
        public String toString() {
            switch (this) {
                case BARITONE:
                case MEZZOSOPRANO:
                case SOPRANO:
                    return getSpecifier() + getLineNumber();
                default:
                    return getSpecifier();
            }
        }
    }

    public enum Octave {
        UP("+8"),
        DOWN("-8"),
        NORMAL("");
        private final String value;
        public static final Octave DEFAULT_OCTAVE = NORMAL;

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase();
        }

        private Octave(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Octave find(String text) {
            if (text != null) {
                if (text.isEmpty()) {
                    return NORMAL;
                }
                for (Octave octave : values()) {
                    if (text.equalsIgnoreCase(octave.name()) || text.equalsIgnoreCase(octave.getValue())) {
                        return octave;
                    }
                }
            }
            return DEFAULT_OCTAVE;
        }

        @Override
        public String toString() {
            return getValue();
        }
    }
}
