package com.javarush.borisov.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RequestStatus {
    @JsonValue
    ASSIGNED ("Назначена"),
    IN_PROGRESS ("Выехали"),
    COMPLETED ("Выполнена"),
    FAIL_DEPARTURE("Ложный выезд"),
        CANCELED("Отменена");

    @JsonValue
    private final String ruName;

    RequestStatus(String ruName) {
        this.ruName = ruName;
    }


    @JsonCreator
    public static RequestStatus fromValue(String value) {
        for (RequestStatus status : RequestStatus.values()) {
            if (status.ruName.equals(value)) {
                return status;
            }
        }
        return null;
    }
}
