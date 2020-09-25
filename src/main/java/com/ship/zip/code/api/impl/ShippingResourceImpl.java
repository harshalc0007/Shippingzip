package com.ship.zip.code.api.impl;

import com.ship.zip.code.api.ShippingResource;
import com.ship.zip.code.entity.ZipcodeData;
import com.ship.zip.code.service.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API Controller
 */
@RestController
@RequestMapping("/api")
public class ShippingResourceImpl implements ShippingResource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  ShippingService shippingService;

    /**
     * API which take zip code data and calculate the range of zipcode data.
     * @param zipcodeData
     * @return
     */
    @Override
    @PostMapping
    public ResponseEntity<List<ZipcodeData>> getShippingCode(@RequestBody List<ZipcodeData> zipcodeData) {
       return new ResponseEntity(shippingService.calculateShippingCode(zipcodeData), HttpStatus.OK);
    }
}
