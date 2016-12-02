package abc.music.core.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Tempo extends Field {

    public static final Integer DEFAULT_UNITS_PER_MINUTE = 96;
    private static final Integer[] mms = {40, 42, 44, 46, 48, 50, 52, 54, 56,
        58, 60, 63, 66, 69, 72, 76, 80, 84, 88, 92, 96, 100, 104, 108, 112, 116,
        120, 126, 132, 138, 144, 152, 160, 168, 176, 184, 192, 200, 208};
    private static final List<Integer> MM = Arrays.asList(mms);
    private String label;
    private Unit unit = Unit.DEFAULT__UNIT;
    private Integer unitsPerMinute = DEFAULT_UNITS_PER_MINUTE;

    public Tempo() {
        super('Q');
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(Marking marking) {
        setLabel(marking.getLabel());
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getUnitsPerMinute() {
        return unitsPerMinute;
    }

    public static List<Integer> getMM() {
        return MM;
    }

    public void setUnitsPerMinute(Integer unitsPerMinute) {
        this.unitsPerMinute = unitsPerMinute;
    }

    public static List<Marking> getTempi() {
        return new ArrayList<>(Arrays.asList(Marking.values()));
    }

    @Override
    public boolean isEmpty() {
        return label == null && (unit == null || unitsPerMinute == null);
    }

    @Override
    public String get() {
        String result = getCode() + ":";
        if (label != null && !label.isEmpty()) {
            result += " \"" + label + "  \"";
        }
        if (unit != null && unitsPerMinute != null) {
            result += " " + unit + "=" + unitsPerMinute;
        }
        return result;
    }

    @Override
    public String toString() {
        if (unit != null && unitsPerMinute != null) {
            return super.toString() + " " + unit + "=" + unitsPerMinute;
        }
        return super.toString();
    }

    public enum Marking {

        LARGHISSIMO(24, 0, 24),
        GRAVE(30, 25, 45),
        LARGO(40, 40, 60),
        LENTO(50, 45, 60),
        LARGHETTO(60, 60, 66),
        ADAGIO(66, 66, 76),
        ADAGIETTO(72, 72, 76),
        MARCIA_MODERATO(84, 83, 85),
        ANDANTE(88, 76, 108),
        ANDANTINO(92, 80, 108),
        ANDANTE_MODERATO(96, 92, 112),
        MODERATO(108, 108, 120),
        ALLEGRETTO(112, 112, 120),
        ALLEGRO_MODERATO(116, 116, 120),
        ALLEGRO(126, 120, 168),
        ALLEGRO_MOLTO(144, 132, 168),
        VIVACE(168, 168, 176),
        ALLEGRO_VIVACE(176, 172, 176),
        VIVACISSIMO(176, 172, 176),
        ALLEGRISSIMO(176, 172, 176),
        PRESTO(192, 168, 200),
        PRESTISSIMO(208, 200, Integer.MAX_VALUE);
        private final int suggested;
        private final int min;
        private final int max;

        private Marking(int suggested, int min, int max) {
            this.suggested = suggested;
            this.min = min;
            this.max = max;
        }

        public int getSuggested() {
            return suggested;
        }

        public String getLabel() {
            return name().substring(0, 1) + name().substring(1).toLowerCase().replaceAll("_", " ");
        }

        @Override
        public String toString() {
            if (max == Integer.MAX_VALUE) {
                return getLabel() + " (≈ " + min + " + )";
            }
            return getLabel() + " (≈ " + min + " - " + max + ")";
        }
    }

    public enum Unit {
        ONE_QUARTER("1/4"),
        ONE_EIGHTH("1/8"),
        THREE_EIGHTH("3/8"),
        TWO_QUARTERS("2/4"),
        THREE_QUARTERS("3/4"),
        ONE_SIXTENTH("1/16"),
        THREE_SIXTENTH("3/16"),
        ONE_HALF("1/2");
        public static final Unit DEFAULT__UNIT = ONE_QUARTER;
        private final String unit;

        private Unit(String unit) {
            this.unit = unit;
        }

        public String getUnit() {
            return unit;
        }

        public static Unit find(String text) {
            for (Unit u : values()) {
                if (u.name().equals(text) || u.getUnit().equals(text)) {
                    return u;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return getUnit();
        }
    }
}
