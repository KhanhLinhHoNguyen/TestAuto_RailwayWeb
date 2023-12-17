package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RegisterPage extends GeneralPage {
    //locators
    private final By _email = By.xpath("//input[@id='email']");
    private final By _password = By.xpath("//input[@id='password']");
    private final By _confirmpassword = By.xpath("//input[@id='confirmPassword']");
    private final By _PID = By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@title='Register']");
    private final By _errorMsg = By.xpath("//p[@class='message error']");
    private final By _body = By.xpath("//body");
    //elements
    public WebElement getBody () {
        return Constant.WEBDRIVER.findElement(_body);
    }
    public WebElement getEmail () {
        return Constant.WEBDRIVER.findElement(_email);
    }
    public WebElement getPassword () {
        return Constant.WEBDRIVER.findElement(_password);
    }
    public WebElement getConfirmPassword () {
        return Constant.WEBDRIVER.findElement(_confirmpassword);
    }
    public WebElement getPID () {
        return Constant.WEBDRIVER.findElement(_PID);
    }
    public WebElement getBtnRegister () {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }
    public WebElement getErrorMsg () {
        return Constant.WEBDRIVER.findElement(_errorMsg);
    }
    //methods
    public RegisterPage register( String email, String password, String confirmpassword, String pid)
    {
        this.getEmail().sendKeys(email);
        this.getPassword().sendKeys(password);
        this.getConfirmPassword().sendKeys(confirmpassword);
        this.getPID().sendKeys(pid);
        this.getBody().sendKeys(Keys.CONTROL,Keys.END);
        this.getBtnRegister().submit();
        return new RegisterPage();
    }
}
