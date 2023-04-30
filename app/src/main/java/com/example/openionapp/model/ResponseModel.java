package com.example.openionapp.model;

public class ResponseModel {

    private String supportWhatsappNumber;
    private String  extraIncome;
    private int totalLinks;
    private int totalClicks;
    private String topSource;
    private String topLocation;
    private String startTime;
    private int appliedCampaign;
    private int todayClicks;

    private boolean status;
    private int statusCode;
    private int linksCreatedToday;

    public String getSupportWhatsappNumber() {
        return supportWhatsappNumber;
    }

    public void setSupportWhatsappNumber(String supportWhatsappNumber) {
        this.supportWhatsappNumber = supportWhatsappNumber;
    }

    public String getExtraIncome() {
        return extraIncome;
    }

    public void setExtraIncome(String extraIncome) {
        this.extraIncome = extraIncome;
    }

    public int getTotalLinks() {
        return totalLinks;
    }

    public void setTotalLinks(int totalLinks) {
        this.totalLinks = totalLinks;
    }

    public int getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(int totalClicks) {
        this.totalClicks = totalClicks;
    }

    public String getTopSource() {
        return topSource;
    }

    public void setTopSource(String topSource) {
        this.topSource = topSource;
    }

    public String getTopLocation() {
        return topLocation;
    }

    public void setTopLocation(String topLocation) {
        this.topLocation = topLocation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getAppliedCampaign() {
        return appliedCampaign;
    }

    public void setAppliedCampaign(int appliedCampaign) {
        this.appliedCampaign = appliedCampaign;
    }

    public int getTodayClicks() {
        return todayClicks;
    }

    public void setTodayClicks(int todayClicks) {
        this.todayClicks = todayClicks;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getLinksCreatedToday() {
        return linksCreatedToday;
    }

    public void setLinksCreatedToday(int linksCreatedToday) {
        this.linksCreatedToday = linksCreatedToday;
    }



  /*  public ResponseModel(String supportWhatsappNumber, String extraIncome, int totalLinks, int totalClicks, String topSource, String topLocation, String startTime, int appliedCampaign, int todayClicks, boolean status, int statusCode, int linksCreatedToday) {
        this.supportWhatsappNumber = supportWhatsappNumber;
        this.extraIncome = extraIncome;
        this.totalLinks = totalLinks;
        this.totalClicks = totalClicks;
        this.topSource = topSource;
        this.topLocation = topLocation;
        this.startTime = startTime;
        this.appliedCampaign = appliedCampaign;
        this.todayClicks = todayClicks;
        this.status = status;
        this.statusCode = statusCode;
        this.linksCreatedToday = linksCreatedToday;
    }*/
    @Override
    public String toString() {
        return "ResponseModel{" +
                "supportWhatsappNumber='" + supportWhatsappNumber + '\'' +
                ", extraIncome='" + extraIncome + '\'' +
                ", totalLinks=" + totalLinks +
                ", totalClicks=" + totalClicks +
                ", topSource='" + topSource + '\'' +
                ", topLocation='" + topLocation + '\'' +
                ", startTime='" + startTime + '\'' +
                ", appliedCampaign=" + appliedCampaign +
                ", todayClicks=" + todayClicks +
                ", status=" + status +
                ", statusCode=" + statusCode +
                ", linksCreatedToday=" + linksCreatedToday +
                '}';
    }



}
