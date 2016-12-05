package abc.music.editor.gui.support;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public class TransposeMap {

    private static final Map<String, Integer> map = new LinkedHashMap<>();

    static {
        map.put("B↓", -1);
        map.put("E↓", -8);
        map.put("G♭↓", -6);
        map.put("D♭↓", -11);
        map.put("A♭↓", -4);
        map.put("A↓", -3);
        map.put("D↓", -10);
        map.put("G↓", -5);
        map.put("E♭↓", -9);
        map.put("B♭↓", -2);
        map.put("F↓", -7);
        map.put("C↓", -12);
        map.put("C", 0);
        map.put("C↑", 12);
        map.put("F↑", 5);
        map.put("B♭↑", 10);
        map.put("E♭↑", 3);
        map.put("G↑", 7);
        map.put("D↑", 2);
        map.put("A↑", 9);
        map.put("A♭↑", 8);
        map.put("D♭↑", 1);
        map.put("G♭↑", 6);
        map.put("E↑", 4);
        map.put("B↑", 11);
    }

    public static List<Item> getItems() {
        List<Item> result = new ArrayList<>();
        map.keySet().stream().forEach((label) -> {
            result.add(new Item(label, map.get(label)));
        });
        return result;
    }

    public static Integer getInterval(String label) {
        return map.get(label);
    }

    public static Item getDefaultItem() {
        return new Item("C", 0);
    }

    public static Item getItem(Integer interval) {
        for (Item i : getItems()) {
            if (i.getInterval().equals(interval)) {
                return i;
            }
        }
        return getDefaultItem();
    }

    public static class Item {

        private final String label;
        private final Integer interval;

        public Item(String label, Integer interval) {
            this.label = label;
            this.interval = interval;
        }

        public String getLabel() {
            return label;
        }

        public Integer getInterval() {
            return interval;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 29 * hash + Objects.hashCode(this.label);
            hash = 29 * hash + Objects.hashCode(this.interval);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Item other = (Item) obj;
            if (!Objects.equals(this.label, other.label)) {
                return false;
            }
            if (!Objects.equals(this.interval, other.interval)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return getLabel();
        }
    }
}
