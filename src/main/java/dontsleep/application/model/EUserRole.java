package dontsleep.application.model;

public enum EUserRole {
    GUEST(0),
    USER(1),
    STAFF(2),
    ADMIN(3);

    private int id;

    private EUserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EUserRole getRoleById(int id) {
        for (EUserRole role : EUserRole.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }
}
