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
import page.LoginPage;
import page.MainPage;
import page.MenuPageFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Story("Раздел «Конструктор»")
@RunWith(Parameterized.class)
public class ConstructorTest {
    private User user;
    private UserClient userClient;
    private MainPage mainPage;

    private List<String> sections;

    public ConstructorTest(List<String> sections) {
        this.sections = sections;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][] {
                {Arrays.asList("Начинки","Булки")},
                {Arrays.asList("Соусы")},
                {Arrays.asList("Начинки")}
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.random();
        userClient.register(user);

        LoginPage loginPage = Selenide.open(LoginPage.getUrl(), LoginPage.class);
        mainPage = loginPage.login(user.getEmail(), user.getPassword());
    }

    @After
    public void tearDown() {
        userClient.delete();
    }

    @Test
    @DisplayName("Переход к разделам ингредиентов конструктора")
    public void checkTabs() {
        String last = null;
        for(String section: sections) {
            mainPage.setSection(section);
            last = section;
        }

        assertTrue("Выбранная секция не отображается", mainPage.checkSection(last));
    }
}
