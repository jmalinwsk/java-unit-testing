package services;

import database.Database;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {
    private Database database;
    private UserService userService;
    private User user;

    @BeforeEach
    public void init() {
        database = new Database();
        userService = new UserService();
        user = new User("test@test.com");
    }

    @Test
    @DisplayName("validation of user (valid)")
    public void userValidationTest() {
        boolean result = userService.userValidation(user);
        assertTrue(result);
    }

    @Test
    @DisplayName("validation of user " +
            "(returns false because of null argument)")
    public void userValidation2Test() {
        assertFalse(userService.userValidation(null));
    }

    @Test
    @DisplayName("validation of user " +
            "(returns false because email is an empty string")
    public void userValidation3Test() {
        user.setEmail("");
        assertFalse(userService.userValidation(user));
    }

    @Test
    @DisplayName("validation of user" +
            "(returns false because email is null")
    public void userValidation4Test() {
        user.setEmail(null);
        assertFalse(userService.userValidation(user));
    }

    @Test
    @DisplayName("validation of user" +
            "(returns false because email is invalid")
    public void userValidation5Test() {
        user.setEmail("invalid email");
        assertFalse(userService.userValidation(user));
    }


    @AfterEach
    public void cleanup() {
        database = null;
        userService = null;
        user = null;
    }
}
