package abc.music.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class Book {

    private String name;
    private String shortDescription;
    private String introduction;
    private Boolean printPersons;
    private String personsHeader;
    private String personsText;
    private List<Tune> tunes = new ArrayList<>();

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Boolean getPrintPersons() {
        return printPersons == null ? false : printPersons;
    }

    public void setPrintPersons(Boolean printPersons) {
        this.printPersons = printPersons;
    }

    public List<Person> getPersons() {
        List<Person> result = new ArrayList<>();
        getTunes().stream().forEach((tune) -> {
            tune.getCreators().stream().filter((pr) -> (!result.contains(pr.getPerson()))).forEach((pr) -> {
                result.add(pr.getPerson());
            });
        });
        Collections.sort(result, (a, b) -> a.getFormalName().compareTo(b.getFormalName()));
        return result;
    }

    public String getPersonsHeader() {
        return personsHeader;
    }

    public void setPersonsHeader(String personsHeader) {
        this.personsHeader = personsHeader;
    }

    public String getPersonsText() {
        return personsText;
    }

    public void setPersonsText(String personsText) {
        this.personsText = personsText;
    }

    public List<Tune> getTunes() {
        return tunes;
    }

    public void setTunes(List<Tune> tunes) {
        this.tunes.clear();
        this.tunes.addAll(tunes);
    }

    public void clearTunes() {
        this.tunes.clear();
    }

    public void addTune(Tune tune) {
        if (!hasTune(tune)) {
            tunes.add(tune);
        }
    }

    public boolean hasTune(Tune tune) {
        return tunes.contains(tune);
    }

    public void removeTune(Tune tune) {
        for (Iterator<Tune> it = tunes.iterator(); it.hasNext();) {
            Tune t = it.next();
            if (t.equals(tune)) {
                it.remove();
            }
        }
    }
}
