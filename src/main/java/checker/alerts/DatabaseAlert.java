package checker.alerts;

public class DatabaseAlert extends Alert {

    public DatabaseAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "Database Alert";
    }

    @Override
    public boolean sendAlert() {

        //
        //  TODO: implement alert persistent data on jdbc database
        //

        return false;
    }

}
