package Data.UserData;

public class Credentials {
    private String email;
    private String password;
    private String name;

    public Credentials() {

    }

    public Credentials(String login, String password) {
        this.email = "incorrect";
        this.password = password;
    }

    public Credentials(String password) {
        this.password = password;
    }

    public static Credentials from(User user) {
        Credentials c = new Credentials();
        c.setLogin(user.getEmail());
        c.setPassword(user.getPassword());
        c.setName(user.getName());
        return c;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
