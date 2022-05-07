import com.codeborne.selenide.Selenide;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.MainPage;

import static org.junit.Assert.assertTrue;

@Story("Раздел «Конструктор»")
@RunWith(Parameterized.class)
public class ConstructorTest extends RunConfiguration {
    private static MainPage mainPage;

    private String section;

    public ConstructorTest(String section) {
        this.section = section;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"Начинки"},
                {"Соусы"},
                {"Булки"}
        };
    }

    @Before
    public void setUp() {
        if (mainPage == null)
            mainPage = Selenide.open(MainPage.getUrl(), MainPage.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    @DisplayName("Переход к разделам ингредиентов конструктора")
    public void checkTabs() {
        mainPage.setSection(section);
        assertTrue("Выбранная секция не отображается", mainPage.checkSection(section));
    }
}
