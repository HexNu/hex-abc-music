package abc.music.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class Project {

    private final Map<Integer, Tune> tunes = new HashMap<>();
    private final String name;

    public Project(String name) {
        this.name = name;
    }

    public List<Tune> getTunes() {
        return new ArrayList<>(tunes.values());
    }

    public void setTunes(List<Tune> tunes) {
        this.tunes.clear();
        tunes.stream().forEach(this::addTune);
    }

    public boolean hasTune(Integer id) {
        return getTune(id) != null;
    }

    public Tune getTune(Integer id) {
        return tunes.get(id);
    }

    public void addTune(Tune tune) {
        this.tunes.put(tune.getId(), tune);
    }

    public Integer getNextId() {
        List<Integer> ids = new ArrayList<>();
        tunes.values().stream().forEach((tune) -> {
            ids.add(tune.getId());
        });
        if (ids.isEmpty()) {
            return 1;
        }
        Collections.sort(ids, (a, b) -> a.compareTo(b));
        return ids.get(ids.size() - 1) + 1;
    }
}
