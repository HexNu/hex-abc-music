package abc.music.core.domain;

import abc.music.core.util.TextUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created 2016-dec-08
 *
 * @author hl
 */
public class Lyrics {

    private static final String NEW_LINE = "\n";
    private static final String TEXT_INDICATOR = "TEXT";
    private final Map<Integer, List<String>> strophes = new TreeMap<>();
    private StringBuilder abcStringBuilder;
    private Tune tune;

    public Lyrics() {
    }

    public Lyrics(Tune tune) {
        this.tune = tune;
    }

    public Tune getTune() {
        return tune;
    }

    public void setTune(Tune tune) {
        this.tune = tune;
    }

    public List<String> getStrophe(Integer number) {
        return strophes.get(number);
    }

    public Map<Integer, List<String>> getStrophes() {
        return strophes;
    }

    public void clearStrophes() {
        this.strophes.clear();
    }

    public void addStrophe(List<String> strophe) {
        addStrophe(strophes.size(), strophe);
    }

    public void addStrophe(Integer number, List<String> strophe) {
        strophes.put(number, strophe);
    }

    public void setStrophes(Map<Integer, List<String>> strophes) {
        this.strophes.clear();
        this.strophes.putAll(strophes);
    }

    public String getAbcString() {
        abcStringBuilder = new StringBuilder("% Lyrics:").append(NEW_LINE).append("%").append(NEW_LINE);
        getStrophes().keySet().stream().forEach((stropheNumber) -> {
            abcStringBuilder.append("W: ").append(stropheNumber + 1).append(".").append(NEW_LINE);
            getStrophes().get(stropheNumber).forEach((line) -> {
                if (line.startsWith(TEXT_INDICATOR)) {
                    abcStringBuilder.append(createTextLine(line).get());
                } else {
                    abcStringBuilder.append("W: ").append(line).append(NEW_LINE);
                }
            });
            abcStringBuilder.append("%").append(NEW_LINE);
        });
        return abcStringBuilder.toString().trim();
    }

    public boolean isEmpty() {
        return getStrophes() != null && getStrophes().isEmpty();
    }

    private TextLine createTextLine(String line) {
        TextLine result = new TextLine();
        result.setText(line.substring(line.indexOf(":") + 1).trim());
        String[] args = line.substring(0, line.indexOf(":")).split(" ");
        result.setFont(PostScriptFont.find(args[1]));
        result.setFontSize(Integer.valueOf(args[2]));
        result.setLineLength(Integer.valueOf(args[3]));
        result.setUpperMargin(Double.valueOf(args[4]));
        if (args.length > 5) {
            result.setLowerMargin(Double.valueOf(args[5]));
        } else {
            result.setLowerMargin(result.getUpperMargin());
        }
        return result;
    }

    public static class TextLine {

        private PostScriptFont font;
        private Integer fontSize;
        private Integer lineLength;
        private Double upperMargin;
        private Double lowerMargin;
        private String text;

        public PostScriptFont getFont() {
            return font;
        }

        public void setFont(PostScriptFont font) {
            this.font = font;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public double getUpperMargin() {
            return upperMargin;
        }

        public void setUpperMargin(double upperMargin) {
            this.upperMargin = upperMargin;
        }

        public double getLowerMargin() {
            return lowerMargin;
        }

        public void setLowerMargin(double lowerMargin) {
            this.lowerMargin = lowerMargin;
        }

        public int getLineLength() {
            return lineLength;
        }

        public void setLineLength(int lineLength) {
            this.lineLength = lineLength;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String get() {
            StringBuilder builder = new StringBuilder();
            builder.append("%%textfont ").append(getFont().getName()).append(" ").append(getFontSize()).append(NEW_LINE)
                    .append("%%vskip ").append(getUpperMargin()).append("cm").append(NEW_LINE)
                    .append("%%begintext").append(NEW_LINE);
            String[] para = new TextUtil(getText()).createLines(getLineLength()).split(NEW_LINE);
            for (String l : para) {
                builder.append("%%").append(l).append(NEW_LINE);
            }
            builder.append("%%endtext").append(NEW_LINE)
                    .append("%%vskip ").append(getLowerMargin()).append("cm").append(NEW_LINE);
            return builder.toString();
        }

        @Override
        public String toString() {
            String result = "TEXT " + font.name() + " " + fontSize + " " + lineLength + " " + upperMargin;
            if (lowerMargin != null) {
                result += " " + lowerMargin;
            }
            if (text == null) {
                return result += ": ";
            } else {
                return result += ": " + text;
            }
        }

    }
}
