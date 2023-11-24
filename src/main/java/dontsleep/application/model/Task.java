package dontsleep.application.model;

public class Task {
    private int id;
    private int billId;
    private int itemId;
    private int quantity;
    private long timestamp;
    private String note;
    private ETaskStatus status;
    public Task(int id, int billId, int itemId, int quantity, long timestamp, String note, ETaskStatus status) {
        this.id = id;
        this.billId = billId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.note = note;
        this.status = status;
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
        return status;
    }
    public void setStatus(ETaskStatus status) {
        this.status = status;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
