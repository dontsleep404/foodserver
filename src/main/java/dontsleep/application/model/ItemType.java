package dontsleep.application.model;

import dontsleep.application.model.Anotation.FieldAnotation;
import dontsleep.application.model.Anotation.TableAnotation;

@TableAnotation(tableName = "itemtype")
public class ItemType extends Model{

    @FieldAnotation(fieldName = "id", isAutoIncrement = true)
    private int id;

    @FieldAnotation(fieldName = "name")
    private String name;

    @FieldAnotation(fieldName = "description")
    private String description;

    public ItemType() {}

    public ItemType(String name, String description) {
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
