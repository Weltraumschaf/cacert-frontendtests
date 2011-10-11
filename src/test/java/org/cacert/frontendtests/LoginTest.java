/*
 * LICENSE
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich(at)weltraumschaf(dot)de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 */
package org.cacert.frontendtests;

import org.junit.Ignore;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Sven Strittmatter <ich@weltraumschaf.de>
 */
public class LoginTest extends AbstractTestCase {
    
    private WebDriver driver;
    
    @Override
    protected void setUp() {
        
    }
    
    @Ignore("Not ready yet")
    public void testLogin() {
        
    }
    
    @Override
    protected void tearDown() {
        driver.close();
        driver = null;
//        person = null;
    }
}
