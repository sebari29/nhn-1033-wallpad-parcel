package com.wallpad.net.sample.basicinfo.response;

import java.util.ArrayList;
import java.util.List;


public class Output {

	private int size;
	private int id;
	private String name;
	private List<Data> datas = new ArrayList<Data>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}
	
	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}

	public List<Data> getDatas() {
		return this.datas;
	}

	@Override
	public String toString() {
		return "Output [size=" + size + ", id=" + id + ", name=" + name + ", datas=" + datas + "]";
	}

	 

}
