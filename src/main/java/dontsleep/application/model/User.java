package dontsleep.application.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private EUserRole role;

    public User(String name) {
        this.id = 0;
        this.name = name;
        role = EUserRole.GUEST;
    }
    public User(int id, String username, String password, String name, EUserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public EUserRole getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }
}