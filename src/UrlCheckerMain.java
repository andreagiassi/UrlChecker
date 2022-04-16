import checker.Site;
import checker.UrlCheckerService;
import checker.alert.ConsoleAlert;

public class UrlCheckerMain {

    public static void main(String [] args) {
        UrlCheckerService urlCheckerService = new UrlCheckerService();
        urlCheckerService.addSite(new Site("https://www.airbnb.com"));

        // test a site with an alert
        urlCheckerService.addSite(new Site.SiteBuilder("https://www.google.com/999")
                                    .timeout(1000)
                                    .alert(new ConsoleAlert("Error during contacting google site"))
                                    .build());

        urlCheckerService.checkSites();

        // dump last site's status
        urlCheckerService.dump();
    }

}
