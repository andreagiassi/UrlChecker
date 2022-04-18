package checker.alerts;

import checker.sites.Site;

public class ConsoleAlert extends Alert {

    public ConsoleAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "Console alert";
    }

    @Override
    public boolean sendAlert(Site site) {
        // standard console log alert
        System.out.println("Console alert: " + this.getMessage());
        return true;
    }

}
