package com.wallpad.net.sample.basicinfo.response;

public class ControlResponse {

	 
	private String  transID; 
	private int  functionID; 
	private String  functionCategory; 
	private int  result; 
	private String  homeServer; 
	private OutputList  outputList;
	 
	public ControlResponse() {
		super();
	}

	public String getTransID() {
		return transID;
	}

	public void setTransID(String transID) {
		this.transID = transID;
	}

	public int getFunctionID() {
		return functionID;
	}

	public void setFunctionID(int functionID) {
		this.functionID = functionID;
	}

	public String getFunctionCategory() {
		return functionCategory;
	}

	public void setFunctionCategory(String functionCategory) {
		this.functionCategory = functionCategory;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getHomeServer() {
		return homeServer;
	}

	public void setHomeServer(String homeServer) {
		this.homeServer = homeServer;
	}

	public OutputList getOutputList() {
		return outputList;
	}

	public void setOutputList(OutputList outputList) {
		this.outputList = outputList;
	}

	@Override
	public String toString() {
		return "ControlResponse \n- transID=" + transID + "\n- functionID=" + functionID + "\n- functionCategory="
				+ functionCategory + "\n- result=" + result + "\n- homeServer=" + homeServer + "\n- outputList:" + outputList
				+ "]";
	}   
	
	
	 
}
