package com.service;

public class Bikes {
	 private int bikeId;
	 public Bikes(int bikeId, String bikeModel, String bikeNumber) {
		super();
		this.bikeId = bikeId;
		this.bikeModel = bikeModel;
		this.bikeNumber = bikeNumber;
	}
	public int getBikeId() {
		return bikeId;
	}
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	public String getBikeModel() {
		return bikeModel;
	}
	public void setBikeModel(String bikeModel) {
		this.bikeModel = bikeModel;
	}
	public String getBikeNumber() {
		return bikeNumber;
	}
	public void setBikeNumber(String bikeNumber) {
		this.bikeNumber = bikeNumber;
	}
	private String bikeModel;
	 private String bikeNumber;
}
