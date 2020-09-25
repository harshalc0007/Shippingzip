package com.ship.zip.code.entity;

import java.util.Objects;

/**
 * Zip Code Data contains zip code Entry for lower and higher frequency range Data.
 */
public class ZipcodeData {

    private Integer lowerFrequency;
    private Integer higherFrequency;


    public Integer getLowerFrequency() {
        return lowerFrequency;
    }

    public void setLowerFrequency(Integer lowerFrequency) {
        this.lowerFrequency = lowerFrequency;
    }

    public Integer getHigherFrequency() {
        return higherFrequency;
    }

    public void setHigherFrequency(Integer higherFrequency) {
        this.higherFrequency = higherFrequency;
    }

    public ZipcodeData(Integer lowerFrequency, Integer higherFrequency) {
        this.lowerFrequency = lowerFrequency;
        this.higherFrequency = higherFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipcodeData that = (ZipcodeData) o;
        return Objects.equals(getLowerFrequency(), that.getLowerFrequency()) &&
                Objects.equals(getHigherFrequency(), that.getHigherFrequency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLowerFrequency(), getHigherFrequency());
    }

    @Override
    public String toString() {
        return "ZipcodeData{" +
                "lowerFrequency=" + lowerFrequency +
                ", higherFrequency=" + higherFrequency +
                '}';
    }
}
