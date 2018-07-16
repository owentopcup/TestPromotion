package com.example.app.domain;

public class ServiceChargeDomain  {
    private String telNo;
    private Long totalTime;
    private Double serviceCharge;

    public ServiceChargeDomain(String telNo, Long totalTime, Double serviceCharge) {
        this.telNo = telNo;
        this.totalTime = totalTime;
        this.serviceCharge = serviceCharge;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
}
