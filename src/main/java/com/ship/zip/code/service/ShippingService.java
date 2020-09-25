package com.ship.zip.code.service;

import com.ship.zip.code.entity.CalculateZipData;
import com.ship.zip.code.entity.ZipcodeData;
import com.ship.zip.code.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Service layer to calculate the shipping zip code
 */
@Service
public class ShippingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Validator zipcodeValidator;

    /**
     * Method to calculate shipping code based on dataList.
     * @param zipcodeDataList zipcodeDataList
     * @return List<ZipcodeData>
     */
    public List<ZipcodeData> calculateShippingCode(List<ZipcodeData> zipcodeDataList) {
        logger.info("Given list for calculation is =>{} ",zipcodeDataList);
        boolean isValidZipCodeData = zipcodeValidator.validate(zipcodeDataList);
        if(!isValidZipCodeData) {
            throw new RuntimeException("Please check and pass correct zip code data includes lower and higher zip frequency [lower frequency must be lower than higher frequency]");
        }
        List<ZipcodeData> calculatedZipDataList = new CopyOnWriteArrayList<>();
        zipcodeDataList.stream().forEach(zipcodeData -> {
            performRangeCalculations(zipcodeData, calculatedZipDataList);
        });

        logger.info("Calculated Zip code list =>{}",calculatedZipDataList);
        return calculatedZipDataList;
    }


    /**
     * Method to calculate range between to zipcode Data
     * @param zipcodeData zipcodeData
     * @param calculatedZipDataList calculatedZipDataList
     */
    private void performRangeCalculations(ZipcodeData zipcodeData, List<ZipcodeData> calculatedZipDataList) {
        if(CollectionUtils.isEmpty(calculatedZipDataList)) {
            calculatedZipDataList.add(zipcodeData);
            return;
        }
        logger.info("given zip code =>{}", zipcodeData);
        List<ZipcodeData> mergedZipList = new CopyOnWriteArrayList<>();
        AtomicBoolean isAdded  = new AtomicBoolean(false);
        for(ZipcodeData calculatedZipData : calculatedZipDataList) {
            CalculateZipData calculateZipData = calculateRanges(zipcodeData, calculatedZipData);
            if(calculateZipData.getValidRange()) {
                calculatedZipDataList.remove(calculatedZipData);
                if(!calculatedZipDataList.contains(calculateZipData.getZipcodeData())) {
                    isAdded.set(true);
                    calculatedZipDataList.add(calculateZipData.getZipcodeData());
                    zipcodeData = calculateZipData.getZipcodeData();
                }
            } else {
                mergedZipList.add(calculateZipData.getZipcodeData());
            }
        }

        if(!CollectionUtils.isEmpty(mergedZipList) && !isAdded.get()) {
            calculatedZipDataList.add(mergedZipList.get(0));
            mergedZipList.clear();
        }


    }

    private CalculateZipData calculateRanges(ZipcodeData zipcodeData, ZipcodeData calculatedZipData) {
        CalculateZipData calculate = new CalculateZipData();
        if(calculatedZipData.getHigherFrequency() >=  zipcodeData.getLowerFrequency()) {
            Integer lower = 0;
            Integer higher = 0;
            if(zipcodeData.getLowerFrequency() >= calculatedZipData.getLowerFrequency()) {
                lower = calculatedZipData.getLowerFrequency();
                if(zipcodeData.getHigherFrequency() >= calculatedZipData.getHigherFrequency()) {

                    higher = zipcodeData.getHigherFrequency();
                } else {
                    higher = calculatedZipData.getHigherFrequency();
                }

            }else {
                lower = zipcodeData.getLowerFrequency();
                if(calculatedZipData.getHigherFrequency() >= zipcodeData.getHigherFrequency()) {
                    higher = calculatedZipData.getHigherFrequency();
                }else {
                    higher = zipcodeData.getHigherFrequency();
                }
            }
            logger.info("Generated lower =>{} and higher => {}", lower,higher);
            calculate.setValidRange(true);
            calculate.setZipcodeData(new ZipcodeData(lower,higher));
        }else {
            calculate.setValidRange(false);
            calculate.setZipcodeData(zipcodeData);

        }
        return calculate;
    }

}
