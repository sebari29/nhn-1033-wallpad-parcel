package com.wallpad.net.sample.loginresponve;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "ControlResponse")
public class OutputList {

    @JacksonXmlProperty(localName = "size")
    private String size;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Output")
    private List<Output> listOutput = new ArrayList<>();

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Output> getListOutput() {
        return listOutput;
    }

    public void setListOutput(List<Output> listOutput) {
        this.listOutput = listOutput;
    }

}
