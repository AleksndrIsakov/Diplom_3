import api.UserClient;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.webdriver.CurrentFrameCondition;
import com.codeborne.selenide.conditions.webdriver.CurrentFrameUrl;
import data.User;
import data.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import lombok.extern.java.Log;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;
import page.RegisterPage;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class RegisterTest {

    private User user;
    private UserClient userClient;

    @Before
    public void setUp(){
        userClient = new UserClient();
        user = UserGenerator.random();
    }

    @After
    public void tearDown() {
        if (user != null) {
            userClient.delete();
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successRegister() {
        // Выполним регистрацию в UI
        RegisterPage registerPage = Selenide.open(RegisterPage.getUrl(),RegisterPage.class);
        LoginPage loginPage = registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        // Проверим через API наличие зарегистрированного пользователя в системе
        ValidatableResponse response = userClient.login(user);
        assertThat(response.extract().statusCode(), equalTo(SC_OK));
    }

    @Test
    @DisplayName("Ошибка регистрации, пароль меньше шести символов")
    public void wrongPasswordLength() {
        user.setPassword(RandomStringUtils.randomAlphabetic(5));
        RegisterPage registerPage = Selenide.open(RegisterPage.getUrl(),RegisterPage.class);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        user = null;
        assertEquals("Ожидаемое сообщение об ошибке не соответствует полученному", "Некорректный пароль", registerPage.getInputError());

    }

}
