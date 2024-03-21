package com.example.platform.service.impl;

import com.example.platform.mapper.RenterMapper;
import com.example.platform.pojo.Rent;
import com.example.platform.service.RenterService;
import com.example.platform.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenterServiceImpl implements RenterService {
    @Autowired
    private RenterMapper renterMapper;
    @Override
    public Rent renterMessageList(String id) {
        return renterMapper.findByStudentNum(id);
    }
    @Override
    public void renterAdd(Rent rent) {
        renterMapper.renterAdd(rent);
    }

    @Override
    public void updateRenter(Rent rent) {
        renterMapper.updateRenter(rent);
    }
    @Override
    public Rent findRenter(String renter) {
        return renterMapper.findRenter(renter);
    }

    @Override
    public void removeRenter(String car_id) {
        renterMapper.removeRenter(car_id);
    }
}
