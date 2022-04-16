package checker.alert;

public class AlertCondition {

    public AlertCondition() {
        // empty impl
    }

    private int statusCode;

    public void setStatusCode(int httpStatus) {
        this.statusCode = httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
