package com.javarush.karpov.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Choice {

    private String text;
    private int nextQuestStageId;
}
