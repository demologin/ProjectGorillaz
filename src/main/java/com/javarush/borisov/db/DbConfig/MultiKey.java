package com.javarush.borisov.db.DbConfig;

import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.util.FolderScanner;
import lombok.Getter;

import java.util.List;
@Getter
public class MultiKey {
    private String year;
    private String month;

    public MultiKey(String year, String month) {
        this.year = year;
        this.month = month;
    }

    @Override
    public String toString() {
        return "MultiKey{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}

