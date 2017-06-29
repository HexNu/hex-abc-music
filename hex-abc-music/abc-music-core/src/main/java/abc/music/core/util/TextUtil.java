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

    public String removeTextBetweenChars(char... chars) {
        String result = str;
        for (char c : chars) {
            result = result.replaceAll(c + ".*?" + c, "");
        }
        return result;
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

    public String capitalizeFirst() {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static void main(String[] args) {
        String notes = "DF AB/c/ d2 | ce/c/ (dA) AF | (G/A/)G/F/ (EB) A>F | GF/E/ (GF) F>F | (E/D/)C/B,/ A,C/E/ AG |";
        int i = new TextUtil(notes.replaceAll("\\s", "")).nextIndexOf('|', 20);
        System.out.println(notes.replaceAll("\\s", "").substring(0, i + 1));
        notes = "G d B3/ | c B/A/ B3/ | c/B/ A G/A/B/ | A G G F/ | E G/F/ D3/ | ";
        i = new TextUtil(notes.replaceAll("\\s", "")).nextIndexOf('|', 20);
        System.out.println(notes.replaceAll("\\s", "").substring(0, i + 1));
        notes = "\"G\"G A/ G : \"D\"F/E/F/ \"D\"G !pizz.!D | \"C\"E D/ C : C/B,/C/ \"D\"D2 | \"G\"G A/ G : \"D\"F/E/F/ \"G\"G B | ";
        notes = new TextUtil(notes.replaceAll("\\s", "")).removeTextBetweenChars('!', '"');
        i = new TextUtil(notes).nextIndexOf('|', 20);
//        i = new TextUtil(notes.replaceAll("\\s", "").removeTextBetweenChars('!', '"')).nextIndexOf('|', 20);
//        System.out.println(new TextUtil(notes.replaceAll("\\s", "")).removeTextBetweenChars('!', '"'));
        System.out.println(notes.substring(0, i + 1));
    }
}
