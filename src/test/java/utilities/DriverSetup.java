package utilities;

import com.microsoft.playwright.*;
import org.testng.annotations.BeforeMethod;

public class DriverSetup {

    public static final String BROWSER_NAME = System.getProperty ("browser", "chromium");

    private static final ThreadLocal<Playwright> PLAYWRIGHT_THREAD_LOCAL = new ThreadLocal<> ();
    private static final ThreadLocal<Browser> BROWSER_THREAD_LOCAL = new ThreadLocal<> ();
    private static final ThreadLocal<Page> PAGE_THREAD_LOCAL = new ThreadLocal<> ();

    public static Playwright getPlaywrightThreadLocal () {
        return PLAYWRIGHT_THREAD_LOCAL.get ();
    }

    public static Browser getBrowserThreadLocal () {
        return BROWSER_THREAD_LOCAL.get ();
    }

    public static Page setPageThreadLocal () {
        return PAGE_THREAD_LOCAL.get ();
    }

    @BeforeMethod
    public void setupBrowserName () {
        Playwright playwright = Playwright.create ();
        PLAYWRIGHT_THREAD_LOCAL.set (playwright);

        Browser browser;

        switch (BROWSER_NAME.toLowerCase ()) {
            case "chromium":
                browser = playwright.chromium ().launch (new BrowserType.LaunchOptions ().setHeadless (false));
                break;
            case "firefox":
                browser = playwright.firefox ().launch (new BrowserType.LaunchOptions ().setHeadless (false));
                break;
            case "webkit":
                browser = playwright.webkit ().launch (new BrowserType.LaunchOptions ().setHeadless (false));
                break;
            default:
                throw new IllegalArgumentException ("Unsupported browser: " + BROWSER_NAME);
        }

        BROWSER_THREAD_LOCAL.set (browser);

        Page page = browser.newPage ();
        PAGE_THREAD_LOCAL.set (page);
    }

}