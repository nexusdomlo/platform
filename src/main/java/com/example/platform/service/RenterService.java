package com.example.platform.service;

import com.example.platform.pojo.Rent;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface RenterService {
    Rent renterMessageList(String id);

    void renterAdd(Rent rent);

    void updateRenter(Rent rent);

    Rent findRenter(String renter);

    void removeRenter(String car_id);
}
