package page;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class MainPage extends AbstractPage implements LoginInterface {

    public static String getUrl(){
        return AbstractPage.getFullUrl("");
    }

    @Override
    @Step("Переход по кнопке «Войти в аккаунт» на главной")
    public LoginPage canLogin() {
        clickButton("Войти в аккаунт");
        return page(LoginPage.class);
    }
}
