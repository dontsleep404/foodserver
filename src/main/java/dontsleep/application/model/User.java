package dontsleep.application.model;

import dontsleep.application.model.Anotation.FieldAnotation;
import dontsleep.application.model.Anotation.TableAnotation;

@TableAnotation(tableName = "users")
public class User extends Model{

    @FieldAnotation(fieldName = "id", isAutoIncrement = true)
    private int id;

    @FieldAnotation(fieldName = "username")
    private String username;

    @FieldAnotation(fieldName = "password")
    private String password;

    @FieldAnotation(fieldName = "name")
    private String name;

    @FieldAnotation(fieldName = "role")
    private int role;

    public String tableId;
    public Bill bill;

    public User() {}

    public User(String name) {
        this.id = 0;
        this.name = name;
        role = EUserRole.GUEST.getId();
    }

    public User(String username, String password, String name, EUserRole role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role.getId();
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
        return EUserRole.getRoleById(this.role);
    }

    public void setName(String name) {
        this.name = name;
    }
}