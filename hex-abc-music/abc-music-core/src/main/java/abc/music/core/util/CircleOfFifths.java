package abc.music.core.util;

import abc.music.core.domain.Key;
import java.util.Arrays;
import java.util.List;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public class CircleOfFifths {

    private static final String[] circle = {"Fb", "Cb", "Gb", "Db", "Ab", "Eb", "Bb",
        "F", "C", "G", "D", "A", "E", "B",
        "F#", "C#", "G#", "D#", "A#", "E#", "B#"};
    private static final List<String> circleOfFifths = Arrays.asList(circle);

    public static Integer getSteps(String oldKey, String newKey) {
        Integer a = 0, b = 0;
        int i = 0;
        for (String k : circleOfFifths) {
            if (k.equals(oldKey)) {
                a = i;
            }
            if (k.equals(newKey)) {
                b = i;
            }
            i++;
        }
        return b - a;
    }

    public static Integer getSteps(Key.Pitch oldPitch, Key.Signature oldSignature, Key.Pitch newPitch, Key.Signature newSignature) {
        return getSteps(oldPitch.getName() + oldSignature.getSign(), newPitch.getName() + newSignature.getSign());
    }

    public static String getNew(String old, int steps) {
        Integer a = 0, b = 0;
        int i = 0;
        for (String k : circleOfFifths) {
            if (k.equals(old)) {
                a = i;
                break;
            }
            i++;
        }
        return circleOfFifths.get(a + steps);
    }

//    public static Key getNew(Key old, int steps) {
//        Key.Mode mode = old.getMode();
//        String keyString = getNew(old.getPitch(), old.getSignature(), steps);
//        Key result = new Key();
//        result.setMode(mode);
//        result.setPitch(Key.getPitchFromString(keyString));
//        result.setSignature(Key.getSignatureFromString(keyString));
//        return result;
//    }

    public static String getNew(Key.Pitch oldPitch, Key.Signature oldSignature, int steps) {
        return getNew(oldPitch.getName() + oldSignature.getSign(), steps);
    }

    public static void main(String[] args) {
        System.out.println(CircleOfFifths.getSteps("C", "B#"));
        System.out.println(CircleOfFifths.getSteps("C", "Fb"));
        System.out.println(CircleOfFifths.getSteps("Fb", "B#"));
        System.out.println(CircleOfFifths.getSteps("C", "Bb"));
        System.out.println(CircleOfFifths.getNew("D", -2));
        System.out.println(CircleOfFifths.getNew("D", 2));

    }
}
