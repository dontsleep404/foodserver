package dontsleep.application.model;

public enum ETaskStatus {
    PENDING("pending"),
    COMPLETED("completed"),
    CANCELLED("cancelled");

    private String status;

    private ETaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public static ETaskStatus fromString(String text) {
        for (ETaskStatus b : ETaskStatus.values()) {
            if (b.status.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
