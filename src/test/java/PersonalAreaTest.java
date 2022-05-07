import api.UserClient;
import com.codeborne.selenide.Selenide;
import data.User;
import data.UserGenerator;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.*;

import static org.junit.Assert.assertTrue;

@Story("Переход в личный кабинет")
public class PersonalAreaTest extends RunConfiguration {

    private User user;
    private UserClient userClient;
    private MenuPageFragment menuPageFragment;
    private ProfilePage profilePage;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.random();
        userClient.register(user);

        LoginPage loginPage = Selenide.open(LoginPage.getUrl(), LoginPage.class);
        loginPage.login(user.getEmail(), user.getPassword());

        menuPageFragment = loginPage.getMainMenu();
    }

    @After
    public void tearDown() {
        userClient.delete();
    }


    @Test
    @DisplayName("Переход по клику в «Личный кабинет»")
    public void enterPersonalArea() {

        profilePage = menuPageFragment.enterPersonalArea();
        assertTrue("Нет перехода в личный кабинет", profilePage.isPageLoad("account"));
    }
}
