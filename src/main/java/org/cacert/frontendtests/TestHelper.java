/*
 * LICENSE
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich(at)weltraumschaf(dot)de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 */
package org.cacert.frontendtests;

//import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Sven Strittmatter <ich@weltraumschaf.de>
 */
public class TestHelper {

    /**
     * Supported driver types.
     */
    public enum DriverType {
        FIREFOX,
        CHROME,
        INTERNET_EXPLORER,
        HTMLUNIT
    }

    /**
     * Determines desired WebDriver type and returns accordingly.
     *
     * @param type
     * @return
     * @throws Exception
     */
    public static WebDriver createDriverByType(DriverType type) throws Exception {
        WebDriver driver;
        switch (type) {
            case FIREFOX:
                driver = new FirefoxDriver();
                return driver;
            case CHROME:
                driver = new ChromeDriver();
                return driver;
            case INTERNET_EXPLORER:
                driver = new InternetExplorerDriver();
                return driver;
            case HTMLUNIT:
                driver = new HtmlUnitDriver();
                return driver;
            default:
                throw new Exception("Invalid driver type passed " + type + "!");
        }
    }
}
