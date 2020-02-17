package com.wallpad.net.sample.basicinfo.response;

import java.util.ArrayList;
import java.util.List;

public class Outputs {
	private int size; 
	private String name;
	List<Output> outputs = new ArrayList<Output>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
 

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}
	
	public void setListOutput(List<Output> outputs) {
		this.outputs = outputs;
	}

	public List<Output> getListOutput() {
		return this.outputs;
	}

	@Override
	public String toString() {
		return "\n\t\t\tOUTPUTS \t size=" + size + ", name=" + name + ", outputs=" + outputs ;
	}
	
}
