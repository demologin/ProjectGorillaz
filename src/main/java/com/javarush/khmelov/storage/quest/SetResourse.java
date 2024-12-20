package com.javarush.khmelov.storage.quest;

import java.util.Map;

import static com.javarush.khmelov.storage.ConstantsCommon.LEFT;
import static com.javarush.khmelov.storage.ConstantsCommon.RIGHT;

public class SetResourse {

    public static final int QUEST_END_STEP = 6;
    public static final int QUEST_LOSS_STEP = 2;
    public static final int QUEST_MIN_RESOURCE = 0;

    //---------------------------------------------TIME-------------------------------------------------

    protected static final Map<String, Integer> DELTA_TIME_MAP_STATE_ONE = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_TIME_MAP_STATE_TWO = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_TIME_MAP_STATE_THREE = Map.of(
            LEFT, -2,
            RIGHT, -1
    );

    protected static final Map<String, Integer> DELTA_TIME_MAP_STATE_FOUR = Map.of(
            LEFT, 0,
            RIGHT, -2
    );

    protected static final Map<String, Integer> DELTA_TIME_MAP_STATE_FIVE = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    //---------------------------------------------EVIDENSE-------------------------------------------------

    protected static final Map<String, Integer> DELTA_EVIDENSE_MAP_STATE_ONE = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_EVIDENSE_MAP_STATE_TWO = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_EVIDENSE_MAP_STATE_THREE = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_EVIDENSE_MAP_STATE_FOUR = Map.of(
            LEFT, 0,
            RIGHT, +1
    );

    protected static final Map<String, Integer> DELTA_EVIDENSE_MAP_STATE_FIVE = Map.of(
            LEFT, -1,
            RIGHT, 0
    );

    //--------------------------------------------GOLD--------------------------------------------------

    protected static final Map<String, Integer> DELTA_GOLD_MAP_STATE_ONE = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_GOLD_MAP_STATE_TWO = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_GOLD_MAP_STATE_THREE = Map.of(
            LEFT, 3,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_GOLD_MAP_STATE_FOUR = Map.of(
            LEFT, -2,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_GOLD_MAP_STATE_FIVE = Map.of(
            LEFT, 0,
            RIGHT, -2
    );

    protected static final String START_TIME = "4";
    protected static final String START_EVIDENCE = "1";
    protected static final String START_GOLD = "3";
    protected static final String START_STEP = "0";


}
