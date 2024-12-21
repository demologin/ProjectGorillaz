package com.javarush.shakhurov;

import com.javarush.shakhurov.controller.StartController;
import com.javarush.shakhurov.dto.UserPage;
import com.javarush.shakhurov.model.User;
import com.javarush.shakhurov.repository.UserRepository;
import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class StartControllerTest {
    private static Context ctx;

    @BeforeEach
    public final void setUp() throws Exception {
        ctx = mock(Context.class);
    }

    @Test
    @DisplayName("Valid userId cookie returns correct user data and renders page")
    public void validUserIdCookieReturnsUserDataTest() throws SQLException {
        when(ctx.cookie("userId")).thenReturn("1");

        User expectedUser = new User("John", "john@email.com", "pass123", "beginner");
        expectedUser.setId(1L);

        try (MockedStatic<UserRepository> userRepositoryMock = mockStatic(UserRepository.class)) {

            userRepositoryMock.when(() -> UserRepository.findById(1L)).thenReturn(Optional.of(expectedUser));
            StartController.index(ctx);

            verify(ctx).render(eq("start.jte"), argThat(model -> {
                UserPage page = (UserPage) ((Map<String, Object>) model).get("page");
                return page.getUser().getId().equals(1L) &&
                        page.getUser().getName().equals("John") &&
                        page.getUser().getEmail().equals("john@email.com");
            }));
        }
    }

    @Test
    @DisplayName("Empty userId cookie returns null user")
    public void emptyUserIdCookieReturnsNullUserTest() throws SQLException {
        when(ctx.cookie("userId")).thenReturn("");

        StartController.index(ctx);

        verify(ctx).render(eq("start.jte"), argThat(model -> {
            UserPage page = (UserPage) ((Map<String,Object>)model).get("page");
            return page.getUser() == null;
        }));
    }
}
