package com.example.app.service;

import com.example.app.domain.CustomerServiceDomain;
import com.example.app.domain.ServiceChargeDomain;

import java.util.List;

public interface CustomerService {
    public List<ServiceChargeDomain> findServiceChargeAll();

    public void calculateServiceCharge();

}
