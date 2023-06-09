package org.edupoll.model.dto;

import java.util.List;

public class AttendanceJoinResponseData {
	
	boolean result;
	String errorMessage;
	
	Integer currentPerson;
	List<String> attendUserIds;
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getCurrentPerson() {
		return currentPerson;
	}
	public void setCurrentPerson(Integer currentPerson) {
		this.currentPerson = currentPerson;
	}
	public List<String> getAttendUserIds() {
		return attendUserIds;
	}
	public void setAttendUserIds(List<String> attendUserIds) {
		this.attendUserIds = attendUserIds;
	}
	
	
	
	

	

}
