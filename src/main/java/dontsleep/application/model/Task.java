package dontsleep.application.model;

import dontsleep.application.model.Anotation.FieldAnotation;
import dontsleep.application.model.Anotation.TableAnotation;

@TableAnotation(tableName = "task")
public class Task extends Model{

    @FieldAnotation(fieldName = "id", isAutoIncrement = true)
    private int id;

    @FieldAnotation(fieldName = "billid")
    private int billId;

    @FieldAnotation(fieldName = "itemid")
    private int itemId;

    @FieldAnotation(fieldName = "quantity")
    private int quantity;

    @FieldAnotation(fieldName = "timestamp")
    private long timestamp;

    @FieldAnotation(fieldName = "note")
    private String note;

    @FieldAnotation(fieldName = "status")
    private String status;

    public Task() {}

    public Task(int billId, int itemId, int quantity, long timestamp, String note, ETaskStatus status) {
        this.billId = billId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.note = note;
        this.status = status.getStatus();
    }
    public int getId() {
        return id;
    }
    public int getBillId() {
        return billId;
    }
    public int getItemId() {
        return itemId;
    }
    public int getQuantity() {
        return quantity;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public String getNote() {
        return note;
    }
    public ETaskStatus getStatus() {
        return ETaskStatus.fromString(this.status);
    }
    public void setStatus(ETaskStatus status) {
        this.status = status.getStatus();
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
