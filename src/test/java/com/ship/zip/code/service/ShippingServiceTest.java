package com.ship.zip.code.service;

import com.ship.zip.code.entity.ZipcodeData;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ShippingServiceTest {

    @Autowired
    private ShippingService shippingService;

    @Test
    public void positiveCalculateShippingCode() {
        List<ZipcodeData> zipcodeDataList = Arrays.asList(new ZipcodeData(94133, 94133),
                                                        new ZipcodeData(94200, 94299),
                                                        new ZipcodeData(94300, 94399));
        List<ZipcodeData> calculatedZipCodeList = shippingService.calculateShippingCode(zipcodeDataList);
        assertNotNull(calculatedZipCodeList);
        assertEquals(3, calculatedZipCodeList.size());
        assertEquals(94133, calculatedZipCodeList.get(0).getLowerFrequency());
    }

    @Test
    public void positiveCalculateShippingCodeSize() {
        List<ZipcodeData> zipcodeDataList = Arrays.asList(new ZipcodeData(94133, 94133),
                new ZipcodeData(94200, 94299),
                new ZipcodeData(94200, 94399));
        List<ZipcodeData> calculatedZipCodeList = shippingService.calculateShippingCode(zipcodeDataList);
        assertNotNull(calculatedZipCodeList);
        assertEquals(2, calculatedZipCodeList.size());
        assertEquals(94200, calculatedZipCodeList.get(1).getLowerFrequency());
        assertEquals(94399, calculatedZipCodeList.get(1).getHigherFrequency());
    }
}
