import api.UserClient;
import com.codeborne.selenide.Selenide;
import data.User;
import data.UserGenerator;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;
import page.MainPage;
import page.MenuPageFragment;

import static org.junit.Assert.assertTrue;

@Story("Переход из личного кабинета в конструктор")
public class ConstructorEnterTest extends RunConfiguration {
    private User user;
    private UserClient userClient;
    private MenuPageFragment menuPageFragment;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.random();
        userClient.register(user);

        LoginPage loginPage = Selenide.open(LoginPage.getUrl(), LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        menuPageFragment = loginPage.getMainMenu();
        menuPageFragment.enterPersonalArea();
    }

    @After
    public void tearDown() {
        userClient.delete();
    }

    @Test
    @DisplayName("Переход в конструктор по клику на «Конструктор»")
    public void enterConstructor() {
        MainPage mainPage = menuPageFragment.enterConstructor();
        assertTrue("Нет перехода к конструктору", mainPage.isPageLoad("BurgerConstructor"));
    }

    @Test
    @DisplayName("Переход в конструктор по клику на логотип «Stellar Burgers»")
    public void enterStellarBurgers() {
        MainPage mainPage = menuPageFragment.enterConstructorByLogo();
        assertTrue("Нет перехода к конструктору", mainPage.isPageLoad("BurgerConstructor"));
    }
}
