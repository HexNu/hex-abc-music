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
public class Project implements Collection {

    private final Map<Integer, Tune> tunes = new HashMap<>();
    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<String, FormatTemplate> templates = new HashMap<>();
    private String preferredTemplate;
    private List<Book> books = new ArrayList<>();
    private String name;
    private List<String> titles = new ArrayList<>();
    private String summary;
    private String prefaceHeader;
    private String preface;
    private Boolean printPersons;
    private String personsHeader;
    private String personsText;
    private Boolean printBooks;
    private String booksHeader;
    private String booksText;
    private String abcVersion;
    private Owner owner = new Owner();
    private LocalDateTime lastUpdated;

    public Project(String name) {
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
        titles.add(title);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
        return printBooks == null ? false : printBooks;
    }

    public void setPrintBooks(Boolean printBooks) {
        this.printBooks = printBooks;
    }

    @Override
    public String getBooksHeader() {
        return booksHeader;
    }

    public void setBooksHeader(String booksHeader) {
        this.booksHeader = booksHeader;
    }

    @Override
    public String getBooksText() {
        return booksText;
    }

    public void setBooksText(String booksText) {
        this.booksText = booksText;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
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

    public Integer getNextTuneId() {
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

    @Override
    public List<Person> getPersons() {
        return new ArrayList<>(persons.values());
    }

    public void setPersons(List<Person> persons) {
        this.persons.clear();
        persons.stream().forEach(this::addPerson);
    }

    public Person getPerson(Integer id) {
        return persons.get(id);
    }

    public Person getPerson(String firstName, String lastName) {
        Person tempPers = new Person();
        tempPers.setFirstName(firstName);
        tempPers.setLastName(lastName);
        for (Person person : persons.values()) {
            if (person.equals(tempPers)) {
                return person;
            }
        }
        return null;
    }

    public void addPerson(Person person) {
        this.persons.put(person.getId(), person);
    }

    public Integer getNextPersonId() {
        List<Integer> ids = new ArrayList<>();
        persons.values().stream().forEach((person) -> {
            ids.add(person.getId());
        });
        if (ids.isEmpty()) {
            return 1;
        }
        Collections.sort(ids, (a, b) -> a.compareTo(b));
        return ids.get(ids.size() - 1) + 1;
    }

    @Override
    public String getPreferredTemplate() {
        return preferredTemplate;
    }

    @Override
    public void setPreferredTemplate(String preferredTemplate) {
        this.preferredTemplate = preferredTemplate;
    }

    public Map<String, FormatTemplate> getFormatTemplates() {
        return templates;
    }

    public FormatTemplate getTemplate(String name) {
        return templates.get(name);
    }

    public void clearFormatTemplates() {
        templates.clear();
    }

    public void setFormatTemplates(Map<String, FormatTemplate> templates) {
        clearFormatTemplates();
        this.templates.putAll(templates);
    }

    public void addFormatTemplate(FormatTemplate template) {
        templates.put(template.getName(), template);
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books.clear();
        this.books.addAll(books);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public String getAbcVersion() {
        return abcVersion;
    }

    public void setAbcVersion(String abcVersion) {
        this.abcVersion = abcVersion;
    }

    public static class Owner extends Person {

        public Owner() {
            super();
        }

        public Owner(Project project) {
            super(project);
        }
    }
}
