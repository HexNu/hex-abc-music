package abc.music.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 2016-dec-18
 *
 * @author hl
 */
public class KeyList {

    private static final List<String> modi = new ArrayList<>();
    private final List<String> selectedModi = new ArrayList<>();

    static {
        modi.add("Major");
        modi.add("Minor");
        modi.add("Dorian");
        modi.add("Frygian");
        modi.add("Lydian");
        modi.add("Mixolydian");
        modi.add("Locrian");
    }

    public KeyList(String... modi) {
        for (String modus : modi) {
            selectedModi.add(modus.substring(0, 3).toLowerCase());
        }
    }

    public static List<String> getModi() {
        return modi;
    }

    public List<Keys> getSelectedModi() {
        List<Keys> result = new ArrayList<>();
        for (Keys k : Keys.values()) {
            if (selectedModi.contains(k.getMode())) {
                result.add(k);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new KeyList("Major", "Minor", "Dorian", "Mixolydian").getSelectedModi().stream().forEach(System.out::println);
    }

}
