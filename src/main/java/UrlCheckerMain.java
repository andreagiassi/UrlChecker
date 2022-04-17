import checker.Site;
import checker.UrlCheckerService;
import checker.conditions.AlertCondition;
import checker.alert.ConsoleAlert;
import checker.conditions.EqualsCondition;
import checker.conditions.InRangeCondition;

public class UrlCheckerMain {

    public static void main(String [] args) {
        UrlCheckerService urlCheckerService = new UrlCheckerService();

        // simple check on http status 200:
        urlCheckerService.addSite(new Site("https://www.airbnb.com"));


        // check with an alert
        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/999")
                                    .timeout(1000)
                                    .alert(new ConsoleAlert("Error during contacting google site"))
                                    .build());


        // check a site with an alert condition, detecting only 404
        ConsoleAlert alert = new ConsoleAlert("404 on google site");

        AlertCondition alertCondition = new EqualsCondition(404);
        alert.setAlertCondition(alertCondition);

        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/999")
                .timeout(1000)
                .alert(alert)
                .build());

        // check a site with an in range condition
        ConsoleAlert alert2 = new ConsoleAlert("404 on google site");

        AlertCondition alertCondition2 = new InRangeCondition(400, 451);
        alert.setAlertCondition(alertCondition2);

        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/999")
                .timeout(1000)
                .alert(alert2)
                .build());

        // check the sites
        urlCheckerService.checkSites();

        // dump last site's status
        urlCheckerService.dumpSites();
    }

}
