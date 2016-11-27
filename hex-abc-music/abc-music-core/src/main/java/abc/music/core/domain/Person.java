package abc.music.core.domain;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Person {

    private String firstName;
    private String lastName;
    private String email;

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
        return getFirstName() + " " + getLastName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    }
}
