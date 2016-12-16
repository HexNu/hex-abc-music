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
public class Book implements Collection {

    private String name;
    private List<String> titles = new ArrayList<>();
    private String shortDescription;
    private String preferredTemplate;
    private String prefaceHeader;
    private String preface;
    private Boolean printPersons;
    private String personsHeader;
    private String personsText;
    private final List<Tune> tunes = new ArrayList<>();

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<String> getTitles() {
        return titles;
    }

    @Override
    public Boolean hasTitles() {
        return titles != null && !titles.isEmpty();
    }

    public void setTitles(List<String> titles) {
        this.titles.clear();
        this.titles.addAll(titles);
    }

    public void addTitle(String title) {
        this.titles.add(title);
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String getPreferredTemplate() {
        return preferredTemplate;
    }

    @Override
    public void setPreferredTemplate(String preferredTemplate) {
        this.preferredTemplate = preferredTemplate;
    }

    @Override
    public String getPrefaceHeader() {
        return prefaceHeader;
    }

    public void setPrefaceHeader(String header) {
        this.prefaceHeader = header;
    }

    @Override
    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    @Override
    public Boolean getPrintPersons() {
        return printPersons == null ? false : printPersons;
    }

    public void setPrintPersons(Boolean printPersons) {
        this.printPersons = printPersons;
    }

    @Override
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

    @Override
    public String getPersonsHeader() {
        return personsHeader;
    }

    public void setPersonsHeader(String personsHeader) {
        this.personsHeader = personsHeader;
    }

    @Override
    public String getPersonsText() {
        return personsText;
    }

    public void setPersonsText(String personsText) {
        this.personsText = personsText;
    }

    @Override
    public Boolean getPrintBooks() {
        return false;
    }

    @Override
    public String getBooksHeader() {
        return null;
    }

    @Override
    public String getBooksText() {
        return null;
    }

    @Override
    public List<Book> getBooks() {
        return Collections.EMPTY_LIST;
    }

    @Override
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
