package com.service;

public class PendingList {
     
    private String serviceDate;
	private String serviceDescription;
	private int bikeId;
	private String bikeModel;
	private String bikeNumber;
	public PendingList(String serviceDate, String serviceDescription, int bikeId, String bikeModel, String bikeNumber) {
        this.serviceDate = serviceDate;
        this.serviceDescription = serviceDescription;
        this.bikeId = bikeId;
        this.bikeModel = bikeModel;
        this.bikeNumber = bikeNumber;
    }

    // Getters and Setters
    public String getServiceDate() { return serviceDate; }
    public void setServiceDate(String serviceDate) { this.serviceDate = serviceDate; }

    public String getServiceDescription() { return serviceDescription; }
    public void setServiceDescription(String serviceDescription) { this.serviceDescription = serviceDescription; }

    public int getBikeId() { return bikeId; }
    public void setBikeId(int bikeId) { this.bikeId = bikeId; }

    public String getBikeModel() { return bikeModel; }
    public void setBikeModel(String bikeModel) { this.bikeModel = bikeModel; }

    public String getBikeNumber() { return bikeNumber; }
    public void setBikeNumber(String bikeNumber) { this.bikeNumber = bikeNumber; }
}

