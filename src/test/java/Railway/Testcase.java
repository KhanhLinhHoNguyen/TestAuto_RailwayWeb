package Railway;

import Common.Constant;
import PageObjects.*;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Testcase {
    String PathScr = System.getProperty("user.dir") + "/Screenshot/";
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");
        WebDriverManager.chromedriver().setup();
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
    @Test
    public void TC01() throws IOException {
        System.out.println("TC01-User can log into Railway with valid username and password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME,Constant.PASSWORD).WelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;
        Assert.assertEquals(actualMsg,expectedMsg,"Welcome message is not displayed as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC01_User can login into Railway with valid username and password.png"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC02() throws IOException {
        System.out.println("TC02-User can't login with blank Username textbox");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login("",Constant.PASSWORD).ErrorMessage();
        String expectedMsg ="There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg,expectedMsg,"Error message is not displayed as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC02_User can not login with blank Username textbox.png"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC03() throws IOException {
        System.out.println("TC03-User cannot log into Railway with invalid password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg =  loginPage.login(Constant.USERNAME,"12345667").ErrorMessage();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg,expectedMsg,"Error message is not displayed as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC03_User can login with invalid Password.png"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC04() throws IOException {
        System.out.println("TC04-Login page displays when un-logged User clicks on Book ticket tab");
        HomePage homePage = new HomePage();
        homePage.open();
        BookTicket bookticket = new BookTicket();
        String titleActual = bookticket.clickBookTicket();
        String titleExpect = "Login page";
        Assert.assertEquals(titleActual,titleExpect,"Login page is not displays when access Book Ticket without Login");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC04_Login page display when un-logged User clicks on Book ticket tab.png"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC05() throws IOException {
        System.out.println("TC05-System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,"linhlinh");
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        String actualMsg = "";
        for (int i = 1; i <=3 ; i++) {
            loginPage.login(Constant.USERNAME,"linhlinh");
            if (i==3) {
                actualMsg= loginPage.login(Constant.USERNAME,"linhlinh").ErrorMessage();
            }
        }
        Assert.assertEquals(actualMsg,expectedMsg,"ErrorMsg should be: " + expectedMsg);
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC05-System shows message when user enters wrong password several times"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC06() throws IOException {
        System.out.println("TC06-Additional pages display once user logged in");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='My ticket']")).isDisplayed();
        Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='Change password']")).isDisplayed();
        Constant.WEBDRIVER.findElement(By.xpath("//span[normalize-space()='Log out']"));
        MyTicketPage myTicketPage = new MyTicketPage();
        myTicketPage.clickMyTicket();
        String tittleActual = Constant.WEBDRIVER.getTitle();
        String tittleExpected = "Safe Railway - My Ticket";
        Assert.assertEquals(tittleActual,tittleExpected,"My ticket page is not display as expected");
        ChangepasswordPage changepasswordPage = new ChangepasswordPage();
        changepasswordPage.clickChangePassword();
        String tittleActual1 = Constant.WEBDRIVER.getTitle();
        String tittleExpected1 ="Safe Railway - Change Password";
        Assert.assertEquals(tittleActual1,tittleExpected1,"Change Password is not display as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC06-Additional pages display once user logged in"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC07() throws IOException {
        System.out.println("TC07-User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();
        GeneralPage goToRegister = new GeneralPage();
        goToRegister.gotoRegisterPage();
        RegisterPage registerPage = new RegisterPage();
        String actualMsg = registerPage.register(Constant.EMAIL,Constant.PASSWORDREGISTER,Constant.CONFIRMPASSWORD,Constant.PID).RegisterMessage();
        String expectedMsg = "Thank you for registering your account";
        Assert.assertEquals(actualMsg,expectedMsg,"Register message is not displayed as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC07-User can create new account"));
        Constant.WEBDRIVER.close();

    }

    @Test
    public void TC08() throws IOException {
        System.out.println("TC08-User can't login with an account hasn't been activated");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("linhhihi@gmail.com","1234567890");
        Constant.WEBDRIVER.findElement(By.xpath("//input[@title='Login']")).submit();
        String titleActual = Constant.WEBDRIVER.getTitle();
        String titleExpected = "Safe Railway - Login";
        Assert.assertEquals(titleActual,titleExpected,"User can't login with an account hasn't been activated");
        String actualMsg = loginPage.login("linhhihi@gmail.com","1234567890").ErrorMessage();
        String expectedMsg ="Invalid username or password. Please try again.";
        Assert.assertEquals(actualMsg,expectedMsg,"Error message is not displayed as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC08-User can't login with an account hasn't been activated"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC09() throws IOException {
        System.out.println("TC09-User can change password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);
        ChangepasswordPage changepasswordPage = new ChangepasswordPage();
        changepasswordPage.clickChangePassword();
        String actualMsg = changepasswordPage.change(Constant.PASSWORD,Constant.NEWPASSWORD,Constant.NEWPASSWORD).MessageSuccess();
        String expectedMsg = "Your password has been updated";
        Assert.assertEquals(actualMsg,expectedMsg,"Message Success is not displayed as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC09-User can change password"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC10() throws IOException {
        System.out.println("TC10-User can't create account with Confirm password is not the same with Password");
        HomePage homePage = new HomePage();
        homePage.open();
        GeneralPage goToRegister = new GeneralPage();
        goToRegister.gotoRegisterPage();
        RegisterPage registerPage = new RegisterPage();
        String actualMsg = registerPage.register(Constant.EMAIL,Constant.PASSWORDREGISTER,"00000000000",Constant.PID).ErrorRegisterMessage();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg,expectedMsg,"Register message is not displayed as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC10-User can't create account with Confirm password is not the same with Password"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC11() throws IOException {
        System.out.println("TC11-User can't create account while password and PID fields are empty");
        HomePage homePage = new HomePage();
        homePage.open();
        GeneralPage goToRegister = new GeneralPage();
        goToRegister.gotoRegisterPage();
        RegisterPage registerPage = new RegisterPage();
        String actualMsg = registerPage.register(Constant.EMAIL,"","","").ErrorRegisterMessage();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg,expectedMsg,"Register message is not displayed as expected");
        String actualMsg1 = registerPage.ErrorInvalidPassword();
        String expectedMsg1 = "Invalid password length.";
        Assert.assertEquals(actualMsg1,expectedMsg1,"Register message is not displayed as expected");
        String actualMsg2 = registerPage.ErrorInvalidID();
        String expectedMsg2 = "Invalid ID length.";
        Assert.assertEquals(actualMsg2,expectedMsg2,"Register message is not displayed as expected");
        File screenshot = ((TakesScreenshot)Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr+ "TC11-User can't create account while password and PID fields are empty"));
        Constant.WEBDRIVER.close();
    }

//    @Test
//    public void TC12(){
//        System.out.println("Errors display when password reset token is blank");
//        GeneralPage goToForgotPassword = new GeneralPage();
//        goToForgotPassword.gotoForgotPasswordPage();
//        ForgotPasswordPage forgotPasswordPagePage = new ForgotPasswordPage();
//        forgotPasswordPagePage.forgot(Constant.USERNAME);
//
//    }

//    @Test
//    public void TC13(){
//        System.out.println("Errors display if password and confirm password don't match when resetting password");
//    }
////

    @Test
    public void TC14() throws InterruptedException, IOException {
        System.out.println("TC14-User can book 1 ticket at a time");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);
        BookTicket bookTicket = new BookTicket();
        bookTicket.clickBookTicket();
        Select Date = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='Date']")));
        Date.selectByVisibleText("12/9/2023");
        Select DepartStation = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='DepartStation']")));
        DepartStation.selectByVisibleText("Sài Gòn");
        Select ArriveStation = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='ArriveStation']")));
        ArriveStation.selectByVisibleText("Nha Trang");
        Select SeatType = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='SeatType']")));
        SeatType.selectByVisibleText("Soft bed with air conditioner");
        Select TicketAmount = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='TicketAmount']")));
        TicketAmount.selectByVisibleText("1");
        Constant.WEBDRIVER.findElement(By.xpath("//input[@value='Book ticket']")).submit();
        Thread.sleep(1000);
        String MsgActual = bookTicket.MsgSuccess();
        String MsgExpected = "Ticket booked successfully!";
        Assert.assertEquals(MsgActual,MsgExpected,"Message is not display as expected");
        List<WebElement> myList=Constant.WEBDRIVER.findElements(By.xpath("//tr[@class='OddRow']/td"));
        List<String> Inform = new ArrayList<>();
        for (int i=0; i<myList.size(); i++){
            Inform.add(myList.get(i).getText());
        }
        List<String> expectedListinfoTicket = new ArrayList<>();
        expectedListinfoTicket.add("Sài Gòn");
        expectedListinfoTicket.add("Nha Trang");
        expectedListinfoTicket.add("Soft bed with air conditioner");
        Assert.assertEquals(Inform,expectedListinfoTicket,"Ticket Infor not matches with ordered Ticket");
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        File screenshot = ((TakesScreenshot) Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr + "TC14_User can book 1 ticket at a time.png"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC15() throws IOException, InterruptedException {
        System.out.println("TC-15User can open Book ticket page by clicking on Book ticket link in Train timetable page");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);
        TimetablePage timetablePage = new TimetablePage();
        timetablePage.clickTimetable();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(10000);
        Constant.WEBDRIVER.findElement(By.xpath("//tbody/tr[17]/td[7]/a[1]")).click();
        Select actualDepartFrom = new Select(Constant.WEBDRIVER.findElement(By.xpath("//li/select[@name='DepartStation']")));
        String DepartFrom = actualDepartFrom.getFirstSelectedOption().getText();
        Select actualArriveAt = new Select((Constant.WEBDRIVER.findElement(By.xpath("//li/span/select[@name='ArriveStation']"))));
        String ArriveAt = actualArriveAt.getFirstSelectedOption().getText();
        if (DepartFrom.contains("Huế") && ArriveAt.contains("Sài Gòn"))
        {
            System.out.println("Book ticket page is loaded with correct Depart from and Arrive at values.");
        }
        else {
            System.out.println("Book ticket page isn't loaded with correct Depart from and Arrive at values.");
        }
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        File screenshot = ((TakesScreenshot) Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr + "TC15-Book ticket page is loaded with correct Depart from and Arrive at values.png"));
        Constant.WEBDRIVER.close();
    }

    @Test
    public void TC16() throws IOException, InterruptedException {
        System.out.println("TC16-User can cancel a ticket");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);
        BookTicket bookTicket = new BookTicket();
        bookTicket.clickBookTicket();
        Select Date = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='Date']")));
        Date.selectByVisibleText("12/15/2023");
        Select DepartStation = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='DepartStation']")));
        DepartStation.selectByVisibleText("Phan Thiết");
        Select ArriveStation = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='ArriveStation']")));
        ArriveStation.selectByVisibleText("Nha Trang");
        Select SeatType = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='SeatType']")));
        SeatType.selectByVisibleText("Soft bed with air conditioner");
        Select TicketAmount = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='TicketAmount']")));
        TicketAmount.selectByVisibleText("1");
        Constant.WEBDRIVER.findElement(By.xpath("//input[@value='Book ticket']")).submit();
        MyTicketPage myTicketPage = new MyTicketPage();
        myTicketPage.clickMyTicket();
        Thread.sleep(1000);
        Select FilterDpStation = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='FilterDpStation']")));
        FilterDpStation.selectByVisibleText("Phan Thiết");
        Select FilterArStation = new Select(Constant.WEBDRIVER.findElement(By.xpath("//select[@name='FilterArStation']")));
        FilterArStation.selectByVisibleText("Nha Trang");
        Constant.WEBDRIVER.findElement(By.xpath("//input[@type='submit']")).submit();
        Thread.sleep(1000);
        File screenshot = ((TakesScreenshot) Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(PathScr + "TC16-Ticket before cancel.png"));
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Constant.WEBDRIVER.findElement(By.xpath("(//input[@value='Cancel'])[1]")).click();
        Thread.sleep(2000);
        Constant.WEBDRIVER.switchTo().alert().accept();
        Thread.sleep(2000);
        String errorMessageAfterCancel = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='error message']")).getText();
        String expectedMessageAfterCancel = "Sorry, can't find any results that match your filters.\n" + "Please change the filters and try again.";
        Assert.assertEquals(errorMessageAfterCancel,expectedMessageAfterCancel,"Ticket is not Canceled!");
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        File screenshot1 = ((TakesScreenshot) Constant.WEBDRIVER).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot1, new File(PathScr + "TC16-User can cancel a ticket.png"));
        Constant.WEBDRIVER.close();
    }
}






