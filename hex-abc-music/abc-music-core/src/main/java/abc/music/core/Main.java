package abc.music.core;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class Main {

    public String invert(String input) {
        String result = "";
        for (String s : input.split("")) {
            result = s + result;
        }
        return result;
    }

    public static void main(String[] args) {

        String alphabet = "abcdefghijklmnopqrstuvwxyzåäö";
        String input = "Den här meningen ska vara inverterad";
        String result = new Main().invert(input);
        System.out.println(result);
//        String consonants = "bcdfghjklmnpqrstvwxz";
//        String vowels = "aeiouyåäö";
//        for (String s : alphabet.split("")) {
//            System.out.println(s);
//        }
//        for (String s : alphabet.split("[" + vowels + "]")) {
//            if (!s.isEmpty()) {
//                System.out.println(s);
//            }
//        }
//        for (String s : alphabet.split("[" + consonants + "]")) {
//            if (!s.isEmpty()) {
//                System.out.println(s);
//            }
//        }
//        for (Keys key : Keys.values()) {
//            if (key.getMode().equals("maj") || key.getMode().equals("min") || key.getMode().equals("dor")) {
//                System.out.println(key);
//            }
//        }
//        String keys = "C#;A#m;G#Mix;D#Dor;E#Phr;F#Lyd;B#Loc\n"
//                + "F#;D#m;C#Mix;G#Dor;A#Phr;BLyd;E#Loc\n"
//                + "B;G#m;F#Mix;C#Dor;D#Phr;ELyd;A#Loc\n"
//                + "E;C#m;BMix;F#Dor;G#Phr;ALyd;D#Loc\n"
//                + "A;F#m;EMix;BDor;C#Phr;DLyd;G#Loc\n"
//                + "D;Bm;AMix;EDor;F#Phr;GLyd;C#Loc\n"
//                + "G;Em;DMix;ADor;BPhr;CLyd;F#Loc\n"
//                + "C;Am;GMix;DDor;EPhr;FLyd;BLoc\n"
//                + "F;Dm;CMix;GDor;APhr;BbLyd;ELoc\n"
//                + "Bb;Gm;FMix;CDor;DPhr;EbLyd;ALoc\n"
//                + "Eb;Cm;BbMix;FDor;GPhr;AbLyd;DLoc\n"
//                + "Ab;Fm;EbMix;BbDor;CPhr;DbLyd;GLoc\n"
//                + "Db;Bbm;AbMix;EbDor;FPhr;GbLyd;CLoc\n"
//                + "Gb;Ebm;DbMix;AbDor;BbPhr;CbLyd;FLoc\n"
//                + "Cb;Abm;GbMix;DbDor;EbPhr;FbLyd;BbLoc";
//        List<String> rows = new ArrayList<>();
//        for (String s : keys.split("\n")) {
//            rows.add(0, s);
//        }
//        for (String r : rows) {
//            for (String str : r.split(";")) {
//                String pitch = null;
//                String mode = null;
//                String signature = null;
//                switch (str.length()) {
//                    case 1:
//                        pitch = str;
//                        mode = "maj";
//                        break;
//                    case 2: {
//                        String s = str.substring(1, 2);
//                        if (s.equals("b") || s.equals("#")) {
//                            pitch = str.substring(0, 1);
//                            mode = "maj";
//                            signature = s;
//                        } else if (s.equals("m")) {
//                            pitch = str.substring(0, 1);
//                            mode = "min";
//                        }
//                        break;
//                    }
//                    case 3: {
//                        String s = str.substring(1, 2);
//                        if (s.equals("b") || s.equals("#")) {
//                            pitch = str.substring(0, 1);
//                            mode = "min";
//                            signature = s;
//                        }
//                        break;
//                    }
//                    case 4: {
//                        pitch = str.substring(0, 1);
//                        mode = str.substring(1).toLowerCase();
//                        break;
//                    }
//                    default:
//                        pitch = str.substring(0, 1);
//                        signature = str.substring(1, 2);
//                        mode = str.substring(2).toLowerCase();
//                        break;
//                }
//                String name = "        ";
//                    name += pitch.toUpperCase();
//                    if (signature != null) {
//                        if (signature.equals("b")) {
//                            name += "_FLAT";
//                        } else {
//                            name += "_SHARP";
//                        }
//                    }
//                    name += "_" + mode.toUpperCase();
//                if (signature == null) {
//                    System.out.println(name + "(\"" + pitch + "\", null, \"" + mode + "\"),");
//                } else {
//                    System.out.println(name + "(\"" + pitch + "\", \"" + signature + "\", \"" + mode + "\"),");
//                }
//            }
//        }
//
////        String t = "a;b;c: b";
////        System.out.println(t.substring(t.indexOf(":") + 1).trim());
////        System.out.println(t.substring(0, t.indexOf(":")));
//        String fonts = "AvantGarde-Demi, AvantGarde-DemiOblique, AvantGarde-Book, AvantGarde-BookOblique, Bookman-Light, Bookman-LightItalic, Bookman-Demi, Bookman-DemiItalic, Courier, Courier-Oblique, Courier-Bold, Courier-BoldOblique, Helvetica, Helvetica-Oblique, Helvetica-Bold, Helvetica-BoldOblique, Helvetica-Narrow, Helvetica-NarrowOblique, Helvetica-NarrowBold, Helvetica-NarrowBoldOblique, NewCenturySchlbk-Roman, NewCenturySchlbk-Italic, NewCenturySchlbk-Bold, NewCenturySchlbk-BoldItalic, Palatino-Roman, Palatino-Italic, Palatino-Bold, Palatino-BoldItalic, Symbol, Times-Roman, Times-Italic, Times-Bold, Times-BoldItalic, ZapfChancery-MediumItalic, ZapfDingbats";
//        List<String> fontList = Arrays.asList(fonts.split(", "));
//        Collections.sort(fontList, (a,b) -> a.compareTo(b));
//        for (String font : fontList) {
////            System.out.println(font.replaceAll("[^A-Z]", ""));
//            System.out.println("            " + font.replaceAll("[^A-Z]", "") + "(\"" + font.trim() + "\"),");
//        }
    }
}
