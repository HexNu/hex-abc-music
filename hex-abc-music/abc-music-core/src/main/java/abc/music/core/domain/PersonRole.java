package abc.music.core.domain;

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
}
