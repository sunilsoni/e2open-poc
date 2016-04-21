package com.jci.model.response;

import java.util.Map;

public class ProcessPoDataResponse {

	private boolean isError;
	private String errorMsg;
	
	private Map<Integer, Integer> poNumToStatus = null;
	
	
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Map<Integer, Integer> getPoNumToStatus() {
		return poNumToStatus;
	}
	public void setPoNumToStatus(Map<Integer, Integer> poNumToStatus) {
		this.poNumToStatus = poNumToStatus;
	}
	
	@Override
	public String toString() {
		return "ProcessPoDataResponse [isError=" + isError + ", errorMsg=" + errorMsg + ", poNumToStatus="
				+ poNumToStatus + "]";
	}
	
	
}
