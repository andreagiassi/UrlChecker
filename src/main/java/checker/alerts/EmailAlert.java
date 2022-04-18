package checker.alerts;

import checker.sites.Site;

public class EmailAlert extends Alert {

    public EmailAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "Email Alert";
    }

    @Override
    public boolean sendAlert(Site site) {

        //
        //  TODO: implement smtp email alert
        //

        return false;
    }

}
