package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MenuPageFragment extends AbstractPage implements LoginInterface {

    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement personalArea;

    @FindBy(how = How.LINK_TEXT, using = "Конструктор")
    private SelenideElement constructor;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'logo')]")
    private SelenideElement stellarBurgerLogo;

    @Step("Переход в конструктор")
    public MainPage enterConstructor() {
        constructor.click();
        return page(MainPage.class);
    }

    @Step("Переход в конструктор по логотипу")
    public MainPage enterConstructorByLogo() {
        stellarBurgerLogo.click();
        return page(MainPage.class);
    }

    @Step("Переход в личный кабинет пользователя")
    public ProfilePage enterPersonalArea() {
        personalArea.click();
        return page(ProfilePage.class);
    }

    @Override
    @Step("Переход к авторизации через кнопку «Личный кабинет»")
    public LoginPage canLogin() {
        personalArea.click();
        return page(LoginPage.class);
    }

}
