package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage{
    //locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@title='Login']");

    //element
    public WebElement getTxtUsername()
    {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }
    public WebElement getTxtPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getBtnLogin()
    {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    //methods
    public HomePage login(String username, String password)
    {
        //submit login credentials
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        //land on homepage
        return new HomePage();
    }
}
