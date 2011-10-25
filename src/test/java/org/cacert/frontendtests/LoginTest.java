/*
 * LICENSE
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich(at)weltraumschaf(dot)de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 */
package org.cacert.frontendtests;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Sven Strittmatter <ich@weltraumschaf.de>
 */
public class LoginTest {

    private static final String TEST_SYSTEM_URI = "https://cacert1.it-sls.de/";
    private static final String MANAGEMENT_SYSTEM_URI = "https://ca-mgr1.it-sls.de/";
    private static final String MAIL_DOMAIN = "cacert1.it-sls.de";

    private WebDriver driver;
    private RandomString stringGenerator;

    public LoginTest() {
        stringGenerator = new RandomString(10);
    }

    @Before public void setUp() {
        try {
            driver = TestHelper.createDriverByType(TestHelper.DriverType.HTMLUNIT);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @After public void tearDown() {
        driver.close();
        driver = null;
    }

    private String generateFirstName() {
        return "First" + stringGenerator.next();
    }

    @Test public void checkFirstNamePrefix() {
        assertEquals("First", generateFirstName().substring(0, 5));
        assertEquals("First", generateFirstName().substring(0, 5));
        assertEquals("First", generateFirstName().substring(0, 5));
    }

    private String generateLastName() {
        return "Last" + stringGenerator.next();
    }

    @Test public void checkLastNamePrefix() {
        assertEquals("Last", generateLastName().substring(0, 4));
        assertEquals("Last", generateLastName().substring(0, 4));
        assertEquals("Last", generateLastName().substring(0, 4));
    }

    private String generatePassword() {
        return "PW-" + stringGenerator.next();
    }

    @Test public void checkPasswordPrefix() {
        assertEquals("PW-", generatePassword().substring(0, 3));
        assertEquals("PW-", generatePassword().substring(0, 3));
        assertEquals("PW-", generatePassword().substring(0, 3));
    }

    private String generateEmailAddres(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder(firstName.toLowerCase());
        sb.append(".")
          .append(lastName.toLowerCase())
          .append("@")
          .append(MAIL_DOMAIN);
        return sb.toString();
    }

    @Test public void checkGeneratedEmailAddreses() {
        assertEquals("hans.dampf@cacert1.it-sls.de", generateEmailAddres("hans", "dampf"));
        assertEquals("hans.dampf@cacert1.it-sls.de", generateEmailAddres("Hans", "dampf"));
        assertEquals("hans.dampf@cacert1.it-sls.de", generateEmailAddres("Hans", "Dampf"));
    }

    private String extractActivationUri(String mailText) {
        String lines[] = mailText.split("[\\r\\n]+");

        for (String line : lines) {
            if (line.startsWith("http://")) {
                return line;
            }
        }
        
        return null;
    }
    
    @Test public void testExtractingUriFromMailText() {
        StringBuilder fixture = new StringBuilder("Read Mail\n");
        fixture.append("Thanks for signing up with CAcert.org, below is the link you need to open ");
        fixture.append("to verify your account. Once your account is verified you will be able to ");
        fixture.append("start issuing certificates till your hearts' content!\n\n");
        fixture.append("http://cacert1.it-sls.de/verify.php?type=email&emailid=240888&hash=57659595e2ade49d072146b0210398cc\n\n");
        fixture.append("Best regards\n");
        fixture.append("CAcert.org Support!\n");
        
        assertEquals(
            "http://cacert1.it-sls.de/verify.php?type=email&emailid=240888&hash=57659595e2ade49d072146b0210398cc", 
            extractActivationUri(fixture.toString())
        );
        assertNull(extractActivationUri("foo bar baz"));
    }
    
    @Ignore
    @Test public void registerActivateAndLoginNewUser() {
        /*
         * 1. register user at TEST_SYSTEM_URI
         */
        String firstName    = generateFirstName();
        String lastName     = generateLastName();
        String emailAddress = generateEmailAddres(firstName, lastName);
        String password     = generatePassword();
        int day             = 23;
        int month           = 5;
        String year         = "1979";

        driver.get(TEST_SYSTEM_URI + "index.php?id=1");
        driver.findElement(By.name("fname"))
              .sendKeys(firstName);
        driver.findElement(By.name("lname"))
              .sendKeys(lastName);

        List<WebElement> days = driver.findElement(By.name("day"))
                                      .findElements(By.tagName("option"));
        days.get(day - 1).click();
        List<WebElement> months = driver.findElement(By.name("month"))
                                        .findElements(By.tagName("option"));
        months.get(month - 1).click();
        WebElement yearInput = driver.findElement(By.name("year"));
        assertEquals("Year field not correctly prefiled!", "19XX", yearInput.getAttribute("value"));
        yearInput.clear();
        yearInput.sendKeys(year);

        driver.findElement(By.name("email"))
              .sendKeys(emailAddress);

        WebElement passwordInput;
        passwordInput = driver.findElement(By.name("pword1"));
        passwordInput.sendKeys(password);
        passwordInput = driver.findElement(By.name("pword2"));
        passwordInput.sendKeys(password);

        driver.findElement(By.name("Q1"))
              .sendKeys("qestion_1");
        driver.findElement(By.name("A1"))
              .sendKeys("answer_1");
        driver.findElement(By.name("Q2"))
              .sendKeys("qestion_2");
        driver.findElement(By.name("A2"))
              .sendKeys("answer_2");
        driver.findElement(By.name("Q3"))
              .sendKeys("qestion_3");
        driver.findElement(By.name("A3"))
              .sendKeys("answer_3");
        driver.findElement(By.name("Q4"))
              .sendKeys("qestion_4");
        driver.findElement(By.name("A4"))
              .sendKeys("answer_4");
        driver.findElement(By.name("Q5"))
              .sendKeys("qestion_5");
        driver.findElement(By.name("A5"))
              .sendKeys("answer_5");

        // Verify that notfy checkboxes are checked by default
        WebElement notifyCheckbox;
        notifyCheckbox = driver.findElement(By.name("general"));
        assertEquals("true", notifyCheckbox.getAttribute("checked"));
        notifyCheckbox = driver.findElement(By.name("country"));
        assertEquals("true", notifyCheckbox.getAttribute("checked"));
        notifyCheckbox = driver.findElement(By.name("regional"));
        assertEquals("true", notifyCheckbox.getAttribute("checked"));
        notifyCheckbox = driver.findElement(By.name("radius"));
        assertEquals("true", notifyCheckbox.getAttribute("checked"));
        
        WebElement agreeCheckbox = driver.findElement(By.name("cca_agree"));
        assertNull("Agreement checkbox not unchecked!", agreeCheckbox.getAttribute("checked"));
        agreeCheckbox.click();
        agreeCheckbox.submit();

        assertEquals(
            "Can't perfomt login!",
            "Your information has been submitted into our system. You will now be sent an email with a web link, you need to open that link in your web browser within 24 hours or your information will be removed from our system!",
            driver.findElement(By.cssSelector(".story")).findElement(By.tagName("p")).getText()
        );

        /*
         * 2. activate user at MANAGEMENT_SYSTEM_URI
         */
        driver.get(MANAGEMENT_SYSTEM_URI);
        driver.findElement(By.name("login_name"))
              .sendKeys(emailAddress);
        driver.findElement(By.name("login_password"))
              .sendKeys(password);
        driver.findElement(By.name("submit"))
              .click();
        assertEquals(
            "Can't login management system!", 
            "Dashboard", 
            driver.findElement(By.tagName("h1")).getText()
        );
        
        System.out.println(driver.getPageSource());
        
        /*
         * 3. login at TEST_SYSTEM_URI
         * 4. logout at TEST_SYSTEM_URI
         */
    }
}
