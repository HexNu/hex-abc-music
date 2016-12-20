package abc.music.core.util;

/**
 * Created 2016-dec-18
 *
 * @author hl
 */
public enum Keys {

    C_FLAT_MAJ("C", "b", "maj"),
    A_FLAT_MIN("A", "b", "min"),
    G_FLAT_MIX("G", "b", "mix"),
    D_FLAT_DOR("D", "b", "dor"),
    E_FLAT_PHR("E", "b", "phr"),
    F_FLAT_LYD("F", "b", "lyd"),
    B_FLAT_LOC("B", "b", "loc"),
    G_FLAT_MAJ("G", "b", "maj"),
    E_FLAT_MIN("E", "b", "min"),
    D_FLAT_MIX("D", "b", "mix"),
    A_FLAT_DOR("A", "b", "dor"),
    B_FLAT_PHR("B", "b", "phr"),
    C_FLAT_LYD("C", "b", "lyd"),
    F_LOC("F", null, "loc"),
    D_FLAT_MAJ("D", "b", "maj"),
    B_FLAT_MIN("B", "b", "min"),
    A_FLAT_MIX("A", "b", "mix"),
    E_FLAT_DOR("E", "b", "dor"),
    F_PHR("F", null, "phr"),
    G_FLAT_LYD("G", "b", "lyd"),
    C_LOC("C", null, "loc"),
    A_FLAT_MAJ("A", "b", "maj"),
    F_MIN("F", null, "min"),
    E_FLAT_MIX("E", "b", "mix"),
    B_FLAT_DOR("B", "b", "dor"),
    C_PHR("C", null, "phr"),
    D_FLAT_LYD("D", "b", "lyd"),
    G_LOC("G", null, "loc"),
    E_FLAT_MAJ("E", "b", "maj"),
    C_MIN("C", null, "min"),
    B_FLAT_MIX("B", "b", "mix"),
    F_DOR("F", null, "dor"),
    G_PHR("G", null, "phr"),
    A_FLAT_LYD("A", "b", "lyd"),
    D_LOC("D", null, "loc"),
    B_FLAT_MAJ("B", "b", "maj"),
    G_MIN("G", null, "min"),
    F_MIX("F", null, "mix"),
    C_DOR("C", null, "dor"),
    D_PHR("D", null, "phr"),
    E_FLAT_LYD("E", "b", "lyd"),
    A_LOC("A", null, "loc"),
    F_MAJ("F", null, "maj"),
    D_MIN("D", null, "min"),
    C_MIX("C", null, "mix"),
    G_DOR("G", null, "dor"),
    A_PHR("A", null, "phr"),
    B_FLAT_LYD("B", "b", "lyd"),
    E_LOC("E", null, "loc"),
    C_MAJ("C", null, "maj"),
    A_MIN("A", null, "min"),
    G_MIX("G", null, "mix"),
    D_DOR("D", null, "dor"),
    E_PHR("E", null, "phr"),
    F_LYD("F", null, "lyd"),
    B_LOC("B", null, "loc"),
    G_MAJ("G", null, "maj"),
    E_MIN("E", null, "min"),
    D_MIX("D", null, "mix"),
    A_DOR("A", null, "dor"),
    B_PHR("B", null, "phr"),
    C_LYD("C", null, "lyd"),
    F_SHARP_LOC("F", "#", "loc"),
    D_MAJ("D", null, "maj"),
    B_MIN("B", null, "min"),
    A_MIX("A", null, "mix"),
    E_DOR("E", null, "dor"),
    F_SHARP_PHR("F", "#", "phr"),
    G_LYD("G", null, "lyd"),
    C_SHARP_LOC("C", "#", "loc"),
    A_MAJ("A", null, "maj"),
    F_SHARP_MIN("F", "#", "min"),
    E_MIX("E", null, "mix"),
    B_DOR("B", null, "dor"),
    C_SHARP_PHR("C", "#", "phr"),
    D_LYD("D", null, "lyd"),
    G_SHARP_LOC("G", "#", "loc"),
    E_MAJ("E", null, "maj"),
    C_SHARP_MIN("C", "#", "min"),
    B_MIX("B", null, "mix"),
    F_SHARP_DOR("F", "#", "dor"),
    G_SHARP_PHR("G", "#", "phr"),
    A_LYD("A", null, "lyd"),
    D_SHARP_LOC("D", "#", "loc"),
    B_MAJ("B", null, "maj"),
    G_SHARP_MIN("G", "#", "min"),
    F_SHARP_MIX("F", "#", "mix"),
    C_SHARP_DOR("C", "#", "dor"),
    D_SHARP_PHR("D", "#", "phr"),
    E_LYD("E", null, "lyd"),
    A_SHARP_LOC("A", "#", "loc"),
    F_SHARP_MAJ("F", "#", "maj"),
    D_SHARP_MIN("D", "#", "min"),
    C_SHARP_MIX("C", "#", "mix"),
    G_SHARP_DOR("G", "#", "dor"),
    A_SHARP_PHR("A", "#", "phr"),
    B_LYD("B", null, "lyd"),
    E_SHARP_LOC("E", "#", "loc"),
    C_SHARP_MAJ("C", "#", "maj"),
    A_SHARP_MIN("A", "#", "min"),
    G_SHARP_MIX("G", "#", "mix"),
    D_SHARP_DOR("D", "#", "dor"),
    E_SHARP_PHR("E", "#", "phr"),
    F_SHARP_LYD("F", "#", "lyd"),
    B_SHARP_LOC("B", "#", "loc");
    private final String pitch;
    private final String accidental;
    private final String mode;
    public static final Keys DEFAULT_KEY = C_MAJ;

    private Keys(String pitch, String signature, String mode) {
        this.pitch = pitch;
        this.accidental = signature;
        this.mode = mode;
    }

    public String getPitch() {
        return pitch;
    }

    public String getAccidental() {
        return accidental;
    }

    public String getMode() {
        return mode;
    }

    @Override
    public String toString() {
        String result = pitch;
        if (accidental != null) {
            result += accidental;
        }
        if (mode.equals("maj")) {
            return result;
        }
        if (mode.equals("min")) {
            return result + "m";
        }
        return result + mode;
    }
}
