package com.javarush.borisov.db.DbConfig;

import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.util.FolderScanner;
import lombok.Getter;

import java.util.List;
/**
 * record Class WTF?
 */
@Getter
public class MultiKey implements Comparable<MultiKey> {
    private final int year;
    private final int month;

    public MultiKey(int year, int month) {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        MultiKey multiKey = (MultiKey) object;
        return year == multiKey.year && month == multiKey.month;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        return result;
    }

    @Override
    public int compareTo(MultiKey o) {
        int yearCompare = Integer.compare(this.year, o.year);
        if (yearCompare != 0) {
            return yearCompare;
        }
        return Integer.compare(this.month, o.month);
    }
}

