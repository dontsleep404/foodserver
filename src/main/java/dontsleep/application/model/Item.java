package dontsleep.application.model;

import dontsleep.application.model.Anotation.FieldAnotation;
import dontsleep.application.model.Anotation.TableAnotation;

@TableAnotation(tableName = "items")
public class Item extends Model{

    @FieldAnotation(fieldName = "id", isAutoIncrement = true)
    private int id;

    @FieldAnotation(fieldName = "name")
    private String name;

    @FieldAnotation(fieldName = "description")
    private String description;

    @FieldAnotation(fieldName = "type")
    private int type;

    @FieldAnotation(fieldName = "price")
    private int price;

    @FieldAnotation(fieldName = "isAvailable")
    private boolean isAvailable;

    @FieldAnotation(fieldName = "image")
    private String image;

    public Item() {}

    public Item(String name, String description, ItemType type, int price, boolean isAvailable, String image) {
        this.name = name;
        this.description = description;
        this.type = type.getId();
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
    public int getType() {
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
