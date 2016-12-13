package abc.music.core.util;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class TextUtil {

    private final String str;

    public TextUtil(String str) {
        this.str = str;
    }

    public int nextIndexOf(char c, int start) {
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public String createLines(int length) {
        String result = "";
        String line = "";
        for (String sl : str.split("\n")) {
            for (String s : sl.split(" ")) {
                line += s + " ";
                if (line.length() >= length) {
                    result += line.trim() + "\n";
                    line = "";
                }
            }
            if (!line.isEmpty()) {
                result += line.trim();
                line = "";
            }
            result += "\n";
        }
        result += line.trim();
        return result;
    }

    public String camelCaseToHyphens() {
        String result = "";
        for (String s : str.split("(?=[A-Z])")) {
            result += "-" + s.toLowerCase();
        }
        return result.substring(1);
    }
    
    public String hyphensToCamelCase() {
        String result = "";
        for (String s : str.split("-")) {
            result += s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        return result;
    }
}
