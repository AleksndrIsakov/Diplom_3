import api.UserClient;
import com.codeborne.selenide.Selenide;
import data.User;
import data.UserGenerator;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.*;

@Story("Вход")
@RunWith(Parameterized.class)
public class LoginTest extends RunConfiguration {

    private User user;
    private UserClient userClient;
    private String url;
    private AbstractPage page;

    @Before
    public void setUp() {
        user = UserGenerator.random();
        userClient = new UserClient();
        userClient.register(user);
    }

    @After
    public void tearDown() {
        userClient.delete();
    }

    public LoginTest(String url, AbstractPage page) {
        this.url = url;
        this.page = page;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][] {
                {AbstractPage.getFullUrl(""), new MenuPageFragment()},
                {MainPage.getUrl(), new MainPage()},
                {RegisterPage.getUrl(), new RegisterPage()},
                {LoginPage.getUrl(), new LoginPage()},
                {ForgotPasswordPage.getUrl(), new ForgotPasswordPage()}
        };
    }

    @Test
    @DisplayName("Авторизация пользователя с разных страниц")
    public void checkLogin(){
        LoginPage loginPage;
        AbstractPage oneOfPage = Selenide.open(url, page.getClass());
        if (oneOfPage instanceof LoginInterface)
            loginPage = ((LoginInterface) oneOfPage).canLogin();
        else
            loginPage = (LoginPage) oneOfPage;

        loginPage.login(user.getEmail(),user.getPassword());
    }
}
