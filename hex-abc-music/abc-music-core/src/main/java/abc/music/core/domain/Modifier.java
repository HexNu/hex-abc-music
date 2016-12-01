package abc.music.core.domain;

import java.util.Objects;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Modifier {

    private Clef clef;
    private OctaveClef octave;
    private Integer transpose;

    public Modifier() {
        this(null);
    }

    public Modifier(Integer transpose) {
        this(Clef.DEFAULT_CLEF, OctaveClef.DEFAULT_OCTAVE, transpose);
    }

    public Modifier(Clef clef, OctaveClef octave, Integer transpose) {
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

    public OctaveClef getOctave() {
        return octave;
    }

    public void setOctave(OctaveClef octave) {
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
                if (getOctave() != null && !getOctave().equals(OctaveClef.DEFAULT_OCTAVE)) {
                    result += " clef=" + getClef();
                }
            } else {
                result += " clef=" + getClef();
            }
        }
        if (getOctave() != null && !getOctave().equals(OctaveClef.DEFAULT_OCTAVE)) {
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
        TREBLE("G", 2),
        FRENCH_VIOLIN("G", 1),
        BASS("F", 4),
        BARITONE("F", 3),
        TENOR("C", 4),
        ALTO("C", 3),
        MEZZOSOPRANO("C", 2),
        SOPRANO("C", 1);
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
            return name().substring(0, 1) + name().substring(1).replaceAll("_", " ").toLowerCase();
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
                case FRENCH_VIOLIN:
                case BARITONE:
                case TENOR:
                case MEZZOSOPRANO:
                case SOPRANO:
                    return getSpecifier() + getLineNumber();
                default:
                    return getSpecifier(); 
            }
        }
    }

    public enum OctaveClef {
        
        NORMAL(""),
        UP("+8"),
        DOWN("-8"),
        TWICE_UP("+15"),
        TWICE_DOWN("-15");
        private final String value;
        public static final OctaveClef DEFAULT_OCTAVE = NORMAL;

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase();
        }

        private OctaveClef(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static OctaveClef find(String text) {
            if (text != null) {
                if (text.isEmpty()) {
                    return NORMAL;
                }
                for (OctaveClef octave : values()) {
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
