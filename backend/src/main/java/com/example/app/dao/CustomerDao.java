package com.example.app.dao;

import com.example.app.domain.CustomerServiceDomain;
import com.example.app.domain.ServiceChargeDomain;

import java.util.List;

public interface CustomerDao {
    public List<ServiceChargeDomain> getCustomerServiceCharge();

    public void storeServiceChargeToDay(List<ServiceChargeDomain> serviceChargeDomains);
}
