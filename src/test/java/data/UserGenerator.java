package data;

import org.apache.commons.lang3.RandomStringUtils;
import com.github.javafaker.*;

public class UserGenerator {

    private static Faker faker = new Faker();

    public static User random() {
        return User.builder()
                .name(faker.name().firstName())
                .password(faker.internet().password(6,10))
                .email(faker.internet().emailAddress())
                .build();
    }
}
