package com.ship.zip.code.api;

import com.ship.zip.code.entity.ZipcodeData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShippingResource {

    ResponseEntity<List<ZipcodeData>> getShippingCode(List<ZipcodeData> zipcodeData);
}
