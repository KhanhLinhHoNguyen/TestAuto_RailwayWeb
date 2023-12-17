package PageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TimetablePage extends GeneralPage{
    private final By _timeTable = By.xpath("//span[normalize-space()='Timetable']");
    private final By _titlePage =  By.xpath("//h1[normalize-space()='Train Timetable']");
    public WebElement clickTxtTimeTable() {return Constant.WEBDRIVER.findElement(_timeTable); }
    public WebElement getTitlePage() {return Constant.WEBDRIVER.findElement(_titlePage); }
    public String clickTimetable()
    {
        this.clickTxtTimeTable().click();
        return this.getTitlePage().getText();
    }
}
