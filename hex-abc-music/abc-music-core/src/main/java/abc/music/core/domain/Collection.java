package abc.music.core.domain;

import java.util.List;

/**
 * Created 2016-dec-12
 *
 * @author hl
 */
public interface Collection {

    String getName();

    List<String> getTitles();

    Boolean hasTitles();
    
    String getPrefaceHeader();

    String getPreface();

    List<Tune> getTunes();

    List<Person> getPersons();

    Boolean getPrintPersons();

    String getPersonsHeader();

    String getPersonsText();

    Boolean getPrintBooks();

    String getBooksHeader();

    String getBooksText();

    List<Book> getBooks();
}
