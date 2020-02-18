package com.HealthWellnessTracker.models.inprogress;

public class QuestionResponseOption {

	private int optionID;
	private long questionID;
	private String option;
	
	public QuestionResponseOption() {
		optionID = 0;
		questionID = 0;
		option = "";
	}

	public int getOptionID() {
		return optionID;
	}

	public void setOptionID(int optionID) {
		this.optionID = optionID;
	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

}
