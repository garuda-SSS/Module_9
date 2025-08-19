package ru.cinescope.api.steps;

import io.qameta.allure.Step;
import ru.cinescope.api.clients.UserClient;
import ru.cinescope.api.dto.AuthResponse;

public class UserSteps {
    private UserClient user = new UserClient();

    @Step("Авторизуемся под почтой '{email}' и паролем '{password}'")
    public AuthResponse userEnter(String email, String password){
        return user.authorization(email,password);
    }
}
