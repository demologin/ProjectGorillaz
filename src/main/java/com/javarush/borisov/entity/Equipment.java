package com.javarush.borisov.entity;

import lombok.Getter;

@Getter
public class Equipment {

    String model;
    String serialNumber;

    public Equipment(String model, String serialNumber) {
        if (!(model == null) && !model.isEmpty()) {
            this.model = model;
        }else {this.model= "-";}
        if (!(serialNumber == null) && !serialNumber.isEmpty()) {
            this.serialNumber = serialNumber;
        }else {this.serialNumber= "-";}

    }
}
