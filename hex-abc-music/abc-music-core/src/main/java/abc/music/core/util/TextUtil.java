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
            System.out.println(line.length());
            if (line.length() >= length) {
                result += line + "\n";
                line = "";
            }
        }
        result += line;
        return result;
    }

    public static void main(String[] args) {
        
        TextUtil textUtil = new TextUtil("På resande fot 649 - 650 besökte jag en rad spelfolk, kvinnor och män, och tecknade ner en mängd nya låtar. Dessa beslöt jag att samla här för att kunna dela med mig av dessa till mina spelvänner.");
        
        System.out.println(textUtil.createLines(100));
//        int nextIndexOf = textUtil.nextIndexOf('\n', 100);
//        System.out.println(nextIndexOf);
    }
}
