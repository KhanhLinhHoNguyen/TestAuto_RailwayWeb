package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends GeneralPage {
    private final By _emailAddress = By.xpath("//input[@id='email']");
    private final By _btnSendInstructions = By.xpath("//input[@value='Send Instructions']");

    public WebElement getEmailAddress() {return Constant.WEBDRIVER.findElement(_emailAddress); }
    public WebElement getBtnSendInstructions() {return Constant.WEBDRIVER.findElement(_btnSendInstructions); }

    public ForgotPasswordPage forgot(String emailAddress)
    {
        this.getEmailAddress().sendKeys(emailAddress);
        this.getBtnSendInstructions().click();
        return new ForgotPasswordPage();
    }
}
