package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GeneralPage {
    //Locators
    private final By tabLogin = By.xpath("//span[normalize-space()='Login']");
    private final By tabLogout = By.xpath("//span[normalize-space()='Log out']");
    private final By tabRegister = By.xpath("//a[@href='/Account/Register.cshtml']");
    private final By tabChangePassword = By.xpath("//span[normalize-space()='Change password']");
    private final By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot Password page']");
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By lblErrorMessage = By.xpath("//p[@class='message error LoginForm']");
    private final By lblRegisterMessage = By.xpath("//div[@id='content']/p");
    private final By lblErrorRegisterMsg = By.xpath("//p[@class='message error']");
    private final By lblErrorInvalidPassword = By.xpath("//label[normalize-space()='Invalid password length']");
    private final By lblErrorInvalidID = By.xpath("//label[normalize-space()='Invalid ID length']");
    private final By lblMessageSucsess = By.xpath("//p[@class='message success']");


    //elements
    protected WebElement getTabLogin(){
        return Constant.WEBDRIVER.findElement(tabLogin);
    }
    protected WebElement getTabLogout(){
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
    protected WebElement getTabRegister(){return Constant.WEBDRIVER.findElement(tabRegister);}
    protected WebElement getTabChangePassword(){return Constant.WEBDRIVER.findElement(tabChangePassword);}
    protected WebElement getLinkforgotPassword() {return Constant.WEBDRIVER.findElement(linkForgotPassword);}
    protected WebElement getlblWelcomeMessage(){
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }
    protected WebElement getlblErrorMessage() {return Constant.WEBDRIVER.findElement(lblErrorMessage);}
    protected WebElement getlblbRegisterMessage() {return Constant.WEBDRIVER.findElement(lblRegisterMessage);}
    protected WebElement getlblErrorRegisterMessage() {return Constant.WEBDRIVER.findElement(lblErrorRegisterMsg);}
    protected WebElement getlblErrorInvalidPassword() {return Constant.WEBDRIVER.findElement(lblErrorInvalidPassword);}
    protected WebElement getlblErrorInvalidID() {return Constant.WEBDRIVER.findElement(lblErrorInvalidID);}
    protected WebElement getlblMessageSuccess() {return Constant.WEBDRIVER.findElement(lblMessageSucsess);}
    //methods
    public String WelcomeMessage()
    {
        return this.getlblWelcomeMessage().getText();
    }
    public String ErrorMessage() {
        return this.getlblErrorMessage().getText();
    }
    public String RegisterMessage() {
        return this.getlblbRegisterMessage().getText();
    }
    public String ErrorRegisterMessage() { return this.getlblErrorRegisterMessage().getText();}
    public String ErrorInvalidPassword() { return this.getlblErrorInvalidPassword().getText();}
    public String ErrorInvalidID() { return this.getlblErrorInvalidID().getText();}
    public String MessageSuccess() {return this.getlblMessageSuccess().getText();}
    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return new LoginPage();
    }
    public RegisterPage gotoRegisterPage()
    {
        this.getTabRegister().click();
        return new RegisterPage();
    }
    public ChangepasswordPage gotoChangePassword()
    {
        this.getTabChangePassword().click();
        return new ChangepasswordPage();
    }
    public ForgotPasswordPage gotoForgotPasswordPage()
    {
        this.getLinkforgotPassword().click();
        return new ForgotPasswordPage();
    }
}
