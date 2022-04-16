package checker.alert;

public class ConsoleAlert extends Alert {

    public ConsoleAlert(String message) {
        super(message);
    }

    @Override
    public boolean sendAlert() {
        System.out.println("Console alert: " + this.getMessage());
        return true;
    }

    @Override
    public String getName() {
        return "Console alert";
    }

}
