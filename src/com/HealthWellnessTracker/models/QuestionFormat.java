package com.HealthWellnessTracker.models;

public class QuestionFormat {

	private byte typeID;
	private String typeName;
	
	public QuestionFormat() {
		typeID = 0;
		typeName = "";
	}

	public byte getTypeID() {
		return typeID;
	}

	public void setTypeID(byte typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
