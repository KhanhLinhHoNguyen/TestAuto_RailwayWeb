package PageObjects;

import Common.Constant;

public class HomePage extends GeneralPage {

    public HomePage open()
    {
        Constant.WEBDRIVER.navigate().to(Constant.RAIWAY_URL);
        return this;
    }
}
