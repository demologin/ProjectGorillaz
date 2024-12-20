package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.storage.quest.QuestRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import static com.javarush.khmelov.storage.ConstantsCommon.LEFT;
import static com.javarush.khmelov.storage.ConstantsCommon.RIGHT;
import static com.javarush.khmelov.storage.quest.SetResourse.*;

@SuppressWarnings("unused")
public class GameQuest implements Command {
    private final UserService userService;
    private final List<QuestInfoEntity> questList;
    private final Map<String, String> questMap;
    private QuestInfoEntity conditionEntity;
    private Integer time;
    private Integer evidence;
    private Integer gold;
    private String pickedButton = LEFT;
    private int step;

    public GameQuest(QuestRepository questService, UserService userService) {
        this.userService = userService;
        this.questMap = questService.getQuestMap();
        this.questList = questService.getQuestList();
    }

    @Override
    public String doGet(HttpServletRequest req) {

        String paramName = req.getParameter("pickedButton");
        if (paramName != null) {
            setCondition(req);
        } else {
            setStartCondition(req);
        }

        if (step < questList.size()) {
            fillRequest(req);
        }
        step++;

        if (lossCheck()) {
            goToLoss(req);
        } else if (winCheck()) {
            goToWin(req);
        }

        return getView();
    }

    private void setStartCondition(HttpServletRequest req) {
        step = Integer.parseInt(questMap.get("START_STEP"));
        time = Integer.parseInt(questMap.get("START_TIME"));
        evidence = Integer.parseInt(questMap.get("START_EVIDENCE"));
        gold = Integer.parseInt(questMap.get("START_GOLD"));
        conditionEntity = questList.get(step);
        req.getSession().setAttribute("IMAGE_URL_EVIDENCE", questMap.get("IMAGE_URL_EVIDENCE"));
        req.getSession().setAttribute("IMAGE_URL_GOLD", questMap.get("IMAGE_URL_GOLD"));
        req.getSession().setAttribute("IMAGE_URL_TIME", questMap.get("IMAGE_URL_TIME"));
    }

    private void setCondition(HttpServletRequest req) {
        if (step < questList.size()) {
            conditionEntity = questList.get(step);
        }
        pickedButton = req.getParameter("pickedButton");
        time += conditionEntity.getDeltaTime(pickedButton);
        evidence += conditionEntity.getDeltaEvidence(pickedButton);
        gold += conditionEntity.getDeltaGold(pickedButton);
    }

    private void fillRequest(HttpServletRequest req) {
        req.setAttribute("buttonLeft", conditionEntity.getButtonLeftText());
        req.setAttribute("buttonRight", conditionEntity.getButtonRightText());
        req.setAttribute("result", (pickedButton.equals(LEFT))
                ? conditionEntity.getResultLeftText()
                : conditionEntity.getResultRightText());
        req.setAttribute("resultRight", conditionEntity.getResultRightText());
        req.setAttribute("description", conditionEntity.getDescription());
        req.setAttribute("time", time);
        req.setAttribute("evidence", evidence);
        req.setAttribute("gold", gold);
        req.setAttribute("imageUrl", conditionEntity.getImageUrl());
    }

    private boolean winCheck() {
        return step == QUEST_END_STEP;
    }

    private boolean lossCheck() {
        return (time <= QUEST_MIN_RESOURCE)
                || (evidence <= QUEST_MIN_RESOURCE)
                || (gold <= QUEST_MIN_RESOURCE)
                || ((step == QUEST_LOSS_STEP) & (pickedButton.equals(RIGHT)));
    }

    private void goToWin(HttpServletRequest req) {
        setWinAtributes(req, "DESCRIPTION_TEXT_WIN", "IMAGE_URL_WIN", "isWin");
        addUserWin(req, userService);
    }

    private void setWinAtributes(HttpServletRequest req, String DESCRIPTION_TEXT_WIN, String IMAGE_URL_WIN, String isWin) {
        req.setAttribute("description", questMap.get(DESCRIPTION_TEXT_WIN));
        req.setAttribute("imageUrl", questMap.get(IMAGE_URL_WIN));
        req.setAttribute(isWin, true);
    }

    private void goToLoss(HttpServletRequest req) {
        String lossСause = getLossCause();
        setLossAtributes(req, lossСause);
        addUserLoss(req, userService);

    }

    private void setLossAtributes(HttpServletRequest req, String lossСause) {
        req.setAttribute("lossСause", lossСause);
        req.setAttribute("description", questMap.get("DESCRIPTION_TEXT_LOSS"));
        req.setAttribute("imageUrl", questMap.get("IMAGE_URL_LOSS"));
        req.setAttribute("isLoss", true);
    }

    private String getLossCause() {
        String lossСause;
        if (time == QUEST_MIN_RESOURCE) {
            lossСause = questMap.get("CAUSE_TEXT_TIME_LOSS");
        } else if (gold == QUEST_MIN_RESOURCE) {
            lossСause = questMap.get("CAUSE_TEXT_GOLD_LOSS");
        } else if (evidence == QUEST_MIN_RESOURCE) {
            lossСause = questMap.get("CAUSE_TEXT_EVIDENCE_LOSS");
        } else if (step == QUEST_LOSS_STEP && pickedButton.equals(RIGHT)) {
            lossСause = questMap.get("CAUSE_TEXT_WRONG_STEP_LOSS");
        } else {
            lossСause = questMap.get("CAUSE_TEXT_UNKNOWN_LOSS");
        }
        return lossСause;
    }


}
