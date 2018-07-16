package com.example.app.service.Impl;

import com.example.app.Utils.Util;
import com.example.app.config.AppConfig;
import com.example.app.dao.CustomerDao;
import com.example.app.domain.CustomerServiceDomain;
import com.example.app.domain.ServiceChargeDomain;
import com.example.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    AppConfig appConfig;
    @Override
    public List<ServiceChargeDomain> findServiceChargeAll() {
        return customerDao.getCustomerServiceCharge();
    }

    @Override
    public void calculateServiceCharge() {
        String datail = Util.readFile(new File(appConfig.getPromotionFilePath()));
        String[] txts = datail.split(("[\\r\\n]+"));
        List<CustomerServiceDomain> customerServiceDomains = new ArrayList<CustomerServiceDomain>();
        for (String txt :
                txts) {
            customerServiceDomains.add(new CustomerServiceDomain(txt));
        }
        customerDao.storeServiceChargeToDay(sumServiceCharge(customerServiceDomains));

    }

    private List<ServiceChargeDomain> sumServiceCharge(List<CustomerServiceDomain> customerServiceDomainList) {
        List<ServiceChargeDomain> result = new ArrayList<ServiceChargeDomain>();
        Map<String, Double> sumServiceCharge = new HashMap();
        Map<String, Long> sumTotaltime = new HashMap();
        for (CustomerServiceDomain cust :
                customerServiceDomainList) {
            if (sumServiceCharge.containsKey(cust.getTelNo())) {
                sumTotaltime.put(cust.getTelNo(), sumTotaltime.get(cust.getTelNo()) + cust.getCallTime());
                sumServiceCharge.put(cust.getTelNo(), sumServiceCharge.get(cust.getTelNo()) + cust.getServiceCharge());
            } else {
                sumTotaltime.put(cust.getTelNo(), cust.getCallTime());
                sumServiceCharge.put(cust.getTelNo(), cust.getServiceCharge());
            }
        }
        for (Map.Entry<String,Double> serviceCharge:
             sumServiceCharge.entrySet()) {
            result.add(new ServiceChargeDomain(serviceCharge.getKey(),sumTotaltime.get(serviceCharge.getKey()),serviceCharge.getValue()));
        }

        return result;
    }
}
