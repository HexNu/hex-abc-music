package abc.music.core.domain;

import java.util.Objects;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Person {

    private Integer id;
    private Project project;
    private String firstName;
    private String lastName;
    private String email;

    public Person() {
        project = null;
        id = 1;
    }

    public Person(Project project) {
        this.project = project;
        id = project.getNextPersonId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        String result = "";
        if (getFirstName() != null) {
            result += getFirstName();
            if (getLastName() != null) {
                result += " ";
            }
        }
        if (getLastName() != null) {
            result += getLastName();
        }
        return result.isEmpty() ? null : result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.firstName);
        hash = 17 * hash + Objects.hashCode(this.lastName);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

    public enum Role {

        COMPOSER('C'),
        AUTHOR('A'),
        TRAD('C'),
        TRANSCRIBER('Z');
        public static Role DEFAULT_ROLE = COMPOSER;
        private final char key;

        private Role(char key) {
            this.key = key;
        }

        public char getKey() {
            return key;
        }

        public static Role find(String text) {
            if (text != null && !text.isEmpty()) {
                for (Role role : values()) {
                    if (text.toUpperCase().equals(role.name()) || text.toUpperCase().equals(role.getKey())) {
                        return role;
                    }
                }
            }
            return DEFAULT_ROLE;
        }

        public String getName() {
            return name().substring(0, 1) + name().replaceAll("_", " ").toLowerCase().substring(1);
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
