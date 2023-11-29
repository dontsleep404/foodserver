package dontsleep.application.model;

import dontsleep.application.model.Anotation.FieldAnotation;
import dontsleep.application.model.Anotation.TableAnotation;

@TableAnotation(tableName = "bills")
public class Bill extends Model{

    @FieldAnotation(fieldName = "id", isAutoIncrement = true)
    private int id;

    @FieldAnotation(fieldName = "userid")
    private int userId;

    @FieldAnotation(fieldName = "status")
    private String status;

    @FieldAnotation(fieldName = "timestamp")
    private int timestamp;

    public Bill() {}

    public Bill(int userId, EBillStatus status, int timestamp) {
        this.userId = userId;
        this.status = status.getStatus();
        this.timestamp = timestamp;
    }
    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public EBillStatus getStatus() {
        return EBillStatus.fromString(this.status);
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setStatus(EBillStatus status) {
        this.status = status.getStatus();
    }
}
