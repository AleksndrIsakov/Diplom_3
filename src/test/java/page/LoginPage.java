package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends AbstractPage {

    private static final String LOGIN_PAGE = "login";

    public static String getUrl() {
        return AbstractPage.getFullUrl(LOGIN_PAGE);
    }

    @FindBy(how = How.NAME, using = "name")
    private SelenideElement email;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement password;

    @Step("Выполним вход под: {email}")
    public MainPage login(String email, String password) {
        this.email.setValue(email);
        this.password.setValue(password);
        clickButton("Войти");
        return page(MainPage.class);
    }
}
