package dontsleep.application.model;

public class Item {
    private int id;
    private String name;
    private String description;
    private ItemType type;
    private int price;
    private boolean isAvailable;
    private String image;
    public Item(int id, String name, String description, ItemType type, int price, boolean isAvailable, String image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
        this.image = image;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }
    public ItemType getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }
    public boolean getIsAvailable() {
        return isAvailable;
    }
    public String getImage() {
        return image;
    }
}
