package com.javarush.khmelov.storage.quest;

import com.javarush.khmelov.entity.QuestInfoEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.javarush.khmelov.storage.quest.ButtonText.*;
import static com.javarush.khmelov.storage.quest.Description.*;
import static com.javarush.khmelov.storage.quest.ImageUrl.*;
import static com.javarush.khmelov.storage.quest.ResultText.*;
import static com.javarush.khmelov.storage.quest.SetResourse.*;

public class QuestRepository {

    private final static List<QuestInfoEntity> questList = new LinkedList<>();
    private final static Map<String, String> questMap = new HashMap<>();

    public QuestRepository() {

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_ONE,
                BUTTON_TEXT_RIGHT_STATE_ONE,
                RESULT_TEXT_LEFT_STATE_ONE,
                RESULT_TEXT_RIGHT_STATE_ONE,
                DELTA_TIME_MAP_STATE_ONE,
                DELTA_EVIDENSE_MAP_STATE_ONE,
                DELTA_GOLD_MAP_STATE_ONE,
                DESCRIPTION_TEXT_STATE_ONE,
                IMAGE_URL_STATE_ONE
        ));

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_TWO,
                BUTTON_TEXT_RIGHT_STATE_TWO,
                RESULT_TEXT_LEFT_STATE_TWO,
                RESULT_TEXT_RIGHT_STATE_TWO,
                DELTA_TIME_MAP_STATE_TWO,
                DELTA_EVIDENSE_MAP_STATE_TWO,
                DELTA_GOLD_MAP_STATE_TWO,
                DESCRIPTION_TEXT_STATE_TWO,
                IMAGE_URL_STATE_TWO
        ));

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_THREE,
                BUTTON_TEXT_RIGHT_STATE_THREE,
                RESULT_TEXT_LEFT_STATE_THREE,
                RESULT_TEXT_RIGHT_STATE_THREE,
                DELTA_TIME_MAP_STATE_THREE,
                DELTA_EVIDENSE_MAP_STATE_THREE,
                DELTA_GOLD_MAP_STATE_THREE,
                DESCRIPTION_TEXT_STATE_THREE,
                IMAGE_URL_STATE_THREE
        ));

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_FOUR,
                BUTTON_TEXT_RIGHT_STATE_FOUR,
                RESULT_TEXT_LEFT_STATE_FOUR,
                RESULT_TEXT_RIGHT_STATE_FOUR,
                DELTA_TIME_MAP_STATE_FOUR,
                DELTA_EVIDENSE_MAP_STATE_FOUR,
                DELTA_GOLD_MAP_STATE_FOUR,
                DESCRIPTION_TEXT_STATE_FOUR,
                IMAGE_URL_STATE_FOUR
        ));

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_FIVE,
                BUTTON_TEXT_RIGHT_STATE_FIVE,
                RESULT_TEXT_LEFT_STATE_FIVE,
                RESULT_TEXT_RIGHT_STATE_FIVE,
                DELTA_TIME_MAP_STATE_FIVE,
                DELTA_EVIDENSE_MAP_STATE_FIVE,
                DELTA_GOLD_MAP_STATE_FIVE,
                DESCRIPTION_TEXT_STATE_FIVE,
                IMAGE_URL_STATE_FIVE
        ));

        questMap.put("DESCRIPTION_TEXT_WIN", DESCRIPTION_TEXT_WIN);
        questMap.put("DESCRIPTION_TEXT_LOSS", DESCRIPTION_TEXT_LOSS);
        questMap.put("IMAGE_URL_WIN", IMAGE_URL_WIN);
        questMap.put("IMAGE_URL_LOSS", IMAGE_URL_LOSS);
        questMap.put("IMAGE_URL_TIME", IMAGE_URL_TIME);
        questMap.put("IMAGE_URL_GOLD", IMAGE_URL_GOLD);
        questMap.put("IMAGE_URL_EVIDENCE", IMAGE_URL_EVIDENCE);
        questMap.put("CAUSE_TEXT_TIME_LOSS", CAUSE_TEXT_TIME_LOSS);
        questMap.put("CAUSE_TEXT_GOLD_LOSS", CAUSE_TEXT_GOLD_LOSS);
        questMap.put("CAUSE_TEXT_WRONG_STEP_LOSS", CAUSE_TEXT_WRONG_STEP_LOSS);
        questMap.put("CAUSE_TEXT_UNKNOWN_LOSS", CAUSE_TEXT_UNKNOWN_LOSS);
        questMap.put("CAUSE_TEXT_EVIDENCE_LOSS", CAUSE_TEXT_EVIDENCE_LOSS);
        questMap.put("START_EVIDENCE", START_EVIDENCE);
        questMap.put("START_TIME", START_TIME);
        questMap.put("START_GOLD", START_GOLD);
        questMap.put("START_STEP", START_STEP);

    }

    public List<QuestInfoEntity> getQuestList() {
        return questList;
    }

    public Map<String, String> getQuestMap() {
        return questMap;
    }

}
