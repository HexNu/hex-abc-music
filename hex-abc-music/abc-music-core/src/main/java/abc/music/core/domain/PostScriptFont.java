package abc.music.core.domain;

/**
 * Created 2016-dec-13
 *
 * @author hl
 */
public enum PostScriptFont {

    EMPTY("NoFont-UseDefault"),
    AGB("AvantGarde-Book"),
    AGBO("AvantGarde-BookOblique"),
    AGD("AvantGarde-Demi"),
    AGDO("AvantGarde-DemiOblique"),
    BD("Bookman-Demi"),
    BDI("Bookman-DemiItalic"),
    BL("Bookman-Light"),
    BLI("Bookman-LightItalic"),
    C("Courier"),
    CB("Courier-Bold"),
    CBO("Courier-BoldOblique"),
    CO("Courier-Oblique"),
    H("Helvetica"),
    HB("Helvetica-Bold"),
    HBO("Helvetica-BoldOblique"),
    HN("Helvetica-Narrow"),
    HNB("Helvetica-NarrowBold"),
    HNBO("Helvetica-NarrowBoldOblique"),
    HNO("Helvetica-NarrowOblique"),
    HO("Helvetica-Oblique"),
    NCSB("NewCenturySchlbk-Bold"),
    NCSBI("NewCenturySchlbk-BoldItalic"),
    NCSI("NewCenturySchlbk-Italic"),
    NCSR("NewCenturySchlbk-Roman"),
    PB("Palatino-Bold"),
    PBI("Palatino-BoldItalic"),
    PI("Palatino-Italic"),
    PR("Palatino-Roman"),
    S("Symbol"),
    TB("Times-Bold"),
    TBI("Times-BoldItalic"),
    TI("Times-Italic"),
    TR("Times-Roman"),
    ZCMI("ZapfChancery-MediumItalic"),
    ZD("ZapfDingbats");
    private final String name;

    public static final PostScriptFont DEFAULT_FONT = EMPTY;

    private PostScriptFont(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PostScriptFont find(String text) {
        if (text == null || text.equalsIgnoreCase(EMPTY.getName())) {
            return null;
        }
        for (PostScriptFont f : values()) {
            if (f.name().equalsIgnoreCase(text.replaceAll("-", "_")) || f.getName().equalsIgnoreCase(text)) {
                return f;
            }
        }
        return DEFAULT_FONT;
    }

    @Override
    public String toString() {
        return getName();
    }
}
