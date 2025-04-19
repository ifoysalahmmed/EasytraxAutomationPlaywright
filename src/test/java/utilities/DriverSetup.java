package utilities;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverSetup {

    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    Playwright playwright;
    BrowserType browserType;

    @BeforeMethod
    public void startBrowser () {
        playwright = Playwright.create ();
        browserType = playwright.chromium ();
        browser = playwright.chromium ().launch (new BrowserType.LaunchOptions ().setHeadless (false));
        browserContext = browser.newContext (new Browser.NewContextOptions ());
        page = browser.newPage ();
    }

    public void openPage (String baseURL) {
        page.navigate (baseURL);
    }

    @AfterMethod
    public void closeChromeBrowser () {
        page.close ();
        browser.close ();
        playwright.close ();
    }
}