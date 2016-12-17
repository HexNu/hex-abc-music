package abc.music.core.domain;

import java.util.List;

/**
 * Created 2016-dec-12
 *
 * @author hl
 */
public interface Collection {

    String getName();

    Project getProject();

    List<String> getTitles();

    Boolean hasTitles();

    String getPrefaceHeader();

    String getPreface();
    
    Boolean hasPreface();

    List<Tune> getTunes();

    List<Person> getPersons();

    Boolean getPrintPersons();

    String getPersonsHeader();

    String getPersonsText();

    Boolean getPrintBooks();

    String getBooksHeader();

    String getBooksText();

    List<Book> getBooks();

    String getPreferredTemplate();

    void setPreferredTemplate(String name);

    String getCopyright();
}
