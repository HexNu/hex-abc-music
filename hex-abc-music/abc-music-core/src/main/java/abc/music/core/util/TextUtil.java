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
    
    public static void main(String[] args) {
        TextUtil textUtil = new TextUtil("E | ^G>^F G/A/B/G/ | E3 B | cd/c/ Bc/B/ | A>B c/B/c/d/ | ee dc/B/ | \n" +
                "d2 cA | B^G ^FG/A/ | ^G3 y:: B | c>B A/B/c/d/ | e2 e2 | c>B A^G/A/ | \n" +
                "B/c/B/^G/ EB | c>B A/B/c/e/ | Bc/d/ c>B | AB/^G/ E^G | A>B c/B/^G/B/ | A3 :|]");
        int nextIndexOf = textUtil.nextIndexOf('\n', 100);
        System.out.println(nextIndexOf);
    }
}
