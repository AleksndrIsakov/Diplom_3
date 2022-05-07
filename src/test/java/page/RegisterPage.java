package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegisterPage extends AbstractPage implements LoginInterface {

    private static final String REGISTER_PAGE = "register";

    @FindBy(how = How.UNSET, using = "")
    private SelenideElement name;

    @FindBy(how = How.UNSET, using = "")
    private SelenideElement email;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement password;

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement toLogin;

    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement inputError;

    public RegisterPage() {
        name = getInput("Имя");
        email = getInput("Email");
    }

    public static String getUrl() {
        return getFullUrl(REGISTER_PAGE);
    }

    @Step("Регистрируем пользователя: {name}")
    public LoginPage register(String name, String email, String password) {
        this.name.setValue(name);
        this.email.setValue(email);
        this.password.setValue(password);
        clickButton("Зарегистрироваться");
        return page(LoginPage.class);
    }


    @Override
    @Step("Переход через кнопку в форме регистрации")
    public LoginPage canLogin() {
        toLogin.click();
        return page(LoginPage.class);
    }

    public String getInputError() {
        return inputError.getText();
    }
}
