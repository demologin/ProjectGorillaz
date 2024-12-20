package com.javarush.khmelov.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import static com.javarush.khmelov.storage.ConstantsCommon.ERROR_MESSAGE;

@UtilityClass
public class RequestHelpers {

    public static void createError(HttpServletRequest request, String errorMessage) {
        request.getSession().setAttribute(ERROR_MESSAGE, errorMessage);
    }
}
