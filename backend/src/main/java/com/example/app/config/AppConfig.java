package com.example.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${service_charge_store_path}")
    private String serviceChargeStorePath;

    @Value("${promotion_file_path}")
    private  String promotionFilePath;

    public String getServiceChargeStorePath() {
        return serviceChargeStorePath;
    }

    public String getPromotionFilePath() {
        return promotionFilePath;
    }
}
