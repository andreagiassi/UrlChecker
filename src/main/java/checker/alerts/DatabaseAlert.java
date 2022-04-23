package checker.alerts;

import checker.sites.Site;

/** A void implementation for future database persistence */
public class DatabaseAlert extends Alert {

    public DatabaseAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "Database Alert";
    }

    @Override
    public boolean send(Site site) {

        //
        //  TODO: implement alert persistent data on jdbc database
        //

        return false;
    }

}
