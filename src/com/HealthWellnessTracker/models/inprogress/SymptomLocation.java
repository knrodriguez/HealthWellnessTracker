package com.HealthWellnessTracker.models.inprogress;

public class SymptomLocation {

	private int symptomLocationID;
	private String symptomLocationName;
	private short parentID; 
	private byte quantity;
	
	public SymptomLocation() {
		symptomLocationID = 0;
		symptomLocationName = "";
		parentID = -1;
		quantity = 0;
	}

	public int getSymptomLocationID() {
		return symptomLocationID;
	}

	public void setSymptomLocationID(int symptomLocationID) {
		this.symptomLocationID = symptomLocationID;
	}

	public String getSymptomLocationName() {
		return symptomLocationName;
	}

	public void setSymptomLocationName(String symptomLocationName) {
		this.symptomLocationName = symptomLocationName;
	}

	public short getParentID() {
		return parentID;
	}

	public void setParentID(short parentID) {
		this.parentID = parentID;
	}

	public byte getQuantity() {
		return quantity;
	}

	public void setQuantity(byte quantity) {
		this.quantity = quantity;
	}

}
