package abc.music.core.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created 2016-dec-16
 *
 * @author hl
 */
public class FormatTemplate {

    private String name;
    private String shortDescription;
    private final Map<Margin, Double> margins = new HashMap<>();
    private final Map<Font, FontValue> fonts = new HashMap<>();
    private final Map<Space, Double> spaces = new HashMap<>();
    private Double indent = null;
    private Double scale = null;
    private Double maxShrinking = null;
    private Boolean stretchLastStaff = false;
    private Boolean landscape = false;
    private Integer barsPerStaff = null;

    public FormatTemplate() {
    }

    public FormatTemplate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Map<Margin, Double> getMargins() {
        return margins;
    }
    
    public Double getMargin(Margin margin) {
        return margins.get(margin);
    }

    public List<String> getMarginsAsAbcStrings() {
        List<String> result = new ArrayList<>();
        margins.keySet().stream().forEach((margin) -> {
            result.add("%%" + margin.getLabel() + " " + margins.get(margin).toString() + "cm");
        });
        return result;
    }

    public void setMargins(Map<Margin, Double> margins) {
        clearMargins();
        this.margins.putAll(margins);
    }
    
    public void setMargin(Margin margin, Double value) {
        this.margins.put(margin, value);
    }

    public void addMargin(Margin margin, Double value) {
        setMargin(margin, value);
    }

    public void clearMargins() {
        this.margins.clear();
    }

    public boolean hasMargins() {
        return !this.margins.isEmpty();
    }

    public Map<Font, FontValue> getFonts() {
        return fonts;
    }

    public boolean hasFontValue(Font font) {
        return fonts.containsKey(font);
    }

    public FontValue getFontValue(Font font) {
        return fonts.get(font);
    }

    public List<String> getDocumentFontsAsAbcStrings() {
        List<String> result = new ArrayList<>();
        fonts.keySet().stream().filter((font) -> (font.isDocumentFont())).forEach((font) -> {
            result.add(getFontAsAbcString(font));
        });
        return result;
    }

    public String getFontAsAbcString(Font font) {
        String result = "";
        result += "%%" + font.getLabel();
        result += " " + fonts.get(font).getAsAbcString();
        if (font.isBoxed) {
            result += " box";
        }
        return result;
    }

    public void setFonts(Map<Font, FontValue> fonts) {
        clearFonts();
        this.fonts.putAll(fonts);
    }

    public void setFont(Font font, FontValue value) {
        fonts.put(font, value);
    }
    public void addFont(Font font, FontValue value) {
        setFont(font, value);
    }

    public void removeFont(Font font) {
        fonts.put(font, null);
        fonts.remove(font);
    }

    public void clearFonts() {
        fonts.clear();
    }

    public boolean hasFonts() {
        return !fonts.isEmpty();
    }

    public Map<Space, Double> getSpaces() {
        return spaces;
    }

    public List<String> getSpacesAsAbcStrings() {
        List<String> result = new ArrayList<>();
        spaces.keySet().stream().forEach((space) -> {
            result.add("%%" + space.getLabel() + " " + spaces.get(space).toString() + "cm");
        });
        return result;
    }

    public void setSpaces(Map<Space, Double> spaces) {
        clearSpaces();
        this.spaces.putAll(spaces);
    }

    public boolean hasSpaceValue(Space space) {
        return this.spaces.containsKey(space);
    }

    public Double getSpaceValue(Space space) {
        return this.spaces.get(space);
    }
    
    public void setSpace(Space space, Double value) {
        spaces.put(space, value);
    }

    public void addSpace(Space space, Double value) {
        setSpace(space, value);
    }

    public void clearSpaces() {
        this.spaces.clear();
    }

    public Integer getBarsPerStaff() {
        return barsPerStaff;
    }

    public String getBarsPerStaffAsAbcString() {
        if (hasBarsPerStaff()) {
            return "%%barsperstaff " + barsPerStaff;
        }
        return null;
    }

    public void setBarsPerStaff(Integer barsPerStaff) {
        this.barsPerStaff = barsPerStaff;
    }

    public boolean hasBarsPerStaff() {
        return barsPerStaff != null;
    }

    public Double getIndent() {
        return indent;
    }

    public String getIndentAsAbcString() {
        if (hasIndent()) {
            return "%%indent " + getIndent() + "cm";
        }
        return null;
    }

    public void setIndent(Double indent) {
        this.indent = indent;
    }

    public boolean hasIndent() {
        return indent != null;
    }

    public Double getScale() {
        return scale;
    }

    public String getScaleAsAbcString() {
        if (hasScale()) {
            return "%%scale " + getScale();
        }
        return null;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public boolean hasScale() {
        return scale != null;
    }

    public Double getMaxShrinking() {
        return maxShrinking;
    }

    public String getMaxShrinkingAsAbcString() {
        if (hasMaxSrhinking()) {
            return "%%maxshrink " + getMaxShrinking();
        }
        return null;
    }

    public void setMaxShrinking(Double maxShrinking) {
        this.maxShrinking = maxShrinking;
    }

    public boolean hasMaxSrhinking() {
        return maxShrinking != null;
    }

    public Boolean getStretchLastStaff() {
        return stretchLastStaff;
    }

    public String getStretchLastStaffAsAbcString() {
        if (hasStretchLastStaff()) {
            return "%%stretchlast " + (stretchLastStaff ? 1 : 0);
        }
        return null;
    }

    public void setStretchLastStaff(Boolean stretchLastStaff) {
        this.stretchLastStaff = stretchLastStaff;
    }

    public boolean hasStretchLastStaff() {
        return stretchLastStaff != null;
    }

    public Boolean getLandscape() {
        return landscape;
    }

    public String getLandscapeAsAbcString() {
        if (hasLandscape()) {
            return "%%landscape " + (landscape ? 1 : 0);
        }
        return null;
    }

    public void setLandscape(Boolean landscape) {
        this.landscape = landscape;
    }

    public boolean hasLandscape() {
        return landscape != null;
    }

    public static class FontValue {

        private PostScriptFont psFont;
        private Integer size;

        public FontValue() {
        }

        public FontValue(PostScriptFont psFont, Integer size) {
            this.psFont = psFont;
            this.size = size;
        }

        public String getAsAbcString() {
            String result = "";
            result += psFont.getName()
                    + " "
                    + size;
            return result;
        }

        public PostScriptFont getPsFont() {
            return psFont;
        }

        public Integer getSize() {
            return size;
        }
    }

    public static List<Font> getTitleFonts() {
        List<Font> titleFonts = new ArrayList<>();
        titleFonts.add(Font.TITLE);
        titleFonts.add(Font.SUBTITLE);
        titleFonts.add(Font.COLLECTION_NAME);
        titleFonts.add(Font.COLLECTION_TITLE);
        titleFonts.add(Font.COLLECTION_SUBTITLE);
        titleFonts.add(Font.COLLECTION_PREFACE_HEADER);
        titleFonts.add(Font.COLLECTION_BOOK_HEADER);
        titleFonts.add(Font.COLLECTION_PERSON_HEADER);
        return titleFonts;
    }

    public static List<Font> getTextFonts() {
        List<Font> textFonts = new ArrayList<>();
        textFonts.add(Font.VOCAL);
        textFonts.add(Font.WORDS);
        textFonts.add(Font.TEXT);
        textFonts.add(Font.TUNE_COMMENT);
        textFonts.add(Font.TUNE_HISTORY);
        textFonts.add(Font.COLLECTION_PREFACE_TEXT);
        textFonts.add(Font.COLLECTION_BOOK_TEXT);
        textFonts.add(Font.COLLECTION_PERSON_TEXT);
        return textFonts;
    }

    public static List<Font> getOtherFonts() {
        List<Font> result = new ArrayList<>();
        List<Font> titleFonts = getTitleFonts();
        List<Font> textFonts = getTextFonts();
        for (Font font : Font.values()) {
            if (!titleFonts.contains(font) && !textFonts.contains(font)) {
                result.add(font);
            }
        }
        return result;
    }

    public enum Font {
        TITLE("titlefont"),
        SUBTITLE("subtitlefont"),
        COMPOSER("composerfont"),
        PARTS("partsfont"),
        TEMPO("tempofont"),
        VOCAL("vocalfont"),
        CHORD("gchordfont"),
        ANNOTATION("annotationfont"),
        TEXT("textfont"),
        WORDS("wordsfont"),
        VOICE("voicefont"),
        BARNUMBER("barnumberfont", true),
        BARLABEL("barlabelfont"),
        INDEX("indexfont"),
        PRESET_1("setfont-1"),
        PRESET_2("setfont-2"),
        PRESET_3("setfont-3"),
        PRESET_4("setfont-4"),
        TUNE_HISTORY("textfont"),
        TUNE_COMMENT("textfont"),
        COLLECTION_NAME("textfont"),
        COLLECTION_TITLE("textfont"),
        COLLECTION_SUBTITLE("textfont"),
        COLLECTION_PREFACE_HEADER("textfont"),
        COLLECTION_PREFACE_TEXT("textfont"),
        COLLECTION_PERSON_HEADER("textfont"),
        COLLECTION_PERSON_TEXT("textfont"),
        COLLECTION_BOOK_HEADER("textfont"),
        COLLECTION_BOOK_TEXT("textfont"),;
        private final String label;
        private final Boolean isBoxed;

        private Font(String label) {
            this(label, false);
        }

        private Font(String label, Boolean isBoxed) {
            this.label = label;
            this.isBoxed = isBoxed;
        }

        public Boolean isDocumentFont() {
            return !name().contains("_");
        }

        public Boolean isBoxed() {
            return isBoxed;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name().substring(0, 1) + name().replaceAll("_", " ").substring(1).toLowerCase();
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public enum Margin {
        TOP("topmargin"),
        RIGHT("rightmargin"),
        BOTTOM("botmargin"),
        LEFT("leftmargin");
        private final String label;

        private Margin(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase();
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public enum Space {
        TOP("topspace"),
        TITLE("titlespace"),
        SUBTITLE("subtitlespace"),
        COMPOSER("composerspace"),
        MUSIC("musicspace"),
        PARTS("partsspace"),
        VOCAL("vocalspace"),
        WORDS("wordspace"),
        SYSTEM("staffsep"),
        SYSSTEMSTAFF("sysstaffsep"),
        TEXT("textspace"),
        COLLECTION_NAME("vsep"),
        COLLECTION_TITLE("vsep"),
        COLLECTION_SUBTITLE("vsep"),
        COLLECTION_PREFACE_HEADER("vsep"),
        COLLECTION_PREFACE_TEXT("vsep"),
        COLLECTION_PERSON_HEADER("vsep"),
        COLLECTION_PERSON_TEXT("vsep"),
        COLLECTION_BOOK_HEADER("vsep"),
        COLLECTION_BOOK_TEXT("vsep");
        private final String label;

        private Space(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name().substring(0, 1) + name().substring(1).toLowerCase().replaceAll("_", " ");
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
