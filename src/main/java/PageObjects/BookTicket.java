package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BookTicket extends GeneralPage{
    private final By _titlePage = By.xpath("//h1[@align='center']");
    private final By _bookTicket = By.xpath("//span[text()='Book ticket']");
    private final By _MsgSuccess = By.xpath("//h1[normalize-space()='Ticket booked successfully!']");
    public WebElement getTxtPageTitle()
    {
        return Constant.WEBDRIVER.findElement(_titlePage);
    }
    public WebElement clickTxtBookTicket()
    {
        return Constant.WEBDRIVER.findElement(_bookTicket);
    }
    public WebElement getMsgSuccess() {return Constant.WEBDRIVER.findElement(_MsgSuccess); }
    public String clickBookTicket()
    {
        //submit login credentials
        this.clickTxtBookTicket().click();
        //land on homepage
        return this.getTxtPageTitle().getText();
    }
    public String MsgSuccess()
    {
        return this.getMsgSuccess().getText();
    }
}
