package abc.music.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Tempo extends Field {

    public static final String LARGHISSIMO = "Larghissimo";
    public static final String GRAVE = "Grave";
    public static final String LARGO = "Largo";
    public static final String LENTO = "Lento";
    public static final String LARGHETTO = "Larghetto";
    public static final String ADAGIO = "Adagio";
    public static final String ADAGIETTO = "Adagietto";
    public static final String ANDANTE = "Andante";
    public static final String ANDANTINO = "Andantino";
    public static final String MARCIA_MODERATO = "Marcia moderato";
    public static final String ANDANTE_MODERATO = "Andante moderato";
    public static final String MODERATO = "Moderato";
    public static final String ALLEGRETTO = "Allegretto";
    public static final String ALLEGRO_MODERATO = "Allegro moderato";
    public static final String ALLEGRO = "Allegro";
    public static final String VIVACE = "Vivace";
    public static final String VIVACISSIMO = "Vivacissimo";
    public static final String ALLEGRO_VIVACE = "Allegro vivace";
    public static final String PRESTO = "Presto";
    public static final String PRESTISSIMO = "Prestissimo";
    private static final List<String> TEMPI = new ArrayList<>();
    private String label;
    private String unit;
    private Integer unitsPerMinute;

    static {
        TEMPI.add(LARGHISSIMO);
        TEMPI.add(GRAVE);
        TEMPI.add(LARGO);
        TEMPI.add(LENTO);
        TEMPI.add(LARGHETTO);
        TEMPI.add(ADAGIO);
        TEMPI.add(ADAGIETTO);
        TEMPI.add(ANDANTE);
        TEMPI.add(ANDANTINO);
        TEMPI.add(MARCIA_MODERATO);
        TEMPI.add(ANDANTE_MODERATO);
        TEMPI.add(MODERATO);
        TEMPI.add(ALLEGRETTO);
        TEMPI.add(ALLEGRO_MODERATO);
        TEMPI.add(ALLEGRO);
        TEMPI.add(VIVACE);
        TEMPI.add(VIVACISSIMO);
        TEMPI.add(ALLEGRO_VIVACE);
        TEMPI.add(PRESTO);
        TEMPI.add(PRESTISSIMO);
    }

    public Tempo() {
        super('Q');
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getUnitsPerMinute() {
        return unitsPerMinute;
    }

    public void setUnitsPerMinute(Integer unitsPerMinute) {
        this.unitsPerMinute = unitsPerMinute;
    }

    public static List<String> getTempi() {
        return TEMPI;
    }

    @Override
    public boolean isEmpty() {
        return label == null && (unit == null || unit.isEmpty() || unitsPerMinute == null);
    }

    @Override
    public String get() {
        String result = getCode() + ":";
        if (label != null && !label.isEmpty()) {
            result += " \"" + label +  "  \"";
        }
        if (unit != null && !unit.isEmpty() && unitsPerMinute != null) {
            result += " " + unit + "=" + unitsPerMinute;
        }
        return result;
    }

    @Override
    public String toString() {
        if (unit != null && !unit.isEmpty() && unitsPerMinute != null) {
            return super.toString() + " " + unit + "=" + unitsPerMinute;
        }
        return super.toString();
    }
}
