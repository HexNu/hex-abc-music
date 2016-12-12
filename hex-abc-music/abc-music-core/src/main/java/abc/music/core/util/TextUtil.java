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
        for (String s : str.split(" ")) {
            line += s + " ";
            if (line.length() >= length) {
                result += line.trim() + "\n";
                line = "";
            }
        }
        result += line.trim();
        return result;
    }
}
