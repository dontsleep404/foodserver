package dontsleep.application.model;

public class Bill {
    private int id;
    private int userId;
    private EBillStatus status;
    private long timestamp;
    public Bill(int id, int userId, EBillStatus status, long timestamp) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.timestamp = timestamp;
    }
    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public EBillStatus getStatus() {
        return status;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setStatus(EBillStatus status) {
        this.status = status;
    }
}
