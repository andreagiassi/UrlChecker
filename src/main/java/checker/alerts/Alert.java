package checker.alerts;

import checker.conditions.AlertCondition;
import checker.sites.Site;

public abstract class Alert {

    public Alert(String message) {
        this.message = message;
        // default no condition
        this.alertCondition = null;
    }

    private String message;
    private AlertCondition alertCondition;

    public String getMessage() {
        return this.message;
    }

    public boolean hasCondition() {
        return alertCondition != null;
    }

    public AlertCondition getAlertCondition() {
        return this.alertCondition;
    }

    public void setAlertCondition(AlertCondition alertCondition) {
        this.alertCondition = alertCondition;
    }

    public abstract boolean send(Site site);

    public abstract String getName();

    @Override
    public String toString() {
        return "Alert " + this.message;
    }

}
