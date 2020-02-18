package com.wallpad.net.sample.energyamonut.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "OutputList")
public class OutputList {
    @JacksonXmlProperty(localName = "Output")
    private Output Output;

    public com.wallpad.net.sample.energyamonut.response.Output getOutput() {
        return Output;
    }

    public void setOutput(com.wallpad.net.sample.energyamonut.response.Output output) {
        Output = output;
    }

    public String get_size() {
        return _size;
    }

    public void set_size(String _size) {
        this._size = _size;
    }

    @JacksonXmlProperty(isAttribute = true, localName = "size")
    private String _size;
}

