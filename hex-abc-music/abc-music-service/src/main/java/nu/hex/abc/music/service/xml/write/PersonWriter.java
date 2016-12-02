package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Person;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class PersonWriter extends XmlWriter<Person> {

    public PersonWriter(Person person) {
        super(person);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("first-name", entity.getFirstName());
        result.addAttribute("last-name", entity.getLastName());
        result.addAttribute("email", entity.getEmail());
        return result;
    }
}
