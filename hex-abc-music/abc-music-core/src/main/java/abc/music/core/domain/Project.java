package abc.music.core.domain;

import java.time.LocalDateTime;
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
    private final List<Person> persons = new ArrayList<>();
    private final String name;
    private LocalDateTime lastUpdated;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
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
        tune.setProject(this);
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons.clear();
        persons.stream().forEach(this::addPerson);
    }

    public Person getPerson(String firstName, String lastName) {
        Person tempPers = new Person();
        tempPers.setFirstName(firstName);
        tempPers.setLastName(lastName);
        for (Person person : persons) {
            if (person.equals(tempPers)) {
                return person;
            }
        }
        return null;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

}
