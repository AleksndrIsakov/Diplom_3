package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MenuFragment extends AbstractPage implements LoginInterface {

    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement toLogin;

    @Override
    @Step("Переход через кнопку «Личный кабинет»")
    public LoginPage canLogin() {
        toLogin.click();
        return page(LoginPage.class);
    }

}
