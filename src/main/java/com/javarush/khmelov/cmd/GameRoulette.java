package com.javarush.khmelov.cmd;

import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.storage.roulette.RouletteService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;


@SuppressWarnings("unused")
public class GameRoulette implements Command {
    private final UserService userService;
    private final RouletteService rouletteService;
    private final Map<String, String> rouletteMap;

    public GameRoulette(UserService userService, RouletteService rouletteService) {
        this.userService = userService;
        this.rouletteService = rouletteService;
        this.rouletteMap = rouletteService.getRoulletteMap();
    }

    @Override
    public String doGet(HttpServletRequest req) {

        String paramName = req.getParameter("pickedButton");
        if (paramName == null) {
            setStartCondition(req);
        } else {
            setFinishCondition(req);
        }

        return getView();
    }

    private void setFinishCondition(HttpServletRequest req) {
        String pickedColor = req.getParameter("pickedButton");
        String rouletteColor = rouletteService.getResultOfRotation();
        String resulColor = "RESULT_COLOR_" + rouletteColor;
        String resultImgColor = "IMAGE_URL_" + rouletteColor;

        req.setAttribute("imageUrl", rouletteMap.get(resultImgColor));
        req.setAttribute("resultColor", rouletteMap.get(resulColor));
        req.setAttribute("winLossDescription",
                (pickedColor.equals(rouletteColor))
                        ? rouletteMap.get("RESULT_WIN")
                        : rouletteMap.get("RESULT_LOSS"));
        req.setAttribute("isDone", true);

        if (pickedColor.equals(rouletteColor)) {
            addUserWin(req, userService);
        } else {
            addUserLoss(req, userService);
        }
    }

    private void setStartCondition(HttpServletRequest req) {
        req.setAttribute("START_DESCRIPTION", rouletteMap.get("START_DESCRIPTION"));
        req.setAttribute("RED_BUTTON_DESCRIPTION", rouletteMap.get("RED_BUTTON_DESCRIPTION"));
        req.setAttribute("BLACK_BUTTON_DESCRIPTION", rouletteMap.get("BLACK_BUTTON_DESCRIPTION"));
        req.setAttribute("ZERO_BUTTON_DESCRIPTION", rouletteMap.get("ZERO_BUTTON_DESCRIPTION"));
        req.setAttribute("IMAGE_URL_START", rouletteMap.get("IMAGE_URL_START"));

    }

}
