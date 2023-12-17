package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyTicketPage extends GeneralPage{
    private final By _titlePage = By.xpath("//h1[@align='center']");
    private final By _myTicket = By.xpath("//span[normalize-space()='My ticket']");
    public WebElement getTxtPageTitle()
    {
        return Constant.WEBDRIVER.findElement(_titlePage);
    }
    public WebElement clickTxtMyTicket()
    {
        return Constant.WEBDRIVER.findElement(_myTicket);
    }
    public String clickMyTicket()
    {
        //submit login credentials
        this.clickTxtMyTicket().click();
        //land on homepage
        return this.getTxtPageTitle().getText();
    }
}
