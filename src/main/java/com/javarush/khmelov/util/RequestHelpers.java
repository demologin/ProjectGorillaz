package com.javarush.khmelov.util;

import com.javarush.khmelov.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class RequestHelpers {

    public Long getId(HttpServletRequest req) {
        return getId(req, Key.ID);
    }

    public Long getId(HttpServletRequest req, String key) {
        String id = req.getParameter(key);
        return id != null && !id.isBlank()
                ? Long.parseLong(id)
                : 0L;
    }

    public Long getId(HttpSession session) {
        Object user = session.getAttribute(Key.USER);
        return user != null
                ? ((User) user).getId()
                : 0L;
    }

    public Optional<User> getUser(HttpSession session) {
        return Optional
                .ofNullable(session.getAttribute(Key.USER))
                .map(User.class::cast); // equivalent to .map(u -> (User) u);
    }
}
