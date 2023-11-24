package dontsleep.application.model;

public enum EBillStatus {
    PENDING("pending"),
    PAID("paid"),
    CANCELLED("cancelled");

    private String status;

    private EBillStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public static EBillStatus fromString(String text) {
        for (EBillStatus b : EBillStatus.values()) {
            if (b.status.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
