package abc.music.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class Main {

    public static void main(String[] args) {
//        String t = "a;b;c: b";
//        System.out.println(t.substring(t.indexOf(":") + 1).trim());
//        System.out.println(t.substring(0, t.indexOf(":")));
        String fonts = "AvantGarde-Demi, AvantGarde-DemiOblique, AvantGarde-Book, AvantGarde-BookOblique, Bookman-Light, Bookman-LightItalic, Bookman-Demi, Bookman-DemiItalic, Courier, Courier-Oblique, Courier-Bold, Courier-BoldOblique, Helvetica, Helvetica-Oblique, Helvetica-Bold, Helvetica-BoldOblique, Helvetica-Narrow, Helvetica-NarrowOblique, Helvetica-NarrowBold, Helvetica-NarrowBoldOblique, NewCenturySchlbk-Roman, NewCenturySchlbk-Italic, NewCenturySchlbk-Bold, NewCenturySchlbk-BoldItalic, Palatino-Roman, Palatino-Italic, Palatino-Bold, Palatino-BoldItalic, Symbol, Times-Roman, Times-Italic, Times-Bold, Times-BoldItalic, ZapfChancery-MediumItalic, ZapfDingbats";
        List<String> fontList = Arrays.asList(fonts.split(", "));
        Collections.sort(fontList, (a,b) -> a.compareTo(b));
        for (String font : fontList) {
//            System.out.println(font.replaceAll("[^A-Z]", ""));
            System.out.println("            " + font.replaceAll("[^A-Z]", "") + "(\"" + font.trim() + "\"),");
        }
    }
}
