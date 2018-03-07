package abc.music.core.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hln
 */
public class FileSize {

    private final Long size;
    private static final double BYTE = 1, KILO_BYTE = 1024,
            MEGA_BYTE = KILO_BYTE * KILO_BYTE,
            GIGA_BYTE = MEGA_BYTE * KILO_BYTE,
            TERA_BYTE = GIGA_BYTE * KILO_BYTE,
            PETA_BYTE = TERA_BYTE * KILO_BYTE,
            EXA_BYTE = PETA_BYTE * KILO_BYTE;
    private static final String B = "byte", kB = "kB",
            MB = "MB",
            GB = "GB",
            TB = "TB",
            PB = "PB",
            EB = "EB";
    private static final Map<String, Double> divMap = new HashMap<>();

    static {
        divMap.put(B, BYTE);
        divMap.put(kB, KILO_BYTE);
        divMap.put(MB, MEGA_BYTE);
        divMap.put(GB, GIGA_BYTE);
        divMap.put(TB, TERA_BYTE);
        divMap.put(PB, PETA_BYTE);
        divMap.put(EB, EXA_BYTE);
    }

    public FileSize(Long size) {
        this.size = size;
    }

    public String get() {
        if (size <= 0.5 * KILO_BYTE) {
            return getAsBytes();
        } else if (size <= 2 * MEGA_BYTE) {
            return getAsKiloBytes();
        } else if (size <= 2 * GIGA_BYTE) {
            return getAsMegaBytes();
        } else if (size <= 2 * TERA_BYTE) {
            return getAsGigaBytes();
        } else if (size <= 2 * PETA_BYTE) {
            return getAsTeraBytes();
        } else if (size <= 2 * EXA_BYTE) {
            return getAsPetaBytes();
        } else {
            return getAsExaBytes();
        }
    }

    public String getAsBytes() {
        return getAsString(B);
    }

    public String getAsKiloBytes() {
        return getAsString(kB);
    }

    public String getAsMegaBytes() {
        return getAsString(MB);
    }

    public String getAsGigaBytes() {
        return getAsString(GB);
    }

    public String getAsTeraBytes() {
        return getAsString(TB);
    }

    public String getAsPetaBytes() {
        return getAsString(PB);
    }

    public String getAsExaBytes() {
        return getAsString(EB);
    }

    private String getAsString(String unit) {
        String string = new DecimalFormat("###,###.#").format(size / divMap.get(unit));
        return new StringBuilder().append(string)
                .append(" ").append(unit).toString();
    }

    public static void main(String[] args) {
        FileSize fileSize = new FileSize(Long.MAX_VALUE);
//        System.out.println(fileSize.getAsString(GB));
//        System.out.println(fileSize.getAsTeraBytes());
        System.out.println(fileSize.get());
        System.out.println(fileSize.getAsPetaBytes());
        System.out.println(fileSize.getAsTeraBytes());
    }
}
