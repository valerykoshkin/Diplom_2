package Data.UserData;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class UserGenerator {
    public User random() {
        return new User(randomAlphanumeric(5, 10)+"@yandex.ru", "132456", "first");
    }
}
