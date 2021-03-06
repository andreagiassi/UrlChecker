import checker.UrlCheckerService;
import checker.alerts.ConsoleAlert;
import checker.alerts.EmailAlert;
import checker.alerts.FileAlert;
import checker.conditions.AlertCondition;
import checker.conditions.EqualsCondition;
import checker.conditions.InRangeCondition;
import checker.sites.Site;

public class UrlCheckerMain {

    public static void main(String [] args) {
        UrlCheckerService urlCheckerService = new UrlCheckerService();

        // simple check on http status 200:
        urlCheckerService.addSite(new Site("https://www.airbnb.com"));


        // check with an alert
        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/99")
                                    .timeout(1000)
                                    .alert(new ConsoleAlert("Error during contacting google site"))
                                    .build());


        // check a site with an alert condition, detecting only 404
        ConsoleAlert alert = new ConsoleAlert("404 on google site");

        AlertCondition alertCondition = new EqualsCondition(404);
        alert.setOptionalAlertCondition(alertCondition);

        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/999")
                .timeout(1000)
                .alert(alert)
                .build());

        // check a site with an in range condition
        ConsoleAlert alert2 = new ConsoleAlert("In range 400-451 on google site");

        AlertCondition alertCondition2 = new InRangeCondition(400, 451);
        alert.setOptionalAlertCondition(alertCondition2);

        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/9999")
                .timeout(1000)
                .alert(alert2)
                .build());

        // use an email condition (TODO)
        EmailAlert emailAlert = new EmailAlert("In range 400-451 on google site");
        emailAlert.setOptionalAlertCondition(new EqualsCondition(404));

        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/99999")
                .timeout(1000)
                .alert(emailAlert)
                .build());

        // use a file alert
        FileAlert fileAlert = new FileAlert("site.log", "Check 404 on google.com");
        fileAlert.setOptionalAlertCondition(new EqualsCondition(404));

        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/99999")
                .timeout(1000)
                .alert(fileAlert)
                .build());

        // check the list of sites
        urlCheckerService.checkSites();

        // dump last site's status
        urlCheckerService.dumpSites();

        // check a single website with one line command
        System.out.println(urlCheckerService.check("https://www.yahoo.com").toString());
    }

}
