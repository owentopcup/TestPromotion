package com.example.app.dao.Impl;

import com.example.app.Constants.Constant;
import com.example.app.Utils.Util;
import com.example.app.config.AppConfig;
import com.example.app.dao.CustomerDao;
import com.example.app.domain.CustomerServiceDomain;
import com.example.app.domain.ServiceChargeDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    AppConfig appConfig;

    @Override
    public List<ServiceChargeDomain> getCustomerServiceCharge() {
        List<ServiceChargeDomain> customerServiceDomains = new ArrayList<ServiceChargeDomain>();
        try {
            File[] files = Util.findFileInDirectoryBeginWith(Util.dateNow("yyyy_MM_dd"),appConfig.getServiceChargeStorePath());
            if(Util.isEmpty(files ))return customerServiceDomains;
            for (File file :
                    files) {
                customerServiceDomains = Constant.G_OBJ_MAPPER.readValue(Util.readFile(file), List.class);
               break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        22/06/2013|10:39:54|12:31:55|0867266297|P1
//        customerSCerviceDomains.add(new CustomerServiceDomain("0867266297", new Double(600), "10:39:54", "12:31:55", "22/06/2013", "p1", "555"));
        return customerServiceDomains;
    }

    public void storeServiceChargeToDay(List<ServiceChargeDomain> serviceChargeDomains) {
        try {
            Util.writeJSONFile(Util.toJSONString(serviceChargeDomains), Util.dateNow("yyyy_MM_dd"), appConfig.getServiceChargeStorePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
