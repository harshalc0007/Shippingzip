package com.ship.zip.code.entity;

import java.util.Objects;

/**
 * Entity used when calculate range between two zip code
 */
public class CalculateZipData {

    boolean validRange;
    ZipcodeData zipcodeData;

    public CalculateZipData() {

    }

    public void setValidRange(boolean validRange) {
        this.validRange = validRange;
    }

    public void setZipcodeData(ZipcodeData zipcodeData) {
        this.zipcodeData = zipcodeData;
    }

    public boolean getValidRange() {
        return validRange;
    }

    public ZipcodeData getZipcodeData() {
        return zipcodeData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculateZipData that = (CalculateZipData) o;
        return getValidRange() == that.getValidRange() &&
                Objects.equals(getZipcodeData(), that.getZipcodeData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValidRange(), getZipcodeData());
    }

    @Override
    public String toString() {
        return "CalculateZipData{" +
                "validRange=" + validRange +
                ", zipcodeData=" + zipcodeData +
                '}';
    }
}
