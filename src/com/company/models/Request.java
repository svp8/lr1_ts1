package com.company.models;



public class Request {
    private int id;
    private int clientId;
    private String dateOfRequest;
    private String reason;
    private int price;
    private String dateOfTransfer;
    public Request(int id,String dateOfRequest,String reason,int price,String dateOfTransfer,int clientId){
        this.id=id;
        this.dateOfRequest = dateOfRequest;
        this.reason = reason;
        this.price = price;
        this.dateOfTransfer = dateOfTransfer;
        this.clientId=clientId;
    }
    public Request(){

    }

    public String getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(String dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(String dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
