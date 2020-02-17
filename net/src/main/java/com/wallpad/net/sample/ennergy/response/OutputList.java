package com.wallpad.net.sample.ennergy.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "OutputList")
public class OutputList {
    @JacksonXmlProperty(localName = "Output")
    private Output outputObject;
    @JacksonXmlProperty(localName = "Outputs")
    private Outputs outputsObject;

    @JacksonXmlProperty(isAttribute = true, localName = "size")
    private String _size;

    public Output getOutputObject() {
        return outputObject;
    }

    public void setOutputObject(Output outputObject) {
        this.outputObject = outputObject;
    }

    public Outputs getOutputsObject() {
        return outputsObject;
    }

    public void setOutputsObject(Outputs outputsObject) {
        this.outputsObject = outputsObject;
    }

    public String get_size() {
        return _size;
    }

    public void set_size(String _size) {
        this._size = _size;
    }
}

