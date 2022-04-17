package checker.alerts;

import checker.conditions.AlertCondition;

public abstract class Alert {

    public Alert() {
        // empty impl
    }

    public Alert(String message) {
        this.message = message;
        this.alertCondition = null;
    }

    private String message;

    public String getMessage() {
        return this.message;
    }

    public boolean hasCondition() {
        return alertCondition != null;
    }

    private AlertCondition alertCondition;

    public AlertCondition getAlertCondition() {
        return this.alertCondition;
    }

    public void setAlertCondition(AlertCondition alertCondition) {
        this.alertCondition = alertCondition;
    }

    public abstract boolean sendAlert();

    public abstract String getName();

    @Override
    public String toString() {
        return "Alert " + this.message;
    }

}
