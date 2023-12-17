package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangepasswordPage extends GeneralPage {

    private final By _currentPassword = By.xpath("//input[@id='currentPassword']");
    private final By _newPassword = By.xpath("//input[@id='newPassword']");
    private final By _confirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _btnChangePassword = By.xpath("//input[@title='Change password']");
    private final By _titlePage = By.xpath("//h1[normalize-space()='Change password']");
    private final By _changePassword = By.xpath("//span[normalize-space()='Change password']");

    public WebElement getCurrentPassword() {
        return Constant.WEBDRIVER.findElement(_currentPassword);
    }
    public WebElement getNewPassword() {
        return Constant.WEBDRIVER.findElement(_newPassword);
    }
    public WebElement getConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_confirmPassword);
    }
    public WebElement getBtnChangePassword() {
        return Constant.WEBDRIVER.findElement(_btnChangePassword);
    }
    public WebElement getTxtPageTitle()
    {
        return Constant.WEBDRIVER.findElement(_titlePage);
    }
    public WebElement clickTxtChangePassword()
    {
        return Constant.WEBDRIVER.findElement(_changePassword);
    }
    public String clickChangePassword()
    {
        //submit login credentials
        this.clickTxtChangePassword().click();
        //land on homepage
        return this.getTxtPageTitle().getText();
    }
    public ChangepasswordPage change(String currentPassword, String newPassword, String confirmPassword)
    {
        this.getCurrentPassword().sendKeys(currentPassword);
        this.getNewPassword().sendKeys(newPassword);
        this.getConfirmPassword().sendKeys(confirmPassword);
        this.getBtnChangePassword().click();
        return new ChangepasswordPage();
    }
}
