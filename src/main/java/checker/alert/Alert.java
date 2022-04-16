package checker.alert;

public abstract class Alert {

    public Alert() {
        // empty impl
    }

    public Alert(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return this.message;
    }

    public abstract boolean sendAlert();

    public abstract String getName();

    @Override
    public String toString() {
        return "Alert " + this.message;
    }

}
