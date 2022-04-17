package checker.sites;

import checker.alerts.Alert;

import java.util.Optional;

public class Site {

    public static int DEFAULT_TIMEOUT = 2000;

    public Site(String uri) {
        this.uri = uri;
        this.enabledCheck = true;
        this.timeout = DEFAULT_TIMEOUT;

        this.status = new SiteStatus();
        this.alertOptional = Optional.empty();
    }

    public Site(SiteBuilder builder) {
        this.uri = builder.uri;
        this.enabledCheck = true; // true as default from builder
        this.timeout = builder.timeout;

        this.status = new SiteStatus();
        this.alertOptional = Optional.of(builder.alert);
    }

    private String uri;
    private boolean enabledCheck;
    private int timeout;
    private SiteStatus status;
    private Optional<Alert> alertOptional;

    public Optional<Alert> getAlertOptional() {
        return alertOptional;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isEnabledCheck() {
        return this.enabledCheck;
    }

    public void setEnabledCheck(boolean enabled) {
        this.enabledCheck = enabled;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public SiteStatus getStatus() {
        return this.status;
    }

    public void setStatus(SiteStatus httpStatus) {
        this.status = httpStatus;
    }

    @Override
    public String toString() {
        return this.uri + " " + this.status;
    }

    public boolean hasAlert() {
        return alertOptional.isPresent();
    }

    // site builder
    public static class SiteBuilder {

        private String uri;
        private boolean enabledCheck;
        private int timeout;
        private Alert alert;

        public SiteBuilder(String uri) {
            this.uri = uri;
        }

        public SiteBuilder enabledCheck(boolean enabled) {
            this.enabledCheck = enabled;
            return this;
        }

        public SiteBuilder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public SiteBuilder alert(Alert alert) {
            this.alert = alert;
            return this;
        }

        public Site build() {
            Site site = new Site(this);
            // set a default status
            site.setStatus(new SiteStatus());
            return site;
        }

    }

}
