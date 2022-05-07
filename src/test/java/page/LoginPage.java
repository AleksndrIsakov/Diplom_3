package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.lang.reflect.Field;

public class LoginPage extends AbstractPage {

    public static final String LOGN_PAGE = "login";

    public static String getUrl(){
        return AbstractPage.getFullUrl(LOGN_PAGE);
    }

    @FindBy(how = How.NAME, using = "name")
    private SelenideElement email;

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement password;

    @Step("Выполним вход под: {email}/{password}")
    public void login(String email, String password){
        this.email.setValue(email);
        this.password.setValue(password);
        clickButton("Войти");
    }
}
