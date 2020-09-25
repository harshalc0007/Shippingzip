package com.ship.zip.code.validator.impl;

import com.ship.zip.code.entity.ZipcodeData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ZipcodeValidatorTest {

    @Autowired
    ZipcodeValidator zipcodeValidator;

    @Test
    public void positiveValidateZipCodeData() {
        List<ZipcodeData> zipcodeDataList = Arrays.asList(new ZipcodeData(94133, 94133),
                new ZipcodeData(94200, 94299),
                new ZipcodeData(94300, 94399));
        boolean isValid = zipcodeValidator.validate(zipcodeDataList);
        assertNotNull(isValid);
        assertEquals(true, isValid);
    }

    @Test
    public void negativeValidateZipCodeDataWithInvalidData() {
        List<ZipcodeData> zipcodeDataList = Arrays.asList(new ZipcodeData(null , null),
                new ZipcodeData(94200, 94299),
                new ZipcodeData(94300, 94399));
        boolean isValid = zipcodeValidator.validate(zipcodeDataList);
        assertNotNull(isValid);
        assertEquals(false, isValid);
    }

    @Test
    public void negativeValidateZipCodeDataWithInvalidLowerAndHigherFreq() {
        List<ZipcodeData> zipcodeDataList = Arrays.asList(new ZipcodeData(123 , 22),
                new ZipcodeData(94200, 94299),
                new ZipcodeData(94300, 94399));
        boolean isValid = zipcodeValidator.validate(zipcodeDataList);
        assertNotNull(isValid);
        assertEquals(false, isValid);
    }
}
