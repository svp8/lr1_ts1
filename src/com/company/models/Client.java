package com.company.models;

public class Client {
    private int id;
    private String FIO;
    private String brand;
    private String model;
    public Client(int id,String FIO,String brand,String model){
        this.id=id;
        this.FIO=FIO;
        this.brand=brand;
        this.model=model;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
