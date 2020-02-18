package com.wallpad.net.sample.basicinfo.response;

public class Data {

	private String  name; 
	private String  value;   
	public Data(String  name, String value) { 
		this.name=name;
		this.value=value;
	}

	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	public void setValue(String value) {
		this.value=value;
	}
	public String getValue() {
	return this.value;
	}

	@Override
	public String toString() {
		return "Data [name=" + name + ", value=" + value + "]";
	}

 
	
}
