package com.example.app.domain;

import com.example.app.Constants.Constant;
import com.example.app.Utils.Util;
import com.example.app.service.CustomerService;
import com.sun.org.glassfish.external.statistics.impl.TimeStatisticImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class CustomerServiceDomain {
    private String telNo;
    private Double serviceCharge;
    private String callTimeStart;
    private String callTimeEnd;
    private String callDate;
    private String promotion;
    private Long callTime;
    private String callTimeTotal;
    private String dateConvFormat;

    public CustomerServiceDomain(String telNo, Double serviceCharge, String callTimeStart, String callTimeEnd, String callDate, String promotion, String callTimeTotal) {
        this.telNo = telNo;
        this.serviceCharge = serviceCharge;
        this.callTimeStart = callTimeStart;
        this.callTimeEnd = callTimeEnd;
        this.callDate = callDate;
        this.promotion = promotion;
        this.callTimeTotal = callTimeTotal;
    }

    public CustomerServiceDomain(String txt) {
        if (Util.isEmpty(txt)) return;
        txtParser(txt);
    }

    private void txtParser(String txt) {
        System.out.println(txt);
//        DATE||START TIME||END TIME|TEL NO|POMOTION
        String[] details = txt.split("\\|");
        if (Util.isEmpty(details)) return;
        this.callDate = details[Constant.TEL_USAGE_TEXT_FORMAT.DATE.getIdx()];
        this.callTimeStart = details[Constant.TEL_USAGE_TEXT_FORMAT.STIME.getIdx()];
        this.callTimeEnd = details[Constant.TEL_USAGE_TEXT_FORMAT.ETIME.getIdx()];
        this.telNo = details[Constant.TEL_USAGE_TEXT_FORMAT.TEL_NO.getIdx()];
        this.promotion = details[Constant.TEL_USAGE_TEXT_FORMAT.PROMOTION_NAME.getIdx()];
        if (!Util.isEmpty(this.callDate))
            try {
                this.dateConvFormat = Util.convertDateFormat(this.callDate, "dd/MM/yyyy", "yyyy/MM/dd");
                this.callTime= (
                        new Date(new StringBuilder(this.dateConvFormat).append(" ").append(this.callTimeEnd).toString()).getTime() -
                                new Date(new StringBuilder(this.dateConvFormat).append(" ").append(this.callTimeStart).toString()).getTime()
                ) / 1000;

                Constant.PROMOTION promotionName = Constant.PROMOTION.valueOf(this.promotion.toUpperCase());

                switch (promotionName) {
                    case P1:
                        this.serviceCharge = calculateServiceChargePromotion(Constant.PROMOTION.P1.getPromotion());
                        break;
                    default:
                        System.out.println("Promotion not found");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    private Double calculateServiceChargePromotion( Map<Integer, Double> promotionRate) {
        Double serviceCharge = new Double(0);
        try {
            if (callTime <= 60) {
                serviceCharge = new Double(promotionRate.get("FIRST_TIME"));
            } else {
                serviceCharge = new Double((Math.floor(callTime / 60)) * promotionRate.get("SECOND_TIME"))+new Double(promotionRate.get("FIRST_TIME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceCharge;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public String getDateConvFormat() {
        return dateConvFormat;
    }

    public void setDateConvFormat(String dateConvFormat) {
        this.dateConvFormat = dateConvFormat;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getCallTimeStart() {
        return callTimeStart;
    }

    public void setCallTimeStart(String callTimeStart) {
        this.callTimeStart = callTimeStart;
    }

    public String getCallTimeEnd() {
        return callTimeEnd;
    }

    public void setCallTimeEnd(String callTimeEnd) {
        this.callTimeEnd = callTimeEnd;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getCallTimeTotal() {
        return callTimeTotal;
    }

    public void setCallTimeTotal(String callTimeTotal) {
        this.callTimeTotal = callTimeTotal;
    }
}
