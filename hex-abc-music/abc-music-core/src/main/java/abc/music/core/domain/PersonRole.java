package abc.music.core.domain;

import abc.music.core.exception.AbcException;
import java.util.Objects;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class PersonRole extends Field {

    private Person person;
    private Person.Role role;

    public PersonRole(Person.Role role) {
        super(role.getKey());
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person.Role getRole() {
        return role;
    }

    public void setRole(Person.Role role) {
        this.role = role;
    }

    @Override
    public String get() {
        if (role.equals(Person.Role.TRAD)) {
            return getCode() + ": efter " + getPerson().getName();
        }
        return getCode() + ": " + getPerson().getName();
    }

    @Override
    public String toString() {
        return getPerson().toString();
    }

    public static Person fromString(String text) {
        Person result = new Person();
        if (text == null || text.isEmpty()) {
            throw new AbcException("Input can not be empty or null");
        } else if (text.contains(" ")) {
            String[] split = text.split(" ");
            result.setFirstName(split[0].trim());
            result.setLastName(split[1].trim());
        } else if (text.contains(",")) {
            String[] split = text.split(",");
            result.setFirstName(split[1].trim());
            result.setLastName(split[0].trim());
        } else {
            result.setFirstName(text);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.person);
        hash = 31 * hash + Objects.hashCode(this.role);
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
        final PersonRole other = (PersonRole) obj;
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        if (this.role != other.role) {
            return false;
        }
        return true;
    }
}
