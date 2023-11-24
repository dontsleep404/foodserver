package dontsleep.application.model;

public class ItemType {
    private int id;
    private String name;
    private String description;
    public ItemType(int id, String name, String description) {
        this.name = name;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
