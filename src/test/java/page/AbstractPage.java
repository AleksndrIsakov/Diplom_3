package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;

public abstract class AbstractPage {
    private static final String APPLICATION_URL = "https://stellarburgers.nomoreparties.site/";
    private static MenuPageFragment menuPageFragment;

    public static String getFullUrl(String partOfUrl) {
        return APPLICATION_URL + partOfUrl;
    }

    public static MenuPageFragment getMainMenu() {
        if (menuPageFragment == null)
            menuPageFragment = Selenide.page(MenuPageFragment.class);
        return menuPageFragment;
    }

    public SelenideElement getInput(String input) {
        return $(By.xpath("//label[text()='" + input + "']/../input"));
    }

    @Step("Нажмем кнопку {button}")
    public void clickButton(String button) {
        $(By.xpath("//button[text()='" + button + "']")).shouldBe(Condition.visible).click();
    }

    @Step("Загрузка страницы {pageClass}")
    public boolean isPageLoad(String pageClass) {
        SelenideElement page = $(By.xpath("//*[contains(@class, '" + pageClass + "')]")).shouldBe(Condition.visible);
       return page.isDisplayed();
    }
}
