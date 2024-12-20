package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestInfoEntity {

    private String buttonLeftText;

    private String buttonRightText;

    private String resultLeftText;

    private String resultRightText;

    private Map<String, Integer> deltaTime;

    private Map<String, Integer> deltaEvidence;

    private Map<String, Integer> deltaGold;

    private String description;

    private String imageUrl;

    public Integer getDeltaTime(String pickedButton) {
        return deltaTime.get(pickedButton);
    }

    public Integer getDeltaEvidence(String pickedButton) {
        return deltaEvidence.get(pickedButton);
    }

    public Integer getDeltaGold(String pickedButton) {
        return deltaGold.get(pickedButton);
    }
}


