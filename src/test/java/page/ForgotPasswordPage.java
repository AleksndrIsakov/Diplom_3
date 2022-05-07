package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage extends AbstractPage implements LoginInterface {

    private static final String FORGOT_PASSWORD_PAGE = "forgot-password";

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement toLogin;


    public static String getUrl(){
        return AbstractPage.getFullUrl(FORGOT_PASSWORD_PAGE);
    }

    @Override
    @Step("Переход через кнопку в форме восстановления пароля")
    public LoginPage canLogin() {
        toLogin.click();
        return page(LoginPage.class);
    }
}
