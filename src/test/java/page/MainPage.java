package page;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainPage extends AbstractPage implements LoginInterface {

    public static String getUrl(){
        return AbstractPage.getFullUrl("");
    }

    @Step("Выберем раздел {section}")
    public void setSection(String section) {
        $(By.xpath("//span[text()='" + section + "']")).shouldBe(Condition.visible).click();
    }

    @Step ("Проверим видимость выбранной секции {section}")
    public boolean checkSection(String section) {
        return $(By.xpath("//h2[text()='" + section + "']")).shouldBe(Condition.visible).isDisplayed();
    }

    @Override
    @Step("Переход по кнопке «Войти в аккаунт» на главной")
    public LoginPage canLogin() {
        clickButton("Войти в аккаунт");
        return page(LoginPage.class);
    }
}
