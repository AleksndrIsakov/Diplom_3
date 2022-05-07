package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;

public abstract class AbstractPage {
    private static final String APPLICATION_URL = "https://stellarburgers.nomoreparties.site/";

    public static String getFullUrl(String partOfUrl){
        return APPLICATION_URL + partOfUrl;
    }

    public SelenideElement getInput(String name) {
        return $(By.xpath("//label[text()='" + name + "']/../input"));
    }

    public void clickButton(String name) {
        $(By.xpath("//button[text()='"+ name + "']")).shouldBe(Condition.enabled).click();
    }
}
