package com.javarush.khmelov.storage.quest;

public class Description {
    protected static final String DESCRIPTION_TEXT_STATE_ONE = "   Вы очнулись в Санкт-Петербурге без воспоминаний. У вас есть карта города, странный ключ и записка: \"Найди реликвию, чтобы вернуть свою память. Осторожно: время, улики и деньги ограничены, и каждая ошибка приближает тебя к провалу. \n Как только любой из ресурсов равен 0 – квест провален!\".\n   Начать приключение?";
    protected static final String DESCRIPTION_TEXT_STATE_TWO = "   Вы изучаете карту города и идете на Дворцовую площадь. Предстоит выбор:";
    protected static final String DESCRIPTION_TEXT_STATE_THREE = "   Получив подсказку, вы узнаёте, что реликвия спрятана в Петропавловской крепости \n   Чтобы войти, нужно:";
    protected static final String DESCRIPTION_TEXT_STATE_FOUR = "   На пути дверь, закрытая шифром\n   Сделайте выбор:";
    protected static final String DESCRIPTION_TEXT_STATE_FIVE = "   Здесь нужно решить последнюю загадку.";

    protected static final String DESCRIPTION_TEXT_WIN = "   Вы находите реликвию. Память возвращается к вам. Ваше внутреннее состояние - экстаз";

    protected static final String DESCRIPTION_TEXT_LOSS = "   Вам не удаётся пройти квест. Однако Питерские бездомные подбадривают вас и согревают своей позитивной энергией.";
    protected static final String CAUSE_TEXT_TIME_LOSS = "   Время истекло\n";
    protected static final String CAUSE_TEXT_GOLD_LOSS = "   Золото закончилось\n";
    protected static final String CAUSE_TEXT_EVIDENCE_LOSS = "   Улик не осталось\n";
    protected static final String CAUSE_TEXT_WRONG_STEP_LOSS = "   Сделан неверный шаг\n";
    protected static final String CAUSE_TEXT_UNKNOWN_LOSS = "   Причина не указана\n";
}
