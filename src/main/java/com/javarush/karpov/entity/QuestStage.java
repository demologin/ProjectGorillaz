package com.javarush.karpov.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestStage {
    private int id;
    private String title;
    private String image;
    private String text;
    private Selector selector;
    private Choice defaultOption;
    private Choice option1;
    private Choice option2;
    private String button;
    private State state;

}