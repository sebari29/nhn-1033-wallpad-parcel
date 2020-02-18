package com.wallpad.net.sample.basicinfo.response;

import java.util.ArrayList;
import java.util.List;

public class OutputList {

	private int size;
	private List<Output> listOutput = new ArrayList<Output>();
	private List<Outputs> listOutputs = new ArrayList<Outputs>();

 
 
	public void setListOutput(List<Output> listOutput) {
		this.listOutput = listOutput;
	}

	public List<Output> getListOutput() {
		return this.listOutput;
	}
	
	public void setListOutputs(List<Outputs> listOutputs) {
		this.listOutputs = listOutputs;
	}

	public List<Outputs> getListOutputs() {
		return this.listOutputs;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return   "\n\tsize \t" + size+"\n\tlistOutput \t" + listOutput
				+ "\n\tlistOutputs \t" + listOutputs;
	}

}
